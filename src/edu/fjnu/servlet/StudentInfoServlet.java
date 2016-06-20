package edu.fjnu.servlet;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.servlet.BaseServlet;
import edu.fjnu.domain.Student;
import edu.fjnu.domain.VScope;
import edu.fjnu.service.PdfUpdateService;
import edu.fjnu.service.StudentInfoService;
import edu.fjnu.service.StudentPaperService;

/**
 * 学生信息请求处理
 * 
 * @author vengeance
 *
 */
@WebServlet("/StudentInfoServlet")
public class StudentInfoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	public StudentInfoService studentService = new StudentInfoService();// 实例化学生service方法

	/**
	 * 获取学生的基本信息并显示
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String stuInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Student student = new Student();

		// 从session中得到学生的登录信息
		student.setMemberID((String) session.getAttribute("userID"));
		student = studentService.studentInfo(student);// 调用学生的service方法查询信息

		request.getSession().setAttribute("sID", student.getMemberID());
		request.getSession().setAttribute("sname", student.getName());
		
		
		
		
		String studentID = (String) request.getSession().getAttribute("sID");
		String studentName =(String) request.getSession().getAttribute("sname");
		
		
		// 通过学生ID得到他所有的年级列表
	    StudentPaperService sts = new StudentPaperService();
		List<VScope> vScopeList = sts.getStudentGradeList(Integer.parseInt(studentID));
		request.getSession().setAttribute("gradeList", vScopeList);
		
		
		Student stu=new Student();
		stu.setSname(studentName);
		stu.setStudentID(studentID);
		
		
		return "f:/index/jsp/firstpage.jsp";
	}
}
