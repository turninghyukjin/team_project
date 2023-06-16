package com.project.sul.service;

import com.project.sul.constant.ItemSellStatus;
import com.project.sul.dto.ItemFormDto;
import com.project.sul.entity.Item;
import com.project.sul.entity.ItemImg;
import com.project.sul.repository.ItemImgRepository;
import com.project.sul.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemImgRepository itemImgRepository;

    List<MultipartFile> createMultipartFiles() throws Exception {
        List<MultipartFile> multipartFileList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String path = "./item/";
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile =
                    new MockMultipartFile(path, imageName,
                            "image/jpg", new byte[]{1, 2, 3, 4});
            multipartFileList.add(multipartFile);
        }
        return multipartFileList;
    }

    @Test
    @DisplayName("상품등록 테스트1") //
    @WithMockUser(username = "admin", roles = "ADMIN")
    void saveItem() throws Exception {
        // given
        ItemFormDto itemFormDto = new ItemFormDto();
        itemFormDto.setItemNm("테스트상품1");
        itemFormDto.setPrice(1000);
        itemFormDto.setStockNumber(10);
        itemFormDto.setItemDetail("AAA");

        List<MultipartFile> multipartFileList = createMultipartFiles();
        Long itemId = itemService.saveItem(itemFormDto, multipartFileList);
        List<ItemImg> itemImgsList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);

        itemFormDto.setItemSellStatus(ItemSellStatus.SELL);
        itemFormDto.setType("aaa");
        itemFormDto.setAbv(20);
        itemFormDto.setSweetness(1);
        itemFormDto.setSourness(2);
        itemFormDto.setSparkling(3);

        // result
        assertEquals(itemFormDto.getItemNm(), item.getItemNm());
        assertEquals(itemFormDto.getPrice(), item.getPrice());
        assertEquals(itemFormDto.getStockNumber(), item.getStockNumber());
        assertEquals(itemFormDto.getItemDetail(), item.getItemDetail());

        assertEquals(multipartFileList.get(0).getOriginalFilename(), itemImgsList.get(0).getOriImgName());

        assertEquals(itemFormDto.getItemSellStatus(), item.getItemSellStatus());
        assertEquals(itemFormDto.getType(), item.getType());
        assertEquals(itemFormDto.getAbv(), item.getAbv());
        assertEquals(itemFormDto.getSweetness(), item.getSweetness());
        assertEquals(itemFormDto.getSourness(), item.getSourness());
        assertEquals(itemFormDto.getSparkling(), item.getSparkling());
    }
}