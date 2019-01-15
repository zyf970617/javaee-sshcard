package com.xsw.entity;

/**
 * @auther 徐森威
 * @date 2018/4/17
 */
public class Card {

    private Integer id;

    private String name;

    private String sex;

    private String department;

    private String mobile;

    private String phone;

    private String email;

    private String address;

    private String flag;

    public Card() {
    }

    public Card(String name, String sex, String department, String mobile, String phone, String email, String address, String flag) {
        this.name = name;
        this.sex = sex;
        this.department = department;
        this.mobile = mobile;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.flag = flag;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", department='" + department + '\'' +
                ", mobile='" + mobile + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
