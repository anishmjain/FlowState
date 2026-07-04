package com.anish.flowstate.service;

import com.anish.flowstate.dto.RegisterRequest;
import com.anish.flowstate.exception.UserAlreadyExistsException;
import com.anish.flowstate.mapper.UserMapper;
import com.anish.flowstate.model.User;
import com.anish.flowstate.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest request){
        User user = UserMapper.toEntity(request);

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(
                    "Username already exists."
            );
        }
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
        user.setRole("USER");

        return userRepository.save(user);
    }
}
