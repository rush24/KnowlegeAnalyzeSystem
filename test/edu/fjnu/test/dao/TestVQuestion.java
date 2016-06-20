package edu.fjnu.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.fjnu.dao.QuestionDao;
import edu.fjnu.domain.VQuestion;
import edu.fjnu.domain.VScope;
import edu.fjnu.service.PaperService;

public class TestVQuestion {
	VQuestion question = new VQuestion();
	QuestionDao questionDao = new QuestionDao();
	PaperService pservice = new PaperService();

//	@Test
//	public void searchWrong() throws SQLException {
//		question = questionDao.getHard();
//		System.out.println(question.getWrong());
//	}
//
//	@Test
//	public void searchScoreBySubjectId() throws SQLException {
//		System.out.println(questionDao.getScoreBySubjectId().getScore());
//	}
//	
//	@Test
//	public void getWrongRealScore() throws SQLException {
//		List<VQuestion> listWrong = new ArrayList(questionDao.getWrong());
//		List<VTestDetail> listScore = new ArrayList(questionDao.getScore());
//		
//	}
//	
//	@Test
//	public void testWrongAndRealScore() throws SQLException {
//		PaperService pservice = new PaperService();
//		double[] arrayWrong = pservice.getWrongArray();
//		double[] arrayScore = pservice.getRealScoreArray();
//		
//		System.out.println(Arrays.toString(arrayWrong));
//		System.out.println(Arrays.toString(arrayScore));
//	}
//	@Test
//	public void getStdDevisionById() throws SQLException {
//		List<VTestDetail> list = questionDao.getStdDeviation();
//		double length = list.size();
//		double sum = 0;
//		double cnt = 0;
//		
//		for (int i=0; i<length; i++){
//			sum += list.get(i).getScore();
//		}
//		
//		double avg = sum / length;
//		
//		for(int i=0; i<length; i++){
//			cnt += (list.get(i).getScore() - avg) * (list.get(i).getScore() - avg);
//		}
//		
//		System.out.println(Math.sqrt(cnt));
//	}
//	
//	@Test
//	public void getStdDevision() throws SQLException {
//		System.out.println(pservice.getStdDevisionById());
//	}
	
	@Test
	public void testGetWrongKeywordsOfStudent(){
		List<String> wrongKeywordsList = questionDao.getWrongKeywordsOfStudent(1128, "语文");
		System.out.println(wrongKeywordsList);

	}
	
}
