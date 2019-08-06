package com.guyue.content.service.impl;

import com.guyue.content.service.ContentCategoryService;
import com.guyue.mapper.TbContentCategoryMapper;
import com.guyue.pojo.TbContentCategory;
import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;
    @Override
    public List<EasyUITreeNode> getContentCategoryList(Long parentId) {
        List<TbContentCategory> contentCategorys = contentCategoryMapper.findContentCatgoryAll(parentId);
        List<EasyUITreeNode> nodes = new ArrayList<EasyUITreeNode>();
        for (TbContentCategory contentCategory:contentCategorys) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(contentCategory.getId());
            node.setText(contentCategory.getName());
            node.setState(contentCategory.getIsParent()?"closed":"open");
            nodes.add(node);
        }
        return nodes;
    }

    @Override
    public TaotaoResult addCategoryService(Long parentId, String name) {
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        contentCategory.setSortOrder(1);
        contentCategory.setStatus(1);
        Date date = new Date();
        contentCategory.setUpdated(date);
        contentCategory.setCreated(date);
        int i = contentCategoryMapper.addContentCategory(contentCategory);
        TbContentCategory tbContentCategory = contentCategoryMapper.findContentCatgoryByParentId(parentId);
        if(!tbContentCategory.getIsParent()){
            tbContentCategory.setIsParent(true);
            contentCategoryMapper.updateContentCategoryByParentId(tbContentCategory);
        }
        if(i != 0){
            return TaotaoResult.ok(contentCategory);
        }
        return TaotaoResult.build(500,"添加失败");
    }

    @Override
    public TaotaoResult updateCategoryService(Long id, String name) {
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setId(id);
        contentCategory.setName(name);
        contentCategory.setStatus(1);
        Date date = new Date();
        contentCategory.setUpdated(date);
        System.out.println(id+"=================================="+name);
        int i = contentCategoryMapper.updateContentCategoryById(contentCategory);
        System.out.println(i+"======================"+contentCategory);
        if(i != 0){
            return TaotaoResult.ok();
        }
        return TaotaoResult.build(500,"修改失败");
    }

    @Override
    public TaotaoResult deleteCategoryService(Long id) {
        List<TbContentCategory> contents = contentCategoryMapper.getContentCatgoryByParentId(id);
        if(contents.size() != 0){
            for (TbContentCategory c:contents) {
                deleteCategoryService(c.getId());
            }
        }
        contentCategoryMapper.deleteContCategoryById(id);
        return TaotaoResult.ok();
    }
}
