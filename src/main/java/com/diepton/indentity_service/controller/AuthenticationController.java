package com.diepton.indentity_service.controller;

import com.diepton.indentity_service.dto.request.AuthenticationRequest;
import com.diepton.indentity_service.dto.response.ApiResponse;
import com.diepton.indentity_service.dto.response.AuthenticationResponse;
import com.diepton.indentity_service.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {

        boolean result = authenticationService.authenticate(request);

//        AuthenticationResponse response = new AuthenticationResponse();
//        response.setAuthenticated(result);
//
//        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();
//        apiResponse.setResult(response);
//
//        return apiResponse;

        return ApiResponse.<AuthenticationResponse>builder()
                .result(AuthenticationResponse.builder()
                        .isAuthenticated(result)
                        .build())
                .build();
    }
}
