package edu.fjnu.service;

import edu.fjnu.dao.StudentDao;
import edu.fjnu.domain.Student;

/**
 * 处理学生业务信息
 * 
 * @author vengeance
 *
 */
public class StudentInfoService {
	private StudentDao studentDao = new StudentDao();

	/**
	 * 判断学生登录信息是否正确
	 * 
	 * @param student
	 * @return
	 */
	public boolean checkInfo(Student student) {
		if (studentDao.checkInfo(student) != null) {
			return true;
		}
		return false;
	}

	/**
	 * 得到学生基本信息
	 * 
	 * @param student
	 * @return 学生基本信息
	 */
	public Student studentInfo(Student student) {
		return studentDao.studentInfo(student);
	}


}
