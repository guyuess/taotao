package com.guyue.controller;

import com.guyue.service.ItemCatService;
import com.taotao.common.pojo.EasyUITreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(value="id", defaultValue="0")Long parentId){
        List<EasyUITreeNode> result = itemCatService.queryItemCatByParentId(parentId);
        return result;
    }
}
