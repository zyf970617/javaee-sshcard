package com.xsw.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xsw.dao.CardDao;
import com.xsw.dao.UserDao;
import com.xsw.entity.Card;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author 徐森威
 * @date 2018/4/17
 */
@ParentPackage("struts-default")
@Namespace(value = "/cards")
public class CardAction extends ActionSupport{

    private CardDao cardDao = new CardDao();

    private Card card;

    private String start;

    private String ids;

    private String flag;

    private String id;

    private String type;

    private String key;

    @Action(
            value = "careList",
            results = {
                    @Result(name = "success",location = "/card/cardManage.jsp",type = "redirect"),
            }
    )
    public String getAllMsg() {
        if (type==null || !type.equals("search")) {
            getList("0", Integer.parseInt(this.start));
        } else {
            getSearch(key,"0", Integer.parseInt(this.start));
        }
        return "success";
    }

    @Action(
            value = "trashList",
            results = {
                    @Result(name = "success",location = "/card/trash.jsp",type = "redirect"),
            }
    )
    public String trashList() {
        if (type==null || !type.equals("search")) {
            getList("1", Integer.parseInt(this.start));
        } else {
            getSearch(key,"1", Integer.parseInt(this.start));
        }
        return "success";
    }

    @Action(
            value = "Inserts",
            results = {
                    @Result(name = "success",location = "/card/cardManage.jsp",type = "redirect"),
            }
    )
    public String Inserts() {
        card.setFlag("0");
        cardDao.insert(card);
        getList("0",1);
        return "success";
    }

    @Action(
            value = "updateMsg",
            results = {
                    @Result(name = "success",location = "/card/insert.jsp",type = "redirect"),
            }
    )
    public String updateMsg() {
        Card card = cardDao.findById(Integer.parseInt(id));
        Map<String,Object> output = ActionContext.getContext().getSession();
        output.put("update_card",card);
        return "success";
    }

    @Action(
            value = "submitForUpdate",
            results = {
                    @Result(name = "success",location = "/card/cardManage.jsp",type = "redirect"),
            }
    )
    public String submitForUpdate() {
        System.out.println("测试"+card.toString());
        cardDao.update(card);
        getList("0",1);
        return "success";
    }

    @Action(
            value = "deleteById",
            results = {
                    @Result(name = "trash",location = "/card/trash.jsp",type = "redirect"),
                    @Result(name = "card",location = "/card/cardManage.jsp",type = "redirect"),
            }
    )
    public String deleteById() {
        cardDao.delete(Integer.parseInt(this.id));
        if (this.flag.equals("1")) {
            getList("1", Integer.parseInt(this.start));
            return "trash";
        } else {
            getList("0", Integer.parseInt(this.start));
            return "card";
        }
    }

    @Action(value = "batchMove")
    public void batchMove() {
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            List<Integer> li  = JSON.parseArray(ids,Integer.TYPE);
            String ss = li.toString();
            if ("0".equals(flag)) {
                int t = cardDao.batchMoveMsg("1", ss.substring(1, ss.length() - 1));
            } else if ("1".equals(flag)) {
                int t = cardDao.batchMoveMsg("0", ss.substring(1, ss.length() - 1));
            }
            response.getWriter().print("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Action(value = "batchDelete")
    public void batchDelete() {
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            List<Integer> li  = JSON.parseArray(ids,Integer.TYPE);
            String ss = li.toString();
            int t = cardDao.batchDeleteMsg(ss.substring(1,ss.length()-1));
            response.getWriter().print("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getList(String flag,int start) {
        List<Card> optList  = cardDao.findByPage(flag,(start-1)*15,15);
        int sum = cardDao.findSum(flag);
        Map<String,Object> output = ActionContext.getContext().getSession();
        output.put("card_list",optList);
        output.put("page_sum",sum);
        int t = start;
        output.put("page_start",t);
        output.put("card_type","normal");
        output.put("card_key","");
    }

    private void getSearch(String key,String flag,int start) {
        Map<String,Object> output = ActionContext.getContext().getSession();
        output.put("card_key",key);
        key = "%" + key + "%";
        List<Card> optList  = cardDao.search(key,flag,(start-1)*15,15);
        int sum = cardDao.findSum1(flag,key);
        output.put("card_list",optList);
        output.put("page_sum",sum);
        int t = start;
        output.put("page_start",t);
        output.put("card_type","search");
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getIds() {
        return this.ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Card getCard() {
        return this.card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
