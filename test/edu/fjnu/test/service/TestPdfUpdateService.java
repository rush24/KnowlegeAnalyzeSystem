package edu.fjnu.test.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import edu.fjnu.service.GradesPdfService;

public class TestPdfUpdateService {

	private GradesPdfService gps = new GradesPdfService();
	@Test
	public void testGetPreScore(){
		try {
			List<String[]> stringArrList = gps.getPreScore("林彦希", "三年级（上）", "语文");
			System.out.println(stringArrList.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
