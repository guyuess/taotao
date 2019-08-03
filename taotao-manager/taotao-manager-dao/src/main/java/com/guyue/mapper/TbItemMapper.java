package com.guyue.mapper;

import com.guyue.pojo.TbItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TbItemMapper {
    @Select("select * from tbitem where id = #{itemId}")
    TbItem queryItemById(Long itemId);

    @Select("select * from tbitem")
    List<TbItem> queryItemList();
    //
    @Delete("<script> delete from tbitem where id in <foreach collection='list' open='(' item='id' separator=',' close=')'> #{id}</foreach></script>")
    int deleteItemById(List<Long> ids);

    @Update("<script>update tbitem set status = 2 where id in <foreach collection='list' open='(' item='id' separator=',' close=')'> #{id}</foreach></script>")
    int updateItemByIdOrStatusUndercarriage(List<Long> ids);

    @Update("<script>update tbitem set status = 1 where id in <foreach collection='list' open='(' item='id' separator=',' close=')'> #{id}</foreach></script>")
    int updateItemByIdOrStatusGrounding(List<Long> ids);

    @Insert("insert into tbitem(id, title, sellPoint, price, num, barcode, image, cid, created, updated) VALUE (#{id},#{title},#{sellPoint},#{price},#{num},#{barcode},#{image},#{cid},#{created},#{updated})")
    int addItem(TbItem tbItem);
    @Select("select * from tbitem where id = #{id}")
    TbItem updateByQueryByItemId(Long id);
    @Update("<script>update tbitem <set>" +
            "<if test='title != null'>title = #{title},</if>" +
            "<if test='sellPoint != null'>sellPoint = #{sellPoint},</if>" +
            "<if test='price != null'>price = #{price},</if>" +
            "<if test='num != null'>num = #{num},</if>" +
            "<if test='barcode != null'>barcode = #{barcode},</if>" +
            "<if test='image != null'>image = #{image},</if>" +
            "<if test='cid != null'>cid = #{cid},</if>" +
            "<if test='status != null'>status = #{status},</if>" +
            "<if test='created != null'>created = #{created},</if>" +
            "<if test='updated != null'>updated = #{updated},</if>" +
            "</set> where id = #{id}</script>")
    int updateItem(TbItem tbItem);
}