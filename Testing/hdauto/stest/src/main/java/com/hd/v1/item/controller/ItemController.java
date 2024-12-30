package com.hd.v1.item.controller;

import com.hd.common.dto.Response;
import com.hd.common.util.Helper;
import com.hd.v1.item.dto.ItemRequestDto;
import com.hd.v1.item.dto.ItemResponseDto;
import com.hd.v1.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {
    private final ItemService itemService;
    private final Response response;

    @PostMapping("/add")
    public ResponseEntity<?> add(@Validated @RequestBody ItemRequestDto requestDto,
                                 Errors errors) {
        if(errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return response.successCreate(new ItemResponseDto(itemService.save(requestDto.toEntity())));
    }
}
