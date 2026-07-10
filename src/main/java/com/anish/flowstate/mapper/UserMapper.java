package com.anish.flowstate.mapper;

import com.anish.flowstate.dto.RegisterRequest;
import com.anish.flowstate.dto.UserResponse;
import com.anish.flowstate.model.User;

public class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(RegisterRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        return user;
    }

    public static UserResponse toResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());

        return response;
    }
}
