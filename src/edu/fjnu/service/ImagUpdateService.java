package edu.fjnu.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import edu.fjnu.util.CSVUtil;
import edu.fjnu.util.FileTools;

/**
 * 更新图片
 * @author li
 *
 */
public class ImagUpdateService 
{
	
	/**
	 * 处理为关联的知识列表的数据
	 * @param courseName
	 * @param year
	 * @return
	 */
	public static List<String> dealRelationList( String courseName, String year)
	{
		RelationService relation = new RelationService();
		List<String> dealList = new ArrayList<String>();
		List<List<String>> initial = new ArrayList<List<String>>();
		initial.addAll(relation.getRelationListForRtool(courseName, year));
		String temp;
		for( int i=0; i<initial.size(); i++)
		{
			temp = initial.get(i).toString();
			temp = temp.replace("[", "");
			temp = temp.replace("]", "");
			temp = temp.replace(" ", "");
			dealList.add(temp);
		}
		return dealList;
	}
	
	/**
	 * 生成每张试卷知识点的csv
	 * @param courseName
	 * @param year
	 */
	public static void createRelationListCSV( String courseName, String year)
	{
		
		String path = "D:/R/temp/use/";		// 在这里改文件路径
		System.out.println("创建文件结果:"+FileTools.createDir(path));
		List<String> relationlist = dealRelationList(courseName, year);
		File file = new File( path+"Relation.csv");
	    System.out.println("生成csv文件结果:"+CSVUtil.exportCsv(file, relationlist));
	}
	
	/**
	 * 更新关联分析的R图
	 * @param course
	 * @param year
	 * @throws RserveException 
	 */
	public void UpdateRelationImg( String course, String year) 
			throws RserveException
	{
		// 生成分析所需的csv文件(自己改目录)
		ImagUpdateService.createRelationListCSV(course, year);
		
		// 启动Rserve(自己改目录)
//		Runtime rtime = Runtime.getRuntime();
//		Process process = null;
//		try {
//			process = rtime.exec("D:/R/R-3.2.3/bin/i386/Rserve.exe");
//		} catch (IOException e) {
//			 TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		// 连接Rserve
		RConnection rconn = new RConnection();
		
		// 调用R脚本
		rconn.eval("source('"+ System.getProperty("user.dir").replace("\\", "/") +"/WebContent/R/RScript/aprioriForJava.R')");
		rconn.eval("aprioriForJava()");	
	}
	
	public void updateRelationToDest( String course, String year)
	{
		FileTools.copyDirectory("D:/R/temp/Relation", 
								System.getProperty("user.dir").replace("\\", "/")
								+"/WebContent/Relation/"
								+course+"/"
								+year.replace("%", "")+"/",
				true);
	}
	public static void main( String[] args) throws RserveException
	{
//		System.out.println(FileTools.createDir("D:/R/temp/use"));
//		System.out.println(FileTools.createDir("D:/R/temp/Relation"));
//		ImagUpdateService imag = new ImagUpdateService();
//		imag.UpdateRelationImg("英语", "二年级%");
//		imag.UpdateRelationImg("数学", "一年级%");
//		imag.updateRelationToDest("数学", "一年级%");
//		imag.UpdateRelationImg("数学", "二年级%");
//		imag.updateRelationToDest("数学", "二年级%");
//		imag.UpdateRelationImg("数学", "三年级%");
//		imag.updateRelationToDest("数学", "三年级%");
//		imag.UpdateRelationImg("数学", "四年级%");
//		imag.updateRelationToDest("数学", "四年级%");
//		imag.UpdateRelationImg("数学", "五年级%");
//		imag.updateRelationToDest("数学", "五年级%");
//		imag.UpdateRelationImg("数学", "六年级%");
//		imag.updateRelationToDest("数学", "六年级%");
//		imag.UpdateRelationImg("语文", "一年级%");
//		imag.updateRelationToDest("语文", "一年级%");
//		imag.UpdateRelationImg("语文", "二年级%");
//		imag.updateRelationToDest("语文", "二年级%");
//		imag.UpdateRelationImg("语文", "三年级%");
//		imag.updateRelationToDest("语文", "三年级%");
//		imag.UpdateRelationImg("语文", "四年级%");
//		imag.updateRelationToDest("语文", "四年级%");
//		imag.UpdateRelationImg("语文", "五年级%");
//		imag.updateRelationToDest("语文", "五年级%");
//		imag.UpdateRelationImg("语文", "六年级%");
//		imag.updateRelationToDest("语文", "六年级%");
//		imag.UpdateRelationImg("英文", "一年级%");
//		imag.updateRelationToDest("英文", "一年级%");
//		imag.UpdateRelationImg("英文", "一年级%");
//		imag.updateRelationToDest("英文", "一年级%");
//		imag.UpdateRelationImg("英文", "二年级%");
//		imag.updateRelationToDest("英文", "二年级%");
//		imag.UpdateRelationImg("英文", "三年级%");
//		imag.updateRelationToDest("英文", "三年级%");
//		imag.UpdateRelationImg("英文", "四年级%");
//		imag.updateRelationToDest("英文", "四年级%");
//		imag.UpdateRelationImg("英文", "五年级%");
//		imag.updateRelationToDest("英文", "五年级%");
//		imag.UpdateRelationImg("英文", "六年级%");
//		imag.updateRelationToDest("英文", "六年级%");
	}
}
