package edu.fjnu.domain;

/**
 * 得到成绩分析学生履历信息
 * 
 * @author vengeance
 *
 */
public class GradeInfo {
	private String progress;// 阶段名称
	private double score;// 成绩
	private double pr;// 阶段PR值

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public double getPr() {
		return pr;
	}

	public void setPr(double pr) {
		this.pr = pr;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "GradeInfo [progress=" + progress + ", score=" + score + ", pr=" + pr + "]";
	}

}
