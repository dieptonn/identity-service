package com.diepton.indentity_service.controller;

import com.diepton.indentity_service.dto.request.UserCreationRequest;
import com.diepton.indentity_service.dto.request.UserUpdateRequest;
import com.diepton.indentity_service.dto.response.ApiResponse;
import com.diepton.indentity_service.dto.response.UserResponse;
import com.diepton.indentity_service.entity.User;
import com.diepton.indentity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Builder
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/users")
public class UserController {

    UserService userService;

    @GetMapping
    ApiResponse<List<User>> getUsers() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<User>>builder()
                .data(userService.getUsers())
                .build();
    }


    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {

        return ApiResponse.<UserResponse>builder()
                .data(userService.createUser(request))
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable String userId) {

        return ApiResponse.<UserResponse>builder()
                .data(userService.getUser(userId))
                .build();
    }

    @GetMapping("/info")
    ApiResponse<UserResponse> getMyinfo() {

        return ApiResponse.<UserResponse>builder()
                .data(userService.getMyInfo())
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest request) {

        return ApiResponse.<UserResponse>builder()
                .data(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId) {

        userService.deleteUser(userId);

        return "User deleted";
    }
}
