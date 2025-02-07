package com.hyundaiautoever.beexample.screen.adapter.in;

import com.hyundaiautoever.beexample.infrastructure.response.DataResponse;
import com.hyundaiautoever.beexample.infrastructure.enums.ResponseMessage;
import com.hyundaiautoever.beexample.screen.adapter.in.dto.CreateScreenAdminRequestDto;
import com.hyundaiautoever.beexample.screen.adapter.in.dto.ModifyScreenAdminRequestDto;
import com.hyundaiautoever.beexample.screen.application.port.in.CreateScreenAdminUseCase;
import com.hyundaiautoever.beexample.screen.application.port.in.ModifyScreenAdminUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/screenAdmin")
@RequiredArgsConstructor
@RestController
public class ScreenAdminController {

    private final CreateScreenAdminUseCase createScreenAdminUseCase;
    private final ModifyScreenAdminUseCase modifyScreenAdminUseCase;

    @PostMapping("/create")
    public DataResponse<Long> createScreenAdmin(@RequestBody CreateScreenAdminRequestDto createScreenAdminRequestDto) {
        return DataResponse.of(ResponseMessage.SUCCESS.getReturnCode(), "정상적으로 ScreenAdmin을 생성하였습니다.",
                createScreenAdminUseCase.create(createScreenAdminRequestDto.toEntity()).getId());
    }

    @PutMapping("/modify/{id}")
    public DataResponse<Long> modifyScreenAdmin(@PathVariable("id") Long screenAdminId, @RequestBody ModifyScreenAdminRequestDto modifyScreenAdminRequestDto) {
        modifyScreenAdminUseCase.modify(screenAdminId, modifyScreenAdminRequestDto.toEntity());
        return DataResponse.of(ResponseMessage.SUCCESS.getReturnCode(), "정상적으로 ScreenAdmin을 수정하였습니다.");
    }

}
