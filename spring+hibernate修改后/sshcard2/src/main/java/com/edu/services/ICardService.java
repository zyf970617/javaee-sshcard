package com.edu.services;

import java.util.List;

import com.edu.entity.Card;

public interface ICardService {

	/**
	 * 根据名片id查找名片数据
	 * @param id 名片id
	 * @return
	 */
	public abstract Card findCardById(int id);
	
	/**
	 * 插入新的名片
	 * @param card 名片实体
	 * @return 返回新插入名片的id
	 */
	public abstract int save(Card card);
	
	/**
	 * 分页查找名片数据
	 * @param flag 名片类型
	 * @param page 页码
	 * @param size 煤业的数据
	 * @return 查询的结果集
	 */
	public abstract List<Card> findCardByPage(String name, String flag, int page, int size);
	
	/**
	 * 批量删除
	 * @param ids 需要删除的id
	 * @return 删除成功的数量
	 */
	public abstract int deleteBatchCard(int...ids);
	
	/**
	 * 根据名片的id删除名片
	 * @param id 名片id
	 * @return 删除的结果
	 */
	public abstract int deleteCard(int id);
	
	/**
	 * 将名片移动到/移出回收站
	 * @param flag 移动的标志
	 * @param id 需要移动的名片的id
	 * @return 
	 */
	public abstract int moveBatchCard(String flag,int id);
	
	/**
	 * 查询到的指定条件的名片的数量
	 * @param flag 名片的类型
	 * @return
	 */
	public abstract int findCardByFlag(String name, String flag);
	
	/**
	 * 模糊查询名片的数量
	 * @param flag 名片类型
	 * @param condition 查询关键字
	 * @return
	 */
	public abstract int findCardByConditionAndFlag(String name, String flag, String condition);
	
	/**
	 * 更新名片
	 * @param card 需要更新的名片实体
	 * @return
	 */
	public abstract int updateCard(Card card);
	
	/**
	 * 模糊搜索名片
	 * @param flag 名片的类型：回收站/名片管理
	 * @param condition 搜素的关键词
	 * @param page 开始页码
	 * @param size 每页的数量
	 * @return
	 */
	public abstract List<Card> findCardByCondition(String name,String flag, String condition, int page, int size);
	
	
}
