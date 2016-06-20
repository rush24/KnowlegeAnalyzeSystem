package edu.fjnu.test.dao;

import org.junit.Test;

import edu.fjnu.dao.KeywordDao;

public class TestKeywodDao {

	KeywordDao keywordDao = new KeywordDao();
	
	@Test
	public void testgetGradeByKeywordName(){
		String s = keywordDao.getGradeByKeywordName("坐井观天");
		System.out.println(s);
	}
}
