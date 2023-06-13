package com.project.sul.dto;

import com.project.sul.constant.ItemSellStatus;
import com.project.sul.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemFormDto { // 상품등록
    private Long id;

    @NotBlank(message="상품명은 필수 입력값입니다")
    private String itemNm;

    @NotNull(message="가격은 필수 입력값입니다")
    private int price; // 가격

    @NotNull(message="재고는 필수 입력값입니다")
    private Integer stockNumber;

    private String selectedOption; // 상품타입
        // 라디오값
        public String getSelectedOption() {
            return selectedOption;
        }
        public void setSelectedOption(String selectedOption) {
            this.selectedOption = selectedOption;
        }

    @NotBlank(message="필수 입력값입니다")
    @Min(value = 0, message = "알콜도수는 0 이상이어야 합니다")
    @Max(value = 60, message = "알콜도수는 60 이하여야 합니다")
    private int abv; // 알콜도수
    @NotBlank(message="필수 입력값입니다")
    @Min(value = 1, message = "당도는 0 이상이어야 합니다")
    @Max(value = 3, message = "당도는 3 이하여야 합니다")
    private int sweetness; // 단맛
    @NotBlank(message="필수 입력값입니다")
    @Min(value = 1, message = "산미는 1 이상이어야 합니다")
    @Max(value = 3, message = "산미는 3 이하여야 합니다")
    private int sourness; // 신맛
    @NotBlank(message="필수 입력값입니다")
    @Min(value = 1, message = "탄산은 1 이상이어야 합니다")
    @Max(value = 3, message = "탄산은 3 이하여야 합니다")
    private int sparkling; // 탄산

    @NotBlank(message="상품 상세정보는 필수 입력값입니다")
    private String itemDetail;

    private ItemSellStatus itemSellStatus;

    @NotBlank(message="필수 입력값입니다")
    private String taste; // 핵심포인트-맛
    @NotBlank(message="필수 입력값입니다")
    private String sideDish; // 핵심포인트-안주
    @NotBlank(message="필수 입력값입니다")
    private String ingredient; // 핵심포인트-재료

    private int expire; // 유통기한
    private String conserve; // 보관방법


    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem(){
        return modelMapper.map(this, Item.class);
    }
    public static ItemFormDto of(Item item){
        return modelMapper.map(item, ItemFormDto.class);
    }

}
