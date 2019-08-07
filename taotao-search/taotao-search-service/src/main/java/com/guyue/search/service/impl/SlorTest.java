package com.guyue.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SlorTest {
    @Test
    public void addshow1() throws Exception{
        // 第一步：把solrJ的jar包添加到工程中。
        // 第二步：创建一个SolrServer，使用HttpSolrServer创建对象。来连接solr
        SolrServer solrServer = new HttpSolrServer("http://192.168.65.120:8080/solr");
        // 第三步：创建一个文档对象SolrInputDocument对象。
        SolrInputDocument doucment = new SolrInputDocument();
        // 第四步：向文档中添加域必须有id域，域的名称必须在schema.xml中定义。
        doucment.addField("id","test01");
        doucment.addField("item_title","测试手机");
        doucment.addField("item_price","499");
        // 第五步：把文档添加到索引库中。
        solrServer.add(doucment);
        // 第三步：提交。
        solrServer.commit();
    }
    @Test
    public void addshow2() throws Exception{
       SolrServer solrServer = new HttpSolrServer("http://192.168.65.120:8080/solr");
       SolrInputDocument document = new SolrInputDocument();
       document.addField("id","test02");
       document.addField("item_title","测试电脑");
       document.addField("item_price","399");
       solrServer.add(document);
       solrServer.commit();
    }
    @Test
    public void addshow3() throws Exception{
        SolrServer solrServer = new HttpSolrServer("http://192.168.65.120:8080/solr");
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id","test03");
        document.addField("item_title","测试电视");
        document.addField("item_price","299");
        solrServer.add(document);
        solrServer.commit();
    }

    @Test
    public void deleteshow() throws IOException, SolrServerException {
        // 第一步：创建一个SolrServer对象。
        SolrServer solrServer = new HttpSolrServer("http://192.168.65.120:8080/solr");
        // 第二步：调用SolrServer对象的根据id删除的方法。
        solrServer.deleteById("test01");
        // 第三步：提交。
        solrServer.commit();
    }
    @Test
    public void updateshow() throws IOException, SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://192.168.65.120:8080/solr");
        SolrInputDocument document = new SolrInputDocument();
        //id不变，其余内容改变，就等同于删除
        document.addField("id","test03");
        document.addField("item_title","测试投影仪");
        document.addField("item_price","288");
        solrServer.add(document);
        solrServer.commit();
    }
    @Test
    public void queryshow() throws SolrServerException {
        // 第一步：创建一个SolrServer对象
        SolrServer solrServer = new HttpSolrServer("http://192.168.65.120:8080/solr");
        // 第二步：创建一个SolrQuery对象。
        SolrQuery query = new SolrQuery();
        // 第三步：向SolrQuery中添加查询条件、过滤条件。。。
        query.setQuery("*:*");
        // 第四步：执行查询。得到一个Response对象。
        QueryResponse queryResponse = solrServer.query(query);
        // 第五步：取查询结果。
        SolrDocumentList results = queryResponse.getResults();
        System.out.println("查询结果的总记录数：" + results.getNumFound());
        // 第六步：遍历结果并打印。
        for (SolrDocument doc:results) {
            System.out.println(doc.get("id"));
            System.out.println(doc.get("item_title"));
            System.out.println(doc.get("item_price"));
        }
    }
    @Test
    public void queryshow2() throws SolrServerException {
        // 第一步：创建一个SolrServer对象
        SolrServer solrServer = new HttpSolrServer("http://192.168.65.120:8080/solr");
        // 第二步：创建一个SolrQuery对象。
        SolrQuery query = new SolrQuery();
        // 第三步：向SolrQuery中添加查询条件、过滤条件。。。
        query.setQuery("测试");
        //指定默认搜索域
        query.set("df","item_keywords");
        //开启高亮显示
        query.setHighlight(true);
        //高亮显示的域
        query.addHighlightField("item_title");
        //开始样式
        query.setHighlightSimplePre("<font style='color:red'>");
        //结束样式
        query.setHighlightSimplePost("<font>");
        //从0开始
        query.setStart(0);
        //显示5条数据
        query.setRows(5);
        //将价格单位降序排序展示
        query.setSort("item_price", SolrQuery.ORDER.desc);
        // 第四步：执行查询。得到一个Response对象。
        QueryResponse queryResponse = solrServer.query(query);
        // 第五步：取查询结果。
        SolrDocumentList results = queryResponse.getResults();
        System.out.println("查询结果的总记录数：" + results.getNumFound());
        // 第六步：遍历结果并打印。
        for (SolrDocument doc:results) {
            System.out.println(doc.get("id"));
            //取高亮显示
            Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
            List<String> list = highlighting.get(doc.get("id")).get("item_title");
            String title = null;
            if(list != null && list.size() > 0){
                title = list.get(0);
            }else{
                title = (String) doc.get("item_title");
            }
            System.out.println(title);
            System.out.println(doc.get("item_price"));
        }
    }
}
