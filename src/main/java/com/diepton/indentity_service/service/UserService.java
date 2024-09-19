package com.diepton.indentity_service.service;

import com.diepton.indentity_service.dto.request.UserCreationRequest;
import com.diepton.indentity_service.dto.request.UserUpdateRequest;
import com.diepton.indentity_service.entity.User;
import com.diepton.indentity_service.exception.BusinessException;
import com.diepton.indentity_service.exception.ErrorCode;
import com.diepton.indentity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(UserCreationRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BusinessException(ErrorCode.Msg_002);
        }

        User user = new User(request.getUsername(), request.getPassword(), request.getFirstName(), request.getLastName(), request.getDayOfBirth());
        return userRepository.save(user);
    }

    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new BusinessException(ErrorCode.Msg_005));
    }

    public User updateUser(String userId, UserUpdateRequest request) {

        User user = getUser(userId);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDayOfBirth(request.getDayOfBirth());

        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        User user = getUser(userId);
        userRepository.deleteById(userId);
    }
}
