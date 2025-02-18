package com.diepton.indentity_service.service;

import com.diepton.indentity_service.dto.request.UserCreationRequest;
import com.diepton.indentity_service.dto.request.UserUpdateRequest;
import com.diepton.indentity_service.dto.response.UserResponse;
import com.diepton.indentity_service.entity.User;
import com.diepton.indentity_service.enums.Role;
import com.diepton.indentity_service.exception.BusinessException;
import com.diepton.indentity_service.exception.ErrorCode;
import com.diepton.indentity_service.mapper.UserMapper;
import com.diepton.indentity_service.repository.RoleRepository;
import com.diepton.indentity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_ADMIN')")
    public List<User> getUsers() {

        log.info("In method getUsers()");
        return userRepository.findAll();
    }

    public UserResponse createUser(UserCreationRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BusinessException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
//       user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PostAuthorize("returnObject.username == authentication.name") //takes effect after the method is executed
    public UserResponse getUser(String userId) {

        return userMapper.toUserResponse(findUser(userId));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {

        User user = findUser(userId);
        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {

        findUser(userId);
        userRepository.deleteById(userId);
    }

    public User findUser(String userId) {

        return userRepository.findById(userId).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXISTED));
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }
}
