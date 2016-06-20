package edu.fjnu.domain;

/**
 * 学生老师课程关联表stcourse
 * @author vengeance
 *
 */
public class Stcourse {
	private String studentID;//学生ID
	private String teacherID;//老师ID
	private String courseID;//课程ID
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	@Override
	public String toString() {
		return "Stcourse [studentID=" + studentID + ", teacherID=" + teacherID + ", courseID=" + courseID + "]";
	}
	
}
