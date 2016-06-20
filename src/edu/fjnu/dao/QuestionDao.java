package edu.fjnu.dao;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import cn.itcast.jdbc.TxQueryRunner;
import edu.fjnu.domain.VQuestion;
import edu.fjnu.domain.VTestDetail;
import edu.fjnu.domain.VTestMain;

/**
 * 对题目的操作
 * 
 * @author vengeance
 *
 */
public class QuestionDao {
	VQuestion question = new VQuestion();// 操作数据库题目表的对象
	QueryRunner qr = new TxQueryRunner();// 操作数据库的对象

	/**
	 * 得到指定试卷ID的试卷难度
	 * 
	 * @return
	 * @throws SQLException
	 */
	public VQuestion getHard(int testID) throws SQLException {
		String sql = "select sum((t_question.num-t_question.successNum)/t_question.num) as wrong from t_question,t_testmain,t_test_detail"
				+ " where t_testmain.pk_test_main_id=t_test_detail.testMain_pk_test_main_id "
				+ "and t_test_detail.question_fk_question_id=t_question.fk_question_id "
				+ "and t_testmain.pk_test_main_id=?";
		Object[] params = { testID };
		question = qr.query(sql, new BeanHandler<VQuestion>(VQuestion.class), params);
		return question;
	}

	/**
	 * 根据题目ID得到该题平均得分
	 * 
	 * @return
	 * @throws SQLException
	 */
	public VTestDetail getScoreBySubjectId() throws SQLException {
		String sql = "select ((sum(t_test_detail.score))/count(*)) as score  " + "from t_test_detail,t_testmain "
				+ "where t_testmain.pk_test_main_id=t_test_detail.testMain_pk_test_main_id "
				+ "and t_test_detail.question_fk_question_id='2' and stuAnswer <>''";
		return qr.query(sql, new BeanHandler<VTestDetail>(VTestDetail.class));
	}

	/**
	 * 得到每份试卷的错误率
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<VQuestion> getWrong() throws SQLException {
		String sql = "select sum((t_question.num-t_question.successNum)/t_question.num) as wrong, t_testmain.realScore "
				+ "from t_question,t_testmain,t_test_detail "
				+ "where t_testmain.pk_test_main_id=t_test_detail.testMain_pk_test_main_id "
				+ "and t_test_detail.question_fk_question_id=t_question.fk_question_id "
				+ "and t_testmain.realScore!='0' " + "and t_testmain.useTime>='5'"
				+ "GROUP BY t_testmain.pk_test_main_id";
		List<VQuestion> list = qr.query(sql, new BeanListHandler<VQuestion>(VQuestion.class));
		return list;
	}

	/**
	 * 得到每份试卷的得分
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<VTestDetail> getScore() throws SQLException {
		String sql = "select sum((t_question.num-t_question.successNum)/t_question.num) as wrong, t_testmain.realScore "
				+ "from t_question,t_testmain,t_test_detail "
				+ "where t_testmain.pk_test_main_id=t_test_detail.testMain_pk_test_main_id "
				+ "and t_test_detail.question_fk_question_id=t_question.fk_question_id "
				+ "and t_testmain.realScore!='0' " + "and t_testmain.useTime>='5'"
				+ "GROUP BY t_testmain.pk_test_main_id";
		List<VTestDetail> list = qr.query(sql, new BeanListHandler<VTestDetail>(VTestDetail.class));
		return list;
	}

	/**
	 * 获取指定ID题目的得分情况，用来计算该题标准差
	 * 
	 * @param questionID
	 * @return
	 * @throws SQLException
	 */
	public List<VTestDetail> getStdDeviation(Long questionID) throws SQLException {
		String sql = "select  t_testmain.student_memberId,t_test_detail.answer,t_test_detail.stuAnswer,t_test_detail.score"
				+ " from t_test_detail,t_testmain"
				+ " where t_testmain.pk_test_main_id=t_test_detail.testMain_pk_test_main_id"
				+ " and t_test_detail.question_fk_question_id=?" + " and t_test_detail.stuAnswer !=''";
		Object[] params = { questionID };
		List<VTestDetail> list = qr.query(sql, new BeanListHandler<VTestDetail>(VTestDetail.class), params);

		return list;
	}

