package edu.fjnu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;
import edu.fjnu.domain.DifficultInfo;
import edu.fjnu.domain.Keyword;

/**
 * 获取知识点信息
 * 
 * @author vengeance
 *
 */
public class KeywordDao {
	Keyword tmpKeyword = new Keyword();// 知识点信息
	DifficultInfo tmpDifficultInfo = new DifficultInfo();// 知识点所出题目类型题数情况
	QueryRunner qr = new TxQueryRunner();

	/**
	 * 输入知识点名称得到做对做错的情况
	 * 
	 * @param keyword
	 * @return 知识点对象
	 */
	public Keyword getKeywordInfo(Keyword keyword) {
		String sql = "SELECT sum(t_question.successNum) as rightCount,(SUM(t_question.num)-sum(t_question.successNum))as wrongCount "
				+ "from t_question where keyword like ?";
		// String paramsString = "%" + keyword.getKeyName()+"%";
		Object[] params = { "%" + keyword.getKeyName() + "%" };

		try {
			// 查询知识点的做对做错次数情况
			tmpKeyword = qr.query(sql, new BeanHandler<Keyword>(Keyword.class), params);
			tmpKeyword.setKeyName(keyword.getKeyName());
			System.out.println(tmpKeyword);// TEST
		} catch (SQLException e) {
			System.err.println("查询知识点做对做错次数信息出错");
			e.printStackTrace();
			return null;
		}
		return tmpKeyword;
	}

	/**
	 * 输入知识点名称得到这个知识点的难中易题数分布情况
	 * 
	 * @param keyword
	 * @return 难中易题数列表
	 */
	public List<DifficultInfo> getKeyDiffInfo(Keyword keyword) {
		String sql = "select DISTINCT difficultyLevel ,COUNT(DISTINCT t_question.fk_question_id) AS questionCount "
				+ "from t_question where keyword like ? GROUP BY difficultyLevel";
		Object[] params = { "%" + keyword.getKeyName() + "%" };
		List<DifficultInfo> diffList = new ArrayList<DifficultInfo>();

		try {
			// 得到这个知识点的难中易题数分布情况的列表
			diffList = qr.query(sql, new BeanListHandler<DifficultInfo>(DifficultInfo.class), params);
		} catch (SQLException e) {
			System.err.println("查询知识点难中易分布情况出错");
			e.printStackTrace();
			return null;
		}
		return diffList;
	}
	
	/**
	 * 通过知识点名称得到其ID（即范围ID）
	 * 例：传入"25坐井观天" 也可查到 "坐井观天"
	 * @param keywordName
	 * @return
	 */
	public Long getKeywordIdByName(String keywordName){
		String sql = "SELECT `pk_scope_id` FROM `t_scope` WHERE `name` LIKE ? LIMIT 1";
		Object[] params = {"%" + keywordName};
		Long id;
		try {
			id =  (Long)qr.query(sql, new ScalarHandler(),params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return id;
	}
	
	/**
	 * 通过知识点名称得到其属于的年级
	 * @param keywordName
	 * @return
	 */
	public String getGradeByKeywordName(String keywordName){
		String sql = 
				"SELECT `name` FROM `t_scope` WHERE `pk_scope_id` = "+
				"(SELECT `fk_parent_id` FROM `t_scope` WHERE `pk_scope_id` = "+ 
				"(SELECT `fk_parent_id` FROM `t_scope` WHERE `pk_scope_id`= "+ 
				"(SELECT `scope_pk_scope_id` FROM `t_question` WHERE `keyword`=? LIMIT 1)))";
		Object[] params = {keywordName};
		String grade;
		try {
			grade =  (String)qr.query(sql, new ScalarHandler(),params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return grade;
	}
}
