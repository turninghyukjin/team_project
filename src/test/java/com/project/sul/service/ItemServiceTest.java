package com.project.sul.service;

import com.project.sul.repository.ItemImgRepository;
import com.project.sul.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemImgRepository itemImgRepository;



    @Test
    @DisplayName("상품등록 테스트1") // 2개 이상 인지
    void saveItem1() throws Exception {

    }

    @Test
    @DisplayName("상품등록 테스트2") // 5개
    void saveItem2() throws Exception {

    }
}