package edu.fjnu.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Encoder;

/**
 * 工具类
 * @author zhangzhiyong
 *
 */
public class Tool {

	/**
	 * 使用MD5算法加密字符串
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String encodeByMD5(String str){
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes()); 
			byte b[] = md.digest(); 
			int i; 
			for (int offset = 0; offset < b.length; offset++) { 
			i = b[offset]; 
			if(i<0) i+= 256; 
			if(i<16) 
				buf.append("0"); 
			buf.append(Integer.toHexString(i)); 
			} 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		
		return buf.toString();
	}
	
	/**
	 *  得到当前登录用户的学号（工号）
	 * @param request
	 * @return
	 */
	public static String getCurrentLoginMemberId(HttpServletRequest request){
		String memberId = null;
		if((String)request.getSession().getAttribute("sID") != null)
			memberId = (String)request.getSession().getAttribute("sID");
		else if((String)request.getSession().getAttribute("userID") != null)
			memberId = (String)request.getSession().getAttribute("userID");
		return memberId;
			
	}
}
