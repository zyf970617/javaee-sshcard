package com.test;

import java.util.List;

import com.dao.ThemeDao;
import com.entity.Theme;

public class Test {

	public static void main(String[] args) {
		System.out.println("开始执行===");
		ThemeDao themeDao = new ThemeDao();
		/*Theme theme = new Theme("这是标题2","这是内容2","徐森威2",3,new Date());
		themeDao.save(theme);*/
		
		//List<Theme> li = themeDao.findThemeByPage(1, 5);
		List<Theme> li = themeDao.findByLikeUser("内容");
		for (int i=0; i<li.size(); i++) {
			System.out.println(li.get(i).toString());
		}
		
		/*Theme theme = themeDao.findByUserName("徐森威");
		System.out.println(theme.toString());*/
		System.out.println("执行结束===");
	}

}
