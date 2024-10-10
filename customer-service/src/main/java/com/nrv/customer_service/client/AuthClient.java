package com.nrv.customer_service.client;

import com.nrv.customer_service.config.FeignConfig;
import com.nrv.customer_service.request.UserInsertionRequest;
import com.nrv.customer_service.response.APIResponse;
import com.nrv.customer_service.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Client Interface for inter service communication with Auth-Service
 *
 * @author Nirav Parekh
 * @since 1.0
 */
@FeignClient(name = "AUTH-SERVICE", configuration = FeignConfig.class)
public interface AuthClient {

    @GetMapping("/api/auth")
    List<UserResponse> getAllAuthUser();

    @PostMapping("/api/auth/register")
    UserResponse addAnAuthUser(@RequestBody @Valid UserInsertionRequest newAuthUser);

    @PutMapping("/api/auth/{id}")
    UserResponse updateAnAuthUser(@PathVariable String id, @RequestBody @Valid UserInsertionRequest updateAuthUser);

    @DeleteMapping("/api/auth/{id}")
    APIResponse deleteAnAuthUser(@PathVariable String id);
}
