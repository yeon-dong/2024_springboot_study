package com.hd.v1.item.service;

import com.hd.common.frame.HDService;
import com.hd.v1.item.entity.ItemEntity;
import com.hd.v1.item.repository.ItemRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService implements HDService<ItemEntity, Long> {

    final ItemRepository itemRepository;

    @Override
    public ItemEntity get(Long aLong) {
        ItemEntity itemEntity =
                itemRepository.findById(aLong).orElse(null);
        return itemEntity;
    }

    @Override
    public ItemEntity save(ItemEntity itemEntity) {
        Optional<ItemEntity>item = itemRepository.findByName(itemEntity.getName());
        if (item.isPresent()) {
            // Exception

        }
        return itemRepository.save(itemEntity);
    }

    @Override
    public ItemEntity modify(ItemEntity itemEntity) {
        return null;
    }

    @Override
    public Long remove(Long aLong) {
        return 0L;
    }

    @Override
    public List<ItemEntity> getall() {
        List<ItemEntity> list = itemRepository.findAll();
        return list;
    }
}
