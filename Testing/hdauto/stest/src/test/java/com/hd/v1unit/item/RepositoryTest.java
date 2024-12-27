package com.hd.v1unit.item;


import com.hd.v1.item.entity.ItemEntity;
import com.hd.v1.item.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*; // assertThat

@Slf4j
@DataJpaTest // spring boot의 모든게 올라오는게 아니라 JPA 관련 메모리만 올리게 된다. -> h2db
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 메모리DB를 무조건 가지므로 NONE으로 해놓으면 필요에 따라 mysql, 메모리DB
@DisplayName("Item Repository Test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("h2db") // 필요에 따라 빠르게 전환 가능하다 -> 어느정도 테스트가 됐다고 하면 "dev"로 바꾸면 된다
public class RepositoryTest {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    EntityManager entityManager;

    // 다시 키값까지 롤백시킨다. 즉 초기화시킨다.
    @BeforeEach
    public void db_int(){
//        String sql = "ALTER TABLE item_db AUTO_INCREMENT = 1"; // mysql
        String sql = "ALTER TABLE item_db ALTER COLUMN id RESTART WITH 1"; // h2db
        entityManager.createNativeQuery(sql).executeUpdate();
    }

    @Test
    @Order(1)
    @DisplayName("1. Insert Test")
    public void test1() {
        // given : 데이터가 주워졌다
        String name = "pants1";
        long price = 1000L;

        // when
        ItemEntity itemEntity = itemRepository.save(ItemEntity.builder()
                .name(name)
                .price(price)
                .build());


        log.info("itemEntity: {}", itemEntity);
        // then : assert로 테스트
        // junit5랑 assert가 같이 있어서 assertThat은 잘 안먹는다
        assertThat(name).isEqualTo(itemEntity.getName());
        assertThat(price).isEqualTo(itemEntity.getPrice());
        assertThat(itemEntity.getId()).isNotNull();
        assertThat(1L).isEqualTo(itemEntity.getId());
    }

    @Test
    @Order(2)
    @DisplayName("2. FindById Test")
    public void test2() {
        // given
        String name = "pants1";
        long price = 1000L;

        // when : insert 하고나서 조회를 해야한다. 왜냐하면 테스트 하나가 끝나면, 자동으로 롤백이 되기 때문에
        ItemEntity itemEntity = itemRepository.save(ItemEntity.builder()
                .name(name)
                .price(price)
                .build());
        log.info("itemEntity: {}", itemEntity);
        Optional<ItemEntity> item = itemRepository.findById(1L); // select 했는데 null일 경우를 대비하여 Optional을 넣어준다.

        // then
        if(item.isPresent()){
//            log.info("itemEntity: {}", item);
            assertThat(itemEntity.getName()).isEqualTo(item.get().getName());
        } else{
//            log.info("itemEntity not found");
            assertThat(item).isEmpty();
        }
    }

    @Test
    @Order(3)
    @DisplayName("3. FindAll Test")
    public void test3() {
        // given
        String name = "pants1";
        long price = 1000L;

        // when : insert 하고나서 조회를 해야한다. 왜냐하면 테스트 하나가 끝나면, 자동으로 롤백이 되기 때문에
        ItemEntity itemEntity = itemRepository.save(ItemEntity.builder()
                .name(name)
                .price(price)
                .build());

        List<ItemEntity> item = itemRepository.findAll();

        // then
        assertThat(item).hasSize(1);
        assertThat(item.get(0).getName()).isEqualTo(name);
    }

    @Test
    @Order(4)
    @DisplayName("4. Update Test")
    public void test4(){
        // given
        String name = "pants1";
        long price = 1000L;
        String updateName = "pants2";
        long updatePrice = 2000L;

        // when
        ItemEntity itemEntity =  itemRepository.save(ItemEntity.builder()
                .name(name)
                .price(price)
                .build());
        ItemEntity updateItemEntity =  itemRepository.save(ItemEntity.builder()
                .id(1L)
                .name(updateName)
                .price(updatePrice)
                .build());
        // then
        assertThat(itemEntity.getId()).isEqualTo(updateItemEntity.getId());
        assertThat(updateItemEntity.getName()).isEqualTo(updateName);
        assertThat(updateItemEntity.getPrice()).isEqualTo(updatePrice);
    }
    @Test
    @Order(5)
    @DisplayName("5. Delete Test")
    public void test5(){
        // given
        String name = "pants1";
        long price = 1000L;

        // when
        ItemEntity itemEntity =  itemRepository.save(ItemEntity.builder()
                .name(name)
                .price(price)
                .build());
        Optional<ItemEntity> item = itemRepository.findById(itemEntity.getId());
        itemRepository.delete(item.get());

        // then
        assertThat(itemRepository.findById(itemEntity.getId())).isNotPresent();

    }
}
