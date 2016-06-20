package edu.fjnu.test.service;

import java.util.List;

import org.junit.Test;

import edu.fjnu.service.RelationService;

public class TestRelationService {

	private RelationService rs = new RelationService();

//	@Test
//	public void testGetSingleGradeRelationListForRtool(){
//		List<String> list = rs.getSingleGradeRelationListForRtool("语文", "一年级");
//		for(String s : list){
//			System.out.println(s);
//		}
//	}
//	
//	@Test
//	public void testGetCrossGradesStudentIDs(){
//		List<Integer> list = rs.getCrossGradesStudentIDs("语文");
//		for(Integer s : list){
//			System.out.println(s);
//		}
//	}
	
	@Test
	public void testSaveUserUploadDataIntoDatabase(){
		rs.saveUserUploadDataIntoDatabase("2560", "数学");
	}
	@Test
	public void testDeleteUploadDataInDatabase(){
		rs.deleteUploadDataInDatabase("2560", "语文");
	}
}
