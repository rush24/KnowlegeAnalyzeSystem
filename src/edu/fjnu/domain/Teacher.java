package edu.fjnu.domain;
/**
 * 老师表teacher
 * @author vengeance
 *
 */
public class Teacher {
	private String teacherID;//老师ID
	private String tname;//老师名
	private String tsex;//老师性别
	private String course;//老师课程
	private String tpassword;//老师密码
	private String tclass;//老师班级
	private Scourse scourse;//老师学生课程关联
	private Stcourse stcourse;//老师课程关联

	private String memberID;
	private String name;
	private String password;
	private final int TYPE=1;

	public int getTYPE() {
		return TYPE;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Scourse getScourse() {
		return scourse;
	}
	public void setScourse(Scourse scourse) {
		this.scourse = scourse;
	}
	public Stcourse getStcourse() {
		return stcourse;
	}
	public void setStcourse(Stcourse stcourse) {
		this.stcourse = stcourse;
	}
	public String getCourse() {
		return course;
	}
	public String getTclass() {
		return tclass;
	}
	public void setTclass(String tclass) {
		this.tclass = tclass;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
	public String getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTsex() {
		return tsex;
	}
	public void setTsex(String tsex) {
		this.tsex = tsex;
	}
	public String getTpassword() {
		return tpassword;
	}
	public void setTpassword(String tpassword) {
		this.tpassword = tpassword;
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"teacherID='" + teacherID + '\'' +
				", tname='" + tname + '\'' +
				", tsex='" + tsex + '\'' +
				", course='" + course + '\'' +
				", tpassword='" + tpassword + '\'' +
				", tclass='" + tclass + '\'' +
				", scourse=" + scourse +
				", stcourse=" + stcourse +
				", memberID='" + memberID + '\'' +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", TYPE=" + TYPE +
				'}';
	}
}
