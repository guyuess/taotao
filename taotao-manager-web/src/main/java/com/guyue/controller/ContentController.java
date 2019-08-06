package com.guyue.controller;

import com.guyue.content.service.ContentService;
import com.guyue.pojo.TbContent;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;
    @RequestMapping("/query/list")
    @ResponseBody
    public EasyUIDataGridResult showContentAll(Long categoryId){
        EasyUIDataGridResult result = contentService.findContentAll(categoryId);
        return result;
    }
    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult addContent(TbContent tbContent){
        TaotaoResult taotaoResult = contentService.addContent(tbContent);
        return taotaoResult;
    }
    @RequestMapping("/edit")
    @ResponseBody
    public TaotaoResult updateContent(TbContent tbContent){
        TaotaoResult taotaoResult = contentService.updateContent(tbContent);
        return taotaoResult;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContent(EasyUIDataGridResult result){
        List<Long> ids = result.getIds();
        TaotaoResult taotaoResult = contentService.deleteContent(ids);
        return taotaoResult;
    }
}
