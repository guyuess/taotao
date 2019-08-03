package com.guyue.service.impl;

import com.guyue.service.PictureService;
import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.Date;

@Service
public class PictureServiceImpl implements PictureService {
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FILI_UPLOAD_PATH}")
    private String FILI_UPLOAD_PATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    @Override
    public PictureResult uploadFile(byte[] bytes, String name) {
        PictureResult pictureResult = new PictureResult();
        //设置图片名称
        String newName = IDUtils.genImageName() + name.substring(name.lastIndexOf("."));
        //拿当前日期来创建文件路径
        String filepath = new DateTime().toString("yyyy/MM/dd");
        //创建一个字节输入流对象
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        //将上传图片进行二进制转换保存
        boolean flag = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FILI_UPLOAD_PATH,
                filepath, newName, bis);
        if(flag){
            pictureResult.setError(0);
            pictureResult.setUrl(IMAGE_BASE_URL + "/" + filepath + "/" + newName);
            System.out.println("======================>"+pictureResult.getUrl());
        }else{
            pictureResult.setError(1);
            pictureResult.setMessage("图片上传失败！");
        }
        return pictureResult;
    }
}
