package com.danilodps.kafkaconsumer.application.consumer;

import com.danilodps.kafkaconsumer.domain.record.received.UserResponse;
import com.danilodps.kafkaconsumer.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final UserService userService;

    @RetryableTopic(
            backOff = @BackOff(delay = 2000),
            kafkaTemplate = "producerKafkaTemplateRetry",
            listenerContainerFactory = "listenerContainerFactoryRetry",
            include = {RuntimeException.class},
            autoCreateTopics = "false",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            dltStrategy = DltStrategy.FAIL_ON_ERROR
    )
    @KafkaListener(
            groupId = "consumer-group",
            topics = {"topic-user-created"},
            containerFactory = "listenerContainerFactory"
    )
    public void consumerCreate(UserResponse userResponse){
        try {
            userService.create(userResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RetryableTopic(
            backOff = @BackOff(delay = 2000),
            kafkaTemplate = "producerKafkaTemplateRetry",
            listenerContainerFactory = "listenerContainerFactoryRetry",
            include = {RuntimeException.class},
            autoCreateTopics = "false",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            dltStrategy = DltStrategy.FAIL_ON_ERROR
    )
    @KafkaListener(
            groupId = "consumer-group",
            topics = {"topic-user-updated"},
            containerFactory = "listenerContainerFactory"
    )
    public void consumerUpdate(UserResponse userResponse){
        try {
            log.info("Solicitação de atualização");
            userService.update(userResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RetryableTopic(
            backOff = @BackOff(delay = 2000),
            kafkaTemplate = "producerKafkaTemplateRetry",
            listenerContainerFactory = "listenerContainerFactoryRetry",
            include = {RuntimeException.class},
            autoCreateTopics = "false",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            dltStrategy = DltStrategy.FAIL_ON_ERROR
    )
    @KafkaListener(
            groupId = "consumer-group",
            topics = {"topic-user-deleted"},
            containerFactory = "listenerContainerFactory"
    )
    public void consumerDelete(UserResponse userResponse){
        try {
            log.info("Solicitação de exclusão");
            userService.delete(userResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DltHandler
    public void listenDLT(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.warn("DLT Recebido: {} do tópico DLT {}", message, topic);
        // Lógica para lidar com mensagens que esgotaram todas as retentativas (ex: persistir para análise manual)
    }

}