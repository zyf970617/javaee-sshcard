package com.edu.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class CodeAction extends ActionSupport{
	
	public static final String VERIFY_CODES = "23456789abcdefghijklmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ"; 
	
	/**
	 * 制作验证码，并将验证码保存到session中
	 * @throws IOException
	 */
    public void index() throws IOException{ 
    	Map<String,Object> output = ActionContext.getContext().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
    	int w = 140, h = 75;  
        String verifyCode = generateVerifyCode(4);
        try {
        	BufferedImage image=outputImage(w, h, verifyCode);  
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);
            output.put("v_code", verifyCode);
            try {
                out.flush();
            } finally {
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
    
    
    public static String generateVerifyCode(int verifySize){  
        return generateVerifyCode(verifySize, VERIFY_CODES);  
    }  
    public static String generateVerifyCode(int verifySize, String sources){  
        if(sources == null || sources.length() == 0){  
            sources = VERIFY_CODES;  
        }  
        int codesLen = sources.length();  
        Random rand = new Random(System.currentTimeMillis());  
        StringBuilder verifyCode = new StringBuilder(verifySize);  
        for(int i = 0; i < verifySize; i++){  
            verifyCode.append(sources.charAt(rand.nextInt(codesLen-1)));  
        }  
        return verifyCode.toString();  
    }  
    public static BufferedImage outputImage(int w, int h, String code) throws IOException{  
        int verifySize = code.length();  
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);  
        Random rand = new Random();  
        Graphics2D g2 = image.createGraphics(); 
        //消除齿痕
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);  
        Color c = new Color(255,255,255); 
        Color c1 = new Color(238,238,238);
        g2.setColor(c1);// 设置边框色  
        g2.fillRect(0, 0, w, h);  
        g2.setColor(c1);// 设置背景色  
        g2.fillRect(0, 2, w, h-4); 
        g2.setColor(new Color(0,0,0));  
        int fontSize = h-5;   
        Font font = new Font("方正姚体", Font.ITALIC, fontSize);  
        g2.setFont(font);  
        char[] chars = code.toCharArray();  
        for(int i = 0; i < verifySize; i++){  
            AffineTransform affine = new AffineTransform();  
            affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1), (w / verifySize) * i + fontSize/2, h/2);  
            g2.setTransform(affine);  
            g2.drawChars(chars, i, 1, ((w-10) / verifySize) * i, h/2 + fontSize/2 - 15);  
        }  
        g2.dispose();  
        return image;
    }  

}
