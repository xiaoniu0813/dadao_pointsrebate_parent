package com.dadao.uploadfile.service.impl;

import com.dadao.uploadfile.service.IUploadFileService;
import com.dadao.yop.service.YeepayService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * 上传文件
 *
 * @auther NFY niufuyang
 * @create 2017-11-14
 */
@Service
public class IUploadFileServiceImpl implements IUploadFileService {
    @Override
    public String uploadFile(MultipartFile file, HttpServletRequest request) {
        String basePath = request.getSession().getServletContext().getRealPath("/");
        String oldName = file.getOriginalFilename();
        //使用uuid重命名
        String newName = UUID.randomUUID().toString().replaceAll("-", "") + oldName.substring(oldName.indexOf("."), oldName.length());
        //设置文件存放路径
        String filePath = "file/" + newName;
        File newFile = new File(basePath + filePath);
        //写入文件
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取文件在服务器路径
        String filePathAndName = basePath + filePath;
        //上传到易宝
        String fileType = "IMAGE";
        Map<String, String> result = YeepayService.upload(fileType, filePathAndName.replaceAll("\\\\", "/"));
        //截取易宝返回图片URL
        String str = result.get("files");
        str = str.substring(1, str.length() - 1);
        JSONObject jb = JSONObject.fromObject(str);
        Map<String, Object> map = (Map<String, Object>) jb;
        String fileName = map.get("fileLocation").toString();
        //String imgUrl=str.substring(str.indexOf("http"),str.indexOf("jpg")+3);

        //删除存储在服务器的图片
        try {
            newFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileName;
    }
}
