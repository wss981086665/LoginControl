package com.wss.demo.controller;

import com.wss.demo.domain.ResultObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

// 标注为返回json数据的controller，该类下的方法的URL前缀为/file
@RestController
@RequestMapping("file")
public class FileController {

    // 文件保存路径
    private static String PATH = "/home/project/";

    // 功能为上传文件，指定请求方法类型为post、URL为/file/upload
    @PostMapping("upload")
    public ResultObject add(MultipartFile file) {
        // 如果file为空直接返回
        if(file.isEmpty()) {
            return new ResultObject(-1, "file is empty");
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件大小
        long size = file.getSize();
        System.out.println(size);

        // 创建文件
        File dest = new File(PATH + fileName);

        try {
            // 保存文件
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return new ResultObject(-1, "state exception");
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultObject(-1, "io exception");
        }

        return new ResultObject(null);
    }
}