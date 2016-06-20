package edu.fjnu.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.fjnu.dao.QuestionDao;
import edu.fjnu.domain.VQuestion;
import edu.fjnu.domain.VTestDetail;

/**
 * 处理试卷分析四个度的业务
 * @author vengeance
 *
 */
public class PaperService {
	QuestionDao questionDao = new QuestionDao();
	
	/**
	 * 得到所有试卷的难度
	 * @return 返回试卷难度数组
	 * @throws SQLException
	 */
	public double[] getWrongArray() throws SQLException{
		List<VQuestion> list = new ArrayList<VQuestion>(questionDao.getWrong());//从数据库按顺序得到所有试卷的难度
		double[] arrayWrong = new double[list.size()];
		
		for(int i=0; i<list.size(); i++){
			arrayWrong[i] = list.get(i).getWrong();
		}
		
		return arrayWrong;
	}
	
	/**
	 * 得到所有试卷的成绩
	 * @return 返回成绩数组
	 * @throws SQLException
	 */
	public double[] getRealScoreArray() throws SQLException{
		List<VTestDetail> list = new ArrayList<VTestDetail>(questionDao.getScore());//从数据库按顺序得到所有试卷的成绩
		double[] arrayScore = new double[list.size()];
		
		for(int i=0; i<list.size(); i++){
			arrayScore[i] = list.get(i).getRealScore();
		}
		return arrayScore;
	}
	
	/**
	 * 得到一道题目的标准差
	 * @return 一道题目的标准差
	 * @throws SQLException
	 */
	public double getStdDevisionById(Long questionID) throws SQLException {
		List<VTestDetail> list = questionDao.getStdDeviation(questionID);
		return calStdDevision(list);
	}
	
	/**
	 * 计算一道题目的标准差
	 * @param list
	 * @return 一道题目的标准差
	 */
	private double calStdDevision(List<VTestDetail> list){
		double length = list.size();
		double sum = 0;
		double avg = 0;
		double cnt = 0;
		
		if(length == 0) return 0;
		for(int i=0; i<length; i++){
			sum += list.get(i).getScore();
		}
		avg = sum/length;
		
		for(int i=0; i<length; i++){
			cnt += Math.abs((list.get(i).getScore() - avg) * (list.get(i).getScore() - avg));
		}
		
		return Math.sqrt(cnt/length);
	}
	
	/**
	 * 根据试卷ID得到信度
	 * @param testID
	 * @return
	 */
	public double getStdByTestId(int testID){
		double cnt = 0;
		try {
			List<VTestDetail> list = new ArrayList<VTestDetail>(questionDao.getQstListById(testID));
			
			for(int i=0; i<list.size(); i++){
				cnt += getStdDevisionById(list.get(i).getQuestion_fk_question_id());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
