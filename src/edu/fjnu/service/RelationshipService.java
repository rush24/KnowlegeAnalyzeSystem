package edu.fjnu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthSpinnerUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;

import org.apache.commons.collections.map.StaticBucketMap;
import org.apache.commons.dbcp.AbandonedObjectPool;
import org.junit.Test;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import edu.fjnu.util.CSVUtil;
import edu.fjnu.util.FileTools;
import javafx.application.Application;
import net.sf.json.JSONObject;

/**
 * 
 * @author Li
 *
 */
public class RelationshipService {

	RelationService relation = new RelationService();
	private static final String[] GRADES = {"一年级","二年级","三年级","四年级","五年级","六年级","初一","初二","初三"};
		
	/**
	 * 构建知识点网图的nodes
	 * 
	 * @param name
	 * @return
	 */
	public List<String> JsonForNodes(List<String> name) {

		JSONObject jsonObject = new JSONObject();
		List<String> jsonStr = new ArrayList<String>();
		for (int i = 0; i < name.size(); i++) {
			jsonObject.put("name", name.get(i));
			jsonStr.add(jsonObject.toString());
		}
		return jsonStr;
	}

	/**
	 * 构建知识网图的links
	 * 
	 * @param link
	 * @return
	 */
	public List<String> JsonForLinks(int[][] link) {
		JSONObject jsonObject = new JSONObject();
		List<String> jsonStr = new ArrayList<String>();
		for (int i = 0; i < link.length; i++) {
			for (int j = 1; j < link[i].length; j++) {
				jsonObject.put("source", link[i][0]);
				jsonObject.put("target", link[i][j]);
				jsonStr.add(jsonObject.toString());
			}
		}
		return jsonStr;
	}

