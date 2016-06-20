package edu.fjnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import cn.itcast.servlet.BaseServlet;
import edu.fjnu.domain.TestInfo;
import edu.fjnu.domain.VQuestion;
import edu.fjnu.domain.VScope;
import edu.fjnu.domain.VTestMain;
import edu.fjnu.service.StudentPaperService;

/**
 * 处理学生端试卷分析业务
 * 
 * @author zhangzhiyong
 *
 */
@WebServlet("/StudentPaperServlet")
public class StudentPaperServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private StudentPaperService sts = new StudentPaperService();
	
	public String loadGradeList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String subject = request.getParameter("subject");
		String studentID = (String) request.getSession().getAttribute("sID");
		
		// 通过学生ID得到他所有的年级列表
		List<VScope> gradeList = new ArrayList<VScope>();
		if(studentID != null)
			gradeList = sts.getStudentGradeList(Integer.parseInt(studentID));
		
		// 将年级List设置回去供下拉框显示
		request.setAttribute("gradeList", gradeList);
		// 将选择的科目设置回去给两个下拉框，供下拉框选中后将当前的科目参数也传回
		request.setAttribute("selectSubject", subject);
		
		return "f:/index/jsp/paper/paper-s.jsp";
	}
	
	/**
	 * 获取试卷列表返回到页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public String loadTestList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String studentID = (String) request.getSession().getAttribute("sID");
		String selectGrade = request.getParameter("grade");
		
		// 得到选择的科目（该参数来自直接选择科目或者选择下拉框后继续将科目的值传过来,以实现不同科目跳转）
		String subject = request.getParameter("subject");
		System.out.println(selectGrade+subject);
		// 通过学生ID，科目，和年级得到试卷list（供选择试卷下拉框显示）
		List<VTestMain> vTestMainList = sts.getTestMainsList(subject, Integer.parseInt(studentID), selectGrade);

		JSONArray jsonArray=JSONArray.fromObject(vTestMainList);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(jsonArray.toString());
		pw.flush();
		pw.close();
		
		return null;
	}

	/**
	 * 加载学生端试卷分析模块所有信息（由于一切从选择年级下拉框开始，所以函数名叫加载年级）
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadGrade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 得到下拉框选择的试卷ID
		String selectTest = request.getParameter("testID");
		TestInfo testInfo = new TestInfo(); //保存试卷信息
		if (selectTest != null) {// 如果有选择试卷才进入查询
			
			// 根据选择的试卷ID查询试卷题目并设置回去
			List<VQuestion> vQuestionList = sts.getVQuestionList(Integer.parseInt(selectTest));
			request.setAttribute("vQuestionList", vQuestionList);
			// 通过选择的试卷ID得到试卷涵盖的知识点范围
			String scope = sts.getScope(Integer.parseInt(selectTest));
			// 通过知识点范围得到知识点和相应的错误率list并设置回去
			List<VQuestion> vQuestionKeywordList = sts.getKeywordAndWrongByScope(scope);
			//request.setAttribute("vQuestionKeywordList", vQuestionKeywordList);
			testInfo.setvQuestionKeywordList(vQuestionKeywordList);
			testInfo.setvQuestionList(vQuestionList);
			
			JSONArray jsonArray=JSONArray.fromObject(testInfo);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.write(jsonArray.toString());
			pw.flush();
			pw.close();
		}
		
		
		/*// 将年级List设置回去供下拉框显示
		request.setAttribute("vScopeList", vScopeList);
		// 将年级下拉框中选中得年级设置回去，供下拉框显示选中哪个
		request.setAttribute("selectGrade", selectGrade);
		// 将试卷下拉框中选中得试卷ID设置回去，供下拉框显示选中哪个
		request.setAttribute("selectTest", selectTest);
		// 将选择的科目设置回去给两个下拉框，供下拉框选中后将当前的科目参数也传回
		request.setAttribute("selectSubject", subject);
		// 将试卷题目Lits设置回去
		request.setAttribute("vTestMainList", vTestMainList);*/

		// 转发到p-chinese-s.jsp页面，不论选择语数英均在该页面显示，只是数据变了
		//return "f:/index/jsp/paper/paper-s.jsp";
		return null;
	}
}
