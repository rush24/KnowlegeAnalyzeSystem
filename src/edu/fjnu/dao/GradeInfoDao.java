package edu.fjnu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;
import edu.fjnu.domain.GradeInfo;

/**
 * 根据学生信息查询成绩履历信息
 * 
 * @author vengeance
 *
 */
public class GradeInfoDao {
	GradeInfo gradeInfo = new GradeInfo();// 履历信息对象
	QueryRunner qr = new TxQueryRunner();

	/**
	 * 根据指定范围查询前测履历信息
	 * 
	 * @param stuName
	 * @param courseName
	 * @param classYear
	 * @return 履历信息对象
	 */
	public List<GradeInfo> getGradeBefByScope(String stuName, String courseName, String classYear) {
		List<GradeInfo> gradeBefList = new ArrayList<GradeInfo>();
		Object[] params = { stuName, classYear, courseName };
		String sql = "SELECT progress,score from t_excel " + "where name=?" + " and classyear=?" + " and `subject`=?"
				+ " and examtype in('前测','S1测验','S2测验')" + " ORDER BY progress ASC";
		try {
			gradeBefList = qr.query(sql, new BeanListHandler<GradeInfo>(GradeInfo.class), params);
		} catch (SQLException e) {
			System.err.println("查询前测履历信息出错");
			return null;
		}
		return gradeBefList;
	}

	/**
	 * 根据指定范围查询后测履历信息
	 * 
	 * @param stuName
	 * @param courseName
	 * @param classYear
	 * @return 履历信息对象
	 */
	public List<GradeInfo> getGradeAftByScope(String stuName, String courseName, String classYear) {
		List<GradeInfo> gradeAftList = new ArrayList<GradeInfo>();
		Object[] params = { stuName, classYear, courseName };
		String sql = "SELECT progress,score from t_excel " + "where name=?" + " and classyear=?" + " and `subject`=?"
				+ " and examtype='后测'" + " ORDER BY progress ASC";
		try {
			gradeAftList = qr.query(sql, new BeanListHandler<GradeInfo>(GradeInfo.class), params);
		} catch (SQLException e) {
			System.err.println("查询后测履历信息出错");
			return null;
		}
		return gradeAftList;
	}

	/**
	 * 根据范围得到成绩和PR的关系
	 * 
	 * @param stuName
	 * @param courseName
	 * @param classYear
	 * @return 履历信息对象
	 */
	public List<GradeInfo> getGradeAndPr(String stuName, String courseName, String classYear) {
		List<GradeInfo> gradePrList = new ArrayList<GradeInfo>();
		Object[] params = { stuName, classYear, courseName };
		String sql = "select score ,PR from t_excel " + "where name=?" + "and classyear=?" + "and `subject`=?";

		try {
			gradePrList = qr.query(sql, new BeanListHandler<GradeInfo>(GradeInfo.class), params);
		} catch (SQLException e) {
			System.err.println("查询后测履历信息出错");
			return null;
		}
		return gradePrList;
	}
}
