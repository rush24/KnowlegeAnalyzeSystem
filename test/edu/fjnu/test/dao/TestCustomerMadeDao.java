package edu.fjnu.test.dao;

import org.junit.Test;

import edu.fjnu.dao.CustomerMadeDao;

public class TestCustomerMadeDao {
	private CustomerMadeDao cmd = new CustomerMadeDao();

	@Test
	public void testGetKeywordIdByName(){
		System.out.println(cmd.getKeywordIdByName("三角函数", "2560"));
	}
	
	@Test
	public void testHascustomerMade(){
		System.out.println(cmd.hascustomerMade("2560", "语文"));
	}
	
	@Test
	public void testSaveCustomerMapType(){
		cmd.saveCustomerMapType("2564", "语文");
	}
}
