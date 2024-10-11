package com.nrv.api_gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

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

//    public static final List<String> adminOnlyApiEndpoints = List.of(
//            "/api/auth"
//    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
