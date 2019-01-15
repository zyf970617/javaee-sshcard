package com.edu.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.edu.entity.Card;
import com.edu.entity.User;
import com.edu.services.ICardService;
import com.edu.services.IUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class CardAction extends ActionSupport{
	
	//action执行成功返回success
	private final String SUCCESS = "success";

	@Autowired
	private ICardService cardService;
	
	@Resource
	private IUserService userService;
	
	//前端显示的数据集
	private List<Card> listCard;
	
	//分页开始的页码
	private int page;
	
	//名片类型：0表示正常名片，1表示回收站名片
	private String flag;
	
	//id的集合
	private String ids;
	
	//上传的头像
	private File file;
	
	//文件类型
	private String fileContentType;

	//文件名
	private String fileFileName;
	
	//分页每页的大小
	private final int SIZE = 10;
	
	//Card的实体集
	private Card myCard;
	
	//模糊搜索关键词
	private String content;
	
	//查找到的名片数量
	private int sum;
	
	/**
	 * 名片管理系统分页取得所有的数据
	 * sum:查询到的所有记录的数量
	 * listCard:查询到的结果集
	 * content:搜索的关键字
	 * @return
	 */
	public String cardList() {
		Map<String,Object> output = ActionContext.getContext().getSession();
		String name = (String) output.get("user_name");
		User u = userService.findUserByName(name);
		if (u.getUserType().equals("超级管理员")) {  //超级管理员显示所有名片，否则只显示自己创建的名片
			name = "%%";
		}
		if (content!=null&&content.length()>0) {
			sum = cardService.findCardByConditionAndFlag(name, flag, content);
			listCard = cardService.findCardByCondition(name, flag, content, page, SIZE);
		} else {
			sum = cardService.findCardByFlag(name, flag);
			listCard = cardService.findCardByPage(name, flag, page, SIZE);
		}
		return SUCCESS;
	}
	
	/**
	 * 添加名片，默认flag为0，表示正常名片
	 * 在添加完成后，根据返回的唯一主键——id，创建头像
	 * 头像存储的文件名为id.jpg
	 * 头衔的存储路径为C:\\workspace\\uploadImg
	 * @return
	 */
	public String newCard() {
		myCard.setFlag("0");
		Map<String,Object> output = ActionContext.getContext().getSession();
		String name = (String) output.get("user_name");
		myCard.setAddby(name);  //由谁创建的名片
		int newId = cardService.save(myCard);
		try {
			String fileN = newId + ".jpg";
			String dir = "C:\\workspace\\uploadImg";
			String path = dir + File.separator+fileN;
			File destFile = new File(path);;
			InputStream inputStream = new FileInputStream(file);
			//拷贝、存储文件
	        FileUtils.copyInputStreamToFile(inputStream, destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 更新名片内容
	 * 如果上传了新的头像则进行头像更新
	 * @return
	 */
	public String updateCard() {
		myCard.setFlag("0");
		Card cc = cardService.findCardById(myCard.getId());
		myCard.setAddby(cc.getAddby());
		cardService.updateCard(myCard);
		if (file!=null) {
			try {
				String fileN = myCard.getId() + ".jpg";
				String dir = "C:\\workspace\\uploadImg";
				String path = dir + File.separator+fileN;
				File destFile = new File(path);;
				InputStream inputStream = new FileInputStream(file);
		        FileUtils.copyInputStreamToFile(inputStream, destFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	// 彻底删除所选的名片(批量)
	public String deleteCards() {
		List<Integer> intList  = JSON.parseArray(ids,Integer.TYPE);
		int[] d = new int[intList.size()];
		for(int i = 0;i<intList.size();i++){
		    d[i] = intList.get(i);
		}
		cardService.deleteBatchCard(d);
		return SUCCESS;
	}
	
	// 删除单张名片
	public String deleteCard() {
		cardService.deleteCard(myCard.getId());
		return SUCCESS;
	}
	
	//将选中名片移动到回收站
	public String moveCardsToTrash() {
		List<Integer> intList  = JSON.parseArray(ids,Integer.TYPE);
		for (int i=0; i<intList.size(); i++) {
			cardService.moveBatchCard("1", intList.get(i));
		}
		return SUCCESS;
	}
	
	//将回收站的名片还原
	public String moveCardsFromTrash() {
		List<Integer> intList  = JSON.parseArray(ids,Integer.TYPE);
		for (int i=0; i<intList.size(); i++) {
			cardService.moveBatchCard("0", intList.get(i));
		}
		return SUCCESS;
	}
	
	//获取名片的头像
	public void getUserImg() {
		try {
			byte[] b =  getHeadByte(myCard.getId());
			HttpServletResponse response = ServletActionContext.getResponse();
			ServletOutputStream out = response.getOutputStream();
			out.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//取得二进制图像
	public byte[] getHeadByte(int id) throws Exception {     
        byte[] buffer = null;  
        try {  
            File file = new File("C:\\workspace\\uploadImg\\"+id+".jpg");  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch(Exception e) {  
        	File file = new File("C:\\workspace\\uploadImg\\test.jpg");  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        }
        return buffer;  
    }  

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Card getMyCard() {
		return myCard;
	}

	public void setMyCard(Card myCard) {
		this.myCard = myCard;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public List<Card> getListCard() {
		return listCard;
	}

	public void setListCard(List<Card> listCard) {
		this.listCard = listCard;
	}
}
