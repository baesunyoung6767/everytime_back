package com.example.everytime.constant;

import com.example.everytime.entity.FreeImage;
import com.example.everytime.entity.FreePost;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FileHandler {

    public List<FreeImage> parseFileInfo(FreePost freePost, List<MultipartFile> multipartFiles) throws Exception {
        List<FreeImage> imageList = new ArrayList<>();

        if(multipartFiles.isEmpty()) return imageList;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

//        if(multipartFiles.isEmpty()) return imageList;

        String absolutePath = new File("").getAbsolutePath() + "\\";
        String path = "images/" + current_date;
        File file = new File(path);

        if(!file.exists()) file.mkdirs();

        for(MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()) {
                String contentType = multipartFile.getContentType();
                String originalFileExtension;
                if(ObjectUtils.isEmpty(contentType)) break;
                else {
                    if(contentType.contains("image/jpeg")) originalFileExtension = ".jpg";
                    else if(contentType.contains("image/png")) originalFileExtension = ".png";
                    else if(contentType.contains("image/gif")) originalFileExtension = ".gif";
                    else break;
                }

                String new_file_name = System.nanoTime() + originalFileExtension;
                FreeImage freeImage = FreeImage.builder()
                        .freePost(freePost)
                        .originalFileName(multipartFile.getOriginalFilename())
                        .storedFileName(path + "/" + new_file_name)
                        .fileSize(multipartFile.getSize())
                        .build();
                imageList.add(freeImage);

                file = new File(absolutePath + path + "/" + new_file_name);
                multipartFile.transferTo(file);
            }
        }
        return imageList;
    }
}
