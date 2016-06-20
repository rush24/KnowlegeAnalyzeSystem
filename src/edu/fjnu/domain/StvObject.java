package edu.fjnu.domain;

public class StvObject {
	private int source; // 源知识点
	private int target;// 目标知识点
	private double value;// 关联长度值

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "StvObject [source=" + source + ", target=" + target + ", value=" + value + "]";
	}

}
