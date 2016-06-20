package edu.fjnu.domain;

/**
 * 知识点关联表模型
 * @author zhangzhiyong
 *
 */
public class KeywordTable {
	private String keywordType;//知识点类型
	private String keywordName;//知识点名称
	private int easyCount;// 相关出题的简单题数
	private int middleCount;// 相关出题的中等题数
	private int hardCount;// 相关出题的较难题数
	private double rightRate;//正确率
	private double wrongRate;//错误率
	public String getKeywordType() {
		return keywordType;
	}
	public void setKeywordType(String keywordType) {
		this.keywordType = keywordType;
	}
	public String getKeywordName() {
		return keywordName;
	}
	public void setKeywordName(String keywordName) {
		this.keywordName = keywordName;
	}
	public int getEasyCount() {
		return easyCount;
	}
	public void setEasyCount(int easyCount) {
		this.easyCount = easyCount;
	}
	public int getMiddleCount() {
		return middleCount;
	}
	public void setMiddleCount(int middleCount) {
		this.middleCount = middleCount;
	}
	public int getHardCount() {
		return hardCount;
	}
	public void setHardCount(int hardCount) {
		this.hardCount = hardCount;
	}
	public double getRightRate() {
		return rightRate;
	}
	public void setRightRate(double rightRate) {
		this.rightRate = rightRate;
	}
	public double getWrongRate() {
		return wrongRate;
	}
	public void setWrongRate(double wrongRate) {
		this.wrongRate = wrongRate;
	}
	@Override
	public String toString() {
		return "KeywordTable [keywordType=" + keywordType + ", keywordName="
				+ keywordName + ", easyCount=" + easyCount + ", middleCount="
				+ middleCount + ", hardCount=" + hardCount + ", rightRate="
				+ rightRate + ", wrongRate=" + wrongRate + "]";
	}
	
	
}
