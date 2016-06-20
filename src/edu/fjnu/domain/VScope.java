package edu.fjnu.domain;

/**
 * 分类范围表
 * 
 * @author vengeance
 *
 */
public class VScope {
	private int pk_scope_ID;// 分类范围ID
	private int level;// 层次
	private String name;// 分类范围名称
	private int keywordCount;// 知识点次数（错误或正确）

	public int getPk_scope_ID() {
		return pk_scope_ID;
	}

	public void setPk_scope_ID(int pk_scope_ID) {
		this.pk_scope_ID = pk_scope_ID;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKeywordCount() {
		return keywordCount;
	}

	public void setKeywordCount(int keywordCount) {
		this.keywordCount = keywordCount;
	}

	@Override
	public String toString() {
		return "VScope [pk_scope_ID=" + pk_scope_ID + ", level=" + level + ", name=" + name + ", keywordCount="
				+ keywordCount + "]";
	}

}
