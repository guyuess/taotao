package com.guyue.controller;

import com.guyue.pojo.TbItem;
import com.guyue.pojo.TbItemDesc;
import com.guyue.service.ItemService;
import com.guyue.service.ItemDescService;
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
    public TaotaoResult updateItemByIdOrStatusUndercarriage(EasyUIDataGridResult result){
        List<Long> ids = result.getIds();
        TaotaoResult taotaoResult = itemService.updateItemByIdOrStatusUndercarriage(ids);
        return taotaoResult;
    }
    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    public TaotaoResult updateItemByIdOrStatusGrounding(EasyUIDataGridResult result){
        List<Long> ids = result.getIds();
        TaotaoResult taotaoResult = itemService.updateItemByIdOrStatusGrounding(ids);
        return taotaoResult;
    }
    
    @RequestMapping("/item/save")
    @ResponseBody
    public TaotaoResult addItemAndItemDesc(TbItem item,String desc){
        TaotaoResult taotaoResult = itemService.addItem(item, desc);
        return taotaoResult;
    }
    /*@RequestMapping("item-edit")
    @ResponseBody
    public TaotaoResult updateItem(Long id){
        System.out.println("__________________________________________________________-----");
       findItemDescByItemId(id);
        return TaotaoResult.ok();
    }*/
    @RequestMapping("/item/query/item/desc/{id}")
    @ResponseBody
    public TaotaoResult findItemDescByItemId(@PathVariable Long id){
        TaotaoResult taotaoResult = itemService.queryItemDescByItemId(id);
        //System.out.println(taotaoResult);
        return taotaoResult;
    }

    @RequestMapping("/rest/item/update")
    @ResponseBody
    public TaotaoResult updateItem(TbItem tbItem,String desc){

        TaotaoResult taotaoResult = itemService.updateItemAndItemDesc(tbItem, desc);
        System.out.println(taotaoResult.getStatus()+","+taotaoResult.getMsg());
        return taotaoResult;
    }
    /*@RequestMapping("/rest/item/param/item/query/{id}")
    @ResponseBody
    public TbItemDesc findItemDesc(@PathVariable Long id){
        System.out.println("/rest/item/param/item/query/---------------------------------------"+id);
        return null;
    }*/
}
