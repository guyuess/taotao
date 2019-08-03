package com.guyue.service;

import com.taotao.common.pojo.PictureResult;

public interface PictureService {
    PictureResult uploadFile(byte[] bytes,String name);
}
