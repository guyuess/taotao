package com.guyue.content.service;

import com.guyue.pojo.TbContent;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

public interface ContentService {
    EasyUIDataGridResult findContentAll(Long categoryId);

    TaotaoResult addContent(TbContent tbContent);

    TaotaoResult updateContent(TbContent tbContent);

    TaotaoResult deleteContent(List<Long> ids);

    List<TbContent> getContentAll(Long categoryId);
}
