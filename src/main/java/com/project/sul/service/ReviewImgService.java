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
    private final FileService fileService;

//    public void saveReImg(ReviewImg reviewImg, MultipartFile reviewImgFile) throws Exception{
//        String RvOriImgName = reviewImgFile.getOriginalFilename();
//        String imgName = "";
//        String imgUrl = "";
//
//        if(!StringUtils.isEmpty(rvOriImgName)){
//            imgName = fileService.uploadFile(reviewImgLocation, rvOriImgName, rvImgFile.getBytes());
//            imgUrl= "/images/review" + imgName;
//        }
//    }
}
