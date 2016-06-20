package edu.fjnu.test.service;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import edu.fjnu.service.RelationshipService;

public class TestRelationshipService {

	private RelationshipService rs = new RelationshipService();
	
	@Test
	public void testCreateCrossGradesRelationListCSV(){
//		rs.createCrossGradesRelationListCSV("语文");
//		rs.createCrossGradesRelationListCSV("数学");
//		rs.createCrossGradesRelationListCSV("英文");
	}
	@Test
	public void testCreateCMRelationCSVForRtool(){
		try {
			rs.createCMRelationCSVForRtool("数学", "2560");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	

}
