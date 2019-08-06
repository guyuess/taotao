package com.guyue.content.service.impl;

import com.guyue.content.jedis.JedisClient;
import com.guyue.content.jedis.JedisClientPool;
import com.guyue.content.service.ContentService;
import com.guyue.mapper.TbContentMapper;
import com.guyue.pojo.TbContent;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;
    @Override
    public EasyUIDataGridResult findContentAll(Long categoryId) {
        List<TbContent> contents = contentMapper.findContentAll(categoryId);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(contents.size());
        result.setRows(contents);
        return result;
    }

    @Override
    public TaotaoResult addContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        int i = contentMapper.addContent(tbContent);
        if(i != 0){
            jedisClient.het(CONTENT_KEY);
            return TaotaoResult.ok();
        }
        return TaotaoResult.build(500,"添加内容失败");
    }

    @Override
    public TaotaoResult updateContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setUpdated(date);
        int i = contentMapper.updateContent(tbContent);
        if(i != 0){
            jedisClient.het(CONTENT_KEY);
            return TaotaoResult.ok();
        }
        return TaotaoResult.build(500,"修改内容失败");
    }

    @Override
    public TaotaoResult deleteContent(List<Long> ids) {
        int i = contentMapper.deleteContent(ids);
        if(i != 0){
            //jedisClient.het(CONTENT_KEY);
            return TaotaoResult.ok();
        }
        return TaotaoResult.build(500,"删除内容失败");
    }

    @Override
    public List<TbContent> getContentAll(Long categoryId) {
       /*
        这里获取缓存
         */
        String json = jedisClient.get(CONTENT_KEY);
        //判断josn不为空
        if(StringUtils.isNotBlank(json)){
            List<TbContent> contents = JsonUtils.jsonToList(json, TbContent.class);
            System.out.println("从缓存中获取数据");
            return contents;
        }
        List<TbContent> contents = contentMapper.findContentAll(categoryId);

        /*
        加入缓存
         */
        jedisClient.set(CONTENT_KEY, JsonUtils.objectToJson(contents));
        System.out.println("从数据库获取数据");
        return contents;
    }
}
