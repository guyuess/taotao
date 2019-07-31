package com.guyue.service.impl;

import com.guyue.mapper.TbItemMapper;
import com.guyue.pojo.TbItem;
import com.guyue.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;
    @Override
    public TbItem getItemById(Long itemId) {
        return itemMapper.queryItemById(itemId);
    }
}
