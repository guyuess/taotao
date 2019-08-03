package com.guyue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guyue.mapper.TbItemCatMapper;
import com.guyue.mapper.TbItemDescMapper;
import com.guyue.mapper.TbItemMapper;
import com.guyue.pojo.TbItem;
import com.guyue.pojo.TbItemCat;
import com.guyue.pojo.TbItemDesc;
import com.guyue.service.ItemService;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.pojo.UpdateItem;
import com.taotao.common.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Override
    public TbItem getItemById(Long itemId) {
        return itemMapper.queryItemById(itemId);
    }

    @Override
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        //设置分页信息
        PageHelper.startPage(page, rows);
        //获取每页显示数据
        List<TbItem> items = itemMapper.queryItemList();
        //取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(items);
        //创建返回结果对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(items);
        return result;
    }

    @Override
    public TaotaoResult deleteItemById(List<Long> ids) {
        int i = itemMapper.deleteItemById(ids);
        if(i != 0){
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult updateItemByIdOrStatusUndercarriage(List<Long> ids) {
        int i = itemMapper.updateItemByIdOrStatusUndercarriage(ids);
        if(i != 0){
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult updateItemByIdOrStatusGrounding(List<Long> ids) {
        int i = itemMapper.updateItemByIdOrStatusGrounding(ids);
        if(i != 0){
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult addItem(TbItem tbItem, String desc) {
        Long id = IDUtils.genItemId();
        tbItem.setId(id);
        tbItem.setStatus((byte) 1);
        Date tiem = new Date();
        tbItem.setCreated(tiem);
        tbItem.setUpdated(tiem);
        int tbItemCount = itemMapper.addItem(tbItem);
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setItemId(id);
        tbItemDesc.setCreated(tiem);
        tbItemDesc.setUpdated(tiem);
        int tbItemDescCount = itemDescMapper.addItemDesc(tbItemDesc);
        if(tbItemCount != 0 && tbItemDescCount != 0){
            return TaotaoResult.ok();
        }else {
            return TaotaoResult.build(500,"商品上传失败，请重新上传！");
        }
    }

    @Override
    public TaotaoResult queryItemDescByItemId(Long itemId) {
        TaotaoResult taotaoResult = new TaotaoResult();
        TbItemDesc itemDesc = itemDescMapper.queryItemDescByItemId(itemId);
        taotaoResult.setStatus(200);
        taotaoResult.setData(itemDesc.getItemDesc());
        System.out.println(taotaoResult);
        return taotaoResult;
    }

    @Override
    public TaotaoResult updateItemAndItemDesc(TbItem tbItem, String desc) {
        tbItem.setStatus((byte) 1);
        Date tiem = new Date();
        //TbItem item = itemMapper.queryItemById(tbItem.getId());
        //tbItem.setCreated(item.getCreated());
        tbItem.setUpdated(tiem);
        int itemCount = itemMapper.updateItem(tbItem);

        //TbItemDesc tbItemDesc = itemDescMapper.queryItemDescByItemId(tbItem.getId());
        //TbItemDesc itemDesc = itemDescMapper.queryItemDescByItemId(tbItem.getId());
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(tbItem.getId());
        itemDesc.setItemDesc(desc);
        //tbItemDesc.setCreated(tiem);
        itemDesc.setUpdated(tiem);
        int itemDescCount = itemDescMapper.updateItemDesc(itemDesc);
        //System.out.println(itemDesc);
        System.out.println(itemCount+","+itemDescCount);
        if(itemCount != 0 && itemDescCount != 0){
            return TaotaoResult.ok();
        }else {
            return TaotaoResult.build(500,"商品修改失败");
        }
    }
    /*@Override
    public UpdateItem updateItemQueryById(Long id) {
        UpdateItem updateItem = new UpdateItem();
        TbItemDesc tbItemDesc = itemDescMapper.queryItemDescByItemId(id);
        updateItem.setStatus("200");
        updateItem.setItemDesc(tbItemDesc.getItemDesc());
        return updateItem;
        //http://192.168.65.128/images/2019/08/02/1564729372932235.jpg
        //http://image.taotao.com/jd/5a45e88aeca046ec88d7b7ffbc47092a.jpg'
    }*/


}
