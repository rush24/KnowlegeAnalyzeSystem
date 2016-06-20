package edu.fjnu.service;

import java.util.List;

import edu.fjnu.dao.SscoreDao;
import edu.fjnu.dao.TeacherDao;
import edu.fjnu.domain.Student;
import edu.fjnu.domain.Teacher;

/**
 * 处理老师业务信息
 * @author vengeance
 *
 */
public class TeacherInfoService {
	private TeacherDao teacherDao = new TeacherDao();
	private SscoreDao sscoreDao = new SscoreDao();
	
	/**
	 * 判断老师登录信息是否正确
	 * @param teacher
	 * @return
	 */
	public boolean checkInfo(Teacher teacher){
		if(teacherDao.checkInfo(teacher) != null){
			return true;
		}
		return false;
	}
	/**
	 * 获取老师基本信息
	 * @param teacher
	 * @return 老师基本信息
	 */
	public Teacher teacherInfo(Teacher teacher){
		return teacherDao.teacherInfo(teacher);
	}
	
	/**
	 * 通过teacherID得到courseID
	 * @param courseID
	 * @return
	 */
	public String getCourseIDByTeacherID(String teacherID){
		return sscoreDao.getCourseIDByTeacherID(teacherID);
	}
	
	/**
	 * 得到学生列表
	 * @param teacherID
	 * @return 这个老师所教的学生
	 */
	public List<Student> getStuList(String teacherID){
		return teacherDao.getStuList(teacherID);
	}
}
