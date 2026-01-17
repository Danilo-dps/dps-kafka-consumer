package com.danilodps.kafkaconsumer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Entity(name = "TB_USER_FULL_NAME")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "USER_ID", updatable = false)
    private UUID userId;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
}
