package com.diepton.indentity_service.controller;

import com.diepton.indentity_service.dto.request.PermissionRequest;
import com.diepton.indentity_service.dto.response.ApiResponse;
import com.diepton.indentity_service.dto.response.PermissionResponse;
import com.diepton.indentity_service.service.PermissionService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Builder
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/permissions")
public class PermissionController {

    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest permissionRequest) {

        return ApiResponse.<PermissionResponse>builder()
                .data(permissionService.create(permissionRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> findAll() {

        return ApiResponse.<List<PermissionResponse>>builder()
                .data(permissionService.findAll())
                .build();
    }


    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission) {

        permissionService.delete(permission);

        return ApiResponse.<Void>builder().build();
    }
}
