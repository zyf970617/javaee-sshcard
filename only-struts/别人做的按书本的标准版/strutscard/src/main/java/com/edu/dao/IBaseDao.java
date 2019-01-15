package com.edu.dao;
import java.util.*;

public interface IBaseDao<T> {
    public int insert(T t);
    public int insertList(List<T> list);
    public int update(T t);
    public int deleteList(int... ids);
    public int delete(T t);
    public int delete(int id);
    public T findById(int id);
    public T find(T t);
    public List<T> findAll();
    public List<T> findByCondition(String Condition);
}
