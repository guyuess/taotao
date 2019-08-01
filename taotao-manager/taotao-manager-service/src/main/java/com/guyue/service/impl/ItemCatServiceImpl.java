package com.guyue.service.impl;

import com.guyue.mapper.TbItemCatMapper;
import com.guyue.pojo.TbItemCat;
import com.guyue.service.ItemCatService;
import com.taotao.common.pojo.EasyUITreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Override
    public List<EasyUITreeNode> queryItemCatByParentId(Long id) {
        List<TbItemCat> itemCats = itemCatMapper.queryItemCatByParentId(id);
        List<EasyUITreeNode> result = new ArrayList<EasyUITreeNode>();
        for (TbItemCat itemCat:itemCats) {
            EasyUITreeNode node = new EasyUITreeNode();
            //绑定分类id
            node.setId(itemCat.getId());
            //绑定分类名称
            node.setText(itemCat.getName());
            //绑定分类对象的状态，通过它是否有次子点，来判断
            node.setState(itemCat.getIsParent()?"closed":"open");
            result.add(node);
        }
        return result;
    }
}
