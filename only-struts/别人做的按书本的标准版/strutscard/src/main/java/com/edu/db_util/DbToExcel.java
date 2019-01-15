package com.edu.db_util;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class DbToExcel {

	public static void excelToDb(String excelpath, String table, String fieldList, int columnCount) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		Workbook workbook = null;
		Sheet sheet = null;
		conn = JdbcPoolUtils.getConnection();
		String sql = "insert into " + table + " " + fieldList + " values(";
		for (int i = 1; i < columnCount; i++) {
			sql += "?,";
		}
		sql += "?)";
		ps = conn.prepareStatement(sql);
		workbook = Workbook.getWorkbook(new File(excelpath));
		sheet = workbook.getSheet(0);
		int r = sheet.getRows();
		for (int i = 1; i < r; i++) {
			for (int j = 0; j < columnCount; j++) {
				ps.setString(j + 1, sheet.getCell(j, i).getContents());
			}
			ps.addBatch();
		}
		ps.executeBatch();
		workbook.close();
		JdbcPoolUtils.close(null, ps, conn);
	}

	public static void dBToExcel(String table, String[] fieldList, String[] titles, String condition, String order,
			String file) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		WritableWorkbook wwb = null;
		WritableSheet ws = null;
		conn = JdbcPoolUtils.getConnection();
		String flist = "";
		int fl = fieldList.length;
		for (int i = 0; i < fl - 1; i++) {
			flist += fieldList[i] + ",";
		}
		flist += fieldList[fieldList.length - 1];
		String sql = "select " + flist + " from " + table + "  where 1=1 ";
		if (condition != null && !condition.equals("")) {
			sql = sql + " and " + condition;
		}
		if (order != null && !order.equals("")) {
			sql = sql + "order by " + order;
		}
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		wwb = Workbook.createWorkbook(new File(file));
		ws = wwb.createSheet("sheet1", 0);
		for (int i = 0; i < fl; i++) {
			ws.addCell(new Label(i, 0, titles[i]));
		}
		int count = 1;
		while (rs.next()) {
			for (int j = 0; j < fl; j++) {
				ws.addCell(new Label(j, count, rs.getString(j + 1)));
			}
			count++;
		}
		wwb.write();
		if (wwb != null) {
			wwb.close();
		}
		JdbcPoolUtils.close(null, ps, conn);
	}
}