package com.project.sul.service;

import com.project.sul.entity.ReviewImg;
import com.project.sul.repository.RvImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewImgService {
    @Value("${reviewImgLocation}")
    private String reviewImgLocation;

    private final RvImgRepository rvImgRepository;
    private final ReviewFileService reviewFileService;

    public void saveReviewImg(ReviewImg reviewImg, MultipartFile reviewImgFile) throws Exception{
        String rvOriImgName = reviewImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(rvOriImgName)){
            imgName = reviewFileService.rvUploadFile(reviewImgLocation, rvOriImgName, reviewImgFile.getBytes() );
            imgUrl= "/review/item" + imgName;
        }

        reviewImg.updateReImg(rvOriImgName, imgName, imgUrl);
        rvImgRepository.save(reviewImg);
    }
}
