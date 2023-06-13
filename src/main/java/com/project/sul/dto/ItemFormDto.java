package com.project.sul.dto;

import com.project.sul.constant.ItemSellStatus;
import com.project.sul.entity.Item;
import com.project.sul.entity.ItemDetails;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemFormDto {
    private Long id; //상품 코드111

    @NotBlank
    private String itemNm; //상품명

    @NotNull
    private int price; //가격

    @NotNull
    private Integer stockNumber; //재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품상세설명

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="itemDetail_id")
    private ItemDetails itemDetails; // 단방향 상세정보(도수, 맛)

    private List<Long> itemImgIds = new ArrayList<>();
    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private ItemSellStatus itemSellStatus;

    @Column(nullable = false)
    private String type; //상품타입(탁주, 약주, 증류주, 과실주)

    private int abv; // 알콜도수

    @Column(nullable = false)
    private int sweetness; // 단맛 (1 to 3 degree)
    private int sourness; // 신맛 (1 to 3 degree)
    private int sparkling; // 탄산 (1 to 3 degree)

    private LocalDateTime regTime;      //등록 시간 >> 유통기한과 연관?
    private LocalDateTime updateTime;   //수정 시간

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem() {
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item) {
        return modelMapper.map(item, ItemFormDto.class);
    }

}
