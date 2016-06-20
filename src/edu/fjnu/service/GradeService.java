package edu.fjnu.service;

import java.sql.SQLException;
import java.util.List;

import edu.fjnu.dao.GradeDao;
import edu.fjnu.domain.Grade;
import edu.fjnu.domain.Scourse;
import edu.fjnu.domain.Sscore;

/**
 * 获取学生成绩相关数据
 * @author vengeance
 *
 */
public class GradeService {
	GradeDao gradeDao = new GradeDao();//操作数据库对象
	
	public List<Grade> getPreGrade(String name,String classyear,String subject) throws SQLException{
		return gradeDao.getPreGrade(name, classyear, subject);
	}
	
	public List<Grade> getLatGrade(String name,String classyear,String subject) throws SQLException{
		return gradeDao.getLatGrade(name, classyear, subject);
	}
	
	public List<Grade> getScorePr(String name,String classyear,String subject) throws SQLException{
		return gradeDao.getScorePr(name, classyear, subject);
	}
	
	
	/**
	 * @param scourse
	 * @return 返回平均分走势
	 */
	public List<Sscore> getAvgScoreList(Scourse scourse){
		return gradeDao.getAvgScoreList(scourse);
	}
	
	/**
	 * @param scourse
	 * @return 返回最高分走势
	 */
	public List<Sscore> getMaxScoreList(Scourse scourse){
		return gradeDao.getMaxScoreList(scourse);
	}
	
	/**
	 * 
	 * @param scourse
	 * @return 返回最低分走势
	 */
	public List<Sscore> getMinScoreList(Scourse scourse){
		return gradeDao.getMinScoreList(scourse);
	}
}
