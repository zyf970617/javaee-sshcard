package com.edu.action.card;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import com.edu.db_util.DbToExcel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.*;

@Namespace("/card")
@Scoped(Scope.REQUEST)
@ParentPackage("struts-default")
public class DownLoadAction extends ActionSupport {
    private String contentType;
    private long contentLength;
    private String contentDisposition;
    private InputStream inputStream;
    private String fileName="名片.xls";
    public String getContentType() {return contentType;}
    public long getContentLength() {return contentLength;}
    public String getContentDisposition() {return contentDisposition;}
    public InputStream getInputStream() {return inputStream;}
    @Action(
    		value="download",
    		results= {@Result(name="success",type="stream")})
    public String excuteDownLoad() throws Exception{
    	String[] fieldList= {"id","name","sex","department","mobile","phone","email","address","flag"};
    	String[] titles= {"序号","姓名","性别","单位","手机","电话","电子邮箱","地址","备注"};
    			String file="名片.xls";
    	        HttpSession session=ServletActionContext.getRequest().getSession();
    	        String condition=(String)session.getAttribute("condition");
    	        String order=(String)session.getAttribute("order");
    	        String sql="";
    	        if(condition!=null&&!condition.equals("")) {
    	        	sql=sql+"(name like '%"+condition+"%'";
    	        	sql=sql+"or sex like '%"+condition+"%'";
    	        	sql=sql+"or department like '%"+condition+"%'";
    	        	sql=sql+"or mobile like '%"+condition+"%'";
    	        	sql=sql+"or phone like '%"+condition+"%'";
    	        	sql=sql+"or email like '%"+condition+"%'";
    	        	sql=sql+"or address like '%"+condition+"%'";
    	        }
    	        contentType="application/vnd.ms-excel";
    	        String name=java.net.URLEncoder.encode(fileName,"UTF-8");
    	        contentDisposition="attachment;filename="+name;
    	        ServletContext servletContext=ServletActionContext.getServletContext();
    	        String fileName2=servletContext.getRealPath("/download/"+file);
    	        File downloadfile=new File(fileName2);
    	        if(!downloadfile.exists()) {downloadfile.getParentFile().mkdirs();}
    	        DbToExcel.dBToExcel("card",fieldList,titles,sql,order,fileName2);
    	        inputStream=new FileInputStream(fileName2);
    	        contentLength=inputStream.available();
    	        return SUCCESS;
    	
    }
}