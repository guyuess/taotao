package com.guyue.service.impl;

import com.guyue.dao.StudentDao;
import com.guyue.pojo.Student;
import com.guyue.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao stuDao;
    @Override
    public Student findStudent(Integer id) {
        return stuDao.findStudent(id);
    }
}
