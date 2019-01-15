package com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="theme")
public class Theme {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="info")
	private String info;
	
	@Column(name="user")
	private String user;
	
	@Column(name="thump")
	private int thump;
	
	@Column(name="time")
	private Date time;

	public Theme(String title, String info, String user, int thump, Date time) {
		super();
		this.title = title;
		this.info = info;
		this.user = user;
		this.thump = thump;
		this.time = time;
	}

	public Theme() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getThump() {
		return thump;
	}

	public void setThump(int thump) {
		this.thump = thump;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Theme [id=" + id + ", title=" + title + ", info=" + info
				+ ", user=" + user + ", thump=" + thump + ", time=" + time
				+ "]";
	}
	
}
