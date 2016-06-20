package edu.fjnu.domain;

/**
 * 用户表
 * @author vengeance
 *
 */
public class VMember {
	private int memberID;//用户ID
	private String name;//用户姓名
	
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Member [memberID=" + memberID + ", name=" + name + "]";
	}
}
