package edu.fjnu.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.junit.Test;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.codec.Base64.InputStream;
import com.lowagie.text.pdf.codec.Base64.OutputStream;

import cn.itcast.servlet.BaseServlet;
import edu.fjnu.domain.Student;
import edu.fjnu.domain.VScope;
import edu.fjnu.service.PdfUpdateService;
import edu.fjnu.service.StudentPaperService;
import edu.fjnu.util.FileTools;
import edu.fjnu.util.PDFUtil;

@WebServlet("/PDFServlet")
public class PDFServlet extends BaseServlet {
	/**
	 * 
	 */
	public void downloadPDF(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 从session取出用户名和id,从请求范围取出scope
		String studentID = (String) request.getSession().getAttribute("sID");
		String studentName = (String) request.getSession().getAttribute("sname");
		String grade = request.getParameter("grade");

		Student stu = new Student();
		stu.setSname(studentName);
		stu.setMemberID(studentID);

		PDFUtil pdftool = new PDFUtil();

		// 创建文档
		Document pdf = pdftool.createDocument(FileTools.getApplicationRootPath() + stu.getSname() + grade + "分析报告.pdf");
		pdf.open();
		System.out.println(FileTools.getApplicationRootPath());

		// 制作封面
		pdftool.makePDFCover(pdf, "\n综 合 分 析 报 告", "学号：" + studentID + "\n姓名：" + studentName);

		
		PdfUpdateService.GreadeAnalasisForAll(stu, grade, pdf);
		PdfUpdateService.TestAnalasisForAll(stu, grade, pdf);
		PdfUpdateService.RelationAnalasisForAll(stu, grade, pdf);
		pdf.close();

		PrintWriter out = response.getWriter();
		out.print("END");

	}

	public String loadToLocal(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String studentID = (String) request.getSession().getAttribute("sID");
		String studentName = (String) request.getSession().getAttribute("sname");
		String grade = request.getParameter("grade");

		// 获取要下载的pdf的路径+名字
		String path = FileTools.getApplicationRootPath() + studentName + grade + "分析报告.pdf";

		File file = new File(path);// 根据拼接路径+名字下载文件
		String filename = file.getName();// 得到文件名

		BufferedInputStream fis = new BufferedInputStream(new FileInputStream(path));
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();

		response.reset();
		// 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
		response.addHeader("Content-Disposition",
				"attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));
		response.addHeader("Content-Length", "" + file.length());
		BufferedOutputStream os = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/octet-stream");

		os.write(buffer);// 输出文件
		os.flush(); 
		os.close();
		file.delete();
		
		
		
		return "f:/index/jsp/firstpage";
	}
	
	@Test
	public void test1(){
		System.out.println(FileTools.getApplicationRootPath());
		
	}

}
