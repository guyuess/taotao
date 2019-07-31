package com.guyue.pojo;
/**
 * 学生类，
 * get，set方法快捷键Alt+Insert
 */
import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    private int id;
    private String name;
    private String sex;
    private Date birth;
    private String department;
    private String address;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Date getBirth() {
        return birth;
    }

    public String getDepartment() {
        return department;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birth=" + birth +
                ", department='" + department + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
