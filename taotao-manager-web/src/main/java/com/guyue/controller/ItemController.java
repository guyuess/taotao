package com.guyue.controller;

import com.guyue.pojo.TbItem;
import com.guyue.service.ItemService;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        //根据商品id查询商品信息
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page,Integer rows){
        EasyUIDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }
    //
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public TaotaoResult deleteItemById(EasyUIDataGridResult result){
        List<Long> ids = result.getIds();
        TaotaoResult taotaoResult = itemService.deleteItemById(ids);
        return taotaoResult;
    }
    @RequestMapping("/rest/item/instock")
    @ResponseBody
    public void updateItemByIdOrStatusUndercarriage(EasyUIDataGridResult result){
        List<Long> ids = result.getIds();
        for (long id:ids) {
            itemService.updateItemByIdOrStatusUndercarriage(id);
        }
    }
    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    public TbItem updateItemByIdOrStatusGrounding(EasyUIDataGridResult result){
        List<Long> ids = result.getIds();
        for (long id:ids) {
            itemService.updateItemByIdOrStatusGrounding(id);
        }
        return null;
    }

    /*@RequestMapping("/rest/item/instock")
    @ResponseBody
    public TbItem updateItemByIdOrUndercarriage(EasyUIDataGridResult result){
        List<Long> ids = result.getIds();

        TbItem tbItem = itemService.updateItemByIdOrUndercarriage(ids);

        return null;
    }*/
}
