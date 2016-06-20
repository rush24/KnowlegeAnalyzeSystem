/**
 * 
 */
package edu.fjnu.service;

import java.util.ArrayList;
import java.util.List;

import edu.fjnu.dao.StudentGrowDao;
import edu.fjnu.domain.StudentPr;

/**
 * 个人成长轨迹模块的service
 * 
 * @author Administrator
 *
 */
public class GrowService {
	StudentGrowDao studentGrowDao = new StudentGrowDao();

	/**
	 * 得到学生成长轨迹图的数据(各阶段PR走势)
	 * 
	 * @param name
	 * @param subject
	 * @return 学生PR对象列表
	 */
	public List<StudentPr> getStudentPrs(String name, String subject) {
		StudentPr stuPr = new StudentPr();
		List<StudentPr> tmpList = new ArrayList<StudentPr>();

		tmpList = studentGrowDao.getStuPrList(name, subject);

		// 如果没有查询到数据,就返回一个默认数据
		if (tmpList.size() == 0 || tmpList == null) {
			stuPr.setClassyear("未查询到结果");
			stuPr.setAvgPR(0);
			tmpList.add(stuPr);
		}
		return tmpList;
	}
}
