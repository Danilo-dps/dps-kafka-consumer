package com.danilodps.kafkaconsumer.service.impl;

import com.danilodps.kafkaconsumer.adapter.UserResponse2UserEntity;
import com.danilodps.kafkaconsumer.entity.UserEntity;
import com.danilodps.kafkaconsumer.record.response.UserResponse;
import com.danilodps.kafkaconsumer.repository.UserEntityRepository;
import com.danilodps.kafkaconsumer.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;

    @Override
    @Transactional
    public void create(UserResponse userResponse) {
        log.info("Criando usuário...");

        UserEntity userEntity = UserResponse2UserEntity.convert(userResponse);

        userEntityRepository.saveAndFlush(userEntity);
        log.info("Usuário criado!");
    }
}
