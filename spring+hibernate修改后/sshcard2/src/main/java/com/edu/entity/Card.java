package com.edu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="card")
public class Card {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
    private Integer id;

	@Column(name="name")
    private String name;

	@Column(name="sex")
    private String sex;

	@Column(name="department")
    private String department;

	@Column(name="mobile")
    private String mobile;

	@Column(name="phone")
    private String phone;

	@Column(name="email")
    private String email;

	@Column(name="address")
    private String address;

	//是否被移到回收站
	@Column(name="flag")
    private String flag;
	
	//由谁创建的名片
	@Column(name="addby")
	private String addby;

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

    public String getAddby() {
		return addby;
	}

	public void setAddby(String addby) {
		this.addby = addby;
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
