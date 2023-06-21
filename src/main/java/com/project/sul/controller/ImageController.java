package com.project.sul.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
public class ImageController {

        @GetMapping(value = "/display/img")
        public ResponseEntity<byte[]> getFile(@Param("fileName") String fileName){
            String path = "C:\\team_project"+fileName;
            File file = new File(path);
            ResponseEntity<byte[]> result = null;

            try{
                HttpHeaders header = new HttpHeaders();
                Path filePath = file.toPath();
                header.add("Content-Type", Files.probeContentType(filePath));
                byte[] resource = FileCopyUtils.copyToByteArray(file);
                result = new ResponseEntity<>( resource, header , HttpStatus.OK );
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

}

