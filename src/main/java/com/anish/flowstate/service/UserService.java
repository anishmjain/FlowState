package com.anish.flowstate.service;

import com.anish.flowstate.dto.RegisterRequest;
import com.anish.flowstate.dto.RoleUpdateRequest;
import com.anish.flowstate.dto.UserResponse;
import com.anish.flowstate.exception.UserAlreadyExistsException;
import com.anish.flowstate.exception.UserNotFoundException;
import com.anish.flowstate.mapper.UserMapper;
import com.anish.flowstate.model.Role;
import com.anish.flowstate.model.User;
import com.anish.flowstate.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        user.setRole(Role.ROLE_USER);

        return userRepository.save(user);
    }

    public User getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Authenticated user not found."));
    }

    public UserResponse getCurrentUserResponse() {
        User user = getCurrentUser();
        return UserMapper.toResponse(user);
    }

    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    public UserResponse changeRole(
            Integer id,
            RoleUpdateRequest request){
        User user = getUser(id);
        user.setRole(request.getRole());
        userRepository.save(user);
        return UserMapper.toResponse(user);
    }

    private User getUser(Integer id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }
}
