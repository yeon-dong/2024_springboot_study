package com.hyundaiautoever.beexample.screen.application.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ScreenComponent {

    @Id @GeneratedValue
    private Long id;
    private String componentName; // 요소이름
    private String description; //설명
    private String type; // 컴포넌트 타입
    private String feature; // 용도
    private String isUsing; // 사용여부

    public ScreenComponent(String componentName, String description, String type, String feature, String isUsing) {
        this.componentName = componentName;
        this.description = description;
        this.type = type;
        this.feature = feature;
        this.isUsing = isUsing;
    }
}

