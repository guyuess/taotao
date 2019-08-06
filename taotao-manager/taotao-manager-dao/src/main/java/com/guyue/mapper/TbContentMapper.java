package com.guyue.mapper;


import com.guyue.pojo.TbContent;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TbContentMapper {
    @Select("select * from tbcontent where categoryId = #{categoryId}")
    List<TbContent> findContentAll(Long categoryId);
    @Insert("insert into tbcontent(categoryId, title, subTitle, titleDesc, url, pic, pic2, content, created, updated) " +
            "value (#{categoryId},#{title},#{subTitle},#{titleDesc},#{url},#{pic},#{pic2},#{content},#{created},#{updated})")
    int addContent(TbContent tbContent);
    @Update("<script>update tbcontent <set>" +
            "<if test='categoryId != null'>categoryId = #{categoryId},</if>" +
            "<if test='title != null'>title = #{title},</if>" +
            "<if test='subTitle != null'>subTitle = #{subTitle},</if>" +
            "<if test='titleDesc != null'>titleDesc = #{titleDesc},</if>" +
            "<if test='url != null'>url = #{url},</if>" +
            "<if test='pic != null'>pic = #{pic},</if>" +
            "<if test='pic2 != null'>pic2 = #{pic2},</if>" +
            "<if test='content != null'>content = #{content},</if>" +
            "<if test='created != null'>created = #{created},</if>" +
            "<if test='updated != null'>updated = #{updated},</if>" +
            "</set> where id = #{id}</script>")
    int updateContent(TbContent tbContent);

    @Delete("<script>delete from tbcontent where id in " +
            "<foreach collection='list' open='(' item='id' separator=',' close=')'>#{id}</foreach></script>")
    int deleteContent(List<Long> ids);
}