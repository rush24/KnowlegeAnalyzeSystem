package edu.fjnu.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rosuda.REngine.Rserve.RserveException;

import cn.itcast.servlet.BaseServlet;
import edu.fjnu.domain.KeywordNode;
import edu.fjnu.service.CMFileUploadService;
import edu.fjnu.service.CustomerMadeMapService;
import edu.fjnu.service.RelationService;
import edu.fjnu.service.RelationshipService;
import edu.fjnu.util.FileTools;
import edu.fjnu.util.RelationResult;
import edu.fjnu.util.Tool;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author zhangzhiyong
 * 知识地图
 *
 */
@WebServlet("/KnowlegeMapServlet")
@SuppressWarnings("unchecked")
public class KnowlegeMapServlet extends BaseServlet {
	
	private static final long serialVersionUID = 1L;
	private RelationResult rr = new RelationResult();// 知识点关联工具（获取关联数据）
	private RelationService rs = new RelationService();
	private RelationshipService rss = new RelationshipService();
	private CustomerMadeMapService cms = new CustomerMadeMapService();
	private static String courseName = "";//学科名称
	public final static String successResponse = "{\"status\":1}";// 成功响应的json格式
	private boolean hasCustomerMade = false;//是否已经定制地图
	private boolean chooseCustomerMade = false;//是否选择查看定制地图

	
	/**
	 * 获取地图所需数据
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getMapData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		courseName = request.getParameter("course");
		request.getSession().setAttribute("course", courseName);
		
		//判断用户是否定制过地图
		if(cms.hasCustomerMade(Tool.getCurrentLoginMemberId(request), courseName)){
			hasCustomerMade = true;
			request.getSession().setAttribute("hasCustomerMade", true);
		}else{
			hasCustomerMade = false;
			request.getSession().setAttribute("hasCustomerMade", false);
		}
		
		//判断用户选择查看哪种地图
		if("customerMadeMap".equals(request.getParameter("mapType"))){
			chooseCustomerMade = true;
			request.getSession().setAttribute("chooseCustomerMade", true);
		}else{
			chooseCustomerMade = false;
			request.getSession().setAttribute("chooseCustomerMade", false);
		}
		
		//切换地图
		if(hasCustomerMade && chooseCustomerMade){
			request.setAttribute("keywordNodeList", cms.get_keywordNodeList(Tool.getCurrentLoginMemberId(request), courseName));
			request.setAttribute("keywordLineList", cms.get_keywordNodeLineList(Tool.getCurrentLoginMemberId(request), courseName));
			cms.initFindSourceAndPredictPath(Tool.getCurrentLoginMemberId(request), courseName);
			System.out.println("------: " +  cms.get_keywordNodeList(Tool.getCurrentLoginMemberId(request), courseName).size());
		}else{
			request.setAttribute("keywordNodeList", rr.get_keywordNodeList(courseName));
			request.setAttribute("keywordLineList", rr.get_keywordLineList(courseName));
			rr.initFindSourcePath(courseName);//初始化知识点朔源前的准备工作
		}
		return "f:/index/jsp/knowlegeMap/knowlegeMap.jsp"; 
	}
	
	/**
	 * 搜索知识点
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchKeywordNode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keywordName = request.getParameter("keywordName");
		KeywordNode keywordNode = new KeywordNode();
		if(hasCustomerMade && chooseCustomerMade){
			keywordNode = cms.getCMKeywordNodeByBlurKeywordName(keywordName, Tool.getCurrentLoginMemberId(request), courseName);
		}else{
			keywordNode = rr.getKeywordNodeByBlurKeywordName(keywordName, courseName);
		}
		response.getWriter().print(JSONObject.fromObject(keywordNode));
	}
	
	/**
	 * 朔源
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findSourcePath(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keywordName = request.getParameter("keywordName");
		List<KeywordNode[]> sourcePathList;
		List<KeywordNode[]> predictPathList;
		if(hasCustomerMade && chooseCustomerMade){
			sourcePathList = cms.getFindSourcePaths(keywordName, Tool.getCurrentLoginMemberId(request), courseName);
			predictPathList = cms.getPredictPaths(keywordName, Tool.getCurrentLoginMemberId(request),courseName);
		}else{
			sourcePathList = rr.getFindSourcePaths(keywordName,courseName);
			predictPathList = rr.getPredictPaths(keywordName,courseName);
		}
		
		List sourceAndpredictPathList = new ArrayList<List<KeywordNode[]>>();
		sourceAndpredictPathList.add(sourcePathList);
		sourceAndpredictPathList.add(predictPathList);
		response.getWriter().print(JSONArray.fromObject(sourceAndpredictPathList));
	}
	
	/**
	 * 上传用户定制地图csv文件
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void fileUpload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CMFileUploadService fileUploadService = new CMFileUploadService();
		String fileName = Tool.getCurrentLoginMemberId(request) + "_" + courseName + "_上传数据.csv";
		fileUploadService.uploadFile(fileName, request);
		//记录该用户地图类型
		cms.saveCustomerMapType(Tool.getCurrentLoginMemberId(request), courseName);
		//存进数据库
		rs.saveUserUploadDataIntoDatabase(Tool.getCurrentLoginMemberId(request), courseName);
		//生成用户原始数据给R
		rss.createCMRelationCSVForRtool(courseName, Tool.getCurrentLoginMemberId(request));
		
		response.getWriter().print("{\"status\":1}");
	}
	
	/**
	 * 调用R算法关联知识点
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void AssociateKeywordsWithR(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = FileTools.getApplicationRootPath() + "/R/csv/customerMade/";
		String filename = path + Tool.getCurrentLoginMemberId(request) 
			+ "_" + FileTools.translateSomeChineseIntoEnglish(courseName) + "_origin.csv";
		try {
			rss.updateRelationCsvFileForCustomMadeKnowledgeMap(new File(filename));
		} catch (RserveException e) {
			e.printStackTrace();
		}
		response.getWriter().print("{\"status\":2}");
	}
	
	
}
