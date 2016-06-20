/**
 * 
 */
package edu.fjnu.domain;

/**
 * 学生pr值的dao 对应excel表
 * 
 * @author Administrator
 *
 */
public class StudentPr {
	private String name; // 学生名字
	private String classyear; // 年级
	private double avgPR; // 某个年级平均pr值
	private String coach; // 教师id

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassyear() {
		return classyear;
	}

	public void setClassyear(String classyear) {
		this.classyear = classyear;
	}

	public double getAvgPR() {
		return avgPR;
	}

	public void setAvgPR(double avgPR) {
		this.avgPR = avgPR;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	@Override
	public String toString() {
		return "StudentPr [name=" + name + ", classyear=" + classyear + ", avgPR=" + avgPR + ", coach=" + coach + "]";
	}

}
