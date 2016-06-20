package edu.fjnu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import cn.itcast.jdbc.TxQueryRunner;
import edu.fjnu.domain.Student;
import edu.fjnu.domain.StudentPr;
import edu.fjnu.domain.VScope;

/**
 * 实现StudentDao接口 操作数据库的学生表
 * 
 * @author vengeance
 *
 */
public class StudentDao {
	private QueryRunner qr = new TxQueryRunner();// 执行数据库操作并存储返回的结果

	public List<Student> query(Student student) {
		try {
			String sql = "select * from student where sname=?";
			Object[] params = { student.getSname() };

			return qr.query(sql, new BeanListHandler<Student>(Student.class), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 验证登陆页面输入的学生信息是否正确
	 */
	public Student checkInfo(Student student) {
		final int type= 1;//学生类型为1
		try {
			String sql = "select * from t_member where memberID=? and password =? and TYPE=?";
			System.out.println(student.getMemberID());
			Object[] params = { student.getMemberID(),student.getPassword(),type};
			return qr.query(sql, new BeanHandler<Student>(Student.class), params);// 验证信息
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据学生登录的ID查询学生的基本信息，并返回给service
	 * 
	 * @param student
	 * @return 学生信息
	 */
	public Student studentInfo(Student student) {
		try {
			String sql ="select * from t_member where memberID=?";
			Object[] params = { student.getMemberID() };
			return qr.query(sql, new BeanHandler<Student>(Student.class), params);// 执行查询方法
		} catch (Exception e) {
			System.err.println("查询学生表信息异常");
			throw new RuntimeException();
		}
	}

	/**
	 * 根据学生ID查询该学生所做过试卷的所有年级
	 * 
	 * @param studentId
	 * @return
	 */
	public List<VScope> getStudentGrade(int studentId) {
		try {
			String sql = "select DISTINCT t_scope.name from t_scope,t_course,t_coursetype ,t_member_t_member ,t_course_t_member where"
					+ "	t_course_t_member.courses_courseId=t_course.courseId"
					+ "	and t_member_t_member.students_memberId=t_course_t_member.members_memberId"
					+ "	and t_coursetype.courseTypeId=t_course.courseTypeID"
					+ "	and t_coursetype.grade_pk_scope_id=t_scope.pk_scope_id"
					+ "	and t_member_t_member.students_memberId=?";
			Object[] params = { studentId };
			return qr.query(sql, new BeanListHandler<VScope>(VScope.class), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据学生姓名得到学生学过的年级
	 * 
	 * @param stuName
	 * @return
	 */
	public List<StudentPr> getStudentListByName(String stuName) {
		String sql = "select DISTINCT classyear from t_excel,t_member " + "where t_excel.`name`=?";
		Object[] params = { stuName };
		List<StudentPr> stuList = new ArrayList<StudentPr>();

		try {
			stuList = qr.query(sql, new BeanListHandler<StudentPr>(StudentPr.class), params);
		} catch (SQLException e) {
			System.err.println("查询学生所学年级出错(excel)");
			return null;
		}

		return stuList;
	}
	
	/**
	 * 得到所有学生的ID
	 * @return
	 */
	public List<Integer> getAllStudentID(){ 
		String sql = "SELECT DISTINCT students_memberId FROM t_member_t_member";
		List<Integer> studentIDList = new ArrayList<Integer>();
		try {
			studentIDList = (List)qr.query(sql, new ColumnListHandler(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentIDList;
	}

}
