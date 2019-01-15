package com.dao;

import java.util.List;

import com.entity.Theme;

public class ThemeDao {
	
	private DaoHibernate<Theme> dao;
	
	public ThemeDao() {
		dao = new DaoHibernate<Theme>(new Theme());
	}
	
	public int save(Theme theme) {
		return dao.insert(theme);
	}
	
	public int delete(int id) {
		return dao.delete(id);
	}
	
	public int update(Theme theme) {
		return dao.update(theme);
	}
	
	public List<Theme> findThemeByPage(int page, int size) {
		String sql = "select * from theme";
		return dao.findPage(sql, null, page, size);
	}
	
	public Theme findByUserName(String name) {
		String sql = "select * from theme where user = ?";
		return dao.findOne(sql, new String[]{name});
	}
	
	public List<Theme> findByLikeUser(String name) {
		String sql = "select * from theme";
		return dao.findByFields(sql, new String[]{"user","info"}, name);
	}
}
