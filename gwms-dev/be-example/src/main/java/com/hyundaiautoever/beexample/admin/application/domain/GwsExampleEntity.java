package com.hyundaiautoever.beexample.admin.application.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class GwsExampleEntity {

    @Id @GeneratedValue
    private Long id;
    private String example;
    private String description;
}
