package com.guyue.mapper;

import com.guyue.pojo.TbItem;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbItemMapper {
    @Select("select * from tbitem where id = #{itemId}")
    TbItem queryItemById(Long itemId);
}