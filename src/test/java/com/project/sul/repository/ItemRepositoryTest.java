package com.project.sul.repository;

import com.project.sul.constant.ItemSellStatus;
import com.project.sul.entity.Item;
import com.project.sul.entity.ItemImg;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    public void createItemList(){
        for(int i=1; i<=10; i++){
            Item item = new Item();
            item.setItemNm("테스트" + i);
            item.setPrice(1000 + i*100);
            item.setStockNumber(10);
            item.setType("A");
            item.setAbv(20+i);
            item.setSweetness(1);
            item.setSourness(2);
            item.setSparkling(3);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            Item savedItem = itemRepository.save(item);
        }
    }

    @Test
    @Transactional
    @DisplayName("상품 저장 테스트 ")
    public void createItemTest(){
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(2000);
        item.setStockNumber(10);
        item.setType("A");
        item.setAbv(22);
        item.setSweetness(1);
        item.setSourness(2);
        item.setSparkling(3);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        Item savedItem = itemRepository.save(item);
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
        for (Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThan(1500);
        for (Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDesc(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(1500);

        for (Item item : itemList){
            System.out.printf(item.toString());
        }
    }

    @Test
    @DisplayName("abv 조회 테스트")
    public void findByAbvLessThan(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByAbvLessThan(25);

        for (Item item : itemList){
            System.out.printf(item.toString());
        }
    }
}