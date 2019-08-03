package com.guyue.mapper;

import com.guyue.pojo.TbItem;
import com.guyue.pojo.TbItemDesc;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TbItemDescMapper {
    @Insert("insert into tbitemdesc(itemId, itemDesc, created, updated) value (#{itemId},#{itemDesc},#{created},#{updated})")
    int addItemDesc(TbItemDesc tbItemDesc);
    @Select("select  itemDesc from tbitemdesc where itemId = #{id}")
    TbItemDesc queryItemDescByItemId(Long id);

    @Update("<script>update tbitemdesc <set>" +
            "<if test='itemDesc != null'>itemDesc = #{itemDesc},</if>" +
            "<if test='created != null'>created = #{created},</if>" +
            "<if test='updated != null'>updated = #{updated},</if>" +
            "</set> where itemId = #{itemId}</script>")
    int updateItemDesc(TbItemDesc itemDesc);
}