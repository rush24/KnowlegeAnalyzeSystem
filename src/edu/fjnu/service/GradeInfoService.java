package edu.fjnu.service;

import java.util.ArrayList;
import java.util.List;

import edu.fjnu.dao.GradeInfoDao;
import edu.fjnu.dao.StudentDao;
import edu.fjnu.domain.GradeInfo;
import edu.fjnu.domain.StudentPr;

public class GradeInfoService {
	GradeInfoDao gradeInfoDao = new GradeInfoDao();
	StudentDao studentDao = new StudentDao();
	private static final int LEN = 10;

	/**
	 * 指定学生姓名得到学过的年级列表
	 * 
	 * @param stuName
	 * @return 年级列表
	 */
	public List<StudentPr> getStudiedYearListByName(String stuName) {
		return studentDao.getStudentListByName(stuName);
	}

	public List<GradeInfo> getGradeBef(String stuName, String courseName, String classYear) {
		List<GradeInfo> gradeBefList = new ArrayList<GradeInfo>();
		GradeInfo gradeInfo = new GradeInfo();
		gradeInfo.setPr(0); 
		gradeInfo.setScore(0);
		gradeBefList = gradeInfoDao.getGradeBefByScope(stuName, courseName, classYear);

		if (gradeBefList == null || gradeBefList.size() == 0) {
			for (int i = 0; i < LEN; i++) {
				gradeBefList.add(gradeInfo);
			}
			return gradeBefList;
		}

		gradeBefList = setGradeInfo(gradeBefList);

		return gradeBefList;
	}

	public List<GradeInfo> getGradeAft(String stuName, String courseName, String classYear) {
		List<GradeInfo> gradeAftList = new ArrayList<GradeInfo>();
		GradeInfo gradeInfo = new GradeInfo();
		gradeInfo.setPr(0);
		gradeInfo.setScore(0);
		gradeAftList = gradeInfoDao.getGradeAftByScope(stuName, courseName, classYear);

		if (gradeAftList == null || gradeAftList.size() == 0) {
			for (int i = 0; i < LEN; i++) {
				gradeAftList.add(gradeInfo);
			}
			return gradeAftList;
		}

		gradeAftList = setGradeInfo(gradeAftList);

		return gradeAftList;
	}

	public List<GradeInfo> getGradeCut(String stuName, String courseName, String classYear) {
		List<GradeInfo> tmpBefList = getGradeBef(stuName, courseName, classYear);
		List<GradeInfo> tmpAftList = getGradeAft(stuName, courseName, classYear);
		List<GradeInfo> tmpCutList = getCreateObjList();

		for (int i = 0; i < LEN; i++) {
			if (tmpBefList.get(i).getScore() > tmpAftList.get(i).getScore()) {
				tmpCutList.get(i).setScore(tmpBefList.get(i).getScore() - tmpAftList.get(i).getScore());
			} else {
				tmpCutList.get(i).setScore(tmpAftList.get(i).getScore() - tmpBefList.get(i).getScore());
			}
		}

		return tmpCutList;
	}

	public List<GradeInfo> getGradeHigh(String stuName, String courseName, String classYear) {
		List<GradeInfo> tmpBefList = getGradeBef(stuName, courseName, classYear);
		List<GradeInfo> tmpAftList = getGradeAft(stuName, courseName, classYear);
		List<GradeInfo> tmpHighList = getCreateObjList();

		for (int i = 0; i < LEN; i++) {
			if (tmpBefList.get(i).getScore() > tmpAftList.get(i).getScore()) {
				tmpHighList.get(i).setScore(tmpAftList.get(i).getScore());
			} else {
				tmpHighList.get(i).setScore(tmpBefList.get(i).getScore());
			}
		}

		return tmpHighList;
	}

	public List<GradeInfo> getGradePr(String stuName, String courseName, String classYear) {
		List<GradeInfo> tmpList = new ArrayList<GradeInfo>();
		tmpList = gradeInfoDao.getGradeAndPr(stuName, courseName, classYear);

		if (tmpList.size() == 0 || tmpList == null) {
			GradeInfo gradeInfo = new GradeInfo();
			gradeInfo.setPr(0);
			gradeInfo.setScore(0);
			tmpList.add(gradeInfo);
			return tmpList;
		}

		return tmpList;
	}

	private List<GradeInfo> getCreateObjList() {
		List<GradeInfo> setTmpList = new ArrayList<GradeInfo>();

		for (int i = 0; i < LEN; i++) {
			GradeInfo tmpGrade = new GradeInfo();
			tmpGrade.setScore(0);
			tmpGrade.setPr(0);
			setTmpList.add(tmpGrade);
		}

		return setTmpList;
	}

	private List<GradeInfo> setGradeInfo(List<GradeInfo> gradeList) {
		List<GradeInfo> tmpList = getCreateObjList();

		for (int i = 0; i < gradeList.size(); i++) {
			if ("T1".equals(gradeList.get(i).getProgress())) {
				tmpList.get(0).setPr(gradeList.get(i).getPr());
				tmpList.get(0).setScore(gradeList.get(i).getScore());
			} else if ("T2".equals(gradeList.get(i).getProgress())) {
				tmpList.get(1).setPr(gradeList.get(i).getPr());
				tmpList.get(1).setScore(gradeList.get(i).getScore());
			} else if ("T3".equals(gradeList.get(i).getProgress())) {
				tmpList.get(2).setPr(gradeList.get(i).getPr());
				tmpList.get(2).setScore(gradeList.get(i).getScore());
			} else if ("T4".equals(gradeList.get(i).getProgress())) {
				tmpList.get(3).setPr(gradeList.get(i).getPr());
				tmpList.get(3).setScore(gradeList.get(i).getScore());
			} else if ("S1".equals(gradeList.get(i).getProgress())) {
				tmpList.get(4).setPr(gradeList.get(i).getPr());
				tmpList.get(4).setScore(gradeList.get(i).getScore());
			} else if ("T5".equals(gradeList.get(i).getProgress())) {
				tmpList.get(5).setPr(gradeList.get(i).getPr());
				tmpList.get(5).setScore(gradeList.get(i).getScore());
			} else if ("T6".equals(gradeList.get(i).getProgress())) {
				tmpList.get(6).setPr(gradeList.get(i).getPr());
				tmpList.get(6).setScore(gradeList.get(i).getScore());
			} else if ("T7".equals(gradeList.get(i).getProgress())) {
				tmpList.get(7).setPr(gradeList.get(i).getPr());
				tmpList.get(7).setScore(gradeList.get(i).getScore());
			} else if ("T8".equals(gradeList.get(i).getProgress())) {
				tmpList.get(8).setPr(gradeList.get(i).getPr());
				tmpList.get(8).setScore(gradeList.get(i).getScore());
			} else if ("S2".equals(gradeList.get(i).getProgress())) {
				tmpList.get(9).setPr(gradeList.get(i).getPr());
				tmpList.get(9).setScore(gradeList.get(i).getScore());
			}
		}

		return tmpList;
	}

}
