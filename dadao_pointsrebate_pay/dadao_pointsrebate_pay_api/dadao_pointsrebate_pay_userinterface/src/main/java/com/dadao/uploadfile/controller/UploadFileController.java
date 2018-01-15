package com.dadao.uploadfile.controller;

import com.dadao.uploadfile.service.IUploadFileService;
import com.dadao.util.DADAO;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * 上传文件到易宝
 *
 * @auther NFY niufuyang
 * @create 2017-11-14
 */
@RestController
@RequestMapping(value = "upload")
public class UploadFileController {

    @Autowired
    private IUploadFileService iUploadFileService;

    @PostMapping(value = "uploadFile")
    public Object uploadFile(@RequestParam(required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS , iUploadFileService.uploadFile(file,request)));
    }
}
