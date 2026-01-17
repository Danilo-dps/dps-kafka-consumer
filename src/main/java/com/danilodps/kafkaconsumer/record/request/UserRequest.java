package com.danilodps.kafkaconsumer.record.request;

import lombok.Builder;

@Builder
public record UserRequest(String name, String lastName) { }
