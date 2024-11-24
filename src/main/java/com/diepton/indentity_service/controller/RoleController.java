package com.diepton.indentity_service.controller;

import com.diepton.indentity_service.dto.request.RoleRequest;
import com.diepton.indentity_service.dto.response.ApiResponse;
import com.diepton.indentity_service.dto.response.RoleResponse;
import com.diepton.indentity_service.service.RoleService;
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
@RequestMapping("/api/v1/roles")
public class RoleController {

    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest roleRequest) {

        return ApiResponse.<RoleResponse>builder()
                .data(roleService.create(roleRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> findAll() {

        return ApiResponse.<List<RoleResponse>>builder()
                .data(roleService.findAll())
                .build();
    }


    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role) {

        roleService.delete(role);

        return ApiResponse.<Void>builder().build();
    }
}
