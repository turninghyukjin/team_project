package com.project.sul.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Service
@Log
public class FileService { // 파일업로드, 삭제
    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception{
        UUID uuid = UUID.randomUUID();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = uuid.toString() + extension;
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;

        try (OutputStream outputStream = new FileOutputStream(fileUploadFullUrl)) {

            outputStream.write(fileData);
            outputStream.flush();
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save file: " + e.getMessage());
        }

        return savedFileName;
    }

    public void deleteFile(String filePath) throws Exception{
        File deleteFile = new File(filePath);
        if(deleteFile.exists()) {
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        } else {
            log.info("파일이 존재하지 않습니다");
        }
        // java.io 패키지는 자바의 기본적인 데이터 입출력 api 제공(File, Console, InputStream..)
    }
}