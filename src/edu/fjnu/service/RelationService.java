package edu.fjnu.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import edu.fjnu.dao.CustomerMadeDao;
import edu.fjnu.dao.KeywordDao;
import edu.fjnu.dao.QuestionDao;
import edu.fjnu.dao.StudentDao;
import edu.fjnu.domain.DifficultInfo;
import edu.fjnu.domain.Keyword;
import edu.fjnu.domain.VQuestion;
import edu.fjnu.domain.VScope;
import edu.fjnu.domain.VTestMain;
import edu.fjnu.util.CSVUtil;
import edu.fjnu.util.CollectionUtil;
import edu.fjnu.util.FileTools;
import edu.fjnu.util.RelationAnalyse;

/**
 * 处理关联分析数据
 * 
 * @author vengeance
 *
 */
public class RelationService {
	QuestionDao qDao = new QuestionDao();
	CustomerMadeDao cmDao = new CustomerMadeDao();//处理用户定制功能的数据查询
	StudentDao studentDao = new StudentDao();	
	RelationAnalyse ra = new RelationAnalyse();// 关联分析处理工具
	KeywordDao keywordDao = new KeywordDao();// 得到知识点信息
	List<List<String>> keywordList = null;// 知识点列表
	String[] keywordArr = null;// 存储关联分析的知识点，用来描点
	private static final String[] GRADES = {"一年级","二年级","三年级","四年级","五年级","六年级","初一","初二","初三"};


	/**
	 * 根据知识点的名称得到这个知识点的相关信息(做对做错次数,相关题目难中易题数分布)
	 * 
	 * @param keyword
	 * @return 知识点对象
	 */
	public Keyword getKeywordInfoByName(Keyword keyword) {
		keyword = keywordDao.getKeywordInfo(keyword);
		List<DifficultInfo> diffList = new ArrayList<DifficultInfo>();// 存储知识点相关题目难中易题数分布
		diffList = keywordDao.getKeyDiffInfo(keyword);// 得到相关题目难中易题数分布

		for (int i = 0; i < diffList.size(); i++) {
			switch (diffList.get(i).getDifficultyLevel()) {
			case 1: {
				keyword.setEasyCount(diffList.get(i).getQuestionCount());
				break;
			}
			case 3: {
				keyword.setMiddleCount(diffList.get(i).getQuestionCount());
				break;
			}
			case 5: {
				keyword.setHardCount(diffList.get(i).getQuestionCount());
				break;
			}
			default:
				keyword.setEasyCount(0);
				keyword.setMiddleCount(0);
				keyword.setHardCount(0);
				break;
			}
		}
		System.out.println(keyword);// TEST
		return keyword;
	}

	/**
	 * 得到没有关联分析的知识点列表，用于传递给R绘制的数据
	 * 
	 * @param courseName
	 * @param year
	 * @return 未关联的知识点列表
	 */
	public List<List<String>> getRelationListForRtool(String courseName, String year) {
		List<List<String>> tempKeywordList = null;
		tempKeywordList = getRelationList(courseName, year);// 得到未处理的知识点列表
		tempKeywordList.remove(0);// 移除列表中的第一个元素，其中存放的是所有出现过的知识点

		return tempKeywordList;
	}

	/**
	 * 得到没有关联分析过的知识点列表
	 * 
	 * @param courseName
	 * @param year
	 * @return 未关联的知识点列表
	 */
	public List<List<String>> getRelationList(String courseName, String year) {
		List<VTestMain> paperList = getPaperIdByYear(courseName, year);// 试卷ID结果集
		List<List<String>> waitRelationList = new ArrayList<List<String>>();// 知识点结果集

		// 试卷知识点结果集，这里首先添加一个所有出现过的知识点列表，用于算法使用，供其他出现知识点做概率比较
		waitRelationList.add(getKeywordByScope(courseName, year));
		for (int i = 0; i < paperList.size(); i++) {
			waitRelationList.add(getQuestionObjListByPaperId(paperList.get(i)));// 根据试卷ID得到每一份试卷的知识点
		}

		return waitRelationList;
	}

