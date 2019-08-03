package com.guyue.service;

import com.guyue.pojo.TbItem;
import com.guyue.pojo.TbItemDesc;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.pojo.UpdateItem;

import java.util.List;

public interface ItemService {
    TbItem getItemById(Long itemId);

    EasyUIDataGridResult getItemList(Integer page,Integer rows);

    TaotaoResult deleteItemById(List<Long> ids);

    TaotaoResult updateItemByIdOrStatusUndercarriage(List<Long> ids);

    TaotaoResult updateItemByIdOrStatusGrounding(List<Long> ids);

    TaotaoResult addItem(TbItem tbItem,String desc);

    TaotaoResult queryItemDescByItemId(Long itemId);

    TaotaoResult updateItemAndItemDesc(TbItem tbItem, String desc);
}
