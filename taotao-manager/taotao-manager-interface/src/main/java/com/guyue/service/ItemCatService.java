package com.guyue.service;

import com.taotao.common.pojo.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {
    List<EasyUITreeNode> queryItemCatByParentId(Long id);
}
