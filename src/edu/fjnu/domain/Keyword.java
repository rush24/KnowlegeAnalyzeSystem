package edu.fjnu.domain;

/**
 * 存储知识点的信息
 * 
 * @author vengeance
 *
 */
public class Keyword {
	private String keyName;// 知识点名称
	private int rightCount;// 正确数
	private int wrongCount;// 错误数
	private int easyCount;// 相关出题的简单题数
	private int middleCount;// 相关出题的中等题数
	private int hardCount;// 相关出题的较难题数

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public int getRightCount() {
		return rightCount;
	}

	public void setRightCount(int rightCount) {
		this.rightCount = rightCount;
	}

	public int getWrongCount() {
		return wrongCount;
	}

	public void setWrongCount(int wrongCount) {
		this.wrongCount = wrongCount;
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

	@Override
	public String toString() {
		return "Keyword [keyName=" + keyName + ", rightCount=" + rightCount + ", wrongCount=" + wrongCount
				+ ", easyCount=" + easyCount + ", middleCount=" + middleCount + ", hardCount=" + hardCount + "]";
	}

}