	/**
	 * 根据试卷ID得到题目ID列表
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<VTestDetail> getQstListById(int testID) throws SQLException {
		String sql = "select t_test_detail.question_fk_question_id from t_testmain,t_test_detail "
				+ " where t_testmain.pk_test_main_id=t_test_detail.testMain_pk_test_main_id"
				+ " and t_testmain.pk_test_main_id=?" + " ORDER BY t_test_detail.sequence ASC";
		Object[] params = { testID };
		List<VTestDetail> list = qr.query(sql, new BeanListHandler<VTestDetail>(VTestDetail.class), params);

		return list;
	}

	/**
	 * 得到所有过滤过的试卷ID
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<VTestMain> getAllTestId() throws SQLException {
		String sql = "select t_testmain.pk_test_main_id " + "from t_question,t_testmain,t_test_detail "
				+ "where t_testmain.pk_test_main_id=t_test_detail.testMain_pk_test_main_id "
				+ "and t_test_detail.question_fk_question_id=t_question.fk_question_id "
				+ "and t_testmain.realScore!='0' " + "and t_testmain.useTime>='5'"
				+ "GROUP BY t_testmain.pk_test_main_id";
		List<VTestMain> list = qr.query(sql, new BeanListHandler<VTestMain>(VTestMain.class));

		return list;
	}

	/**
	 * 根据试卷的题目ID得到做过这道题的所有学生得分情况
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<VTestMain> getStuIdAndScoreByQstId(VTestDetail testdetail) throws SQLException {
		String sql = "select  t_testmain.student_memberId,t_test_detail.answer,t_test_detail.stuAnswer, "
				+ " case when t_test_detail.stuanswer != t_test_detail.answer  then t_test_detail.score='0' else t_test_detail.score end AS realscore "
				+ " from t_test_detail,t_testmain  "
				+ " where  t_testmain.pk_test_main_id=t_test_detail.testMain_pk_test_main_id "
				+ " and t_test_detail.question_fk_question_id=? " + " and t_test_detail.stuAnswer !='' "
				+ " ORDER BY student_memberId ";
		Object[] params = { testdetail.getQuestion_fk_question_id() };
		List<VTestMain> stuIdList = qr.query(sql, new BeanListHandler<VTestMain>(VTestMain.class), params);
		List<VTestDetail> scoreList = qr.query(sql, new BeanListHandler<VTestDetail>(VTestDetail.class), params);

		for (int i = 0; i < stuIdList.size(); i++) {
			stuIdList.get(i).setTestdetail(scoreList.get(i));
		}

		return stuIdList;
	}

	/**
	 * 通过学生ID得到这个学生的阶段平均分（普通使用版）
	 * 
	 * @param testmain
	 * @return 该学生的阶段平均分
	 * @throws SQLException
	 */
	public List<VTestMain> getStuAvgScoreById(VTestMain testmain) throws SQLException {
		String sql = "select t_testmain.pk_test_main_id,avg(t_testmain.realScore)as realscore "
				+ " from t_scope,t_course,t_coursetype ,t_member_t_member ,t_course_t_member,t_testmain  "
				+ " where t_course_t_member.courses_courseId=t_course.courseId "
				+ " and t_member_t_member.students_memberId=t_course_t_member.members_memberId "
				+ " and t_coursetype.courseTypeId=t_course.courseTypeID "
				+ " and t_coursetype.grade_pk_scope_id=t_scope.pk_scope_id "
				+ " and t_testmain.student_memberId=t_member_t_member.students_memberId "
				+ " and t_member_t_member.students_memberId=? " + " and t_scope.name='初二（上）' "
				+ " and t_testmain.`subject`='语文' " + " group by t_testmain.pk_test_main_id";

		Object[] params = { testmain.getStudent_memberId() };

		return qr.query(sql, new BeanListHandler<VTestMain>(VTestMain.class), params);
	}

