package com.hyundaiautoever.beexample.screen.application.domain;

import com.hyundaiautoever.beexample.infrastructure.config.auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ScreenAdmin extends BaseEntity {

    @Id @GeneratedValue
    private Long id;
    private String screenId; // 화면 ID
    private String screenUrl; //화면 URL
    private String screenName; // 화면명
    private String description; // 설명
    private String isUsing; // 사용여부

    @OneToMany(cascade = CascadeType.ALL)
    private List<ScreenComponent> screenComponentList = new ArrayList<>();


    public ScreenAdmin(String screenId, String screenUrl, String screenName, String description, String isUsing) {
        this.screenId = screenId;
        this.screenUrl = screenUrl;
        this.screenName = screenName;
        this.description = description;
        this.isUsing = isUsing;
    }

    public static ScreenAdmin of(String screenId, String screenUrl, String screenName, String description, String isUsing) {
        return new ScreenAdmin(screenId, screenUrl, screenName, description, isUsing);
    }

    public void updateScreenAdmin(ScreenAdmin updateScreenAdmin) {
        this.screenId = updateScreenAdmin.getScreenId();
        this.screenUrl = updateScreenAdmin.getScreenUrl();
        this.screenName = updateScreenAdmin.getScreenName();
        this.description = updateScreenAdmin.getDescription();
        this.isUsing = updateScreenAdmin.getIsUsing();
    }
}

