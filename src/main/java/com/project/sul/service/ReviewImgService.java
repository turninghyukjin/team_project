package com.project.sul.service;

import com.project.sul.entity.ItemImg;
import com.project.sul.entity.ReviewImg;
import com.project.sul.repository.RvImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;

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
            imgName = reviewFileService.uploadFile(reviewImgLocation, rvOriImgName, reviewImgFile.getBytes() );
            imgUrl= "/review/item" + imgName;
        }
        reviewImg.updateReImg(rvOriImgName, imgName, imgUrl);
        rvImgRepository.save(reviewImg);
    }
    public void updateItemImg(Long reviewImgId, MultipartFile reviewImgFile) throws Exception{
        // MultipartFile 첨부파일의 정보를 갖고 있는 객체! 파일의 이름, 크기, 내용등
        if(!reviewImgFile.isEmpty()){
            ReviewImg savedReviewImg = rvImgRepository.findById(reviewImgId)
                    .orElseThrow(EntityNotFoundException::new);

            //기존 이미지 파일 삭제
            if(!StringUtils.isEmpty(savedReviewImg.getRvImgName())) {
                reviewFileService.deleteFile(reviewImgLocation +"/"+ savedReviewImg.getRvImgName());
            }

            String oriImgName = reviewImgFile.getOriginalFilename();
            String imgName = reviewFileService.uploadFile(reviewImgLocation, oriImgName, reviewImgFile.getBytes());
            String imgUrl = "/images/item/" + imgName;
            savedReviewImg.updateReImg(oriImgName, imgName, imgUrl);
        }
    }
}
