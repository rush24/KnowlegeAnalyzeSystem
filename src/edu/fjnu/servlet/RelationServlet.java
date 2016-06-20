package edu.fjnu.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import cn.itcast.servlet.BaseServlet;
import edu.fjnu.domain.Cluster;
import edu.fjnu.domain.Keyword;
import edu.fjnu.domain.KeywordTable;
import edu.fjnu.domain.VScope;
import edu.fjnu.service.ClusterService;
import edu.fjnu.service.RelationService;
import edu.fjnu.service.StudentPaperService;
import edu.fjnu.service.TeacherPaperService;
import edu.fjnu.util.DoubleFormat;
import edu.fjnu.util.RelationResult;

/**
 * 处理关联分析模块
 * 
 * @author vengeance
 *
 */
@WebServlet("/RelationServlet")
public class RelationServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private boolean isStudent;// 判断当前是否为学生登录
	// private RelationshipService rsh = new RelationshipService();//
	// 用来处理R图数据及调用
	private TeacherPaperService tts = new TeacherPaperService();// 得到老师的教学信息
	private ClusterService cluserService = new ClusterService();// 引入聚类service
	private StudentPaperService sts = new StudentPaperService();// 为了得到学生所做过试卷的年级列表
	private RelationResult rr = new RelationResult();// 知识点关联工具
	private RelationService rs = new RelationService();
	
	private String[][] staticNewKeywordArr;
	private Object[][] staticNewStvArr;
	private String staticCourse;
	private String staticGrade;

	/**
	 * 处理关联分析
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String relation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String courseName = "";// 学科
		String grade = "";// 年级
		// 加载下拉框年级信息
		loadInfo(request, response);

		courseName = request.getParameter("course");
		// 把选择的科目设置回去以供下拉框传值（另一种做法是直接把course设为session域）
		request.setAttribute("selectedCourse", courseName);
		staticCourse = courseName;
		grade = request.getParameter("grade");// 得到下拉框选择的年级
		// 把选择的年级设置回去以供下拉框显示选中哪个年纪
		request.setAttribute("selectedGrade", grade);
		staticGrade = grade;

		if (grade != null) {
			// 设置R图
			setPicture(request, response);
			// 调用R画出图，然后读取
			// try {
			// rsh.createRForRelastion(courseName, grade);
			// } catch (RserveException e) {
			// e.printStackTrace();
			// }
			String course = request.getParameter("course");
			// 截取前面三个字符串，得到类似于（三年级）
			String year = request.getParameter("grade").substring(0, 3);

			String[] keywordArr = rr.getKeywordArr(year, course);
			Object[][] stvArr = rr.getStvArr(year, course);
			
			// 定义两个转化后的二维数组，分别由keywordArr，stvArr转化。
			String[][] newKeywordArr;
			Object[][] newStvArr;

			if (keywordArr != null && stvArr != null) {

				// 定义两个转化后的二维数组，分别由keywordArr，stvArr转化。
				newKeywordArr = new String[keywordArr.length][3];
				newStvArr = new Object[stvArr.length][4];

				// 存放可以高亮的知识点下标，供stvArr转化使用
				List<Integer> sourceList = new ArrayList<Integer>();
				// 存放知识点List
				List<Cluster> clusterList = new ArrayList<Cluster>();
				if (isStudent) {// 当前登录为学生
					String studentID = (String) request.getSession().getAttribute("userID");
					// 得到该学生所需知识点List
					clusterList = cluserService.getKeywordListOfStudent(grade, courseName, Integer.parseInt(studentID));
				}
				// 有关联网数据才能进入
				if (keywordArr != null && stvArr != null) {

					// 转化keywordArr数组为带高亮参数的二维数组
					for (int i = 0; i < keywordArr.length; i++) {
						newKeywordArr[i][0] = keywordArr[i];
						newKeywordArr[i][2] = "0";
						if (isStudent) {
							int j;
							for (j = 0; j < clusterList.size(); j++) {
								if (changeString(clusterList.get(j).getName()).equals(keywordArr[i])) {
									newKeywordArr[i][1] = "1";
									sourceList.add(i);
									break;
								}
							}
							if (j == clusterList.size()) {
								newKeywordArr[i][1] = "0";
							}
						} else {
							newKeywordArr[i][1] = "0";
						}
					}
					// 转化stvArr数组为带高亮参数的二维数组
					for (int i = 0; i < stvArr.length; i++) {
						newStvArr[i][0] = stvArr[i][0];
						newStvArr[i][1] = stvArr[i][1];
						newStvArr[i][2] = stvArr[i][2];
						if (isStudent) {
							int j;
							for (j = 0; j < sourceList.size(); j++) {

								if (sourceList.get(j) == stvArr[i][0] || sourceList.get(j) == stvArr[i][1]) {
									newStvArr[i][3] = "1";
									break;
								}
							}
							if (j == sourceList.size()) {
								newStvArr[i][3] = "0";
							}
						} else {
							newStvArr[i][3] = "0";
						}
					}
				}
			} else {
				newKeywordArr = new String[1][2];
				newStvArr = new Object[1][4];
				newKeywordArr[0][0] = "没有可关联知识点,请重新选择";
				newStvArr[0][0] = 0;
				newStvArr[0][1] = 0;
				newStvArr[0][2] = 0;
				newStvArr[0][3] = 0;
			}

			staticNewKeywordArr = newKeywordArr;
			staticNewStvArr = newStvArr;
			
			request.setAttribute("keywordArray", newKeywordArr);
			request.setAttribute("edges", newStvArr);
		}
		return "f:/index/jsp/relation/relation.jsp";

	}

	/**
	 * 加载下拉框年级信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void loadInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> gradeList = new ArrayList<String>();
		// 能得到sID表明现在是学生登录
		if ((String) request.getSession().getAttribute("sID") != null) {
			String studentID = (String) request.getSession().getAttribute("userID");
			// 得到学生所有做过试卷的年级列表
			List<VScope> vScopeList = sts.getStudentGradeList(Integer.parseInt(studentID));
			for (int i = 0; i < vScopeList.size(); i++) {
				VScope vscope = vScopeList.get(i);
				gradeList.add(vscope.getName());
			}
			isStudent = true;
		} else if((String) request.getSession().getAttribute("userID") != null){// 否则是教师登录
			String teacherID = (String) request.getSession().getAttribute("userID");// 得到教师ID
			gradeList = tts.getGradeList(Integer.parseInt(teacherID));// 得到教师的年级列表
			isStudent = false;
		}

		request.setAttribute("gradeList", gradeList);

	}

	/**
	 * 根据科目和年级拼接5张R图路径并设置到jsp
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void setPicture(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String course = staticCourse;
		// 截取前面三个字符串，得到类似于（三年级）
		String grade = staticGrade.substring(0, 3);

		StringBuffer picturePath = new StringBuffer("relation/");

		if (course.equals("语文")) {
			picturePath.append("语文/").append(grade + "/");
		} else if (course.equals("数学")) {
			picturePath.append("数学/").append(grade + "/");
		} else if (course.equals("英文")) {
			// 英文只有三年级的数据
			picturePath.append("英文/").append("三年级/");
		} else {
			System.out.println("course不正确或为空");
		}

		String fsetsLiftPath = picturePath.toString() + "fsetsLift.png";
		String ScottPlotPath = picturePath.toString() + "ScottPlot.png";
		String fsetsSupPath = picturePath.toString() + "fsetsSup.png";
		String GraphPath = picturePath.toString() + "Graph.png";
		String GroupedMatrixPath = picturePath.toString() + "GroupedMatrix.png";

		request.setAttribute("img_fsetsLift", fsetsLiftPath);
		request.setAttribute("img_ScottPlot", ScottPlotPath);
		request.setAttribute("img_fsetsSup", fsetsSupPath);
		request.setAttribute("img_Graph", GraphPath);
		request.setAttribute("img_GroupedMatrix", GroupedMatrixPath);
	}

	public String changeString(String keyword) {
		if (keyword.contains(" ")) {
			String[] strArray = keyword.split(" ");
			return strArray[1];
		} else {
			return keyword;
		}
	}
	
	/**
	 * 搜索知识点
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String keyword = request.getParameter("keyword");
		
		for (int i = 0; i < staticNewKeywordArr.length; i++) {
			//如果之前已经被标记为搜索知识点，则清空标记
			if(staticNewKeywordArr[i][2].equals("1")){
				staticNewKeywordArr[i][2] = "0";
			}
			//如果该知识点包含搜索字符串，标记为搜索知识点
			if(staticNewKeywordArr[i][0].contains(keyword)){
				staticNewKeywordArr[i][2] = "1";
			}
		}
		
		// 加载下拉框年级信息
		loadInfo(request, response);
		if(staticGrade != null){
			// 设置R图
			setPicture(request, response);
		}
		
		//设置年级学科信息，用于页面其他地方需要
		request.setAttribute("selectedCourse", staticCourse);
		request.setAttribute("selectedGrade", staticGrade);
		//如果staticGrade为不为空，表示没有选择年级，也就没有相关知识网
		if(staticGrade != null){
			request.setAttribute("keywordArray", staticNewKeywordArr);
			request.setAttribute("edges", staticNewStvArr);
		}
		
		return "f:/index/jsp/relation/relation.jsp";
	}
	
	public void getKeywordDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyName = request.getParameter("keywordName");
		int index = 0;	
		List<Integer> targetList = new ArrayList<Integer>();
		List<KeywordTable> KeywordTableList = new ArrayList<KeywordTable>();
		//遍历知识点数组找出所需知识点的索引位置
		for(int i = 0;i < staticNewKeywordArr.length;i++){
			if(staticNewKeywordArr[i][0].equals(keyName)){
				index = i;
				break;
			}
		}
		//遍历知识点连线数组找出源头为所需索引的目标知识点索引位置，并装进targetList
		for(int j = 0;j < staticNewStvArr.length;j++){
			if((Integer)staticNewStvArr[j][0] == index){
				int target = (Integer)staticNewStvArr[j][1];
				targetList.add(target);
			}
		}
		//将前继知识点转换成知识点关联表模型装进KeywordTableList
		Keyword sKeyword = new Keyword();
		sKeyword.setKeyName(keyName);
		Keyword sourceKeyword = rs.getKeywordInfoByName(sKeyword);
		KeywordTable sKeywordTable = new KeywordTable();
		sKeywordTable.setKeywordType("前继知识点");
		sKeywordTable.setKeywordName(sourceKeyword.getKeyName());
		sKeywordTable.setEasyCount(sourceKeyword.getEasyCount());
		sKeywordTable.setMiddleCount(sourceKeyword.getMiddleCount());
		sKeywordTable.setHardCount(sourceKeyword.getHardCount());	
		sKeywordTable.setRightRate(0);
		sKeywordTable.setWrongRate(0);
		
		KeywordTableList.add(sKeywordTable);
		
		//遍历后继知识点并转换成知识点关联表模型装进KeywordTableList
		for (Integer targetIndex : targetList) {
			String targetName = staticNewKeywordArr[targetIndex][0];
			
			Keyword tKeyword = new Keyword();
			tKeyword.setKeyName(targetName);
			Keyword targetKeyword = rs.getKeywordInfoByName(tKeyword);
			KeywordTable tKeywordTable = new KeywordTable();
			tKeywordTable.setKeywordType("后序知识点");
			tKeywordTable.setKeywordName(targetKeyword.getKeyName());
			tKeywordTable.setEasyCount(targetKeyword.getEasyCount());
			tKeywordTable.setMiddleCount(targetKeyword.getMiddleCount());
			tKeywordTable.setHardCount(targetKeyword.getHardCount());
			
			double sourceKeywordCount = sourceKeyword.getRightCount() + sourceKeyword.getWrongCount();
			double targetKeywordCount = targetKeyword.getRightCount() + targetKeyword.getWrongCount();
			double sourceRightRate = sourceKeywordCount == 0 ? 0 : (sourceKeyword.getRightCount() / sourceKeywordCount);
			double targetRightRate = targetKeywordCount == 0 ? 0 : (targetKeyword.getRightCount() / targetKeywordCount);
			double sourceWrongRate = sourceKeywordCount == 0 ? 0 : (sourceKeyword.getWrongCount() / sourceKeywordCount);
			double targetWrongRate = targetKeywordCount == 0 ? 0 : (targetKeyword.getWrongCount() / targetKeywordCount);
			double rightRate = sourceRightRate * targetRightRate;
			double wrongRate = sourceWrongRate * targetWrongRate;
			tKeywordTable.setRightRate(DoubleFormat.getDoubleFormat(rightRate,2));
			tKeywordTable.setWrongRate(DoubleFormat.getDoubleFormat(wrongRate,2));
			
			KeywordTableList.add(tKeywordTable);
		}

		//将KeywordTableList转成json并传回后台
		response.getWriter().print(JSONArray.fromObject(KeywordTableList));
	}
}

