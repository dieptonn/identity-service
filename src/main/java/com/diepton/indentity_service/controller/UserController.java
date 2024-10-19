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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Builder
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    UserService userService;

    @GetMapping
    ApiResponse<List<User>> getUsers() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        ApiResponse<List<User>> apiResponse = new ApiResponse<>();
        apiResponse.setData(userService.getUsers());

        return apiResponse;
    }


    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {

        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(userService.createUser(request));

        return apiResponse;
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable String userId) {

        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(userService.getUser(userId));

        return apiResponse;
    }

    @GetMapping("/info")
    ApiResponse<UserResponse> getMyinfo() {

        return ApiResponse.<UserResponse>builder()
                .data(userService.getMyInfo())
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest request) {

        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(userService.updateUser(userId, request));

        return apiResponse;
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId) {

        userService.deleteUser(userId);

        return "User deleted";
    }
}
