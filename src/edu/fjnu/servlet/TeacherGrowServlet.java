package edu.fjnu.servlet;

import cn.itcast.servlet.BaseServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import edu.fjnu.domain.GradeInfo;
import edu.fjnu.domain.GrowPicInfo;
import edu.fjnu.domain.StuPrInfo;
import edu.fjnu.domain.StudentPr;
import edu.fjnu.service.GradeInfoService;
import edu.fjnu.service.GrowService;
import edu.fjnu.service.TeacherGrowService;

/**
 * 老师得到学生成长轨迹图的servlet
 */
@WebServlet("/TeacherGrowServlet")
public class TeacherGrowServlet extends BaseServlet {
	public TeacherGrowService tgs = new TeacherGrowService();
	public GrowService gs = new GrowService();
	GradeInfoService gradeInfoService = new GradeInfoService();
	private static final long serialVersionUID = 1L;

	/**
	 * 加载学生给轨迹模块下拉框
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadGrowStu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String teacherId = (String) request.getSession().getAttribute("userID"); // 得到
																					// 教师id
		List<String> stuList = tgs.getStudent(teacherId); // 调用dao得到学生名字的list
		request.setAttribute("stuList", stuList); // 把这个list返回给jsp的下拉框
		return "f:/index/jsp/grade/grow-t.jsp";
	}

	/**
	 * 加载学生给履历模块下拉框
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadGradeStu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String teacherId = (String) request.getSession().getAttribute("userID"); // 得到
																					// 教师id
		List<String> stuList = tgs.getStudent(teacherId); // 调用dao得到学生名字的list
		request.getSession().setAttribute("stuList", stuList); // 把这个list返回给jsp的下拉框
		return "f:/index/jsp/grade/grade-t.jsp";
	}

	/**
	 * 加载学生年级给履历模块下拉框
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadStuGradeList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stuName = request.getParameter("stuName");// 得到学生的名字
		System.out.println(stuName);
		//request.setAttribute("selectedStu", stuName);
		//String teacherId = (String) request.getSession().getAttribute("userID"); // 得到教师id
		//List<String> stuList = tgs.getStudent(teacherId); // 调用dao得到学生名字的list
		//request.getSession().setAttribute("stuList", stuList); // 把这个list返回给jsp的下拉框
		List<StudentPr> stuYearList = gradeInfoService.getStudiedYearListByName(stuName);
		
		JSONArray jsonArray = JSONArray.fromObject(stuYearList);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(jsonArray.toString());
		pw.flush();
		pw.close();

		//return "f:/index/jsp/grade/grade-t.jsp";
		return null;
	}

	/**
	 * 加载学生成长轨迹图的数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadStuPr(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stuName = request.getParameter("stuName");
		
		List<StudentPr> chineseList = new ArrayList<StudentPr>(); // 语文的StudentPr值列表
		List<StudentPr> mathList = new ArrayList<StudentPr>(); // 数学的StudentPr值列表
		List<StudentPr> englishList = new ArrayList<StudentPr>(); // 英语的StudentPr值列表
		
		chineseList = gs.getStudentPrs(stuName, "语文");
		//System.out.println(chineseList);
		mathList = gs.getStudentPrs(stuName, "数学");
		englishList = gs.getStudentPrs(stuName, "英文");
		
		//把上面三个集合都存进stuPrInfo里面
		StuPrInfo stuPrInfo = new StuPrInfo(); 
		stuPrInfo.setChineseList(chineseList);
		stuPrInfo.setMathList(mathList);
		stuPrInfo.setEnglishList(englishList);
		
		//把stuPrInfo转成json格式并传给前端
		JSONArray jsonArray = JSONArray.fromObject(stuPrInfo);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(jsonArray.toString());
		pw.flush();
		pw.close();
		

		/*List<StudentPr> chineseList = new ArrayList<StudentPr>(); // 语文的StudentPr值列表
		List<StudentPr> mathList = new ArrayList<StudentPr>(); // 数学的StudentPr值列表
		List<StudentPr> englishList = new ArrayList<StudentPr>(); // 英语的StudentPr值列表

		chineseList = gs.getStudentPrs(stuName, "语文");
		//System.out.println(chineseList);
		mathList = gs.getStudentPrs(stuName, "数学");
		englishList = gs.getStudentPrs(stuName, "英文");

		request.setAttribute("selectStu", stuName);
		request.setAttribute("chineseList", chineseList);// 把语文的StudentPr值列表传到jsp界面
		request.setAttribute("mathList", mathList);// 把数学的StudentPr值列表传到jsp界面
		request.setAttribute("englishList", englishList); // 把英文的StudentPr值列表传到jsp界面
*/
		//return "f:/index/jsp/grade/grow-t.jsp";
		return null;
	}

	/**
	 * 加载学生成长履历图的数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadGradeInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("come here");
		String teacherId = (String) request.getSession().getAttribute("userID"); // 得到
		String stuName = request.getParameter("stuName");
		String classYear = request.getParameter("grade");
		//System.out.println("学生："+stuName+"年纪："+classYear);

	/*	
		List<String> stuList = tgs.getStudent(teacherId); // 调用dao得到学生名字的list
		request.setAttribute("stuList", stuList); // 把这个list返回给jsp的下拉框
		//System.out.println(stuList);
		if(stuName != null){//如果有选择学生
			//System.out.println("选择了学生");
			//加载年级下拉框
			List<StudentPr> stuYearList = gradeInfoService.getStudiedYearListByName(stuName);
			request.setAttribute("stuYearList", stuYearList);
		}
		request.setAttribute("selectedStu", stuName);
		request.setAttribute("selectedGrade", classYear);*/
		
		if(stuName != null && classYear != null){//如果学生年级都有选择
			//System.out.println("选择了年级");
			List<GradeInfo> chineseGradeBefList = new ArrayList<GradeInfo>();// 语文前测
			List<GradeInfo> chineseGradeAftList = new ArrayList<GradeInfo>();// 语文后测
			List<GradeInfo> chineseGradeCutList = new ArrayList<GradeInfo>();// 语文前后测分差
			List<GradeInfo> chineseGradeHighList = new ArrayList<GradeInfo>();// 语文前后测分差起始位置
			List<GradeInfo> mathGradeBefList = new ArrayList<GradeInfo>();
			List<GradeInfo> mathGradeAftList = new ArrayList<GradeInfo>();
			List<GradeInfo> mathGradeCutList = new ArrayList<GradeInfo>();
			List<GradeInfo> mathGradeHighList = new ArrayList<GradeInfo>();
			List<GradeInfo> englishGradeBefList = new ArrayList<GradeInfo>();
			List<GradeInfo> englishGradeAftList = new ArrayList<GradeInfo>();
			List<GradeInfo> englishGradeCutList = new ArrayList<GradeInfo>();
			List<GradeInfo> englishGradeHighList = new ArrayList<GradeInfo>();
	
			List<GradeInfo> chineseGradeAndPr = new ArrayList<GradeInfo>();// 语文成绩与PR值关系分布数据
			List<GradeInfo> mathGradeAndPr = new ArrayList<GradeInfo>();
			List<GradeInfo> englishGradeAndPr = new ArrayList<GradeInfo>();
	
			chineseGradeBefList = gradeInfoService.getGradeBef(stuName, "语文", classYear);
			chineseGradeAftList = gradeInfoService.getGradeAft(stuName, "语文", classYear);
			chineseGradeCutList = gradeInfoService.getGradeCut(stuName, "语文", classYear);
			chineseGradeHighList = gradeInfoService.getGradeHigh(stuName, "语文", classYear);
			mathGradeBefList = gradeInfoService.getGradeBef(stuName, "数学", classYear);
			mathGradeAftList = gradeInfoService.getGradeAft(stuName, "数学", classYear);
			mathGradeCutList = gradeInfoService.getGradeCut(stuName, "数学", classYear);
			mathGradeHighList = gradeInfoService.getGradeHigh(stuName, "数学", classYear);
			englishGradeBefList = gradeInfoService.getGradeBef(stuName, "英文", classYear);
			englishGradeAftList = gradeInfoService.getGradeAft(stuName, "英文", classYear);
			englishGradeCutList = gradeInfoService.getGradeCut(stuName, "英文", classYear);
			englishGradeHighList = gradeInfoService.getGradeHigh(stuName, "英文", classYear);
	
			chineseGradeAndPr = gradeInfoService.getGradePr(stuName, "语文", classYear);
			mathGradeAndPr = gradeInfoService.getGradePr(stuName, "数学", classYear);
			englishGradeAndPr = gradeInfoService.getGradePr(stuName, "英文", classYear);
			
			GrowPicInfo growPicInfo = new GrowPicInfo();
			
			growPicInfo.setChineseGradeBefList(chineseGradeBefList);
			growPicInfo.setChineseGradeAftList(chineseGradeAftList);
			growPicInfo.setChineseGradeCutList(chineseGradeCutList);
			growPicInfo.setChineseGradeHighList(chineseGradeHighList);
			growPicInfo.setChineseGradeAndPr(chineseGradeAndPr);
			
			growPicInfo.setEnglishGradeBefList(englishGradeBefList);
			growPicInfo.setEnglishGradeAftList(englishGradeAftList);
			growPicInfo.setEnglishGradeCutList(englishGradeCutList);
			growPicInfo.setEnglishGradeHighList(englishGradeHighList);
			growPicInfo.setEnglishGradeAndPr(englishGradeAndPr);
			
			growPicInfo.setMathGradeBefList(mathGradeBefList);
			growPicInfo.setMathGradeAftList(mathGradeAftList);
			growPicInfo.setMathGradeCutList(mathGradeCutList);
			growPicInfo.setMathGradeHighList(mathGradeHighList);
			growPicInfo.setMathGradeAndPr(mathGradeAndPr);
			
			JSONArray jsonArray=JSONArray.fromObject(growPicInfo);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.write(jsonArray.toString());
			pw.flush();
			pw.close();
	
			/*request.setAttribute("chineseGradeBefList", chineseGradeBefList);
			request.setAttribute("chineseGradeAftList", chineseGradeAftList);
			request.setAttribute("chineseGradeCutList", chineseGradeCutList);
			request.setAttribute("chineseGradeHighList", chineseGradeHighList);
	
			request.setAttribute("mathGradeBefList", mathGradeBefList);
			request.setAttribute("mathGradeAftList", mathGradeAftList);
			request.setAttribute("mathGradeCutList", mathGradeCutList);
			request.setAttribute("mathGradeHighList", mathGradeHighList);
	
			request.setAttribute("englishGradeBefList", englishGradeBefList);
			request.setAttribute("englishGradeAftList", englishGradeAftList);
			request.setAttribute("englishGradeCutList", englishGradeCutList);
			request.setAttribute("englishGradeHighList", englishGradeHighList);
	
			request.setAttribute("chineseGradeAndPrList", chineseGradeAndPr);
			request.setAttribute("mathGradeAndPrList", mathGradeAndPr);
			request.setAttribute("englishGradeAndPrList", englishGradeAndPr);*/
		}

		//return "f:/index/jsp/grade/grade-t.jsp";
		return null;
	}
}
