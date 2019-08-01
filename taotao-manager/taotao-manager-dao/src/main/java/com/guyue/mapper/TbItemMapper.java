package com.guyue.mapper;

import com.guyue.pojo.TbItem;
import org.apache.ibatis.annotations.Delete;
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

    @Update("update tbitem set status = 2 where id = #{id}")
    void updateItemByIdOrStatusUndercarriage(long id);

    @Update("update tbitem set status = 1 where id = #{id}")
    void updateItemByIdOrStatusGrounding(long id);

    @Update("update tbitem set status = 2 where id = #{id}")
    TbItem updateItemByIdOrUndercarriage(List<Long> ids);
}