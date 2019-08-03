package com.guyue.service.impl;

import com.guyue.mapper.TbItemDescMapper;
import com.guyue.pojo.TbItemDesc;
import com.guyue.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDescServiceImpl implements ItemDescService {
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Override
    public TbItemDesc queryItemDescByItemId(Long itemId) {
        return itemDescMapper.queryItemDescByItemId(itemId);
    }
}
