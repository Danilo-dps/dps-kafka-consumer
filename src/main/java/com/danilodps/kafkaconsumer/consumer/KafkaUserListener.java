package com.danilodps.kafkaconsumer.consumer;

import com.danilodps.kafkaconsumer.record.response.UserResponse;
import com.danilodps.kafkaconsumer.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaUserListener {

    private static final String TOPIC_USER_V1 = "topic.user.v1";
    private final UserService userService;

    @KafkaListener(
            groupId = "user-group-v2",
            topics = TOPIC_USER_V1,
            containerFactory = "listenerContainerFactory")
    public void consumer(UserResponse userResponse){
        userService.create(userResponse);
        log.info("Usuário recebido");
    }
}