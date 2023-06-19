package com.project.sul.service;

import com.project.sul.entity.ItemImg;
import com.project.sul.repository.ItemImgRepository;
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
public class ItemImgService {
    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;
    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception{
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            imgUrl= "/images/item" + imgName;
        }
        itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        itemImgRepository.save(itemImg);
    }

    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception{
        // MultipartFile 첨부파일의 정보를 갖고 있는 객체! 파일의 이름, 크기, 내용등
        if(!itemImgFile.isEmpty()){
            ItemImg savedItemImg = itemImgRepository.findById(itemImgId)
                    .orElseThrow(EntityNotFoundException::new);

            //기존 이미지 파일 삭제
            if(!StringUtils.isEmpty(savedItemImg.getImgName())) {
                fileService.deleteFile(itemImgLocation +"/"+ savedItemImg.getImgName());
            }

            String oriImgName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            String imgUrl = "/images/item/" + imgName;
            savedItemImg.updateItemImg(oriImgName, imgName, imgUrl);
            // 변경감지 (repository에 접근하지 않았음. 생성메소드를 호출해서 값을 넣는 것만으로도 jpa의 변경감지로 가능)
            // 덮어쓰기가 아니라 삭제후 재저장
            // 변경감지는 영속성, transactional 상태일 때 가능
            // 영속성이 @Entity를 선언하는 것만으로 되는건가?
            // >> 여기서 itemImg의 영속성이 @EntityListeners가 선언된 baseEntity 상속으로 이뤄지는게 맞나??? 아니었음 ㅇㅇ
            // https://velog.io/@koo8624/Spring-%EB%B3%80%EA%B2%BD-%EA%B0%90%EC%A7%80%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-Entity-%EC%88%98%EC%A0%95
        }
    }
}
