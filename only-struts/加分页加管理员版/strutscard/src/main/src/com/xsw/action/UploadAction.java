package com.xsw.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Scope;
import com.opensymphony.xwork2.inject.Scoped;
import com.xsw.util.DbToExcel;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * @author 徐森威
 * @date 2018/4/18
 */
@ParentPackage("struts-default")
@Scoped(Scope.REQUEST)
@Namespace(value = "/upload")
public class UploadAction extends ActionSupport{

    private File file;

    private String fileContentType;

    private String fileFileName;

    @Action(
            value = "cardExcel",
            results = {
                    @Result(name = "success",location = "../cards/careList?start=1",type = "redirect")
            }
    )
    public String upload() throws Exception {
        ServletContext servletContext = ServletActionContext.getServletContext();
        String dir = System.getProperty("user.dir")+File.separator+"Upload";
        File saveFile = new File(dir,fileFileName);
        FileUtils.copyFile(file,saveFile);
        String fieldList = "(name,sex,department,mobile,phone,email,address)";
        DbToExcel.excelToDb(dir+"/"+fileFileName,"card",fieldList,7);
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
