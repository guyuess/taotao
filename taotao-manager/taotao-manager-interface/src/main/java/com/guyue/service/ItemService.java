package com.guyue.service;

import com.guyue.pojo.TbItem;
import com.taotao.common.pojo.EasyUIDataGridResult;

import java.util.List;

public interface ItemService {
    TbItem getItemById(Long itemId);

    EasyUIDataGridResult getItemList(Integer page,Integer rows);

    void deleteItemById(long id);

    void updateItemByIdOrStatusUndercarriage(long id);

    void updateItemByIdOrStatusGrounding(long id);

    TbItem updateItemByIdOrUndercarriage(List<Long> ids);
}
