/**
 * 
 */
package edu.fjnu.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 学习履历图片需要的信息
 * @author Administrator
 *
 */
public class GrowPicInfo {
	List<GradeInfo> chineseGradeBefList = new ArrayList<GradeInfo>();// 语文前测
	List<GradeInfo> chineseGradeAftList = new ArrayList<GradeInfo>();// 语文后测
	List<GradeInfo> chineseGradeCutList = new ArrayList<GradeInfo>();// 语文前后测分差
	List<GradeInfo> chineseGradeHighList = new ArrayList<GradeInfo>();// 语文前后测分差起始位置
	
	List<GradeInfo> mathGradeBefList = new ArrayList<GradeInfo>();
	List<GradeInfo> mathGradeAftList = new ArrayList<GradeInfo>();
	List<GradeInfo> mathGradeCutList = new ArrayList<GradeInfo>();
	List<GradeInfo> mathGradeHighList = new ArrayList<GradeInfo>();
	
	List<GradeInfo> englishGradeBefList = new ArrayList<GradeInfo>();
	List<GradeInfo> englishGradeAftList = new ArrayList<GradeInfo>();
	List<GradeInfo> englishGradeCutList = new ArrayList<GradeInfo>();
	List<GradeInfo> englishGradeHighList = new ArrayList<GradeInfo>();

	List<GradeInfo> chineseGradeAndPr = new ArrayList<GradeInfo>();// 语文成绩与PR值关系分布数据
	List<GradeInfo> mathGradeAndPr = new ArrayList<GradeInfo>();
	List<GradeInfo> englishGradeAndPr = new ArrayList<GradeInfo>();

	
	public List<GradeInfo> getChineseGradeBefList() {
		return chineseGradeBefList;
	}
	public void setChineseGradeBefList(List<GradeInfo> chineseGradeBefList) {
		this.chineseGradeBefList = chineseGradeBefList;
	}
	public List<GradeInfo> getChineseGradeAftList() {
		return chineseGradeAftList;
	}
	public void setChineseGradeAftList(List<GradeInfo> chineseGradeAftList) {
		this.chineseGradeAftList = chineseGradeAftList;
	}
	public List<GradeInfo> getChineseGradeCutList() {
		return chineseGradeCutList;
	}
	public void setChineseGradeCutList(List<GradeInfo> chineseGradeCutList) {
		this.chineseGradeCutList = chineseGradeCutList;
	}
	public List<GradeInfo> getChineseGradeHighList() {
		return chineseGradeHighList;
	}
	public void setChineseGradeHighList(List<GradeInfo> chineseGradeHighList) {
		this.chineseGradeHighList = chineseGradeHighList;
	}
	public List<GradeInfo> getMathGradeBefList() {
		return mathGradeBefList;
	}
	public void setMathGradeBefList(List<GradeInfo> mathGradeBefList) {
		this.mathGradeBefList = mathGradeBefList;
	}
	public List<GradeInfo> getMathGradeAftList() {
		return mathGradeAftList;
	}
	public void setMathGradeAftList(List<GradeInfo> mathGradeAftList) {
		this.mathGradeAftList = mathGradeAftList;
	}
	public List<GradeInfo> getMathGradeCutList() {
		return mathGradeCutList;
	}
	public void setMathGradeCutList(List<GradeInfo> mathGradeCutList) {
		this.mathGradeCutList = mathGradeCutList;
	}
	public List<GradeInfo> getMathGradeHighList() {
		return mathGradeHighList;
	}
	public void setMathGradeHighList(List<GradeInfo> mathGradeHighList) {
		this.mathGradeHighList = mathGradeHighList;
	}
	public List<GradeInfo> getEnglishGradeBefList() {
		return englishGradeBefList;
	}
	public void setEnglishGradeBefList(List<GradeInfo> englishGradeBefList) {
		this.englishGradeBefList = englishGradeBefList;
	}
	public List<GradeInfo> getEnglishGradeAftList() {
		return englishGradeAftList;
	}
	public void setEnglishGradeAftList(List<GradeInfo> englishGradeAftList) {
		this.englishGradeAftList = englishGradeAftList;
	}
	public List<GradeInfo> getEnglishGradeCutList() {
		return englishGradeCutList;
	}
	public void setEnglishGradeCutList(List<GradeInfo> englishGradeCutList) {
		this.englishGradeCutList = englishGradeCutList;
	}
	public List<GradeInfo> getEnglishGradeHighList() {
		return englishGradeHighList;
	}
	public void setEnglishGradeHighList(List<GradeInfo> englishGradeHighList) {
		this.englishGradeHighList = englishGradeHighList;
	}
	public List<GradeInfo> getChineseGradeAndPr() {
		return chineseGradeAndPr;
	}
	public void setChineseGradeAndPr(List<GradeInfo> chineseGradeAndPr) {
		this.chineseGradeAndPr = chineseGradeAndPr;
	}
	public List<GradeInfo> getMathGradeAndPr() {
		return mathGradeAndPr;
	}
	public void setMathGradeAndPr(List<GradeInfo> mathGradeAndPr) {
		this.mathGradeAndPr = mathGradeAndPr;
	}
	public List<GradeInfo> getEnglishGradeAndPr() {
		return englishGradeAndPr;
	}
	public void setEnglishGradeAndPr(List<GradeInfo> englishGradeAndPr) {
		this.englishGradeAndPr = englishGradeAndPr;
	}

	
}
