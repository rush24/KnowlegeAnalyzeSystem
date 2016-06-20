/**
 * 
 */
package edu.fjnu.service;

import java.util.ArrayList;
import java.util.List;

import edu.fjnu.dao.TeacherGrowDao;
import edu.fjnu.domain.StudentPr;

/**
 * 教师端成长轨迹模块的service
 * 
 * @author Administrator
 *
 */
public class TeacherGrowService {
	TeacherGrowDao tgd = new TeacherGrowDao();

	public List<String> getStudent(String teacherId) {
		List<StudentPr> list = tgd.getStudentList(teacherId);
		List<String> stuList = new ArrayList<String>(); // 保存学生名字
		for (StudentPr studentPr : list) {
			stuList.add(studentPr.getName());
		}
		return stuList;

	}

}
