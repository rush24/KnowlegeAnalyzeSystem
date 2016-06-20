/**
 * 
 */
package edu.fjnu.domain;

import java.util.List;

import edu.fjnu.domain.VQuestion;

/**
 * @author Administrator
 *
 */
public class TestInfo {

	List<VQuestion> vQuestionList;
	List<VQuestion> vQuestionKeywordList;
	double hardrate;
	double believerate;
	String hardString;
	String believeString;
	
	public List<VQuestion> getvQuestionList() {
		return vQuestionList;
	}
	public void setvQuestionList(List<VQuestion> vQuestionList) {
		this.vQuestionList = vQuestionList;
	}
	public List<VQuestion> getvQuestionKeywordList() {
		return vQuestionKeywordList;
	}
	public void setvQuestionKeywordList(List<VQuestion> vQuestionKeywordList) {
		this.vQuestionKeywordList = vQuestionKeywordList;
	}
	public double getHardrate() {
		return hardrate;
	}
	public void setHardrate(double hardrate) {
		this.hardrate = hardrate;
	}
	public double getBelieverate() {
		return believerate;
	}
	public void setBelieverate(double believerate) {
		this.believerate = believerate;
	}
	public String getHardString() {
		return hardString;
	}
	public void setHardString(String hardString) {
		this.hardString = hardString;
	}
	public String getBelieveString() {
		return believeString;
	}
	public void setBelieveString(String believeString) {
		this.believeString = believeString;
	}
	
	

}
