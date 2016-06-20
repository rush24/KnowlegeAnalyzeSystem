package edu.fjnu.domain;

/**
 * 梯度表
 * @author vengeance
 *
 */
public class VGrandient {
	private int grandientID;//梯度ID
	private int grandientSecequenceNO;//该梯度为第几梯度
	private int course_courseID;//该梯度所属课程ID
	
	public int getCourse_courseID() {
		return course_courseID;
	}
	public void setCourse_courseID(int course_courseID) {
		this.course_courseID = course_courseID;
	}
	public int getGrandientID() {
		return grandientID;
	}
	public void setGrandientID(int grandientID) {
		this.grandientID = grandientID;
	}
	public int getGrandientSecequenceNO() {
		return grandientSecequenceNO;
	}
	public void setGrandientSecequenceNO(int grandientSecequenceNO) {
		this.grandientSecequenceNO = grandientSecequenceNO;
	}
	@Override
	public String toString() {
		return "Grandient [grandientID=" + grandientID + ", grandientSecequenceNO=" + grandientSecequenceNO
				+ ", course_courseID=" + course_courseID + "]";
	}
	
}
