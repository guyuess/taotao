package com.guyue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guyue.mapper.TbItemMapper;
import com.guyue.pojo.TbItem;
import com.guyue.service.ItemService;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;

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
    public void updateItemByIdOrStatusUndercarriage(long id) {
        itemMapper.updateItemByIdOrStatusUndercarriage(id);
    }

    @Override
    public void updateItemByIdOrStatusGrounding(long id) {
        itemMapper.updateItemByIdOrStatusGrounding(id);
    }

    @Override
    public TbItem updateItemByIdOrUndercarriage(List<Long> ids) {
        return itemMapper.updateItemByIdOrUndercarriage(ids);
    }
}
