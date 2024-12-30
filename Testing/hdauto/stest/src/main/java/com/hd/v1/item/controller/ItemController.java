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
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/get")
    public ResponseEntity<?> get() {
        //log.info("get: -------");

        return response.success(itemService.getall().stream().map(ItemResponseDto::new).toList());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get( @PathVariable("id") Long id)  {
        return response.success(new ItemResponseDto(itemService.get(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        //log.info("Delete -------------------------------"+id);
        return response.success(itemService.remove(id));
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@Validated @RequestBody ItemRequestDto dto, Errors errors) {
        //log.info("Service  ex: -------update "+dto.toString());
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        //log.info("update cont: -------");
        return response.success(new ItemResponseDto(itemService.modify(dto.toEntity())));
    }

}
