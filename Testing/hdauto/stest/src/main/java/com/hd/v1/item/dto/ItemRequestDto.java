package com.hd.v1.item.dto;

import com.hd.v1.item.entity.ItemEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 의미없는 것을 제작하는 것을 방지하는 ??
@AllArgsConstructor
@Builder
public class ItemRequestDto {
    Long id;
    @NotEmpty(message = "Name can not be empty")
    String name;
    @Min(value = 10, message = "10 이상이어야 합니다")
    Long price;
    public ItemEntity toEntity() {
        return ItemEntity.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price).build();
    }
}
