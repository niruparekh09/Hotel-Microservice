package com.nrv.api_gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Route Validator class for {@link AuthenticationFilter}. Here we will add our routes which we want to
 * secure, not secure (open to all), or add custom validation.
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/api/auth/register",
            "/api/auth/login",
            "/eureka"
    );
    private final List<Pattern> adminEndpoints = List.of(
            Pattern.compile("POST:/api/rooms/[\\w-]+"),
            Pattern.compile("PUT:/api/rooms/[\\w-]+"),
            Pattern.compile("DELETE:/api/rooms/[\\w-]+"),
            Pattern.compile("DELETE:/api/customer/[\\w-]+"),
            Pattern.compile("GET:/api/auth"),
            Pattern.compile("DELETE:/api/auth/[\\w-]+")
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

    public boolean isAdminEndpoint(String method, String uri) {
        String key = method + ":" + uri;
        return adminEndpoints.stream().anyMatch(pattern -> pattern.matcher(key).matches());
    }

    public boolean isRoleAllowed(String method, String uri, String userRole) {
        return "ROLE_ADMIN".equals(userRole) && isAdminEndpoint(method, uri);
    }
}
