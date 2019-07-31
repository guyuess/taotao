package com.guyue.dao;

import com.guyue.pojo.Student;
import org.apache.ibatis.annotations.Select;

public interface StudentDao {
    @Select("select * from student where id = #{id}")
    Student findStudent(Integer id);
}
