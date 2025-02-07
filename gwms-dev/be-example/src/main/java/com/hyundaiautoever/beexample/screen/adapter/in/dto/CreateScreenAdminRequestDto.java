package com.hyundaiautoever.beexample.screen.adapter.in.dto;

import com.hyundaiautoever.beexample.screen.application.domain.ScreenAdmin;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateScreenAdminRequestDto {

    private String screenId; // 화면 ID
    private String screenUrl; //화면 URL
    private String screenName; // 화면명
    private String description; // 설명
    private String isUsing; // 사용여부


    public ScreenAdmin toEntity() {
        return ScreenAdmin.of(screenId, screenUrl, screenName, description, isUsing);
    }
}
