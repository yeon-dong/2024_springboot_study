package com.hyundaiautoever.beexample.admin.application.domain;

import com.hyundaiautoever.beexample.admin.application.domain.enums.ScreenType;
import com.hyundaiautoever.beexample.infrastructure.config.auditing.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ScreenUI extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String Url;

    private ScreenType type;

    private Boolean isActive = true;

    @OneToMany(mappedBy = "screenUI")
    private List<ScreenUIComponent> components = new ArrayList<>();

}
