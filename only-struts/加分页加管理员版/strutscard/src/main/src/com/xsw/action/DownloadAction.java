package com.xsw.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Scope;
import com.opensymphony.xwork2.inject.Scoped;
import com.xsw.util.DbToExcel;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author 徐森威
 * @date 2018/4/18
 */
@ParentPackage("struts-default")
@Scoped(Scope.REQUEST)
@Namespace(value = "/download")
public class DownloadAction extends ActionSupport {

    private String contentType;

    private long contentLength;

    private String contentDisposition;

    private InputStream inputStream;

    private String fileName = "userCards.xls";

    @Action(
            value = "/cardExcel",
            results = {
                    @Result(name = "success",type = "stream")
            }
    )
    public String execute() throws Exception{
        String[] fieldList = {"id","name","sex","department","mobile","phone","email","address","flag"};
        String[] title = {"序号","姓名","性别","单位","手机","电话","电子邮箱","地址","备注"};
        String file = java.net.URLEncoder.encode("userCards.xls","UTF-8");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String condition = (String)session.getAttribute("condition");
        String order = (String)session.getAttribute("order");
        String sql = "";
        if (condition!=null&&!condition.equals("")) {
            sql = sql + "(name like '%" + condition + "%'";
            sql = sql + "or sex like '%" + condition + "%'";
            sql = sql + "or department like '%" + condition + "%'";
            sql = sql + "or mobile like '%" + condition + "%'";
            sql = sql + "or phone like '%" + condition + "%'";
            sql = sql + "or address like '%" + condition + "%'";
            sql = sql + "or email like '%" + condition + "%')";
        }
        contentType = "application/octet-stream";
        String name = java.net.URLEncoder.encode(fileName,"UTF-8");
        contentDisposition = "attachment;filename="+name;
        ServletContext servletContext = ServletActionContext.getServletContext();
        //String dir = System.getProperty("user.dir")+ File.separator+"Upload";
        String fileName2 = servletContext.getRealPath("/download/"+file);
        File downloadfile = new File(fileName2);
        if (!downloadfile.exists()) {
            downloadfile.getParentFile().mkdir();
        }
        DbToExcel.dbToExcel("card",fieldList,title,sql,order,fileName2);
        inputStream = new FileInputStream(fileName2);
        contentLength = inputStream.available();
        return "success";
    }

    public String getContentType() {
        return contentType;
    }

    public long getContentLength() {
        return contentLength;
    }

    public String getContentDisposition() {
        return contentDisposition;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

}
