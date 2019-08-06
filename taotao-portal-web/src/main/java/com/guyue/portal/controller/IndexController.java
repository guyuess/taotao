package com.guyue.portal.controller;

import com.guyue.content.service.ContentService;
import com.guyue.pojo.TbContent;
import com.guyue.portal.pojo.Ad1Node;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ContentService contentService;

    @Value("${AD1_CID}")
    private Long AD1_CID;
    @Value("${AD1_HEIGHT}")
    private Integer AD1_HEIGHT;
    @Value("${AD1_WIDTH}")
    private Integer AD1_WIDTH;
    @Value("${AD1_HEIGHT_B}")
    private Integer AD1_HEIGHT_B;
    @Value("${AD1_WIDTH_B}")
    private Integer AD1_WIDTH_B;


    @RequestMapping("/")
    public String show(Model model){
        List<TbContent> result = contentService.getContentAll(AD1_CID);
        List<Ad1Node> ad1List = new ArrayList<>();
        for (TbContent content:result) {
            Ad1Node node = new Ad1Node();
            node.setAlt(content.getTitle());
            node.setHref(content.getUrl());
            node.setSrc(content.getPic());
            node.setSrcB(content.getPic2());
            node.setHeight(""+AD1_HEIGHT);
            node.setHeightB(""+AD1_HEIGHT_B);
            node.setWidth(""+AD1_WIDTH);
            node.setWidthB(""+AD1_WIDTH_B);
            //添加到列表
            ad1List.add(node);
        }
        model.addAttribute("ad1", JsonUtils.objectToJson(ad1List));
        return "index";
    }
}
