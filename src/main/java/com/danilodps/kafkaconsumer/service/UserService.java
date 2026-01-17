package com.danilodps.kafkaconsumer.service;

import com.danilodps.kafkaconsumer.record.response.UserResponse;

public interface UserService {

    void create(UserResponse userResponse);
}