	/**
	 * 指定科目、年级得到知识点列表
	 * 
	 * @param courseName
	 * @param year
	 */
	private List<String> getKeywordByScope(String courseName, String year) {
		List<VQuestion> keywordObjList = null;
		List<String> tempList = new ArrayList<String>();// 存放知识点的列表

		keywordObjList = qDao.getKeywordOneYear(courseName, year);// 得到指定范围的所有知识点
		tempList = getKeywordList(keywordObjList);

		return tempList;
	}

	/**
	 * 把从数据库读取的知识点对象列表转化为知识点列表
	 * 
	 * @param keywordObjList
	 * @return
	 */
	private List<String> getKeywordList(List<VQuestion> keywordObjList) {
		List<String> tempList = new ArrayList<String>();// 存放知识点的列表

		for (int i = 0; i < keywordObjList.size(); i++) {// 把知识点对象列表转化成知识点列表
			tempList.add(keywordObjList.get(i).getKeyword());
		}

		return tempList;
	}

	/**
	 * 指定学科、年级得到这个范围内所有的试卷ID
	 * 
	 * @param courseName
	 * @param year
	 * @return 返回指定范围的所有试卷ID
	 */
	private List<VTestMain> getPaperIdByYear(String courseName, String year) {
		return qDao.getPaperIdOneYear(courseName, year);
	}

	/**
	 * 指定试卷ID得到这份试卷出现的知识点
	 * 
	 * @param testmain
	 * @return 返回指定试卷ID的所有知识点
	 */
	private List<String> getQuestionObjListByPaperId(VTestMain testmain) {
		return getKeywordList(qDao.getKeywordByPaperId(testmain));
	}
	
	/**
	 * 得到单年级关联的错误知识点列表，提供给R处理
	 * List每个元素为一个人该科目该年级下的所有错题知识点
	 * 每个元素形如 [知识点A，知识点B，知识点C] 的字符串
	 * @param courseName 课程
	 * @return 该科目所有学生的所有错题知识点
	 */
	public List<String> getSingleGradeWrongKeywordsList(String courseName,String grade){
		List<String> wrongKeywordsListOfStudents = new ArrayList<String>();//存放所有错误知识点
		List<String> wrongKeywordsList = new ArrayList<String>();//存放一个学生的错误知识点
		String newWrongKeywordString;//临时变量，接收包含一个学生所有错误知识点的字符串
		List<Integer> studentIDList = studentDao.getAllStudentID();
		
		for(Integer studentID : studentIDList){
			wrongKeywordsList = qDao.getWrongKeywordsOfStudentByGrade(studentID, courseName,grade);
			wrongKeywordsList = CollectionUtil.removeDuplicate(wrongKeywordsList);
			if(wrongKeywordsList.size() > 0){
				newWrongKeywordString = wrongKeywordsList.toString();
				newWrongKeywordString = newWrongKeywordString.replace("[", "");
				newWrongKeywordString = newWrongKeywordString.replace("]", "");
				newWrongKeywordString = newWrongKeywordString.replace(" ", "");
				
				wrongKeywordsListOfStudents.add(newWrongKeywordString);
			}
		}
		return wrongKeywordsListOfStudents;
	}
	
