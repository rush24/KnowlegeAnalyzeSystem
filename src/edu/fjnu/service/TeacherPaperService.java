/**
 * 
 */
package edu.fjnu.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.fjnu.dao.QuestionDao;
import edu.fjnu.dao.StudentPaperDao;
import edu.fjnu.dao.TeacherPaperDao;
import edu.fjnu.domain.VQuestion;
import edu.fjnu.domain.VScope;
import edu.fjnu.domain.VTeaacherStudent;
import edu.fjnu.domain.VTestMain;
import edu.fjnu.util.DoubleFormat;

/**
 * 教师端考卷分析模块的service
 * 
 * @author Administrator
 *
 */
public class TeacherPaperService {
	private StudentPaperDao studentTestDao = new StudentPaperDao();
	private TeacherPaperDao teacherTestDao = new TeacherPaperDao();
	private QuestionDao questionDao = new QuestionDao();

	/**
	 * 通过教师id得到年级列表
	 * 
	 * @param teacherId
	 * @return
	 */
	public List<String> getGradeList(int teacherId) {
		List<String> gradeList = new ArrayList<String>();
		List<VScope> listScopes = teacherTestDao.searchGradeByTeacherId(teacherId);
		for (VScope vScope : listScopes) {
			gradeList.add(vScope.getName());
		}
		return gradeList;
	}

	/**
	 * 查询某个老师教的这些学生id限定某个年级里的某个科目的学生ID
	 * 
	 * @param teacherId
	 * @param courseName
	 * @param grade
	 * @return stuIdList
	 */
	public List<Integer> getStudentId(int teacherId, String courseName, String grade) {
		List<Integer> stuIdList = new ArrayList<Integer>();
		List<VTeaacherStudent> stuList = teacherTestDao.searchStuId(teacherId, courseName, grade);
		for (VTeaacherStudent v : stuList) {
			stuIdList.add(v.getStudents_memberId());
		}
		return stuIdList;
	}

	/**
	 * 传入一个学生列表 得到 所有学生的考卷
	 * 
	 * @param subject
	 *            科目
	 * @param stuIdList
	 *            学生列表
	 * @param gradeName
	 *            年级名字
	 * @return vList 试卷列表
	 */
	public List<VTestMain> getTestMainsList(String subject, List<Integer> stuIdList, String gradeName) {

		List<VTestMain> vList = new ArrayList<VTestMain>();
		for (int s : stuIdList) {
			for (VTestMain v : studentTestDao.findTestMainId(subject, s, gradeName)) {
				vList.add(v);
			}
		}

		return vList;
	}

	/**
	 * 输入试卷id 得到 类VQuestion 的知识点keyword 和wrong 错误率
	 * 
	 * @param testMainId
	 * @return 返回一个List<VQuestion>
	 */

	public List<VQuestion> getWrongRate(int testMainId) {
		String scope = studentTestDao.getScope(testMainId);
		return studentTestDao.searchWrongRate(scope);
	}

	/**
	 * 输入试卷id 得到 这张试卷所有的题目表中的subject（题目） ,successnum（做对人数）,num（总人数） ，rate（错误率）
	 * stustatus（学生对错） 这些属性都在类VQuestion里面
	 * 
	 * @param testMainId
	 * @return VQuestion的一个list
	 */
	public List<VQuestion> getQuestionList(int testMainId) {
		List<VQuestion> tmpList = studentTestDao.searchQuestionList(testMainId);
		
		for(int i=0; i<tmpList.size(); i++){
			tmpList.get(i).setRate(DoubleFormat.getDoubleFormat(tmpList.get(i).getRate()*100,2));
		}
		return tmpList;
	}

	/**
	 * 根据指定ID得到试卷难度
	 * 
	 * @return
	 * @throws SQLException
	 */
	public VQuestion getHardRateById(int testID) throws SQLException {
		return questionDao.getHard(testID);
	}
}
