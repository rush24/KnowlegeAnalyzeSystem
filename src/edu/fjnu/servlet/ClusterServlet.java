package edu.fjnu.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;
import edu.fjnu.domain.Cluster;
import edu.fjnu.service.ClusterService;

/**
 * 聚类分析
 * @author zhangzhiyong
 *
 */
@WebServlet("/ClusterServlet")
public class ClusterServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ClusterService clusterService = new ClusterService(); 
	
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String subject = request.getParameter("subject");
		
		/**
		 * 以下语句实时运算出聚类常模
		 */
//		List<Cluster> goodList = clusterService.getAllKeywordsByLevel(subject,"good");
//		List<Cluster> badList = clusterService.getAllKeywordsByLevel(subject,"bad");
//		List<Cluster> middle_goodList = clusterService.getAllKeywordsByLevel(subject,"middle_good");
//		List<Cluster> middle_badList = clusterService.getAllKeywordsByLevel(subject,"middle_bad");
		
		/**
		 * 以下语句将实时运算出得聚类常模存入数据库
		 */
//		for (Cluster cluster : goodList) {
//			clusterService.add(cluster, "good", subject);
//		}
//		for (Cluster cluster : badList) {
//			clusterService.add(cluster, "bad", subject);
//		}
//		for (Cluster cluster : middle_goodList) {
//			clusterService.add(cluster, "middle_good", subject);
//		}
//		for (Cluster cluster : middle_badList) {
//			clusterService.add(cluster, "middle_bad", subject);
//		}
		
		/**
		 * 直接从数据库中读取已经存好的聚类常模（载入速度提升1000倍）
		 */
		List<Cluster> goodList = clusterService.findAll("good", subject);
		List<Cluster> badList = clusterService.findAll("bad", subject);
		List<Cluster> middle_goodList = clusterService.findAll("middle_good", subject);
		List<Cluster> middle_badList = clusterService.findAll("middle_bad", subject);
		
		request.setAttribute("goodList", goodList);
		request.setAttribute("badList", badList);
		request.setAttribute("middle_goodList", middle_goodList);
		request.setAttribute("middle_badList", middle_badList);
		
		return "f:/index/jsp/class/cluster.jsp";
	}
}
