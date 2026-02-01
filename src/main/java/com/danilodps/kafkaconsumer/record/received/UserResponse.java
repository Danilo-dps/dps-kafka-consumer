package com.danilodps.kafkaconsumer.record.received;

import lombok.Builder;

@Builder
public record UserResponse(String userId, String name, String lastName) { }
