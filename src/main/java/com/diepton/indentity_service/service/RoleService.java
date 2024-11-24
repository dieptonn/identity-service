package com.diepton.indentity_service.service;

import com.diepton.indentity_service.dto.request.RoleRequest;
import com.diepton.indentity_service.dto.response.RoleResponse;
import com.diepton.indentity_service.mapper.RoleMapper;
import com.diepton.indentity_service.repository.PermissionRepository;
import com.diepton.indentity_service.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class RoleService {

    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    public RoleResponse create(RoleRequest roleRequest) {

        var role = roleMapper.toRole(roleRequest);
        var permissions = permissionRepository.findAllById(roleRequest.getPermissions());

        role.setPermissions(new HashSet<>(permissions));
        role = roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> findAll() {

        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    public void delete(String role) {

        roleRepository.deleteById(role);
    }
}
