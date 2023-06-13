package com.project.sul.service;


import com.project.sul.dto.ItemDetailsDto;
import com.project.sul.entity.Item;
import com.project.sul.entity.ItemDetails;
import com.project.sul.repository.ItemDetailsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculateItemDetailsService {
    @Autowired
    ItemDetailsRepository itemDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Item item;
    public ItemDetails itemDetails = new ItemDetails();

    public ItemDetailsDto  doCalculation() {
        int abv = item.getAbv();
        int r_abv = (int)abv/10;

        itemDetails.setR_abv(r_abv);
        return modelMapper.map(itemDetails, ItemDetailsDto.class);
    }
    public void saveCalculationResult(ItemDetailsDto itemDetailsDto) {
        ItemDetails itemDetails = modelMapper.map(itemDetailsDto, ItemDetails.class);
        itemDetailsRepository.save(itemDetails);
    }
    @Autowired
    public CalculateItemDetailsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}

