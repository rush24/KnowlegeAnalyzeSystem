package edu.fjnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import cn.itcast.servlet.BaseServlet;
import edu.fjnu.domain.GradeInfo;
import edu.fjnu.domain.GrowPicInfo;
import edu.fjnu.domain.StudentPr;
import edu.fjnu.service.GradeInfoService;
import edu.fjnu.service.GrowService;

/**
 * 处理学生端的成绩分析
 */
@WebServlet("/StudentGrowServlet")
public class StudentGrowServlet extends BaseServlet {
	public GrowService gs = new GrowService(); // 实例化GrowService
	GradeInfoService gradeInfoService = new GradeInfoService();
	private static final long serialVersionUID = 1L;

	/**
	 * 加载学生成长轨迹图
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadPr(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stuName = (String) request.getSession().getAttribute("sname");// 得到学生的名字

		List<StudentPr> chineseList = new ArrayList<StudentPr>(); // 语文的StudentPr值列表
		List<StudentPr> mathList = new ArrayList<StudentPr>(); // 数学的StudentPr值列表
		List<StudentPr> englishList = new ArrayList<StudentPr>(); // 英语的StudentPr值列表

		chineseList = gs.getStudentPrs(stuName, "语文");
		mathList = gs.getStudentPrs(stuName, "数学");
		englishList = gs.getStudentPrs(stuName, "英文");

		request.setAttribute("chineseList", chineseList);// 把语文的StudentPr值列表传到jsp界面
		request.setAttribute("mathList", mathList);// 把数学的StudentPr值列表传到jsp界面
		request.setAttribute("englishList", englishList); // 把英文的StudentPr值列表传到jsp界面
		return "f:/index/jsp/grade/grow-s.jsp";
	}

	/**
	 * 根据学生姓名加载学过的年级
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadStuYear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stuName = (String) request.getSession().getAttribute("sname");// 得到学生的名字
		List<StudentPr> stuYearList = gradeInfoService.getStudiedYearListByName(stuName);
		request.setAttribute("stuYearList", stuYearList);

		return "f:/index/jsp/grade/grade-s.jsp";
	}

	/**
	 * 加载学生履历图
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadGrade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stuName = (String) request.getSession().getAttribute("sname");// 得到学生的名字
		String classYear = request.getParameter("grade");
		//request.setAttribute("selectedGrade", classYear);
		//List<StudentPr> stuYearList = gradeInfoService.getStudiedYearListByName(stuName);
		System.out.println(classYear);
		List<GradeInfo> chineseGradeBefList = new ArrayList<GradeInfo>();
		List<GradeInfo> chineseGradeAftList = new ArrayList<GradeInfo>();
		List<GradeInfo> chineseGradeCutList = new ArrayList<GradeInfo>();
		List<GradeInfo> chineseGradeHighList = new ArrayList<GradeInfo>();
		List<GradeInfo> mathGradeBefList = new ArrayList<GradeInfo>();
		List<GradeInfo> mathGradeAftList = new ArrayList<GradeInfo>();
		List<GradeInfo> mathGradeCutList = new ArrayList<GradeInfo>();
		List<GradeInfo> mathGradeHighList = new ArrayList<GradeInfo>();
		List<GradeInfo> englishGradeBefList = new ArrayList<GradeInfo>();
		List<GradeInfo> englishGradeAftList = new ArrayList<GradeInfo>();
		List<GradeInfo> englishGradeCutList = new ArrayList<GradeInfo>();
		List<GradeInfo> englishGradeHighList = new ArrayList<GradeInfo>();

		List<GradeInfo> chineseGradeAndPr = new ArrayList<GradeInfo>();
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
		request.setAttribute("englishGradeAndPrList", englishGradeAndPr);

		request.setAttribute("stuYearList", stuYearList);*/

		//return "f:/index/jsp/grade/grade-s.jsp";
		return null;
	}
}
