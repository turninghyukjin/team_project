package com.project.sul.dto;

import com.project.sul.constant.ItemSellStatus;
import com.project.sul.entity.Address;
import com.project.sul.entity.Item;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemFormDto { // 상세페이지
    private Long id;

    @NotBlank(message="상품명은 필수 입력값입니다")
    private String itemNm; // 이미지파일명(동일 이름이 있을 수 있으니까)

    @NotBlank(message="상품 상세는 필수 입력값입니다")
    private String itemDetail; // 이미지 조회경로

    @NotNull(message="가격은 필수 입력값입니다")
    private int price; // 가격

    // 별점

    @NotNull(message="재고는 필수 입력값입니다")
    private Integer stockNumber; // 대표이미지 여부

    private ItemSellStatus itemSellStatus;
    // private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
    // private List<Long> itemImgIds = new ArrayList<>();

    //private static ModelMapper modelMapper = new ModelMapper();
    // 나중에 공유 자원으로 관리하려고!!!! 공통자원이니까

}
