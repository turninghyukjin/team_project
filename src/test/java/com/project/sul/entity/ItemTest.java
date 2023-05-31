package com.project.sul.entity;

import com.project.sul.constant.ItemSellStatus;
import com.project.sul.dto.ItemFormDto;
import com.project.sul.exception.OutOfStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class ItemTest {

    private Item item;

    @BeforeEach
    public void setUp() {
        item = new Item();
    }

    @Test
    public void updateItem_ShouldUpdateItemProperties() {
        // Given
        ItemFormDto itemFormDto = new ItemFormDto();
        itemFormDto.setItemNm("New Item Name");
        itemFormDto.setPrice(10000);
        itemFormDto.setStockNumber(10);
        itemFormDto.setItemDetail("New Item Detail");
        itemFormDto.setItemSellStatus(ItemSellStatus.SELL);

        // When
        item.updateItem(itemFormDto);

        // Then
        assertEquals("New Item Name", item.getItemNm());
        assertEquals(10000, item.getPrice());
        assertEquals(10, item.getStockNumber());
        assertEquals("New Item Detail", item.getItemDetail());
        assertEquals(ItemSellStatus.SELL, item.getItemSellStatus());
    }

    @Test
    public void removeStock_WithSufficientStock_ShouldUpdateStockNumber() {
        // Given
        item.setStockNumber(10);

        // When
        item.removeStock(5);

        // Then
        assertEquals(5, item.getStockNumber());
    }

    @Test
    public void removeStock_WithInsufficientStock_ShouldThrowOutOfStockException() {
        // Given
        item.setStockNumber(3);

        // When
        OutOfStockException exception = assertThrows(OutOfStockException.class, () -> item.removeStock(5));

        // Then
        assertEquals("상품의 재고가 부족합니다. (현재 재고 수량 :3)", exception.getMessage());
        assertEquals(3, item.getStockNumber());
    }

    @Test
    public void addStock_ShouldUpdateStockNumber() {
        // When
        item.addStock(5);

        // Then
        assertEquals(5, item.getStockNumber());
    }

}