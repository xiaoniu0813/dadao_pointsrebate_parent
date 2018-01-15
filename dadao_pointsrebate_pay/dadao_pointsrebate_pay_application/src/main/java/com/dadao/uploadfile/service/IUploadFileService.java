package com.dadao.uploadfile.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by NFY on 2017-11-14.
 */
public interface IUploadFileService {
    String uploadFile(MultipartFile flile,HttpServletRequest request);
}
