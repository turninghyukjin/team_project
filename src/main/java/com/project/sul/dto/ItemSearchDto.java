package com.project.sul.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDto {
    private String type;
    private int sweetness;
    private int sourness;
    private int sparkling;

    private String searchBy;
    private String searchQuery= "";
}

