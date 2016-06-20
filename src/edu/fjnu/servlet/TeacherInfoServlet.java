package edu.fjnu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.servlet.BaseServlet;
import edu.fjnu.domain.Teacher;
import edu.fjnu.service.TeacherInfoService;

/**
 * 教师端处理
 * 
 * @author vengeance
 *
 */
@WebServlet("/TeacherInfoServlet")
public class TeacherInfoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private TeacherInfoService teacherService = new TeacherInfoService();

	/**
	 * 老师登录后载入index页面的所有信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void teaLoadInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Teacher teacher = new Teacher();

		teacher.setMemberID((String) session.getAttribute("userID"));// 设置ID
		teacherInfo(request, response, teacher);

		request.getRequestDispatcher("/index/jsp/firstpage-t.jsp").forward(request, response);
	}

	/**
	 * 得到教师信息
	 * @param request
	 * @param response
	 * @param teacher
	 * @throws ServletException
	 * @throws IOException
	 */
	private void teacherInfo(HttpServletRequest request, HttpServletResponse response, Teacher teacher)
			throws ServletException, IOException {
		teacher = teacherService.teacherInfo(teacher);

		request.getSession().setAttribute("name", teacher.getName());
		request.getSession().setAttribute("course", teacher.getCourse());
	}
}