	/**
	 * 根据知识点列表生成关联分析文档
	 * 
	 * @param item
	 * @throws REngineException
	 */
	public void createAnalysisPDF(List<List<String>> item) throws REngineException {
		RConnection rconn = new RConnection();

		String[] strR = new String[200];

		try {
			for (int i = 0; i < item.size(); i++) {
				for (int j = 0; j < item.get(i).size(); j++) {
					strR[j] = item.get(i).get(j);
				}

				rconn.assign(("x" + i).toString(), strR);
			}
		} catch (RserveException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 处理为关联的知识列表的数据
	 * 
	 * @param courseName
	 * @param year
	 * @return
	 */
	public static List<String> dealRelationList(String courseName, String year) {
		RelationService relation = new RelationService();
		List<String> dealList = new ArrayList<String>();
		List<List<String>> initial = new ArrayList<List<String>>();
		initial.addAll(relation.getRelationListForRtool(courseName, year));
		// System.out.println(initial);
		String temp;
		for (int i = 0; i < initial.size(); i++) {
			temp = initial.get(i).toString();
			temp = temp.replace("[", "");
			temp = temp.replace("]", "");
			temp = temp.replace(" ", "");
			dealList.add(temp);
		}
		// for (int i1 = 0; i1 < initial.size(); i1++) {
		// System.out.println(initial.get(i1));
		// }
		// for (int i1 = 0; i1 < dealList.size(); i1++) {
		// System.out.println(dealList.get(i1));
		// }
		return dealList;
	}

	/**
	 * 
	 * @param courseName
	 * @param year
	 * @throws UnsupportedEncodingException 
	 */
	public void createRelationListCSV(String courseName, String year) throws UnsupportedEncodingException {

		String path = FileTools.getApplicationRootPath() + "/R/csv/"; 
		List<String> relationlist = dealRelationList(courseName, year);
		File file = new File(path + "temp.csv");// 读取数据文件的路径
		boolean isCSVSuccess = CSVUtil.exportCsv(file, relationlist);
		System.out.println(isCSVSuccess);
	}
	
	
	
	
	/**
	 * 创建所有年级的关联原始数据给R
	 * @param courseName
	 * @throws UnsupportedEncodingException 
	 */

	public void createCrossGradesRelationCSVForRtool(String courseName) throws UnsupportedEncodingException{
		
		String courseNameInEnglish = FileTools.translateSomeChineseIntoEnglish(courseName);
		String path = FileTools.getApplicationRootPath() + "/R/csv/" + courseNameInEnglish + "/";
		//得到各个年级的错误知识点
		for(int i = 0;i < GRADES.length;i++){
			List<String> singleGradeRelationList = relation.getSingleGradeWrongKeywordsList(courseName,GRADES[i]);
			String filename = path + FileTools.translateSomeChineseIntoEnglish(GRADES[i]) + ".csv";
			File file = new File( filename);// 读取数据文件的路径
			CSVUtil.exportCsv(file, singleGradeRelationList);
		}
		//得到跨年级的错误知识点
		List<String> crossGradesRelationList = relation.getCrossGradesWrongKeywordsList(courseName);
		File file = new File(path + "cross.csv");// 读取数据文件的路径
		CSVUtil.exportCsv(file, crossGradesRelationList);
//		new File(path + "cross.csv").delete();
//		file.renameTo(new File(path + "cross.csv"));
	}
	
	public void createCMRelationCSVForRtool(String courseName,String userID) throws UnsupportedEncodingException{
		String path = FileTools.getApplicationRootPath() + "/R/csv/customerMade/";
		String filename = path + userID + "_" + FileTools.translateSomeChineseIntoEnglish(courseName) + "_origin.csv";
		List<String> relationList = relation.getCMWrongKeywordsList(courseName,userID);
		CSVUtil.exportCsv(new File(filename), relationList);
	}

	/**
	 * 为R语言关联分析创建文件路径
	 * @param courseName
	 * @param year
	 * @return
	 * @throws IOException 
	 */
	public static List<String> createRelationFilePathForR( String courseName, String year) throws IOException{
		List<String> fileList = new ArrayList<String>();
		courseName = FileTools.translateSomeChineseIntoEnglish(courseName);
		year = FileTools.translateSomeChineseIntoEnglish(year);
		// 脚本路径
		String rPath = FileTools.getApplicationRootPath() + "R/RScript/Relation.R";
		String rPathForImag = FileTools.getApplicationRootPath() + "R/RScript/ImagForR.R";
		// 文件路径
		String inputPath = FileTools.getApplicationRootPath() + "R/csv/"+courseName + "/";
		String outputPath = FileTools.getApplicationRootPath()+ "relation/" + courseName + "/"+year+"/";
		// 保证文件路劲存在
		if(!new File(outputPath).exists()){
			new File(outputPath).mkdirs();
		}
		// 输入文件的路径
		String inputFile =  inputPath  + year + ".csv";
			
		// 输出文件
		String outputFile1 = outputPath  +  "rule1.csv";
		String outputFile2 = outputPath  + "rule.csv";
		
		// 图片的路径fsetsSupPath, fsetsLiftPath, ScottPlotPath, GroupedMatrixPath,GraphPath
		String fsetsSupPath 		= outputPath + "fsetsSup.png";
		String fsetsLiftPath 		= outputPath + "fsetsLift.png";
		String scottPlotPath 		= outputPath + "ScottPlot.png";
		String MatrixPath 	= outputPath + "Matrix.png";
		String graphPath			= outputPath + "Graph.png";
		String fsetsCsvPath			= outputPath + "fsets.csv";
		
		
		fileList.add(rPath);				// 0
		fileList.add(inputFile);			// 1
		fileList.add(outputFile1);			// 2
		fileList.add(outputFile2);			// 3
		fileList.add(outputPath);			// 4
		fileList.add(rPathForImag);			// 5
		fileList.add(fsetsSupPath);			// 6
		fileList.add(fsetsLiftPath);		// 7
		fileList.add(scottPlotPath);		// 8
		fileList.add(MatrixPath);	// 9
		fileList.add(graphPath);			// 10
		fileList.add(fsetsCsvPath);			// 11
		
		return fileList;
	}
	
	/**
	 * 调用R脚本进行关联分析
	 * @param rconn
	 * @throws RserveException
	 * @throws UnsupportedEncodingException
	 * @throws REXPMismatchException
	 */
	public boolean callRScriptForRelation( RConnection rconn, String relationFunctionName){
		
		boolean flag = false;
		 try {
			rconn.eval( relationFunctionName+"(input, rule1, rule2)");
			flag = true;
			return flag;
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			flag = false;
			return flag;
		}
	}
	public boolean callRScriptForRelation( RConnection rconn, String relationFunctionName, double sup){
		
		boolean flag = false;
		 try {
			rconn.eval( relationFunctionName+"(input, rule1, rule2, "+sup+")");
			flag = true;
			return flag;
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			flag = false;
			return flag;
		}
	}
	public static boolean callRScriptForRImag( RConnection rconn, String functionName) {
		
		boolean flag = false;
		
		 try {
				rconn.eval( functionName);
				flag = true;
				return flag;
			} catch (RserveException e) {
				// TODO Auto-generated catch block
				flag = false;
				return flag;
			}
	}
	
	
	/**
	 * 通过R语言进行关联分析
	 * @param courseName
	 * @param year
	 * @throws RserveException 
	 * @throws IOException 
	 */
	public void AssociationAnalasisByRForMap( String courseName, String year) throws RserveException, IOException {
		RelationshipService rs = new RelationshipService();
		
		// 确定文件路径 (R脚本/输入文件/输出文件) 
		List<String> file = createRelationFilePathForR(courseName, year);
		// 删除所有标志文件
		List<String> txtList = new ArrayList<String>();
		txtList = FileTools.getFileListWithSpecifiedExtensionInDirectory(RelationshipService.createRelationFilePathForR(courseName, year).get(4), ".txt", txtList);
		for( int i=0; i<txtList.size(); i++){
			new File(txtList.get(i)).delete();
		}
		// 创建连接
		RConnection rconn = new RConnection();
		
		
		// 路径赋值
		rconn.assign("rscript", file.get(0).toString());
		rconn.assign("input", file.get(1).toString());
		rconn.assign("rule1", file.get(2).toString());
		rconn.assign("rule2", file.get(3).toString());
	
		// 调用脚本
		rconn.eval("source(rscript)");
		
		// 标志坐标的标志
		int m=8, n=0;
		// 关联规则分析
		boolean flag = false;
		// 支持度递减调用R脚本
		flag = rs.callRScriptForRelation(rconn, "aprioriForJava8");
		if( true == flag){
			n = 8;
			new File(file.get(4).toString() + n + ".txt").createNewFile();
		}
		// 如果文件不存在创建一个文件
		if( false == flag && false == new File(file.get(2).toString()).exists())
		{
			// 创建标志文件
			n=-1;
			new File(file.get(4).toString() + n + ".txt").createNewFile();
			// 创建空文件
			new File(file.get(3).toString()).createNewFile();
			System.out.println(flag);
			rconn.close();
			return;
		}
		else if( false == flag 
				|| (8*1024 >= new File(file.get(2).toString()).length()
				&& new File(file.get(2).toString()).length() <= 500*1024))
		{
			for( int i=7; i>1; i--){
				m--;
				flag = rs.callRScriptForRelation(rconn, "aprioriForCustom", i*0.1);
				n = i;
				
				if( 8*1024 <= new File(file.get(2).toString()).length() 
						|| new File(file.get(2).toString()).length() >= 500*1024){
					break;
				}
			}	
		}
		if( false == flag 
			|| 8*1024 >= new File(file.get(2).toString()).length())
		{
			n = m;
			new File(file.get(4).toString() + n + ".txt").createNewFile();
			new File(file.get(3).toString()).delete();
			new File(file.get(2).toString()).renameTo(new File(file.get(3).toString()));
			System.out.println(flag);
			rconn.close();
			System.exit(0);
			return;
		}
		new File(file.get(4).toString() + n + ".txt").createNewFile();
		System.out.println(flag);
		rconn.close();
		System.exit(0);
	}
	
	/**
	 * 为知识地图更新csv（CDIO数据）
	 * @param courseName
	 * @throws RserveException
	 * @throws IOException
	 */
	public void updateCsvFileForCreateMap( String courseName) throws RserveException, IOException
	{
		RelationshipService rs = new RelationshipService();
		for( int i=0; i<GRADES.length; i++)
		{
			rs.AssociationAnalasisByRForMap(courseName, GRADES[i]);
		}
		rs.AssociationAnalasisByRForMap(courseName, "跨年级");
	}
	
	/**
	 * 更新定制地图的csv文件
	 * @param userCsv	用户csv的完整路径
	 * @return
	 * @throws IOException 
	 * @throws RserveException 
	 */
	public void updateRelationCsvFileForCustomMadeKnowledgeMap( File userCsv) throws IOException, RserveException{
		
		String userCsvPath = userCsv.toString();
		
		System.out.println(userCsvPath);
		RConnection rconn = new RConnection();
		System.out.println(userCsvPath);
		// 规则文件
		File rule1 = new File(userCsvPath.replace(".csv", "")+"_rule1.csv");
		File rule  = new File(userCsvPath.replace(".csv", "")+"_rule.csv");
		System.out.println(userCsvPath.replace(".csv", ""));
		// 路径赋值
		rconn.assign("rscript", RelationshipService.createRelationFilePathForR("..", "..").get(0).toString());
		System.out.println(RelationshipService.createRelationFilePathForR("..", "..").get(0).toString());
		rconn.assign("input", userCsvPath);
		System.out.println(userCsv.toString());
		rconn.assign("rule1", rule1.toString());
		rconn.assign("rule2", rule.toString());
		// 调用脚本
		rconn.eval("source(rscript)");
		// 关联规则分析标志
		boolean flag = false;
		// 开始进行关联分析
		flag = callRScriptForRelation(rconn, "aprioriForJava8");
		// 如果文件不存在创建一个文件
		if( false == flag && false == rule1.exists()){	
			// 创建空文件
			rule.createNewFile();
			System.out.println(flag);
			rconn.close();
			System.out.println("结束了");
			return;
		}
		if( true == flag 
				|| (8*1024 >= rule1.length()
				&& rule1.length() <= 500*1024)){
			for( int i=7; i>0; i--){
				
				flag = callRScriptForRelation(rconn, "aprioriForCustom", i*0.1);
				if( true == flag 
						&& (8*1024 <= rule1.length()
						&& rule1.length() >= 500*1024)){
					
					break;
				}
				if(rule1.length() >= 500*1024){
					break;
				}
			}
		}
		if( true == flag 
				|| (8*1024 >= rule1.length()
				&& rule1.length() <= 500*1024)){
			for( int i=9; i>0; ){
				
				flag = callRScriptForRelation(rconn, "aprioriForCustom", i*0.01);
				if( true == flag 
						&& (8*1024 <= rule1.length()
						&& rule1.length() >= 500*1024)){
					break;
				}
				if(rule1.length() >= 500*1024){
					break;
				}
				if( i >= 1){
					i = i-2;
				}
			}
		}
		
		if( false == flag 
				|| 8*1024 >= rule1.length())
		{
			rule.delete();
			rule1.renameTo(rule);
			System.out.println(flag);
			rconn.close();
			System.out.println("结束了");
			return;
		}else{
			rule1.delete();
			rconn.close();
			System.out.println("结束了");
		}
		System.exit(0);	
	}
	
	/**
	 * 更新R语言图片
	 * @param courseName
	 * @param year
	 * @throws RserveException 
	 * @throws IOException 
	 */
	public void updateRImag(String courseName, String year) throws RserveException, IOException {
		
		// rule1文件
		File rule1 = new File(RelationshipService.createRelationFilePathForR(courseName, year).get(2));
		// 标志文件路径名
		String bz = RelationshipService.createRelationFilePathForR(courseName, year).get(4);
		System.out.println(bz);
		// 无法进行关联规则
		if( !rule1.exists() && new File(bz + "-1.txt").exists()){
			
			System.out.println("--------------------无关联结束-------------------");
			return;
		}
		
		
		// 根据标识文件给支持度赋值
		List<String> txtList = new ArrayList<String>();
		txtList = FileTools.getFileListWithSpecifiedExtensionInDirectory(bz, ".txt", txtList);
		DecimalFormat df = new DecimalFormat("######0.00");
		double support = 0.0;
		String sup = "0.5";
		for( int i=0; i<txtList.size(); i++){
			sup= txtList.get(i);
			sup = sup.replace("\\", "/").replace(bz, "").replace(".txt", "");
			support = Double.valueOf(sup)*0.1;
			df.format(support);
			System.out.println(support);
		}
		
		// 创建连接
		RConnection rconn = new RConnection();
		// 路劲赋值
		rconn.assign("rscript", RelationshipService.createRelationFilePathForR(courseName, year).get(5));
		rconn.assign("input", RelationshipService.createRelationFilePathForR(courseName, year).get(1));
		rconn.assign("supImag", RelationshipService.createRelationFilePathForR(courseName, year).get(6));
		rconn.assign("liftImag", RelationshipService.createRelationFilePathForR(courseName, year).get(7));
		rconn.assign("scottImag", RelationshipService.createRelationFilePathForR(courseName, year).get(8));
		rconn.assign("matrixImag", RelationshipService.createRelationFilePathForR(courseName, year).get(9));
		rconn.assign("graphImag", RelationshipService.createRelationFilePathForR(courseName, year).get(10));
		rconn.assign("fsetsCsv", RelationshipService.createRelationFilePathForR(courseName, year).get(11));
		
		// 调用脚本
		rconn.eval("source(rscript)");
		// 创建事务
		rconn.eval("trans<-readTrans(input)");
		
		// 标识
		boolean flag = false;
		// 更新support相关图片
		flag = callRScriptForRImag( rconn, "ImagForSup( trans,"+df.format(0.35)+",fsetsCsv, supImag)");
		if( false == flag)
			flag = callRScriptForRImag( rconn, "ImagForSup( trans,"+df.format(support-0.25)+",fsetsCsv, supImag)");
		if(false == flag)
			flag = callRScriptForRImag( rconn, "ImagForSup( trans,"+df.format(support-0.15)+",fsetsCsv, supImag)");
			
		// 更新lift相关图片
		flag = false;
		flag = callRScriptForRImag( rconn, "ImagForLift( trans,"+df.format(0.35)+", liftImag)");
		if( false == flag)
			flag = callRScriptForRImag( rconn, "ImagForLift( trans,"+df.format(0.25)+", liftImag)");
		if(false == flag)
			flag = callRScriptForRImag( rconn, "ImagForLift( trans,"+df.format(0.15)+", liftImag)");
			
		// 更新关联规则图片
		flag = false;
		if( rule1.exists()){
			flag = callRScriptForRImag(rconn, "ImagForRelation2( trans, "+df.format(support)+", scottImag, matrixImag, graphImag)");
			if( false == flag){
				for( int i=0; i<5; i++){
					flag = callRScriptForRImag(rconn, "ImagForRelation2( trans, "+df.format(support - i*0.1)+", scottImag, matrixImag, graphImag)");
					if( true == flag)
						break;
					if( 0 == (support - i*0.1))
						break;
				}
			}
		}else{
			flag = callRScriptForRImag(rconn, "ImagForRelation1( trans, "+df.format(support)+", scottImag, matrixImag, graphImag)");
			if( false == flag){
				for( int i=0; i<5; i++){
					flag = callRScriptForRImag(rconn, "ImagForRelation2( trans, "+df.format(support - i*0.1)+", scottImag, matrixImag, graphImag)");
					if( true == flag)
						break;
					if( 0 == (support - i*0.1))
						break;
				}
			}
		}
		System.gc();
		// 关闭连接
		rconn.close();
		System.out.println(".......................结束了......................");
				
	}
	public void updateRImag( String courseName) throws UnsupportedEncodingException, IOException, RserveException{
		
		for( int i=0; i<GRADES.length; i++){
			// 删除原先的图片文件
			updateRImag(courseName, GRADES[i]);
			updateRImag(courseName, "跨年级");
		}
		System.out.println("-----------------------end---------------------");
		System.exit(0);
	}
}
