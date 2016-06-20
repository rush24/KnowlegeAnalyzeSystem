package edu.fjnu.domain;

public class Grade {
	private String progress;
	private String examtype;
	private int  score;
	private String PR;
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getExamtype() {
		return examtype;
	}
	public void setExamtype(String examtype) {
		this.examtype = examtype;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getPr() {
		return PR;
	}
	public void setPr(String pr) {
		this.PR = pr;
	}
	@Override
	public String toString() {
		return "Score [progress=" + progress + ", examtype=" + examtype + ", score=" + score +", PR=" + PR + "]";
	}
	
}
