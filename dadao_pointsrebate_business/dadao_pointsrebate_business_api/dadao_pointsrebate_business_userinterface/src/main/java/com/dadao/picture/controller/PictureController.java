package com.dadao.picture.controller;

import com.dadao.oss.activity.OSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PictureController {

    @Autowired
    private OSSClientUtil ossClient;

    @PostMapping(value = "fileUpload")
    public Object fileUpload(MultipartFile file) {
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
        String subImgUrl = imgUrl;
        if (imgUrl != null) {
            subImgUrl = imgUrl.substring(0, imgUrl.indexOf("?"));
        }
        return subImgUrl;
    }
}