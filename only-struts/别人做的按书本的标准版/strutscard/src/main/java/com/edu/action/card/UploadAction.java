package com.edu.action.card;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import com.edu.db_util.DbToExcel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.*;

@Namespace("/card")
@Scoped(Scope.REQUEST)
@ParentPackage("struts-default")
public class UploadAction extends ActionSupport{
private File file;
private String fileContentType;
private String fileFileName;

public File getFile() {
	return file;
}

public void setFile(File file) {
	this.file = file;
}

public String getFileContentType() {
	return fileContentType;
}

public void setFileContentType(String fileContentType) {
	this.fileContentType = fileContentType;
}

public String getFileFileName() {
	return fileFileName;
}

public void setFileFileName(String fileFileName) {
	this.fileFileName = fileFileName;
}

@Action(
     value="upload",
     results={@Result(name="success",location="/find",type="redirectAction")})
public String upload()throws Exception{
ServletContext servletContext=ServletActionContext.getServletContext();
String dir=servletContext.getRealPath("/upload");
File saveFile=new File(dir,fileFileName);
FileUtils.copyFile(file,saveFile);
String fieldList="(name,sex,department,mobile,phone,email,address,flag)";
DbToExcel.excelToDb(dir+"/"+fileFileName,"card",fieldList,8);
return "success";
}}
