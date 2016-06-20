/**
 * 
 */
package edu.fjnu.domain;

/**
 * 学生老师关联的表的类
 * @author Administrator
 *
 */
public class VTeaacherStudent {
	private int students_memberId;  //老师id
	private int t_member_memberId;   //学生id
	public int getStudents_memberId() {
		return students_memberId;
	}
	public void setStudents_memberId(int students_memberId) {
		this.students_memberId = students_memberId;
	}
	public int getT_member_memberId() {
		return t_member_memberId;
	}
	public void setT_member_memberId(int t_member_memberId) {
		this.t_member_memberId = t_member_memberId;
	}
	
}
