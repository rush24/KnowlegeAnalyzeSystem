package edu.fjnu.dao;

import java.sql.SQLException;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;
import edu.fjnu.domain.Sscore;

public class SscoreDao {
	private QueryRunner qr = new TxQueryRunner();//执行数据库操作并存储返回的结果
	
	/**
	 * 通过teacherID得到courseID
	 * @param courseID
	 * @return
	 */
	public String getCourseIDByTeacherID(String teacherID) {
		try{
			String sql = "select courseID from stcourse where teacherID=? limit 1";
			Object[] params = {teacherID};
			return (String) qr.query(sql, new ScalarHandler(),params);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 通过studentID得到其S1阶段（后期可更改为最近阶段)总分
	 * @param studentID
	 * @return
	 */
	public Sscore findSumOfStudent(String studentID){
		try{
			String sql= "select SUM(score)as score FROM student ,sscore,scourse,testmain,stcourse where " 
						+	"testmain.testID=sscore.testID "
						+	"AND sscore.courseID=stcourse.courseID "
						+	"and sscore.studentID=student.studentID "
						+	"and student.studentID=stcourse.studentID "
						+	"and scourse.courseID=stcourse.courseID "
						+	"AND testmain.subject in ('语文','数学','英语') "
						+	"and testmain.progress='S1' "
						+	"and student.studentID=? ";
			return qr.query(sql, new BeanHandler<Sscore>(Sscore.class),studentID);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 通过courseID查找指定课程最近一次成绩
	 * @param courseID
	 * @return
	 */
	public List<Sscore> lastScores(String courseID) {
		try{
			String sql = "select score from sscore where courseID=? "
							+ "and testID in(select max(testID) from sscore where courseID=? )";
			Object[] params = {courseID,courseID};
			return qr.query(sql, new BeanListHandler<Sscore>(Sscore.class), params);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过courseID查找该课程下历次学生的最高分（按试卷ID排序）（后期还需根据班级）
	 * @param courseID
	 * @return
	 */
	public List<Sscore> maxScores(String courseID) {
		try{
			String sql = "select max(score) as score ,testID from sscore "
							+ "where courseID=? "
						+ "group by testID";
			Object[] params = {courseID};
			return qr.query(sql, new BeanListHandler<Sscore>(Sscore.class), params);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 通过courseID查找该课程下历次学生的最低分（按试卷ID排序）（后期还需根据班级）
	 * @param courseID
	 * @return
	 */
	public List<Sscore> minScores(String courseID) {
		try{
			String sql = "select min(score) as score,testID from sscore "
					+ "where courseID=? "
				+ "group by testID";
			Object[] params = {courseID};
			return qr.query(sql, new BeanListHandler<Sscore>(Sscore.class), params);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 通过courseID查找该课程下历次学生的平均分（按试卷ID排序）（后期还需根据班级）
	 * @param courseID
	 * @return
	 */
	public List<Sscore> avgScores(String courseID) {
		try{
			String sql = "select avg(score) as score,testID from sscore "
					+ "where courseID=? "
				+ "group by testID";
			Object[] params = {courseID};
			return qr.query(sql, new BeanListHandler<Sscore>(Sscore.class), params);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过courseID和studentID查找指定学生历次分数（按试卷ID排序）（后期还需根据班级）
	 * @param courseID
	 * @param stidentID
	 * @return
	 */
	public List<Sscore> stuScores(String courseID, String studentID) {
		try{
			String sql = "select * from sscore "
							+ "where studentID=? and courseID=? "
						+ "order by testID";
			Object[] params = {studentID,courseID};
			return qr.query(sql, new BeanListHandler<Sscore>(Sscore.class), params);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
