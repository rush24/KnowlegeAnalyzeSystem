/**
 * 
 */
package edu.fjnu.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;
import edu.fjnu.domain.StudentPr;

/**
 * 学生成长曲线图的dao
 * 
 * @libiao
 *
 */
public class StudentGrowDao {
	/**
	 * 得到学生的成长曲线数据pr值 年级的dao
	 */
	QueryRunner qr = new TxQueryRunner();

	public List<StudentPr> getStuPrList(String stuName, String subject) {
		String sql = "SELECT CASE classyear_final_table.testclassyear "
				+ "	 when '1.1' then '一年级（上）' when '1.2' then '一年级（下）' "
				+ "	 when '2.1' then '二年级（上）' when '2.2' THEN '二年级（下）' "
				+ "	when '3.1' then '三年级（上）'  when '3.2' then '三年级（下）' "
				+ "	WHEN '4.1' THEN '四年级（上）'  when '4.2' THEN '四年级（下）' "
				+ "	 when '5.1' then '五年级（上）'  when '5.2' then '五年级（下）' "
				+ "	 when '6.1' then '六年级（上）'  when '6.2' then '六年级（下）'  "
				+ "	 when '7.1' then '初一（上）' WHEN '7.2' THEN '初一（下）'  "
				+ "	WHEN '8.1' THEN '初二（上）' when '8.2' THEN '初二（下）' "
				+ "	 END classyear,classyear_final_table.avgpr avgPR " + "	FROM "
				+ "	(SELECT CASE classyear_table.classyear  "
				+ "	when '2013版 二年级(下)' THEN '2.2' when '一年级（上）' then '1.1' when '一年级（下）' then '1.2' "
				+ "	when '三年级（上）' then '3.1' when '三年级（下）' then '3.2' when '二年级（上）' then '2.1' "
				+ "	when '二年级（下）' then '2.2' when '五年级(上) 2014.8' then '5.1' when '五年级（上）' then '5.1' "
				+ "	when '五年级（下）' then '5.2' when '六年级（上）' then '6.1' when '六年级（上）2014.8' then '6.1' "
				+ "	when '六年级（下）' then '6.2' when '初一(上)2013.6版' then '7.1' when '初一（上）' then '7.1' "
				+ "	WHEN '初一（下）' THEN '7.2' when '初二(上) 2013.6版' THEN '8.1' when '初二(下) 2013版' then '8.2' "
				+ "	WHEN '初二（上）' THEN '8.1' when '初二（下）' THEN '8.2' when '四年级(上) 201306版' then '4.1' "
				+ "	WHEN '四年级（上）' THEN '4.1' when '四年级（下）' THEN '4.2' "
				+ "	 END  testclassyear,FORMAT(AVG(classyear_table.pr),2) avgpr " + "	FROM "
				+ "	(SELECT classyear,pr  " + "	from t_excel " + "	where " + "	name=? " + "	and `subject`=? "
				+ "	GROUP BY classyear,pr) classyear_table " + "	GROUP BY testclassyear "
				+ "	ORDER BY testclassyear ) classyear_final_table";
		Object[] params = { stuName, subject };
		List<StudentPr> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<StudentPr>(StudentPr.class), params);
		} catch (SQLException e) {
			System.err.println("查询getstuPrList异常");
			e.printStackTrace();
		}

		return list;
	}

}
