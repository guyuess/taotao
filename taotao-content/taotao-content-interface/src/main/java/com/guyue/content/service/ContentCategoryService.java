package com.guyue.content.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {
    List<EasyUITreeNode> getContentCategoryList(Long parentId);

    TaotaoResult addCategoryService(Long parentId, String name);

    TaotaoResult updateCategoryService(Long id, String name);

    TaotaoResult deleteCategoryService(Long id);
}
