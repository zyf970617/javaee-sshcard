package com.edu.action.card;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import com.edu.dao.card.CardDao;
import com.edu.model.card.Card;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.*;

@Namespace("/card")
@Scoped(Scope.REQUEST)
@ParentPackage("struts-default")
public class CardAction extends ActionSupport {
	private CardDao cardDao = new CardDao();
	private Card card; // �����ύֵ�������¼��
	//�ύҳ��--��ѯ��Ϣ�ύ
	private String condition;
	private String[] checkList;  //����ѡ�������¼�ĸ�ѡ�����γɵļ�¼��ŵ��ַ�
	private int id;   // ����¼ʱ�������
	private String order;  //���ò�ѯ�������ʽ������¼���������
	private long pageNo;  //���ò�ѯ���Ҫ��ʾ�ķ�ҳҳ���
	private int pageSize;  //���ò�ѯ���Ҫ��ʾ�ķ�ҳ��ÿҳҪ��ʾ�ļ�¼����
	//����ִ�н��ķ�����Ϣ
	private long recordCount; // ��ѯ���������ļ�¼������
	private long pageCount;//��ѯ����¼����ҳ��
	private List<Card> listCard; //������ѯ����
	private String msg; //�������к����Ϣ
	private HttpSession session;
	@Action(
			value = "insert",
			results={@Result(name="success",location="/find",type="redirectAction")}
			)
	public String insert() throws Exception{
		cardDao.insert(card);
		msg = "插入成功";
		return "success";
				
	}
	@Action(
			value = "insertList",
			results={@Result(name="success",location="/find",type="redirectAction")}
			)
	public String insertList() throws Exception{
		int n = cardDao.insertList(listCard);
		msg = "����һ�飨"+n+"������¼�ɹ���";
		return "success";
	}
	@Action(
			value = "delete",
			results={@Result(name="success",location="/find",type="redirectAction")}
			)
	public String delete() throws Exception{
		cardDao.delete(id);
		msg = "ɾ��һ����¼�ɹ�";
		return "success";
	}
	@Action(
			value = "deleteList",
			results={@Result(name="success",location="/find",type="redirectAction")}
			)
	public String deleteList() throws Exception{
		int ids[] = new int[checkList.length];
		for(int i=0;i<checkList.length;++i){
			ids[i] = Integer.parseInt(checkList[i]);
		}
		int n = cardDao.deleteList(ids);
		msg = "ɾ��һ�飨"+n+"������¼�ɹ���";
		return "success";
	}
	@Action(
			value = "find",
			results={@Result(name="success",location="/card/list.jsp",type="dispatcher")}
			)
	public String find() throws Exception{
		listCard = cardDao.findByCondition(condition, "0");
		session = ServletActionContext.getRequest().getSession();
		session.setAttribute("condition", condition);
		session.setAttribute("order", order);
		return "success";
	}
	@Action(
			value = "find2",
			results={@Result(name="success",location="/card/retrieve.jsp",type="dispatcher")}
			)
	public String find2() throws Exception{
		listCard = cardDao.findByCondition(condition, "1");
		session = ServletActionContext.getRequest().getSession();
		session.setAttribute("condition", condition);
		session.setAttribute("order", order);
		return "success";
	}//sfsdcdsfsd
	@Action(
			value = "findupdate",
			results={@Result(name="success",location="/card/update.jsp",type="dispatcher")}
			)
	public String findUpdate() throws Exception{
		card = cardDao.findById(id,"0");
		return "success";
	}
	@Action(
			value = "retrieve",
			results={@Result(name="success",location="/find",type="redirectAction")}
			)
	public String retrieve() throws Exception{
		int ids[] = new int[checkList.length];
		for(int i=0;i<checkList.length;++i){
			ids[i] = Integer.parseInt(checkList[i]);
		}
		cardDao.retrieve(ids);
		return "success";
	}
	@Action(
			value = "update",
			results={@Result(name="success",location="/find",type="redirectAction")}
			)
	public String update() throws Exception{
		cardDao.update(card);
		return "success";
	}
	public CardDao getCardDao() {
		return cardDao;
	}
	public void setCardDao(CardDao cardDao) {
		this.cardDao = cardDao;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String[] getCheckList() {
		return checkList;
	}
	public void setCheckList(String[] checkList) {
		this.checkList = checkList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public long getPageNo() {
		return pageNo;
	}
	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public List<Card> getListCard() {
		return listCard;
	}
	public void setListCard(List<Card> listCard) {
		this.listCard = listCard;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	
}