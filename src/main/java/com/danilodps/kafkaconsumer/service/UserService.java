package com.danilodps.kafkaconsumer.service;

import com.danilodps.kafkaconsumer.record.received.UserResponse;

public interface UserService {

    void create(UserResponse userResponse);
}
