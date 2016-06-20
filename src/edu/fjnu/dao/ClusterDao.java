package edu.fjnu.dao;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import cn.itcast.jdbc.TxQueryRunner;



import edu.fjnu.domain.Cluster;

import edu.fjnu.domain.Testmain;
import edu.fjnu.domain.VScope;

/**
 * 聚类分析模块
 * @author zhangzhiyong
 *
 */
public class ClusterDao {

	QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 通过指定科目指定年级以及指定学生IDList得到正确知识点及其次数
	 * @param grade
	 * @param subject
	 * @param idList
	 * @return
	 */
	public List<VScope> getKeywordsByBadLevel(String grade,String subject,ArrayList<Integer> idList){
		//有几个参数拼接几个？
		String idListString = "";
		for(int i = 0 ;i < idList.size();i++){
			if(i == idList.size()-1){
				idListString = idListString + "?";
			}else{
				idListString = idListString + "?,";
			}
		}
		
		try{
			String sql = "select t_scope.pk_scope_id as pk_scope_ID,t_scope.`name` as name,COUNT(keyword) as keywordCount from t_question,t_scope "
						+ "where fk_question_id in "
						+ "(Select question_fk_question_id from t_test_detail "
						+ "where t_test_detail.answer!=t_test_detail.stuAnswer AND testmain_pk_test_main_id in "
						+ "(SELECT pk_test_main_id FROM t_testmain "
						+ "where  t_testmain.`subject`= ? and realScore!='0' and t_testmain.student_memberId in (" + idListString + ") "
						+ "and grandient_grandientId in "
						+ "(select DISTINCT t_grandient.grandientId as `grandientId` "
						+ "from t_grandient,t_grandient_t_scope,t_scope as `chapter`,t_scope as `banben`,t_scope as `unit`,t_scope as `grade` "
						+ "where  t_grandient.grandientId=t_grandient_t_scope.t_grandient_grandientId "
						+ "and t_grandient_t_scope.scopes_pk_scope_id=chapter.pk_scope_id "
						+ "and chapter.fk_parent_id=unit.pk_scope_id "
						+ "and unit.fk_parent_id=grade.pk_scope_id "
						+ "and grade.fk_parent_id=banben.pk_scope_id "
						+ "and grade.name like ?) "
						+ ")) "
						+ "and t_scope.pk_scope_id=t_question.scope_pk_scope_id "
						+ "GROUP BY t_question.scope_pk_scope_id	 "
						+ "Having keywordCount > 10 "
						+ "ORDER BY t_scope.pk_scope_id ASC ";

			//拼接模板
			Object[] params = new Object[idList.size()+2];
			params[0] = subject;
			for(int i = 1; i <= idList.size();i++){
				params[i] = idList.get(i-1);
			}
			params[idList.size()+1] = grade;
			
			return qr.query(sql, new BeanListHandler<VScope>(VScope.class), params);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
		
		/**
		 * 通过指定科目指定年级以及指定学生IDList得到错误知识点及其次数
		 * @param grade
		 * @param subject
		 * @param idList
		 * @return
		 */
		public List<VScope> getKeywordsByGoodLevel(String grade,String subject,ArrayList<Integer> idList){
			//有几个参数拼接几个？
			String idListString = "";
			for(int i = 0 ;i < idList.size();i++){
				if(i == idList.size()-1){
					idListString = idListString + "?";
				}else{
					idListString = idListString + "?,";
				}
			}
			
			try{
				String sql = "select t_scope.pk_scope_id as pk_scope_ID,t_scope.`name` as name,COUNT(keyword) as keywordCount from t_question,t_scope "
							+"where fk_question_id in "
							+"(Select question_fk_question_id from t_test_detail "
							+"where t_test_detail.answer=t_test_detail.stuAnswer AND testmain_pk_test_main_id in "
							+"(SELECT pk_test_main_id FROM t_testmain " 
							+"where  t_testmain.`subject`=? and realScore!='0'and t_testmain.student_memberId in (" + idListString + ") "
							+"and grandient_grandientId in "
							+"(select DISTINCT t_grandient.grandientId as `grandientId` "
							+"from t_grandient,t_grandient_t_scope,t_scope as `chapter`,t_scope as `banben`,t_scope as `unit`,t_scope as `grade` "
							+"where  t_grandient.grandientId=t_grandient_t_scope.t_grandient_grandientId "
							+"and t_grandient_t_scope.scopes_pk_scope_id=chapter.pk_scope_id "
							+"and chapter.fk_parent_id=unit.pk_scope_id "
							+"and unit.fk_parent_id=grade.pk_scope_id "
							+"and grade.fk_parent_id=banben.pk_scope_id "
							+"and grade.name like ?)	 "																		
							+")) "
							+"and t_scope.pk_scope_id=t_question.scope_pk_scope_id "
							+"GROUP BY t_question.scope_pk_scope_id "
							+"Having keywordCount > 10 "
							+"ORDER BY t_scope.pk_scope_id ASC ";

				//拼接模板
				Object[] params = new Object[idList.size()+2];
				params[0] = subject;
				for(int i = 1; i <= idList.size();i++){
					params[i] = idList.get(i-1);
				}
				params[idList.size()+1] = grade;
				
				return qr.query(sql, new BeanListHandler<VScope>(VScope.class), params);
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
		
		
	}
	
	/**
	 * 查询指定年级指定科目指定范围的学生ID，平均成绩列表
	 * @param grade
	 * @param subject
	 * @param from	列表截取开始位置
	 * @param num	列表截取个数
	 * @return
	 */
	public List<Testmain> rangeOfIDs(String grade,String subject,int from,int num){
		try{
			String sql = "select  t_testmain.student_memberId,avg(realscore)as avgscore from t_testmain "
						+ "where   grandient_grandientId in "
						+ "(select DISTINCT t_grandient.grandientId as `grandientId` "
						+ "from t_grandient,t_grandient_t_scope,t_scope as `chapter`,t_scope as `unit`,t_scope as `grade` "
						+ "where  t_grandient.grandientId=t_grandient_t_scope.t_grandient_grandientId "
						+ "and t_grandient_t_scope.scopes_pk_scope_id=chapter.pk_scope_id "
						+ "and chapter.fk_parent_id=unit.pk_scope_id "
						+ "and unit.fk_parent_id=grade.pk_scope_id "
						+ "and grade.name like ? "
						+ ") "
						+ "and t_testmain.`subject`=? "
						+ "and realScore!='0' "
						+ "GROUP BY t_testmain.student_memberId "
						+ "ORDER BY avgscore DESC  "  
						+ "limit ?,? ";
			Object[] params = {grade,subject,from,num};
			return qr.query(sql, new BeanListHandler<Testmain>(Testmain.class), params);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 通过科目，年级得到该年级参加考试的人数
	 * @param grade
	 * @param subject
	 * @return
	 */
	public int count(String grade,String subject){
		try{
			String sql = "select  count(DISTINCT student_memberId)from t_testmain "
						+ "where   grandient_grandientId in "
						+ "(select DISTINCT t_grandient.grandientId as `grandientId` "
						+ "from t_grandient,t_grandient_t_scope,t_scope as `chapter`,t_scope as `unit`,t_scope as `grade` "
						+ "where  t_grandient.grandientId=t_grandient_t_scope.t_grandient_grandientId "
						+ "and t_grandient_t_scope.scopes_pk_scope_id=chapter.pk_scope_id "
						+ "and chapter.fk_parent_id=unit.pk_scope_id "
						+ "and unit.fk_parent_id=grade.pk_scope_id "
						+ "and grade.name like ? "
						+ ") "
						+ "and t_testmain.`subject`=? "
						+ "and realScore!='0' ";
			Object[] params = {grade,subject};
			Long result = (Long)qr.query(sql, new ScalarHandler(),params);
			Integer count = new Integer(String.valueOf(result));
			return count;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 添加Cluster
	 * @param book
	 */
	public void add(Cluster cluster,String level,String subject) {
		String sql = "insert into cluster values(?,?,?,?,?,?,?)";
		Object[] params = {null,cluster.getName(),cluster.getArea(),cluster.getyAxis(),cluster.getGradeNum(),level,subject};
		try {
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 根据level和subject查询聚类List
	 * @return
	 */
	public List<Cluster> findAll(String level,String subject) {
		String sql = "select * from cluster where level=? and subject=? order by id ASC";
		Object[] params = {level,subject};
		try {
			return qr.query(sql, new BeanListHandler<Cluster>(Cluster.class),params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 查询聚类list
	 * @param level
	 * @param subject
	 * @param gradeName
	 * @return
	 */
	public List<Cluster> findByScope( String level, String subject, int gradeNum)
	{
		String sql = "select * "
				+ "from cluster "
				+ "where level=? and subject=? and gradeNum=? "
				+ "order by id asc";
		Object[] params = {level,subject, gradeNum};
		try {
			return qr.query(sql, new BeanListHandler<Cluster>(Cluster.class),params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
