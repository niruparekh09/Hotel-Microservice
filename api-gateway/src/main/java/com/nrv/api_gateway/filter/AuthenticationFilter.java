package com.nrv.api_gateway.filter;

import com.nrv.api_gateway.exception.InvalidRequestException;
import com.nrv.api_gateway.exception.NotAuthorizedException;
import com.nrv.api_gateway.util.JwtUtil;
import jakarta.ws.rs.core.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * This filter checks if incoming requests require authentication. If so, it verifies the presence
 * and validity of a JWT in the Authorization header. If the token is missing or invalid,
 * it throws an exception; otherwise, it allows the request to continue. We have also added custom
 * authorization for endpoints only accessible to Admin.
 *
 * @author Nirav Parekh
 * @see RouteValidator
 * @see JwtUtil
 * @since 1.0
 */
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpRequest loggedInUser = null;

            if (validator.isSecured.test(request)) {
                if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new InvalidRequestException("missing authorization header");
                }
                String authHeader = request
                        .getHeaders()
                        .get(HttpHeaders.AUTHORIZATION)
                        .get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    jwtUtil.validateToken(authHeader);

                    String userRole = jwtUtil.extractRole(authHeader);

                    // Extract method and URI
                    String method = request.getMethod().toString();
                    String uri = request.getURI().getPath();
                    log.info("{}------{}", userRole, method + ":" + uri);
                    if (validator.isAdminEndpoint(method, uri)) { // Checking if the URI is Admin endpoint or not
                        if (!validator.isRoleAllowed(method, uri, userRole)) { // Checking if user is ADMIN or not
                            throw new NotAuthorizedException("User role does not have permission for this action");
                        }
                    }

                    loggedInUser = exchange.getRequest()
                            .mutate()
                            .header("loggedInUser", jwtUtil.extractUsername(authHeader))
                            .build();
                } catch (Exception e) {
                    log.error("Token Not Valid! User Un-Authorized");
                    throw new NotAuthorizedException("Un-Authorized access to application");
                }
            }
            return chain.filter(exchange.mutate().request(loggedInUser).build());
        }));
    }

    public static class Config {

    }
}
