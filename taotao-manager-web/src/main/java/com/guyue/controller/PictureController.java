package com.guyue.controller;

import com.guyue.service.PictureService;
import com.taotao.common.pojo.PictureResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/pic")
public class PictureController {
    @Autowired
    private PictureService pictureService;
    @RequestMapping("/upload")
    @ResponseBody
    public PictureResult uploda(MultipartFile uploadFile){
        try {
            byte[] bytes = uploadFile.getBytes();
            String name = uploadFile.getOriginalFilename();
            PictureResult pictureResult = pictureService.uploadFile(bytes,name);
            return pictureResult;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
