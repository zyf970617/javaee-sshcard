package com.edu.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.edu.services.IDbToExcelService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class DownloadAction extends ActionSupport {
	
	//依赖注入
	@Resource
	private IDbToExcelService dbToExcelService;

	//内容类型
    private String contentType;
    
    //内容长度
    private long contentLength;

    private String contentDisposition;

    //输入流
    private InputStream inputStream;

    //输出文件名
    private String fileName = "userCards.xls";

    /**
     * 将数据表中的名片数据导出到excel中
     * 和书本上不同的地方是将DbToExcel包装成了DbToExcelService
     * 将原声的jdbcUtil改成了hibernate+spring注入
     */
    public String execute() throws Exception{
        String[] fieldList = {"id","name","sex","department","mobile","phone","email","address","addby","flag"};
        String[] title = {"序号","姓名","性别","单位","手机","电话","电子邮箱","地址","创建人","备注"};
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
        dbToExcelService.dbToExcel("card",fieldList,title,sql,order,fileName2);
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
