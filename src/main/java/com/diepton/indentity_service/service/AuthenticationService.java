package com.diepton.indentity_service.service;

import com.diepton.indentity_service.dto.request.AuthenticationRequest;
import com.diepton.indentity_service.exception.BusinessException;
import com.diepton.indentity_service.exception.ErrorCode;
import com.diepton.indentity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    UserRepository userRepository;

    public boolean authenticate(AuthenticationRequest request) {

        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new BusinessException(ErrorCode.Msg_006));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }
}
