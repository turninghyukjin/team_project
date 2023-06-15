package com.project.sul.service;

import com.project.sul.dto.ItemDto;
import com.project.sul.dto.ItemFormDto;
import com.project.sul.dto.ItemImgDto;
import com.project.sul.dto.ItemSearchDto;
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

import javax.persistence.EntityManager;
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

    private final EntityManager em;

    // 별점
    public void updateAvgStar(Long id, Double avgStar) {
        Item item = em.find(Item.class, id);
        item.setAvgStar(avgStar);
    }
    public void updateNumComment(Long itemId) {
        Item item = itemRepository.findById(itemId).orElse(null);
        if (item != null) {
            int numComment = itemRepository.countCommentsByItem(item);
            item.setNumComment(numComment);
            itemRepository.save(item);
        }
    }
    // 등록
    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {
        Item item = itemFormDto.createItem();
        itemRepository.save(item);

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

    @Transactional(readOnly = true)
    public Page<ItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        return itemRepository.getItemPage(itemSearchDto, pageable);
    }
}