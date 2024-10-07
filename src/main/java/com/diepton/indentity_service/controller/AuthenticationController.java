package com.diepton.indentity_service.controller;

import com.diepton.indentity_service.dto.request.AuthenticationRequest;
import com.diepton.indentity_service.dto.request.IntrospectRequest;
import com.diepton.indentity_service.dto.response.ApiResponse;
import com.diepton.indentity_service.dto.response.AuthenticationResponse;
import com.diepton.indentity_service.dto.response.IntrospectResponse;
import com.diepton.indentity_service.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {

        AuthenticationResponse result = authenticationService.authenticate(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .data(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {

        IntrospectResponse result = authenticationService.introspect(request);

        return ApiResponse.<IntrospectResponse>builder()
                .data(result)
                .build();
    }
}
