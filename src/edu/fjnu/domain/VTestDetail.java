package edu.fjnu.domain;

/**
 * 测试详细表
 * 
 * @author vengeance
 *
 */
public class VTestDetail {
	private Long pk_test_ID;// 测试详细表ID
	private String answer;// 正确答案
	private double score;// 分数
	private int sequence;// 顺序
	private String stuAnswer;// 学生答案
	private double realScore;// 学生试卷得分
	private Long question_fk_question_id;// 试卷的题目ID
	private double avgscore;// 范围历次平均分

	public double getAvgscore() {
		return avgscore;
	}

	public void setAvgscore(double avgscore) {
		this.avgscore = avgscore;
	}

	public Long getQuestion_fk_question_id() {
		return question_fk_question_id;
	}

	public void setQuestion_fk_question_id(Long question_fk_question_id) {
		this.question_fk_question_id = question_fk_question_id;
	}

	public double getRealScore() {
		return realScore;
	}

	public void setRealScore(double realScore) {
		this.realScore = realScore;
	}

	public Long getPk_test_ID() {
		return pk_test_ID;
	}

	public void setPk_test_ID(Long pk_test_ID) {
		this.pk_test_ID = pk_test_ID;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getStuAnswer() {
		return stuAnswer;
	}

	public void setStuAnswer(String stuAnswer) {
		this.stuAnswer = stuAnswer;
	}

	@Override
	public String toString() {
		return "VTestDetail [pk_test_ID=" + pk_test_ID + ", answer=" + answer + ", score=" + score + ", sequence="
				+ sequence + ", stuAnswer=" + stuAnswer + ", realScore=" + realScore + ", question_fk_question_id="
				+ question_fk_question_id + ", avgscore=" + avgscore + "]";
	}

}
