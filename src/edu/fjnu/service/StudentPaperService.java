/**
 * 
 */
package edu.fjnu.service;

import java.util.List;

import edu.fjnu.dao.StudentDao;
import edu.fjnu.dao.StudentPaperDao;
import edu.fjnu.domain.VQuestion;
import edu.fjnu.domain.VScope;
import edu.fjnu.domain.VTestMain;
import edu.fjnu.util.DoubleFormat;

/**
 * 学生端试卷分析的service
 * 
 * @author 丽标
 *
 */
public class StudentPaperService {

	private StudentPaperDao std = new StudentPaperDao();
	private StudentDao studentDao = new StudentDao();

	/**
	 * 根据 学科 姓名 年级 得到 学生考卷的list
	 * 
	 * @param subject
	 * @param studentName
	 * @param gradeName
	 * @return
	 */
	public List<VTestMain> getTestMainsList(String subject, int studentId, String gradeName) {

		return std.findTestMainId(subject, studentId, gradeName);
	}

	/**
	 * 输入学生id 查询学生做过试卷的所有年级
	 * 
	 * @param studentId
	 * @return
	 */
	public List<VScope> getStudentGradeList(int studentId) {
		return studentDao.getStudentGrade(studentId);
	}

	/**
	 * 传入试卷的id 得到 这张试卷所有的题目表vQuestion中的subject(题目内容) ,successnum(答对人数),num（总人数
	 * ），rate（错误率） stustatus（学生对错） 对应前端的表格
	 * 
	 * @param testMainId
	 * @return List<VQuestion>
	 */
	public List<VQuestion> getVQuestionList(int testMainId) {
		List<VQuestion> tmpList = std.searchQuestionList(testMainId);

		for (int i = 0; i < tmpList.size(); i++) {
			tmpList.get(i).setRate(DoubleFormat.getDoubleFormat(tmpList.get(i).getRate() * 100,2));
		}
		
		return tmpList;
	}

	/**
	 * 传入 试卷测试范围（scope）得到 知识点（keyword） 知识点的错误率（wrong）
	 * 
	 * @param scope
	 * @return List<VQuestion>
	 */
	public List<VQuestion> getKeywordAndWrongByScope(String scope) {
		List<VQuestion> tmpList = std.searchWrongRate(scope);

		for (int i = 0; i < tmpList.size(); i++) {
			tmpList.get(i).setWrong(DoubleFormat.getDoubleFormat(tmpList.get(i).getWrong()*100,2));
		}
		
		return tmpList;
	}

	/**
	 * 输入试卷id 得到改试卷的scope（测试范围）
	 * 
	 * @param testId
	 * @return
	 */
	public String getScope(int testId) {
		return std.getScope(testId);
	}

}
