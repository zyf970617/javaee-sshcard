package com.edu.services.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.dao.CardDao;
import com.edu.entity.Card;
import com.edu.services.IDbToExcelService;

@Service
@Transactional
public class DbToExcelService implements IDbToExcelService{
	
	@Resource
	private CardDao cardDao;

	@Override
	public void excelToDb(String excelpath, String table, String fieldList,
			int columnCount) {
			try {
		        Workbook workbook = null;
		        Sheet sheet = null;
		        String sql = "insert into "+table+" "+fieldList+" values(";
		        for (int i=1; i<columnCount; i++) {
		            sql += "?,";
		        }
		        sql += "?)";
		        workbook = Workbook.getWorkbook(new File(excelpath));
		        sheet = workbook.getSheet(0);
		        int r = sheet.getRows();
		        for (int i=1; i<r; i++) {
		        	List<String> li = new ArrayList<String>();
		            for (int j=1; j<=columnCount; j++) {
		            	li.add(sheet.getCell(j,i).getContents());
		            }
		            String[] para = li.toArray(new String[li.size()]);
		            cardDao.exeSql(sql, para);
		        }
		        workbook.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	@Override
	public void dbToExcel(String table, String[] fieldList, String[] titles,
			String condition, String order, String file) {
		try {
			WritableWorkbook wwb = null;
	        WritableSheet ws = null;
	        String flist = "";
	        int fl = fieldList.length;
	        for (int i=0; i<fl-1; i++) {
	            flist += fieldList[i] + ",";
	        }
	        flist += fieldList[fieldList.length-1];
	        String sql = "select "+flist+" from "+table+" where 1=1";
	        if (condition!=null&&!condition.equals("")) {
	            sql = sql + " and " + condition;
	        }
	        if (order!=null&&!order.equals("")) {
	            sql = sql+ " order by "+order;
	        }
	        wwb = Workbook.createWorkbook(new File(file));
	        ws = wwb.createSheet("sheet1",0);
	        for (int i=0; i<fl; i++) {
	            ws.addCell(new Label(i,0,titles[i]));
	        }
	        List<Card> li = cardDao.find(sql, null);
	        int count = 1;
	        for (int i=0; i<li.size(); i++) {
	        	ws.addCell(new Label(0,count,li.get(i).getId().toString()));
	        	ws.addCell(new Label(1,count,li.get(i).getName()));
	        	ws.addCell(new Label(2,count,li.get(i).getSex()));
	        	ws.addCell(new Label(3,count,li.get(i).getDepartment()));
	        	ws.addCell(new Label(4,count,li.get(i).getMobile()));
	        	ws.addCell(new Label(5,count,li.get(i).getPhone()));
	        	ws.addCell(new Label(6,count,li.get(i).getEmail()));
	        	ws.addCell(new Label(7,count,li.get(i).getAddress()));
	        	ws.addCell(new Label(8,count,li.get(i).getAddby()));
	        	ws.addCell(new Label(9,count,li.get(i).getFlag()));
	        	count++;
	        }
	        wwb.write();
	        if (wwb!=null) {
	            wwb.close();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
