package edu.fjnu.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import cn.itcast.servlet.BaseServlet;
import edu.fjnu.domain.TestInfo;
import edu.fjnu.domain.VQuestion;
import edu.fjnu.domain.VTestMain;
import edu.fjnu.service.PaperService;
import edu.fjnu.service.TeacherPaperService;
import edu.fjnu.util.DoubleFormat;

@WebServlet("/TeacherPaperServlet")
public class TeacherPaperServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private TeacherPaperService tts = new TeacherPaperService();
	private PaperService pservice = new PaperService();
	
	/**
	 * 加载年级列表
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public String loadGradeList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String subject = request.getParameter("subject");
		String teacherID = (String) request.getSession().getAttribute("userID");
		// 得到下拉框选择的年级名
		List<String> gradeList = tts.getGradeList(Integer.parseInt(teacherID));
		
		// 将年级List设置回去供下拉框显示
		request.setAttribute("gradeList", gradeList);
		// 将选择的科目设置回去给两个下拉框，供下拉框选中后将当前的科目参数也传回
		request.setAttribute("selectSubject", subject);
		
		return "f:/index/jsp/paper/paper-t.jsp";
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
		String teacherID = (String) request.getSession().getAttribute("userID");
		String selectGrade = request.getParameter("grade");
		String subject = request.getParameter("subject");
		
		List<VTestMain> vTestMainList = null;// 存放试卷列表（主要取试卷ID）
		List<Integer> studentIDList = null;// 存放学生ID列表
		
		if (selectGrade != null) {// 有选择年级
			// 通过teacherID、courseName、grade得到studentIDList
			studentIDList = tts.getStudentId(Integer.parseInt(teacherID), subject, selectGrade);
			if (studentIDList.size() >= 1) {// 有学生
				// 通过学生ID列表、科目、年级得到试卷List（供选择试卷下拉框显示）
				vTestMainList = tts.getTestMainsList(subject, studentIDList, selectGrade);
			}
		}
		
		JSONArray jsonArray=JSONArray.fromObject(vTestMainList);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(jsonArray.toString());
		pw.flush();
		pw.close();
		
		return null;
	}
	
	/**
	 * 加载教师端试卷分析模块所有信息（由于一切从选择年级下拉框开始，所以函数名叫加载年级）
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public String loadGrade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		String selectTest = request.getParameter("testID");
		// 通过教师ID得到他所有的年级列表
		//String teacherID = (String) request.getSession().getAttribute("userID");
		// 得到下拉框选择的年级名
		//List<String> gradeList = tts.getGradeList(Integer.parseInt(teacherID));
		// 得到下拉框选择的试卷ID
		//String selectGrade = request.getParameter("grade");
		// 得到选择的科目（该参数来自直接选择科目或者选择下拉框后继续将科目的值传过来,以实现不同科目跳转）
		//String subject = request.getParameter("subject");

		//List<Integer> studentIDList = null;// 存放学生ID列表
		//List<VTestMain> vTestMainList = null;// 存放试卷列表（主要取试卷ID）

		/*if (selectGrade != null) {// 有选择年级
			// 通过teacherID、courseName、grade得到studentIDList
			studentIDList = tts.getStudentId(Integer.parseInt(teacherID), subject, selectGrade);
			if (studentIDList.size() >= 1) {// 有学生
				// 通过学生ID列表、科目、年级得到试卷List（供选择试卷下拉框显示）
				vTestMainList = tts.getTestMainsList(subject, studentIDList, selectGrade);
			}
		}*/
		TestInfo testInfo = new TestInfo(); //保存试卷信息
		if (selectTest != null) {// 如果有选择试卷才进入查询
			// 根据选择的试卷ID查询试卷题目并设置回去
			List<VQuestion> vQuestionList = tts.getQuestionList(Integer.parseInt(selectTest));
			
			// 通过试卷ID得到知识点和相应的错误率list并设置回去
			List<VQuestion> vQuestionKeywordList = tts.getWrongRate(Integer.parseInt(selectTest));
			double hardrate = DoubleFormat.getDoubleFormat(((tts.getHardRateById(Integer.parseInt(selectTest)).getWrong()) / 24),2);
			double believerate = DoubleFormat.getDoubleFormat((1 - (pservice.getStdByTestId(Integer.parseInt(selectTest))) / 60),2);
			String hardString = "";
			String believeString = "";
			if (hardrate < 0.4) {
				hardString = "难度一般";
			} else if (hardrate >=0.4 && hardrate <= 0.7) {
				hardString = "难度中等";
			} else {
				hardString = "难度较难";
			}

			if (believerate < 0.4) {
				believeString = "可信度一般";
			} else if (believerate >= 0.4 && believerate <= 0.7) {
				believeString = "比较可信";
			} else {
				believeString = "可信度较强";
			}
			
			testInfo.setvQuestionList(vQuestionList);
			testInfo.setvQuestionKeywordList(vQuestionKeywordList);
			testInfo.setHardString(hardString);
			testInfo.setHardrate(hardrate);
			testInfo.setBelieveString(believeString);
			testInfo.setBelieverate(believerate);
			
			JSONArray jsonArray=JSONArray.fromObject(testInfo);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.write(jsonArray.toString());
			pw.flush();
			pw.close();
			/*request.setAttribute("vQuestionList", vQuestionList);
			request.setAttribute("vQuestionKeywordList", vQuestionKeywordList);
			request.setAttribute("hardrate", hardrate);
			request.setAttribute("believerate", believerate);
			request.setAttribute("hardString", hardString);
			request.setAttribute("believeString", believeString);*/
		}

		/*// 将年级List设置回去供下拉框显示
		request.setAttribute("gradeList", gradeList);
		// 将年级下拉框中选中得年级设置回去，供下拉框显示选中哪个
		request.setAttribute("selectGrade", selectGrade);
		// 将试卷下拉框中选中得试卷ID设置回去，供下拉框显示选中哪个
		request.setAttribute("selectTest", selectTest);
		// 将选择的科目设置回去给两个下拉框，供下拉框选中后将当前的科目参数也传回
		request.setAttribute("selectSubject", subject);
		// 将试卷题目Lits设置回去
		request.setAttribute("vTestMainList", vTestMainList);*/

		// 转发到paper-t.jsp页面，不论选择语数英均在该页面显示，只是数据变了
		/*return "f:/index/jsp/paper/paper-t.jsp";*/
		return null;
	}
}
