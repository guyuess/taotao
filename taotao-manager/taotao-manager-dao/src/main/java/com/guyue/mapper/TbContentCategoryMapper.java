package com.guyue.mapper;


import com.guyue.pojo.TbContentCategory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TbContentCategoryMapper {
    @Select("select * from tbcontentcategory where parentId = #{parentId}")
    List<TbContentCategory> findContentCatgoryAll(Long parentId);
    @Insert("insert into tbcontentcategory(parentId, name, status, sortOrder, isParent, created, updated) " +
            "value (#{parentId},#{name},#{status},#{sortOrder},#{isParent},#{created},#{updated})")
    int addContentCategory(TbContentCategory contentCategory);
    @Select("select * from  tbcontentcategory where id = #{id}")
    TbContentCategory findContentCatgoryByParentId(Long parentId);
    @Update("update tbcontentcategory set isParent = #{isParent} where id = #{id}")
    void updateContentCategoryByParentId(TbContentCategory tbContentCategory);
    @Update("<script>update tbcontentcategory <set>" +
            "<if test='parentId != null'>parentId = #{parentId},</if>" +
            "<if test='name != null'>name = #{name},</if>" +
            "<if test='status != null'>status = #{status},</if>" +
            "<if test='sortOrder != null'>sortOrder = #{sortOrder},</if>" +
            "<if test='isParent != null'>isParent = #{isParent},</if>" +
            "<if test='created != null'>created = #{created},</if>" +
            "<if test='updated != null'>updated = #{updated},</if>" +
            "</set> where id = #{id}</script>")
    int updateContentCategoryById(TbContentCategory contentCategory);
    @Delete("delete from tbcontentcategory where id = #{id}")
    int deleteContCategoryById(Long id);
    @Select("select * from tbcontentcategory where id = #{id}")
    TbContentCategory findContentCatgoryById(Long id);

    @Select("select * from  tbcontentcategory where parentId = #{id}")
    List<TbContentCategory> getContentCatgoryByParentId(Long id);

    @Delete("<script>delete from tbcontentcategory where id in <foreach collection='list' open='(' item='id' separator=',' close=')'>#{id}</foreach></script>")
    int deleteContCattgoryByIds(List<Long> ids);
}