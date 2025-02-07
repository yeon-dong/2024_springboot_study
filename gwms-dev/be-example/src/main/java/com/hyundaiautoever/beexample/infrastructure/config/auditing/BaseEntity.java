package com.hyundaiautoever.beexample.infrastructure.config.auditing;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime registerDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    @CreatedBy
    private String registerUserSeq;

    @LastModifiedBy
    private String updateUserSeq;

    protected LocalDateTime deleteDate;
    protected Long deleteUserSeq;
}
