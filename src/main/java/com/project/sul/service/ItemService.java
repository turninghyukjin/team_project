package com.project.sul.service;

import com.project.sul.dto.*;
import com.project.sul.entity.Item;
import com.project.sul.entity.ItemImg;
import com.project.sul.repository.ItemImgRepository;
import com.project.sul.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;

    // 등록
    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {
        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        //최소 2장의 이미지를 넣도록 (대표이미지, 상세이미지)
        if (itemImgFileList.size() < 1) {
            throw new IllegalArgumentException("최소한 2장의 이미지를 넣어야 합니다");
        }

        // 이미지 등록
        for (int i = 0; i < itemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if (i == 0)
                itemImg.setRepImgYn("Y"); // 첫번째 이미지를 대표이미지로 지정
            else
                itemImg.setRepImgYn("N");

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }
        return item.getId();
    }

    @Transactional(readOnly = true) // 목록을 조회
    public ItemFormDto getItemDetails(Long itemId) {
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        // itemId에 해당하는 itemImg 목록 조회, 결과를 가져옴
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();
        // itemImgDto에서 매핑되었기 때문에 해당 itemId의 사진만 나옴

        for (ItemImg itemImg : itemImgList) {
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }
        // itemImgList 순회하면서 각각의 ItemImg를 itemImgDto로 변환하여 itemImgDtoList 추가

        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        ItemFormDto itemFormDto = ItemFormDto.of(item);
        // itemFormDto.of(item) 호출 > item을 itemFormDto로 변환
        itemFormDto.setItemImgDtoList(itemImgDtoList);
        // 변환된 itemFormDto를 ItemImgDtoList에 넣음
        return itemFormDto;
    }

    public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {
        Item item = itemRepository.findById(itemFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormDto);
        List<Long> itemImgIds = itemFormDto.getItemImgIds();

        for (int i = 0; i < itemImgFileList.size(); i++) {
            itemImgService.updateItemImg(itemImgIds.get(i),
                    itemImgFileList.get(i));
        }
        return item.getId();
    }

    @Transactional
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        return itemRepository.getAdminItemPage(itemSearchDto, pageable);
    }

//    @Transactional(readOnly = true)
//    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
//        return itemRepository.getMainItemPage(itemSearchDto, pageable);
//    }
}

    // 지금 구현하고 싶은 거
    // 지정된 값에 따라서 자동으로 그림을 불러오기(별점, 맛(도수, 산미, 탄산, 단맛))
    // >> 별점: @Scheduled, @EnableScheduling (매일 정해진 시간에 별점 검색> 유의미한 변화가 있을 때 자동 변환)

    // 이건 기본 틀 >> 나중에 완성할게요
    // 스프링 프로퍼티 이용! + https://hanke-r.tistory.com/109
    // 리뷰에 별점기능 >> https://velog.io/@hellocdpa/220305-%EB%A6%AC%EB%B7%B0-%EB%B3%84%EC%A0%90-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0
//    @Scheduled(cron = "0 0 0 * * *") // 매일밤
//    public void processStarPictures() {
//        List<Double> ratingScores = getRatingScores();
//
//        for (Double ratingScore : ratingScores) {
//            String starPicture = fetchStarPicture(ratingScore);
//            saveStarPicture(starPicture, ratingScore);
//        }
//    }
//    private List<Double> getRatingScores() {
//
//        return ratingScores;
//    }
//    private String fetchStarPicture(Double ratingScore) {
//
//        return starPicture;
//    }
//    private void saveStarPicture(String starPicture, Double ratingScore) {
//
//    }

