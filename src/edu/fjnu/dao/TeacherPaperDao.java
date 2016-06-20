/**
 * 
 */
package edu.fjnu.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import cn.itcast.jdbc.TxQueryRunner;
import edu.fjnu.domain.*;

/**
 * 教师端操作试卷分析模块的dao
 * @author Administrator
 *
 */
public class TeacherPaperDao {
	private QueryRunner qr = new TxQueryRunner();//执行数据库操作并存储返回的结果
	
	
	/**
	 * 通过老师id得到 所教学生的id
	 * @param teacherId
	 * @return
	 */
	public List<VTeaacherStudent> studentInfo(int teacherId) {
		try {
			String sql = "select DISTINCT students_memberId from t_member_t_member where " 
						+" t_member_memberId=?"
						+" ORDER BY students_memberId ASC";
			Object[] params = {teacherId};
			
			return  qr.query(sql, new BeanListHandler<VTeaacherStudent>(VTeaacherStudent.class),params);
		} catch (Exception e) {
			System.err.println("查询学生表信息异常");
			throw new RuntimeException();
		}	
	}
	
	/**
	 * 根据教师id得到 所教 学生的所有年级
	 * @param teacherId
	 * @return
	 */
	public List<VScope> searchGradeByTeacherId(int teacherId) {
		try {
			String sql = "select DISTINCT t_scope.name from t_scope,t_course,t_coursetype where " 
						+"	 t_coursetype.courseTypeId=t_course.courseTypeID "
						+"	and t_coursetype.grade_pk_scope_id=t_scope.pk_scope_id "
						+"	and  t_course.coachID=? ";
			Object[] params = {teacherId};
			
			return  qr.query(sql, new BeanListHandler<VScope>(VScope.class),params);
		} catch (Exception e) {
			System.err.println("查询学生年级信息异常");
			throw new RuntimeException();
		}	
	}
	
	/**
	 * 查询某个老师教的这些学生限定某个年级里的某个科目的学生ID
	 * @param teacherId
	 * @param courseName
	 * @param subjuect  //年级
	 * @return
	 */
	public List<VTeaacherStudent> searchStuId(int teacherId,String courseName,String subjuect) {
		try {
			String sql = "select DISTINCT t_member_t_member.students_memberId "
							+"	from t_member_t_member,t_testmain,t_scope,t_coursetype,t_course,t_course_t_member "
							+"	where t_testmain.student_memberId=t_member_t_member.students_memberId "
							+"	and t_course.courseId=t_course_t_member.courses_courseId "
							+"	and t_course_t_member.members_memberId=t_member_t_member.students_memberId "
							+"	and t_coursetype.courseTypeId=t_course.courseTypeID "
							+"	and t_coursetype.grade_pk_scope_id=t_scope.pk_scope_id "
							+"	and t_member_t_member.t_member_memberId=? "
							+"	and t_testmain.`subject`=? "
							+"	 and t_scope.`name`=? "
							+"	ORDER BY t_member_t_member.students_memberId ASC";
			Object[] params = {teacherId,courseName,subjuect};
			
			return  qr.query(sql, new BeanListHandler<VTeaacherStudent>(VTeaacherStudent.class),params);
		} catch (Exception e) {			
			throw new RuntimeException();
		}	
	}
	
	
	
	
	
	
	
	
	
	
	
}
