package edu.fjnu.test.service;

import java.util.List;

import org.junit.Test;

import edu.fjnu.dao.GradeInfoDao;
import edu.fjnu.domain.GradeInfo;
import edu.fjnu.service.GradeInfoService;

public class TestGradeInfo {
	GradeInfoDao gradeInfoDao = new GradeInfoDao();
	GradeInfoService gradeInfoService = new GradeInfoService();
	
	@Test
	public void test1(){
		String stuName = "林彦希";
		String courseName = "语文"; 
		String classYear = "三年级（上）";
//		System.out.println(gradeInfoService.getGradeBef(stuName, courseName, classYear));
		List<GradeInfo> gradeBef = gradeInfoService.getGradeBef(stuName, courseName, classYear);
		List<GradeInfo> gradeAft = gradeInfoService.getGradeAft(stuName, courseName, classYear);
		List<GradeInfo> gradeCut = gradeInfoService.getGradeCut(stuName, courseName, classYear);
		List<GradeInfo> gradeHigh = gradeInfoService.getGradeHigh(stuName, courseName, classYear);
		for(int i=0; i<gradeBef.size(); i++){
			System.out.println(gradeBef.get(i).getScore());
		}
		System.out.println("----------");
		for(int i=0; i<gradeAft.size(); i++){
			System.out.println(gradeAft.get(i).getScore());
		}
		System.out.println("----------");
		for(int i=0; i<gradeCut.size(); i++){
			System.out.println(gradeCut.get(i).getScore());
		}
		System.out.println("----------");
		for(int i=0; i<gradeHigh.size(); i++){
			System.out.println(gradeHigh.get(i).getScore());
		}
	}
}
