package com.edu.services;

public interface IDbToExcelService {
	
	/**
	 * 将Excel中的数据导入到数据库中
	 * @param excelpath excel所在的路径
	 * @param table 数据表名
	 * @param fieldList 字段
	 * @param columnCount 一共有多少列
	 */
	public abstract void excelToDb(String excelpath, String table, String fieldList, int columnCount);
	
	/**
	 * 将数据表导出到Excel
	 * @param table 数据表名
	 * @param fieldList 字段
	 * @param titles 标题
	 * @param condition 条件
	 * @param order 顺序
	 * @param file 文件路径
	 */
	public abstract void dbToExcel(String table, String[] fieldList, String[] titles, String condition, String order, String file);

}
