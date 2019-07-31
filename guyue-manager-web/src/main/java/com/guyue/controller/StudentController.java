package com.guyue.controller;

import com.guyue.pojo.Student;
import com.guyue.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {
    @Autowired
    private StudentService stuService;
    @RequestMapping("/stu/{id}")
    @ResponseBody
    public Student showStudent(@PathVariable Integer id){
        Student stu = stuService.findStudent(id);
        return stu;
    }
}
