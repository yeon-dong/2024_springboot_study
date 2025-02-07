package com.hyundaiautoever.beexample.admin.application.domain;

import com.hyundaiautoever.beexample.admin.application.domain.enums.ScreenComponentType;
import com.hyundaiautoever.beexample.infrastructure.config.auditing.BaseEntity;
import jakarta.persistence.*;

@Entity
public class ScreenUIComponent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tagId;

    private String name;

    private String description;

    private ScreenComponentType type;

    private Boolean isEnabled = true;

    private Boolean isVisibility;

    @ManyToOne
    private ScreenUI screenUI;

}
