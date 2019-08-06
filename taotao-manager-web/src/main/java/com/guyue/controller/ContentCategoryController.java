package com.guyue.controller;

import com.guyue.content.service.ContentCategoryService;
import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;
    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(value="id", defaultValue="0") Long parentId){
        List<EasyUITreeNode> nodes = contentCategoryService.getContentCategoryList(parentId);
        return nodes;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createCategory(Long parentId, String name){
        TaotaoResult taotaoResulr = contentCategoryService.addCategoryService(parentId, name);
        return taotaoResulr;
    }
    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult updateCategory(Long id, String name){
        TaotaoResult taotaoResulr = contentCategoryService.updateCategoryService(id, name);
        System.out.println(id+"----------------------"+name);
        return taotaoResulr;
    }
    @RequestMapping("/delete/")
    @ResponseBody
    public TaotaoResult updateCategory(Long id, Long parentId){
        System.out.println(id+"----------------------"+parentId);
        TaotaoResult taotaoResulr = contentCategoryService.deleteCategoryService(id);
        return taotaoResulr;
    }
}
