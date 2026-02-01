package com.danilodps.kafkaconsumer.adapter;

import com.danilodps.kafkaconsumer.entity.UserEntity;
import com.danilodps.kafkaconsumer.record.received.UserResponse;

import java.time.LocalDateTime;

public class UserResponse2UserEntity {

    private UserResponse2UserEntity(){}

    public static UserEntity convert(UserResponse userResponse){
        return UserEntity.builder()
                .fullName(userResponse.name() + " " + userResponse.lastName())
                .createdAt(LocalDateTime.now()).build();
    }
}
