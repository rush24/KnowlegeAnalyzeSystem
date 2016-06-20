package edu.fjnu.test.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import edu.fjnu.domain.Testmain;
import edu.fjnu.domain.VGrandientScope;
import edu.fjnu.domain.VMember;
import edu.fjnu.domain.VQuestion;
import edu.fjnu.domain.VScope;
import edu.fjnu.domain.VTestDetail;
import edu.fjnu.domain.VTestMain;

public class TestUpeoeDao {
	QueryRunner qr = new TxQueryRunner();
	VMember member = new VMember();
	VGrandientScope gs = new VGrandientScope();
	VQuestion question = new VQuestion();
	VScope scope = new VScope();
	VTestMain testmain = new VTestMain();
	VTestDetail testdetail = new VTestDetail();
	String sql = "";

	@Test
	public void searchGrandient() throws SQLException {
		sql = "select * from t_grandient_t_scope where t_grandient_grandientid = '423'";
		gs = qr.query(sql, new BeanHandler<VGrandientScope>(VGrandientScope.class));
		System.out.println(gs);
	}

	@Test
	public void searchMember() throws SQLException {
		sql = "select * from t_member where memberid = '100'";
		member = qr.query(sql, new BeanHandler<VMember>(VMember.class));
		System.out.println(member);
	}

	@Test
	public void searchQuestion() throws SQLException {
		sql = "select * from t_question where fk_question_id = '1'";
		question = qr.query(sql, new BeanHandler<VQuestion>(VQuestion.class));
		System.out.println(question);
	}

	@Test
	public void searchScope() throws SQLException {
		sql = "select * from t_scope where pk_scope_id = '1'";
		scope = qr.query(sql, new BeanHandler<VScope>(VScope.class));
		System.out.println(scope);
	}

	@Test
	public void searchTestMain() throws SQLException {
		sql = "select * from t_testmain where pk_test_main_id = '1'";
		testmain = qr.query(sql, new BeanHandler<VTestMain>(VTestMain.class));
		System.out.println(testmain);
	}

	@Test
	public void searchTestDetail() throws SQLException {
		sql = "select * from t_test_detail where pk_test_id = '1'";
		// testdetail = qr.query(sql, new
		// BeanHandler<VTestDetail>(VTestDetail.class));//查询某个表，单行多列记录
		// Map map= qr.query(sql, new
		// BeanListHandler<VTestMain>(VTestMain.class));//查询某个表，多行多列记录
		// qr.query(sql, new MapHandler());//学生+老师+课程表 查出单行多列记录
		// qr.query(sql, new MapListHandler());//学生+老师+课程表 查出多行多列记录
		// CommonUtils.toBean(map, VTestMain.class);

		System.out.println(testdetail);
	}

	@Test
	public void searchVTestMain() throws SQLException {
		sql = "select pk_test_main_id,subject,student_memberId from  t_testmain "
				+ "where subject like '语文' and student_memberId in "
				+ "(select memberid from t_member where name like '陈子凡') "
				+ "and  grandient_grandientId in(select DISTINCT "
				+ "t_grandient.grandientId as `grandientId` from t_grandient,"
				+ "t_grandient_t_scope,t_scope as `chapter`,"
				+ "t_scope as `unit`,"
				+ "t_scope as `grade` "
				+ "where  t_grandient.grandientId=t_grandient_t_scope.t_grandient_grandientId "
				+ "and t_grandient_t_scope.scopes_pk_scope_id=chapter.pk_scope_id "
				+ "and chapter.fk_parent_id=unit.pk_scope_id and unit.fk_parent_id=grade.pk_scope_id "
				+ "and grade.name like '初二（上）%')";
//		String sub = "语文";
//		sql ="select * from t_testmain where student_memberId = '2' and subject='语文' and realscore != 0";
//		List list = qr.query(sql, new BeanListHandler<VTestMain>(VTestMain.class));
//		for(int i=0; i<list.size(); i++){
//			System.out.println(list.get(i));
//		}
//		System.out.println(sql);
		System.out.println(qr.query(sql, new BeanListHandler<VTestMain>(VTestMain.class)));
	}
}
