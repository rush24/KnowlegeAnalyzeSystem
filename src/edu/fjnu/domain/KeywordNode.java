package edu.fjnu.domain;
/**
 * 存储地图上每一个知识点节点对象
 * @author zhangzhiyong
 *
 */

public class KeywordNode {
	
	private String name;//知识点名称
	private double longtitude;//该知识点的经度
	private double latitude;//该知识点的纬度
	private int gradeIndex;//所属年级索引
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public int getGradeIndex() {
		return gradeIndex;
	}
	public void setGradeIndex(int gradeIndex) {
		this.gradeIndex = gradeIndex;
	}
	public KeywordNode(){
		
	}
	
	public KeywordNode(String name, double longtitude, double latitude, int gradeIndex) {
		super();
		this.name = name;
		this.longtitude = longtitude;
		this.latitude = latitude;
		this.gradeIndex = gradeIndex;
	}
	public int hashCode(){
		return getName().hashCode() + Double.hashCode(getLatitude()) + Double.hashCode(getLongtitude());
	}
	@Override
	public String toString() {
		return "KeywordNode [name=" + name + ", longtitude=" + longtitude + ", latitude=" + latitude + ", gradeIndex="
				+ gradeIndex + "]";
	}
	
	
	
}
