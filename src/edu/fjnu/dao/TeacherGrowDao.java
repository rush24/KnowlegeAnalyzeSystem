package edu.fjnu.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;
import edu.fjnu.domain.StudentPr;
import edu.fjnu.domain.VTeaacherStudent;

public class TeacherGrowDao {
	private QueryRunner qr = new TxQueryRunner();//执行数据库操作并存储返回的结果
	/**
	 * 传入老师id得到学生名字的dao
	 * @param teacherId
	 * @return
	 */
	public List<StudentPr> getStudentList(String teacherId)
	{
		try {
			String sql = "SELECT DISTINCT name from t_excel where coach=?";
			Object[] params = {teacherId};
			
			return  qr.query(sql, new BeanListHandler<StudentPr>(StudentPr.class),params);
		} catch (Exception e) {
			System.err.println("getStudentList方法发生异常");
			throw new RuntimeException();
		}	
	}

}
