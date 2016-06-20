/**
 * 
 */
package edu.fjnu.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 保存学生语数英三科的pr值
 * @author Administrator
 *
 */
public class StuPrInfo {
	List<StudentPr> chineseList = new ArrayList<StudentPr>(); // 语文的StudentPr值列表
	List<StudentPr> mathList = new ArrayList<StudentPr>(); // 数学的StudentPr值列表
	List<StudentPr> englishList = new ArrayList<StudentPr>(); // 英语的StudentPr值列表

	
	public List<StudentPr> getChineseList() {
		return chineseList;
	}


	public void setChineseList(List<StudentPr> chineseList) {
		this.chineseList = chineseList;
	}


	public List<StudentPr> getMathList() {
		return mathList;
	}


	public void setMathList(List<StudentPr> mathList) {
		this.mathList = mathList;
	}


	public List<StudentPr> getEnglishList() {
		return englishList;
	}


	public void setEnglishList(List<StudentPr> englishList) {
		this.englishList = englishList;
	}


	

}
