package com.diepton.indentity_service.service;

import com.diepton.indentity_service.entity.User;
import com.diepton.indentity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;



}
