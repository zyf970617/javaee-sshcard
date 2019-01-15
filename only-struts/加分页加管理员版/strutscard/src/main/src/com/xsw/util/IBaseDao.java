package com.xsw.util;

import java.util.List;

/**
 * @author 徐森威
 * @date 2018/4/17
 */
public interface IBaseDao<T> {

    public int insert(T o);

    public int findSum(String flag);

//    public int insertList(List<T> list);
//
    public int update(T o);
//
//    public int deleteList(int...ids);

    public int delete(int id);

//    public int delete(T o);
//
//    public T findById(int id);

    public T find(String key);

    public List<T> findAll();

    public List<T> findByPage(String  flag, int start, int len);

//    public List<T> findByCondition(String condition);

}
