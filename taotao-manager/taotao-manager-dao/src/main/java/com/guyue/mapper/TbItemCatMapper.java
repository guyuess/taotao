package com.guyue.mapper;

import com.guyue.pojo.TbItemCat;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbItemCatMapper {
    @Select("select * from tbitemcat where parentId = #{id}")
    List<TbItemCat> queryItemCatByParentId(Long id);
    @Select("select * from tbitemcat where id = #{cid}")
    TbItemCat queryItemCatById(Long cid);
}