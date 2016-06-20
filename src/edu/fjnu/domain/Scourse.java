package edu.fjnu.domain;
/**
 * 课程表 scourse
 * @author vengeance
 *
 */
public class Scourse {
	private String courseID;//课程号
	private String subject;//所属课程(语、数、英)
	private String classyear;//课程属性(所属年级、所属学期)
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getClassyear() {
		return classyear;
	}
	public void setClassyear(String classyear) {
		this.classyear = classyear;
	}
	@Override
	public String toString() {
		return "Scourse [courseID=" + courseID + ", subject=" + subject + ", classyear=" + classyear + "]";
	}
	
}
