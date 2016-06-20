package edu.fjnu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class CustomerMadeDao {
	QueryRunner qr = new TxQueryRunner();// 操作数据库的对象

	public List<String> getStudentIdList(String courseName, String userID) {
		String sql = "SELECT DISTINCT student_id FROM u_upload WHERE user_id = ? AND course_name = ?";
		Object[] params = {userID,courseName};
		List<String> studentIdList = new ArrayList<String>();
		try {
			studentIdList = (List)qr.query(sql, new ColumnListHandler(1),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentIdList;
	}

	public List<String> getWrongKeywordsOfStudent(String studentID, String courseName, String userID) {
		String sql = "SELECT scope_name FROM u_upload WHERE student_id = ? AND user_id = ? AND course_name = ?";
		Object[] params = {studentID,userID,courseName};
		List<String> wrongKeywordList = new ArrayList<String>();
		try {
			wrongKeywordList = (List)qr.query(sql, new ColumnListHandler(1),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wrongKeywordList;
	}

	public String getKeywordIdByName(String keywordName,String userId) {
		String sql = "SELECT scope_id FROM u_upload WHERE scope_name = ? AND user_id = ? LIMIT 1";
		Object[] params = {keywordName,userId};
		String keywordId = null;
		try {
			keywordId = (String) qr.query(sql, new ScalarHandler(),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return keywordId;
	}

	public String getGradeByKeywordName(String keywordName,String userId) {
		String sql = "SELECT belong_grade FROM u_upload WHERE scope_name = ? AND user_id = ? LIMIT 1";
		Object[] params = {keywordName,userId};
		String gradeName = "";
		try {
			gradeName = (String) qr.query(sql,new ScalarHandler(),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gradeName;
	}

	/**
	 * 批量保存用户上传数据
	 * @param dataList
	 * @param userId
	 * @param courseName
	 */
	public void saveUserDataByBatch(List<String[]> dataList,String userId,String courseName){
		String sql = "INSERT INTO u_upload(student_id,scope_name,scope_id,belong_grade,course_name,user_id) "
				+ "values(?,?,?,?,?,?)";
		Object[][] params = new Object[dataList.size()][6];
		for(int i = 0;i < dataList.size();i++){
			String[] strArr = dataList.get(i);
			params[i][0] = strArr[0];
			params[i][1] = strArr[1];
			params[i][2] = strArr[2];
			params[i][3] = strArr[3];
			params[i][4] = courseName;
			params[i][5] = userId;
		}
		try {
			qr.batch(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除数据库中指定用户上传的指定科目数据
	 * @param dataList
	 * @param userId
	 * @param courseName
	 */
	public void deleteUserDataByBatch(String userId,String courseName){
		String sql = "DELETE FROM u_upload WHERE user_id = ? AND course_name = ?";
		Object[] params = {userId, courseName};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用户是否定制过地图
	 * @param userId
	 * @param courseName
	 * @return
	 */
	public boolean hascustomerMade(String userId,String courseName){
		String sql = "SELECT member_id FROM customer_made_map WHERE member_id = ? AND map_course = ? AND map_type = 1";
		Object[] params = {userId, courseName};
		Integer memberId = null;
		try {
			memberId = (Integer) qr.query(sql, new ScalarHandler(),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberId != null ? true:false;
	}

	/**
	 * 记录用户有定制的地图类型
	 * @param userId
	 * @param courseName
	 */
	public void saveCustomerMapType(String userId,String courseName){
		String sql = "INSERT INTO customer_made_map(member_id,map_type,map_course) values(?,1,?)";
		Object[] params = {userId,courseName};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