	/**
	 * 得到跨年级关联的错误知识点列表，提供给R处理
	 * List每个元素为一个人该科目下所学过年级的所有错题知识点
	 * 每个元素形如 [知识点A，知识点B，知识点C] 的字符串
	 * @param courseName 课程
	 * @return 该科目所有学生的所有错题知识点
	 */
	public List<String> getCrossGradesWrongKeywordsList(String courseName){
		List<String> wrongKeywordsListOfStudents = new ArrayList<String>();//存放所有错误知识点
		List<String> wrongKeywordsList = new ArrayList<String>();//存放一个学生的错误知识点
		String newWrongKeywordString;//临时变量，接收包含一个学生所有错误知识点的字符串
		List<Integer> studentIDList = getCrossGradesStudentIDs(courseName);
		
		for(Integer studentID : studentIDList){
			wrongKeywordsList = qDao.getWrongKeywordsOfStudent(studentID, courseName);
			if(wrongKeywordsList.size() > 0){
				newWrongKeywordString = wrongKeywordsList.toString();
				newWrongKeywordString = newWrongKeywordString.replace("[", "");
				newWrongKeywordString = newWrongKeywordString.replace("]", "");
				newWrongKeywordString = newWrongKeywordString.replace(" ", "");
				
				wrongKeywordsListOfStudents.add(newWrongKeywordString);
			}
		}
		return wrongKeywordsListOfStudents;
	}
	
	/**
	 * 得到至少做过两个年级错题的学生ID
	 * @param courseName
	 * @return crossGradesStdentIDs
	 */
	public List<Integer> getCrossGradesStudentIDs(String courseName){
		List<Integer> crossGradesStdentIDs = new ArrayList<Integer>();//存放有跨年级的学生ID
		List<String> wrongKeywordsList = new ArrayList<String>();//存放一个学生的错误知识点
		List<Integer> studentIDList = studentDao.getAllStudentID();
		
		for(Integer studentID : studentIDList){
			int gradeCount = 0;
			//找出至少做过两个年级错题的学生ID
			for(int i = 0; i < GRADES.length;i++){
				wrongKeywordsList = qDao.getWrongKeywordsOfStudentByGrade(studentID, courseName,GRADES[i]);
				if(wrongKeywordsList.size() > 0){
					gradeCount++;
				}
			}
			if(gradeCount >=2){
				crossGradesStdentIDs.add(studentID);
			}
		}
		return crossGradesStdentIDs;
	}

	/**
	 * 得到用户定制的所有错误知识点列表
	 * @param courseName
	 * @param customerName
	 * @return
	 */
	public List<String> getCMWrongKeywordsList(String courseName,String userID){
		List<String> wrongKeywordsListOfStudents = new ArrayList<String>();//存放所有错误知识点
		List<String> wrongKeywordsList = new ArrayList<String>();//存放一个学生的错误知识点
		String newWrongKeywordString;//临时变量，接收包含一个学生所有错误知识点的字符串
		List<String> studentIDList = cmDao.getStudentIdList(courseName,userID);
		
		for(String studentID : studentIDList){
			wrongKeywordsList = cmDao.getWrongKeywordsOfStudent(studentID, courseName,userID);
			if(wrongKeywordsList.size() > 0){
				newWrongKeywordString = wrongKeywordsList.toString();
				newWrongKeywordString = newWrongKeywordString.replace("[", "");
				newWrongKeywordString = newWrongKeywordString.replace("]", "");
				newWrongKeywordString = newWrongKeywordString.replace(" ", "");
				
				wrongKeywordsListOfStudents.add(newWrongKeywordString);
			}
		}
		return wrongKeywordsListOfStudents;
	}
	
	/**
	 * 将用户上传的原始数据csv解析后暂存入数据库备用
	 * @param userId
	 * @param courseName
	 * @return
	 */
	public boolean saveUserUploadDataIntoDatabase(String userId,String courseName){
		String fileName = userId + "_" + courseName + "_上传数据.csv";
		String filePath = FileTools.getApplicationRootPath() + "/R/csv/customerMade/" + fileName;
		List<String[]> userDataList = null;
		try {
			userDataList = CSVUtil.importCsv(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		cmDao.saveUserDataByBatch(userDataList, userId, courseName);

		return true;
	}
	
	/**
	 * 将用户上传的原始数据从数据库中删除
	 * @param userId
	 * @param courseName
	 * @return
	 */
	public boolean deleteUploadDataInDatabase(String userId,String courseName){
		cmDao.deleteUserDataByBatch(userId, courseName);
		return true;
	}
}
