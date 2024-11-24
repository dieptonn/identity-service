package com.diepton.indentity_service.mapper;

import com.diepton.indentity_service.dto.request.PermissionRequest;
import com.diepton.indentity_service.dto.response.PermissionResponse;
import com.diepton.indentity_service.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
