package com.edu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edu.entity.Card;
import com.edu.entity.User;

@Repository
public class CardDao extends DaoHibernate<Card>{
		
	/**
	 * 统一注入Class类型
	 */
	public CardDao() {
		super.setT(new Card());
	}
	
	/**
	 * 增加新的名片
	 * @param card
	 * @return
	 */
	public int saveCard(Card card) {
		return this.insert(card);
	}
	
	/**
	 * 根据名片id找名片
	 * @param id 名片id
	 * @return
	 */
	public Card findCardById(int id) {
		return this.findById(id);
	}
	
	/**
	 * 更新名片信息
	 * @param card 需要更新的名片实体
	 * @return
	 */
	public int updateCard(Card card) {
		return this.update(card);
	}
	
	/**
	 * 根据名片id删除名片
	 * @param id 名片的id
	 * @return
	 */
	public int deleteCard(int id) {
		return this.delete(id);
	}
	
	/**
	 * 根据id集合批量删除名片
	 * @param ids id的集合
	 * @return
	 */
	public int deleteCardList(int...ids) {
		return this.deleteList(ids);
	}
	
	/**
	 * 分页查找名片
	 * @param flag 名片的类型：名片管理/回收站
	 * @param page 页码
	 * @param size 每页的数量
	 * @return
	 */
	public List<Card> findCardByPage(String name, String flag, int page, int size) {
		String sql = "select * from card where flag = ? and addby like ? ";
		return this.findPage(sql, new String[]{flag,name}, page, size);
	}
	
	/**
	 * 取得查找名片管理的总数
	 * @param flag 名片类型：名片管理/回收站
	 * @return
	 */
	public int findCardNumber(String name, String flag) {
		String sql = "select * from card where flag = ? and addby like ? ";
		return this.find(sql, new String[]{flag,name}).size();
	}
	
	/**
	 * 取得模糊查询的名片数量
	 * @param flag 名片类型：名片管理系统/回收站
	 * @param condition 模糊查询的关键词
	 * @return
	 */
	public int findConditionNumber(String name, String flag, String condition) {
		String sql = "select * from card where flag = ? and addby like ? ";
		List<Card> li =  this.findByFields(sql, new String[]{"name","department","address"},new String[]{flag, name}, condition, 0, 10);
		return li.size();
	}
	
	/**
	 * 模糊查找名片数据
	 * @param flag 名片类型
	 * @param condition 模糊查询的关键词
	 * @param page 页码
	 * @param size 每页的数量
	 * @return
	 */
	public List<Card> findConditionByPage(String name, String flag, String condition, int page, int size) {
		String sql = "select * from card where flag = ? and addby like ? ";
		return this.findByFields(sql, new String[]{"name","department","address"},new String[]{flag,name}, condition, page, size);
	}
	
	/**
	 * 执行自定义SQL语句
	 * @param sql
	 * @param params
	 * @return
	 */
	public int exeSql(String sql, String[] params) {
		return this.ExeSql(sql, params);
	}
	
	/**
	 * 移动名片
	 * @param id 名片的id
	 * @param flag 移动的类型
	 * @return
	 */
	public int moveBatchCard(int id, String flag) {
		String sql = "update card set flag = ? where id = ?";
		return this.ExeSql(sql, new String[]{flag,String.valueOf(id)});
	}
}
