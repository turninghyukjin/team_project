package com.project.sul.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderPageDto {

    private List<OrderPageItemDto> orders;

}