	/**
	 * 根据试卷题目ID得到学生的阶段平均分（待修正）
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<VTestMain> getStuAvgScoreOfScope() throws SQLException {
		String sql = "select t_member_t_member.students_memberId,avg(t_testmain.realScore) as realscore"
				+ " from t_scope,t_course,t_coursetype ,t_member_t_member ,t_course_t_member,t_testmain t_testmain"
				+ " where t_course_t_member.courses_courseId=t_course.courseId"
				+ " and t_member_t_member.students_memberId=t_course_t_member.members_memberId"
				+ " and t_coursetype.courseTypeId=t_course.courseTypeID"
				+ " and t_coursetype.grade_pk_scope_id=t_scope.pk_scope_id"
				+ " and  t_member_t_member.students_memberId=t_testmain.student_memberId"
				+ " and t_member_t_member.students_memberId in (select DISTINCT t_testmain.student_memberId from t_testmain,t_test_detail"
				+ " where  t_testmain.pk_test_main_id=t_test_detail.testMain_pk_test_main_id"
				+ " and t_test_detail.question_fk_question_id='02'" + " and t_test_detail.stuAnswer !='' )"
				+ " and t_scope.name='初二（上）'" + " and t_testmain.`subject`='语文'" + " and t_testmain.realScore!='0'"
				+ " GROUP BY t_member_t_member.students_memberId";
		List<VTestMain> avgScoreList = qr.query(sql, new BeanListHandler<VTestMain>(VTestMain.class));

		return avgScoreList;
	}

	/**
	 * 根据课程名与年级查到这个范围内所有出现的知识点
	 * 
	 * @param courseName
	 *            课程名
	 * @param year
	 *            年级
	 * @return 指定范围的所有知识点列表
	 */
	public List<VQuestion> getKeywordOneYear(String courseName, String year) {
		String getKeyword = "select distinct  keyword" + " from t_question " + " where fk_question_id in "
				+ " (select question_fk_question_id from t_test_detail" + " where testmain_pk_test_main_id in"
				+ " (select pk_test_main_id from t_testmain  "
				+ "      where  subject like ? and grandient_grandientId in"
				+ " (select DISTINCT t_grandient.grandientId as `grandientId`"
				+ " from t_grandient,t_grandient_t_scope,t_scope as `chapter`,t_scope as `unit`,t_scope as `grade`"
				+ " where  t_grandient.grandientId=t_grandient_t_scope.t_grandient_grandientId"
				+ " and t_grandient_t_scope.scopes_pk_scope_id=chapter.pk_scope_id"
				+ " and chapter.fk_parent_id=unit.pk_scope_id" + " and unit.fk_parent_id=grade.pk_scope_id"
				+ " and grade.name like ? and keyword <> ''" + " )))";
		Object[] params = { courseName, year };
		List<VQuestion> keywordList = null;
		try {
			keywordList = qr.query(getKeyword, new BeanListHandler<VQuestion>(VQuestion.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return keywordList;
	}

	/**
	 * 根据课程名、年级查询这个范围内的所有试卷ID
	 * 
	 * @param courseName
	 *            课程名
	 * @param year
	 *            年级
	 * @return 指定范围的所有试卷ID
	 */
	public List<VTestMain> getPaperIdOneYear(String courseName, String year) {
		String getPaperId = "select distinct pk_test_main_id  from  t_testmain where" + " subject like ? "
				+ " and  grandient_grandientId in(select DISTINCT t_grandient.grandientId as `grandientId`"
				+ " from t_grandient,t_grandient_t_scope,t_scope as `chapter`,t_scope as `unit`,t_scope as `grade`"
				+ " where  t_grandient.grandientId=t_grandient_t_scope.t_grandient_grandientId"
				+ " and t_grandient_t_scope.scopes_pk_scope_id=chapter.pk_scope_id"
				+ " and chapter.fk_parent_id=unit.pk_scope_id" + " and unit.fk_parent_id=grade.pk_scope_id"
				+ " and grade.name like ?" + " )";// 指定课程名、年级查询所有试卷ID
		Object[] params = { courseName, year };
		List<VTestMain> paperIdList = null;

		try {
			paperIdList = qr.query(getPaperId, new BeanListHandler<VTestMain>(VTestMain.class), params);// 得到试卷ID集
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return paperIdList;// 试卷ID集
	}

	/**
	 * 根据指定试卷ID得到这份试卷的题目列表
	 * 
	 * @param testmain
	 * @return 题目列表
	 */
	public List<VTestDetail> getQuestionIdByPaperId(VTestMain testmain) {
		String getQuestionId = "select t_test_detail.question_fk_question_id from t_testmain,t_test_detail"
				+ " where t_testmain.pk_test_main_id=t_test_detail.testMain_pk_test_main_id "
				+ " and t_testmain.pk_test_main_id=? " + " ORDER BY t_test_detail.sequence ASC";
		Object[] params = { testmain.getPk_test_main_ID() };
		List<VTestDetail> questionIdList = null;
		try {
			questionIdList = qr.query(getQuestionId, new BeanListHandler<VTestDetail>(VTestDetail.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return questionIdList;
	}

	/**
	 * 根据试卷ID得到这份试卷的所有知识点
	 * @param testmain
	 * @return 知识点列表
	 */
	public List<VQuestion> getKeywordByPaperId(VTestMain testmain) {
		String paperKeyword = "select distinct keyword from t_question "
				+ "where fk_question_id in(Select question_fk_question_id from t_test_detail "
				+ "where testmain_pk_test_main_id=?) and keyword  <>'' ";
		Object[] params = {testmain.getPk_test_main_ID()};
		List<VQuestion> paperKeywordList = null;
		try {
			paperKeywordList = qr.query(paperKeyword, new BeanListHandler<VQuestion>(VQuestion.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return paperKeywordList;
	}
	
	/**
	 * 根据学生ID,学科查询该学生所有错题的知识点
	 * @param studentID 学生ID
	 * @param courseName 学科
	 * @return 该学生所有错题的知识点List
	 */
	public List<String> getWrongKeywordsOfStudent(Integer studentID,String courseName){
		String sql = 
			"SELECT DISTINCT keyword FROM " + 
			"t_question WHERE keyword!='' AND fk_question_id in " +
			"(SELECT question_fk_question_id FROM t_test_detail " +
			"where stuAnswer!=answer AND testMain_pk_test_main_id in " + 
			"(SELECT pk_test_main_id from t_testmain " +
			"WHERE `subject`=? and student_memberId=? and realScore!='0' " +
			")) ";
		
		Object[] params = {courseName, studentID};
		List<String> wrongKewordsList = new ArrayList<String>();
		try {
			wrongKewordsList = (List)qr.query(sql, new ColumnListHandler("keyword"),params);
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		
		return wrongKewordsList;
	}
	
	/**
	 * 根据学生ID,学科，年级查询该学生错题知识点
	 * @param studentID
	 * @param courseName
	 * @param grade
	 * @return 该学生该科目该年级下错题的知识点List
	 */
	public List<String> getWrongKeywordsOfStudentByGrade(Integer studentID,String courseName,String grade){
		String sql = 
				"select distinct t_question.scope_pk_scope_id, keyword " +
				"from t_question " + 
				"where keyword !='' and fk_question_id in " + 
				    "(select question_fk_question_id from t_test_detail " +           
				"where stuAnswer != answer and testmain_pk_test_main_id in " +
				"(select pk_test_main_id from t_testmain " +  
				         "where subject =? and student_memberId=? and grandient_grandientId in " +
				"(select DISTINCT t_grandient.grandientId as `grandientId` " +
				"from t_grandient,t_grandient_t_scope,t_scope as `chapter`,t_scope as `unit`,t_scope as `grade` " +
				"where  t_grandient.grandientId=t_grandient_t_scope.t_grandient_grandientId " +
				"and t_grandient_t_scope.scopes_pk_scope_id=chapter.pk_scope_id " +
				"and chapter.fk_parent_id=unit.pk_scope_id " +
				"and unit.fk_parent_id=grade.pk_scope_id " +
				"and grade.name like ?))) ";
			
		String likeGrade = grade + "%";
		Object[] params = {courseName, studentID,likeGrade};
		List<String> wrongKewordsList = new ArrayList<String>();
		try {
			wrongKewordsList = (List)qr.query(sql, new ColumnListHandler("keyword"),params);
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		
		return wrongKewordsList;
	}

}
