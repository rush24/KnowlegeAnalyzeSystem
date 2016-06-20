package edu.fjnu.domain;

/**
 * 学生成绩表sscore
 * @author vengeance
 *
 */
public class Sscore {
	private String studentID;//学生ID
	private String courseID;//课程ID
	private String testID;//试卷ID
	private double score;//成绩
	private double pr;//pr值(rank排名值)
	
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getTestID() {
		return testID;
	}
	public void setTestID(String testID) {
		this.testID = testID;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public double getPr() {
		return pr;
	}
	public void setPr(double pr) {
		this.pr = pr;
	}
	@Override
	public String toString() {
		return "Sscore [studentID=" + studentID + ", courseID=" + courseID + ", testID=" + testID + ", score=" + score
				+ ", pr=" + pr + "]";
	}
}
