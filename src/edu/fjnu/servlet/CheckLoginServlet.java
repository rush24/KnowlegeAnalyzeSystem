package edu.fjnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.servlet.BaseServlet;
import edu.fjnu.domain.Student;
import edu.fjnu.domain.Teacher;
import edu.fjnu.service.StudentInfoService;
import edu.fjnu.service.TeacherInfoService;
import edu.fjnu.util.Tool;

/**
 * 验证登录信息
 * 
 * @author vengeance
 * 
 */
@WebServlet("/CheckLoginServlet")
public class CheckLoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 判断登录的是老师还是学生
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String permission = request.getParameter("user");
		HttpSession session = request.getSession();
		String userID = request.getParameter("username");
		String password = request.getParameter("password");
		
		// 对密码进行MD5加密,//如果从br跳转，不加密
		if(request.getParameter("from")!=null && "BR".equals(request.getParameter("from")) ){
			//不加密
		}else {
			password = Tool.encodeByMD5(password);	
		}
	
		if (permission.equals("student")) {// 如果是学生
			Student student = new Student();
			StudentInfoService stuService = new StudentInfoService();

			student.setMemberID(userID);
			student.setPassword(password);
			
			if (stuService.checkInfo(student)) {// 验证学生登录信息是否正确，如果正确，将登录信息保存在session中
				session.setAttribute("userID", student.getMemberID());
				if (request.getParameter("from")!=null && "BR".equals(request.getParameter("from"))) {
					//BR组登录
					request.getRequestDispatcher("/StudentInfoServlet?method=stuInfo").forward(request, response);
				}else {
					PrintWriter out = response.getWriter();
					out.print("studentOk");
				}
				
			} else {// 如果登录失败
				PrintWriter out = response.getWriter();
				out.print("no");
			}
		} else if (permission.equals("teacher")) {// 如果是老师
			Teacher teacher = new Teacher();
			TeacherInfoService teaService = new TeacherInfoService();
			teacher.setMemberID(userID);
			teacher.setPassword(password);
			if (teaService.checkInfo(teacher)) {// 验证老师登录信息是否正确，如果正确，将登录信息保存在session中
				session.setAttribute("userID", userID);
				PrintWriter out = response.getWriter();
				out.print("teacherOk");
			} else {// 如果登录失败
				PrintWriter out = response.getWriter();
				out.print("no");// Ajax回调到index.jsp

			}
		}
	}
}
