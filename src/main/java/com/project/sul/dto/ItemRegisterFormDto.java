package com.project.sul.dto;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemRegisterFormDto {
    private Long id;

    @NotBlank
    private String itemNm;

    @NotNull
    private int price;

    @NotNull
    private Integer stockNumber;

    @NotBlank
    private String itemDetail;

    private List<Long> itemImgIds = new ArrayList<>();
    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();


    @NotNull
    private String selectedOption;

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }
}
