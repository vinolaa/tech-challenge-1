package com.github.vinolaa.tc1.dto;

import com.github.vinolaa.tc1.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
    UUID id,
    String name,
    String email,
    String login,
    String tipo,
    LocalDateTime createdAt
) {
    public static UserResponse from(User user) {
        return new UserResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getLogin(),
            user.getClass().getSimpleName(),
            user.getCreatedAt()
        );
    }
}