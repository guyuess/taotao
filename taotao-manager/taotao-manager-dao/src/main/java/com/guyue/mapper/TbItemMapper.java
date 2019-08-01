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

    @Delete("delete from tbitem where id = #{id}")
    void deleteItemById(Long id);

    @Update("update tbitem set status = 2 where id = #{id}")
    void updateItemByIdOrStatusUndercarriage(long id);

    @Update("update tbitem set status = 1 where id = #{id}")
    void updateItemByIdOrStatusGrounding(long id);

    @Update("update tbitem set status = 2 where id = #{id}")
    TbItem updateItemByIdOrUndercarriage(List<Long> ids);
}