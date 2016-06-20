package edu.fjnu.domain;

/**
 * 试卷主表
 * @author vengeance
 *
 */
public class Testmain {
	private int student_memberId;//学生ID
	private int avgscore;//学生本学期本科目平均分
	
	public int getStudent_memberId() {
		return student_memberId;
	}
	public void setStudent_memberId(int student_memberId) {
		this.student_memberId = student_memberId;
	}
	public int getAvgscore() {
		return avgscore;
	}
	public void setAvgscore(int avgscore) {
		this.avgscore = avgscore;
	}
	@Override
	public String toString() {
		return "Testmain [student_memberId=" + student_memberId + ", avgscore="
				+ avgscore + "]";
	}
	
	
}
