package com.edu.action;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.edu.services.IDbToExcelService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class UploadAction extends ActionSupport{
	
	//依赖注入
	@Resource
	private IDbToExcelService dbToExcelService;

	//上传的文件
	private File file;

	//上传的文件类型
    private String fileContentType;

    //上传的文件名
    private String fileFileName;

    //将上传的Excel转化为数据表中的数据
    public String upload() throws Exception {
        ServletContext servletContext = ServletActionContext.getServletContext();
        String dir = System.getProperty("user.dir")+File.separator+"Upload";
        File saveFile = new File(dir,fileFileName);
        FileUtils.copyFile(file,saveFile);
        String fieldList = "(name,sex,department,mobile,phone,email,address,addby)";
        dbToExcelService.excelToDb(dir+File.separator+fileFileName,"card",fieldList,8);
        return "success";

    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return this.fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return this.fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }
	
}
