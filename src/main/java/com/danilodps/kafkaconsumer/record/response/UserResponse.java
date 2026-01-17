package com.danilodps.kafkaconsumer.record.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record UserResponse(UUID userId, String name, String lastName, LocalDateTime createdAt) { }
