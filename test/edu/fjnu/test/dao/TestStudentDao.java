package edu.fjnu.test.dao;

import java.util.List;

import org.junit.Test;

import edu.fjnu.dao.StudentDao;

public class TestStudentDao {
	StudentDao studentDao = new StudentDao();
	
	@Test
	public void testGetAllStudentID(){
		List studentIDList = studentDao.getAllStudentID();
		System.out.println(studentIDList);
	}
}
