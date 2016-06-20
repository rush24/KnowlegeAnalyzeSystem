package edu.fjnu.domain;

//存放聚类分析模块图表实体
public class Cluster {
	
	private String name;//该知识点名字
	private int area;//改知识点显示直径（次数）
	private int yAxis;//该知识点显示Y轴坐标
	private int gradeNum;//该知识点所属年级编号
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getyAxis() {
		return yAxis;
	}
	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}
	
	public int getGradeNum() {
		return gradeNum;
	}
	public void setGradeNum(int gradeNum) {
		this.gradeNum = gradeNum;
	}
	
	
	public Cluster(String name, int area, int yAxis, int gradeNum) {
		super();
		this.name = name;
		this.area = area;
		this.yAxis = yAxis;
		this.gradeNum = gradeNum;
	}
	
	public Cluster(){
		
	}
	@Override
	public String toString() {
		return "Cluster [name=" + name + ", area=" + area + ", yAxis=" + yAxis
				+ ", gradeNum=" + gradeNum + "]";
	}
	
	
	
}
