package edu.fjnu.service;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.print.Doc;
import javax.swing.JFileChooser;
import javax.swing.plaf.ScrollPaneUI;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.mysql.fabric.xmlrpc.base.Value;

import edu.fjnu.dao.StudentDao;
import edu.fjnu.dao.StudentPaperDao;
import edu.fjnu.domain.Grade;
import edu.fjnu.domain.GradeInfo;
import edu.fjnu.domain.Student;
import edu.fjnu.domain.TestInfo;
import edu.fjnu.domain.VQuestion;
import edu.fjnu.domain.VScope;
import edu.fjnu.domain.VTestMain;
import edu.fjnu.servlet.StudentPaperServlet;
import edu.fjnu.util.CSVUtil;
import edu.fjnu.util.FileTools;
import edu.fjnu.util.ImagUtil;
import edu.fjnu.util.PDFUtil;

public class PdfUpdateService {
	
	static GradeInfoService gradeInfoService = new GradeInfoService();
	/**
	 * 前测综合
	 * @param stuName
	 * @param scope
	 * @return
	 * @throws SQLException 
	 */
	public List<String[]> getAvgPreScore( String stuName, String scope) throws SQLException
	{
		List<String[]> avgScoreTable = new ArrayList< String[]>();
		double chineseAvgScore = 0;
		double mathAvgScore    = 0;
		double englishAvgScore = 0;
		double sumAvgScore     = 0;
		List<GradeInfo> grades = new ArrayList<>();
		
		// 平均分
		grades = gradeInfoService.getGradeBef(stuName, "语文", scope);
	
		for( int i=0; i<grades.size(); i++)
		{
			chineseAvgScore = chineseAvgScore + grades.get(i).getScore();
		}
		chineseAvgScore = chineseAvgScore / grades.size();
		grades.clear();
		grades = gradeInfoService.getGradeBef(stuName, "数学", scope);
		for( int i=0; i<grades.size(); i++)
		{
			mathAvgScore = mathAvgScore + grades.get(i).getScore();
		}
		mathAvgScore = mathAvgScore / grades.size();
		grades.clear();
		grades = gradeInfoService.getGradeBef(stuName, "英文", scope);
		for( int i=0; i<grades.size(); i++)
		{
			englishAvgScore = englishAvgScore + grades.get(i).getScore();
		}
		englishAvgScore = englishAvgScore / grades.size();
		grades.clear();
		
		// 总平均分
		DecimalFormat df = new DecimalFormat("#.00");
		sumAvgScore = (chineseAvgScore+mathAvgScore+englishAvgScore) / 3;
		sumAvgScore = Double.valueOf(df.format(sumAvgScore));
		System.out.println("------------------"+sumAvgScore);
		
		// 插入
		String[] str1 = new String[3];
		str1[0] = "语文";
		str1[1] = String.valueOf(chineseAvgScore);
		str1[2] = String.valueOf(Double.valueOf(df.format(chineseAvgScore/sumAvgScore)));
		avgScoreTable.add(str1);
		String[] str2 = new String[3];
		str2[0] = "数学";
		str2[1] = String.valueOf(mathAvgScore);
		str2[2] = String.valueOf(Double.valueOf(df.format(mathAvgScore/sumAvgScore)));
		avgScoreTable.add(str2);
		String[] str3 = new String[3];
		str3[0] = "英语";
		str3[1] = String.valueOf(englishAvgScore);
		str3[2] = String.valueOf(Double.valueOf(df.format(englishAvgScore/sumAvgScore)));
		avgScoreTable.add(str3);
		

//		// 打印
//		for( int i=0; i<avgScoreTable.size(); i++){
//			for( int j=0; j<avgScoreTable.get(i).length; j++){
//				System.out.println(avgScoreTable.get(i)[j]);
//			}
//			System.out.println("------------------------");
//		}
		return avgScoreTable;
		
		
	}
	
	/**
	 * 后测综合
	 * @param stuName
	 * @param scope
	 * @return
	 * @throws SQLException 
	 */
	public List<String[]> getAvgLastScore( String stuName, String scope) throws SQLException
	{
		List<String[]> avgScoreTable = new ArrayList< String[]>();
		double chineseAvgScore = 0;
		double mathAvgScore    = 0;
		double englishAvgScore = 0;
		double sumAvgScore     = 0;
		List<GradeInfo> grades = new ArrayList<>();
		
		// 平均分
		grades = gradeInfoService.getGradeAft(stuName, "语文", scope);
		for( int i=0; i<grades.size(); i++)
		{
			chineseAvgScore = chineseAvgScore + grades.get(i).getScore();
		}
		chineseAvgScore = chineseAvgScore / grades.size();
		grades.clear();
		grades = gradeInfoService.getGradeAft(stuName, "数学", scope);
		for( int i=0; i<grades.size(); i++)
		{
			mathAvgScore = mathAvgScore + grades.get(i).getScore();
		}
		mathAvgScore = mathAvgScore / grades.size();
		grades.clear();
		grades = gradeInfoService.getGradeAft(stuName, "英文", scope);
		for( int i=0; i<grades.size(); i++)
		{
			englishAvgScore = englishAvgScore + grades.get(i).getScore();
		}
		englishAvgScore = englishAvgScore / grades.size();
		grades.clear();
		
		// 总平均分
		DecimalFormat df = new DecimalFormat("#.00");
		sumAvgScore = (chineseAvgScore+mathAvgScore+englishAvgScore) / 3;
		sumAvgScore = Double.valueOf(df.format(sumAvgScore));
		System.out.println("------------------"+sumAvgScore);
		
		// 插入
		String[] str1 = new String[3];
		str1[0] = "语文";
		str1[1] = String.valueOf(chineseAvgScore);
		str1[2] = String.valueOf(Double.valueOf(df.format(chineseAvgScore/sumAvgScore)));
		avgScoreTable.add(str1);
		String[] str2 = new String[3];
		str2[0] = "数学";
		str2[1] = String.valueOf(mathAvgScore);
		str2[2] = String.valueOf(Double.valueOf(df.format(mathAvgScore/sumAvgScore)));
		avgScoreTable.add(str2);
		String[] str3 = new String[3];
		str3[0] = "英语";
		str3[1] = String.valueOf(englishAvgScore);
		str3[2] = String.valueOf(Double.valueOf(df.format(englishAvgScore/sumAvgScore)));
		avgScoreTable.add(str3);
		

//		// 打印
//		for( int i=0; i<avgScoreTable.size(); i++){
//			for( int j=0; j<avgScoreTable.get(i).length; j++){
//				System.out.println(avgScoreTable.get(i)[j]);
//			}
//			System.out.println("------------------------");
//		}
		return avgScoreTable;
	}
	
	/**
	 * 返回前测成绩
	 * @param stuName
	 * @param scope
	 * @param subject
	 * @return
	 * @throws SQLException 
	 */
	public static List<String[]> getPreScore( String stuName, 
												String scope,
												String subject)
						throws SQLException
	{
		List<String[]> preScoreTable = new ArrayList< String[]>();
		
		List<GradeInfo> grades = new ArrayList<>();
		
		// 平均分
		grades = gradeInfoService.getGradeBef(stuName, subject, scope);
		for( int i=0; i<grades.size(); i++)
		{
			String[] preScoreMes = new String[3];
			preScoreMes[0] = grades.get(i).getProgress();
			preScoreMes[1] = "前测";
			preScoreMes[2] = String.valueOf(grades.get(i).getScore());
			preScoreTable.add(preScoreMes);
		}
		
		// 打印
//		for( int i=0; i<preScoreTable.size(); i++){
//			for( int j=0; j<preScoreTable.get(i).length; j++){
//				System.out.println(preScoreTable.get(i)[j]);
//			}
////			System.out.println("--------------------------------");
//		}
		return preScoreTable;
	}
	
	
	/**
	 * 返回后测成绩
	 * @param stuName
	 * @param scope
	 * @param subject
	 * @return
	 * @throws SQLException
	 */
	public static List<String[]> getLastScore( String stuName, 
												String scope,
												String subject)
						throws SQLException
	{
		List<String[]> lastScoreTable = new ArrayList< String[]>();
		
		List<GradeInfo> grades = new ArrayList<>();
		
		// 平均分
		grades = gradeInfoService.getGradeAft(stuName, subject, scope);
		for( int i=0; i<grades.size(); i++)
		{
			String[] lastScoreMes = new String[3];
			lastScoreMes[0] = grades.get(i).getProgress();
			lastScoreMes[1] = "后测";
			lastScoreMes[2] = String.valueOf(grades.get(i).getScore());
			lastScoreTable.add(lastScoreMes);
		}
		return lastScoreTable;
	}
	/**
	 * 成绩 分析
	 * 
	 * @param stu
	 * @param scope
	 * @param pdf
	 * @throws Exception
	 * @throws DocumentException
	 */

	public static void GreadeAnalasisForAll(Student stu, String scope, Document pdf)
			throws DocumentException, Exception {

		GradesPdfService gps = new GradesPdfService();

		Paragraph para = new Paragraph("\n");
		PDFUtil tools = new PDFUtil();
		tools.InsertModuleTitle(pdf, "1    成绩分析报告");
		String[] subject = new String[] { "语文", "数学", "英语" };

		for (int i = 0; i < subject.length; i++) {
			tools.InsertTitle(pdf, "1." + (i + 1) + "	" + subject[i]);
			tools.InsertSubhead(pdf, "1." + (i + 1) + ".1	 前测后测成绩对比分析报告");
			pdf.add(para);

			Paragraph par = new Paragraph();
			par.add(PDFUtil.convertChunkByChinese("前 测 成 绩", 18, Font.BOLD, Color.BLACK));
			par.setAlignment(Element.ALIGN_CENTER);
			pdf.add(par);
			pdf.add(para);
			String[] TableHeader = new String[] { "考试名称", "考试类型", "分数" };

			List<String[]> tableHeader = new ArrayList<String[]>();
			tableHeader.add(TableHeader);
			tools.InsertTable(pdf, new float[] { 40, 40, 20 }, 100, tableHeader, 20, Font.BOLD, Color.BLACK);
			List<String[]> preScore = getPreScore(stu.getSname(), scope, subject[i]);
			String[] examName = new String[]{"T1", "T2", "T3", "T4","S1", "T5", "T6", "T7", "T8", "S2"};
			for( int n=0; n<examName.length; n++){
				preScore.get(n)[0] = examName[n];
			}
			tools.InsertTable(pdf, new float[] { 40, 40, 20 }, 100, preScore, 15, Font.NORMAL, Color.BLACK);
			pdf.add(para);

			par = new Paragraph();
			par.add(PDFUtil.convertChunkByChinese("后 测 成 绩", 18, Font.BOLD, Color.BLACK));
			par.setAlignment(Element.ALIGN_CENTER);
			pdf.add(par);
			pdf.add(para);
			TableHeader = new String[] { "考试名称", "考试类型", "分数" };
			tableHeader.clear();
			tableHeader.add(TableHeader);
			tools.InsertTable(pdf, new float[] { 40, 40, 20 }, 100, tableHeader, 20, Font.BOLD, Color.BLACK);
			List<String[]> lastScore = getLastScore(stu.getSname(), scope, subject[i]);
			for( int n=0; n<examName.length; n++){
				lastScore.get(n)[0] = examName[n];
			}
			tools.InsertTable(pdf, new float[] { 40, 40, 20 }, 100, lastScore, 15, Font.NORMAL, Color.BLACK);
			pdf.add(para);
//
//			tools.InsertSubhead(pdf, "1." + (i + 1) + ".2 	成长轨迹");
//			pdf.newPage();
		}
//
//		pdf.newPage();
	}


	
	public static String getPaperName( int key) {
		Map< Integer, String> map = new HashMap< Integer, String>();
		String[] testName = new String[]{ "T1", "T2", "T3" , "T4", "S1", "T5", "T6", "T7", "T8", "S2"};
		int[]	 testType = new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		for( int i=0; i<testType.length; i++){
			map.put( testType[i], testName[i]);
		}
		return map.get(key);
	}

	/**
	 * 试卷分析
	 * 
	 * @param stu
	 * @param scope
	 * @param pdf
	 * @throws DocumentException
	 * @throws Exception
	 */
	public static void TestAnalasisForAll(Student stu, String scope, Document pdf) throws DocumentException, Exception {

		StudentPaperService sps = new StudentPaperService();
		Paragraph para = new Paragraph("\n");
		PDFUtil tools = new PDFUtil();
		tools.InsertModuleTitle(pdf, "2 	试卷分析报告");
		String[] subject = new String[] { "语文", "数学", "英语" };
		for (int i = 0; i < subject.length; i++) {
			tools.InsertTitle(pdf, "2." + (i + 1) + "	" + subject[i]);
			// 得到试卷
			List<VTestMain> vTestMainList = sps.getTestMainsList(subject[i], Integer.parseInt(stu.getMemberID()), scope);
			for( int j=0; j< vTestMainList.size(); j++){
				tools.InsertSubhead(pdf, getPaperName(vTestMainList.get(j).getTestType()));
				
				
				
				// 知识点错误率统计
				Paragraph par = new Paragraph();
				par.add(PDFUtil.convertChunkByChinese( getPaperName(vTestMainList.get(j).getTestType())+"知识点错误率统计表", 12, Font.BOLD, Color.BLACK));
				par.setAlignment(Element.ALIGN_CENTER);
				pdf.add(par);
				pdf.add(para);
				String[] tableHeader = new String[] { "知识点", "错误率" };
				List<String[]> tableHeader1 = new ArrayList<String[]>();
				tableHeader1.add(tableHeader);
				tools.InsertTable(pdf, new float[] { 70, 20 }, 100, tableHeader1, 13, Font.BOLD, Color.BLACK);
				// 通过选择的试卷ID得到试卷涵盖的知识点范围
				String paperScope = sps.getScope(vTestMainList.get(j).getPk_test_main_ID());
				List<VQuestion> vQuestionKeywordList = sps.getKeywordAndWrongByScope(paperScope);
				List< String[]> keywordList = new ArrayList< String[]>();
				for( int k=0; k<vQuestionKeywordList.size(); k++){
					String[] temp = new String[2];
					temp[0] = vQuestionKeywordList.get(k).getKeyword();
					temp[1] = String.valueOf(vQuestionKeywordList.get(k).getWrong());
					keywordList.add(temp);
				}
				tools.InsertTable(pdf, new float[] { 70, 20}, 100, keywordList, 13, Font.NORMAL, Color.BLACK);
				pdf.add(para);
				
				// 详细试题错误率统计
				par = new Paragraph();
				par.add(PDFUtil.convertChunkByChinese( getPaperName(vTestMainList.get(j).getTestType())+"试题错误率统计表", 12, Font.BOLD, Color.BLACK));
				par.setAlignment(Element.ALIGN_CENTER);
				pdf.add(par);
				pdf.add(para);
				tableHeader = new String[] { "试题", "正确率", "学生状态" };
				tableHeader1 = new ArrayList<String[]>();
				tableHeader1.add(tableHeader);
				tools.InsertTable(pdf, new float[] { 70, 10, 10 }, 100, tableHeader1, 13, Font.BOLD, Color.BLACK);
				List<VQuestion> vQuestionList = sps.getVQuestionList(vTestMainList.get(j).getPk_test_main_ID());
				List< String[]> questionList = new ArrayList< String[]>();
//				System.out.println(vQuestionList.toString());
				
				for( int k=0; k<vQuestionList.size(); k++){
					String[] temp = new String[3];
					temp[0] = vQuestionList.get(k).getSubject() + "\n"
							+ "(A)、" + vQuestionList.get(k).getChoiceA() + "\n"
							+ "(B)、" + vQuestionList.get(k).getChoiceB() + "\n"
							+ "(C)、" + vQuestionList.get(k).getChoiceC() + "\n"
							+ "(D)、" +vQuestionList.get(k).getChoiceD() + "\n";
					temp[0] = temp[0].replace("<br>", "\n");
					temp[1] = String.valueOf(vQuestionList.get(k).getRate())+"%";
					temp[2] = vQuestionList.get(k).getStustatus();
					questionList.add(temp);
				}
				tools.InsertTable(pdf, new float[] { 70, 10, 10}, 100, questionList, 13, Color.BLACK);
				
				pdf.add(para);
				
			}
		}
		pdf.newPage();
	}
	

	/**
	 * 关联规则分析模块
	 * @param stu
	 * @param scope
	 * @param pdf
	 * @throws DocumentException
	 * @throws Exception
	 */
	public static void RelationAnalasisForAll(Student stu, String scope, Document pdf ) throws DocumentException, Exception 
	{
		
		// 修改年级乙方万一
		String[] nj = new String[]{"一年级","二年级","三年级","四年级","五年级","六年级"};
		if( true == scope.contains(nj[0])){
			scope = "一年级";
		}else if(true == scope.contains(nj[1])){
			scope = "二年级";
		}else if( true == scope.contains(nj[2])){
			scope = "三年级";
		}else if( true == scope.contains(nj[3])){
			scope = "四年级";
		}else if( true == scope.contains(nj[4])){
			scope = "五年级";
		}else{
			scope = "六年级";
		}
		
		Paragraph para = new Paragraph("\n");
		PDFUtil tools = new PDFUtil();
		tools.InsertModuleTitle(pdf, "3 	知识点关联分析报告");
		
		tools.InsertTitle(pdf, "3.1		关联规则关键词解释");
		tools.InsertSubhead(pdf, "3.1.1		支持度(Support):");
		pdf.add(para);
		tools.InsertParagh(pdf, "某个知识点、某几个知识点构成的集合或某条规则出现的概率，Support的值越大，代表出现次数越多。");
		pdf.add(para);
		tools.InsertSubhead(pdf, "3.1.2		置信度(Confidence):");
		pdf.add(para);
		tools.InsertParagh(pdf, "知识点A,B同时出现和知识点A出现的次数的比值。");
		pdf.add(para);
		tools.InsertSubhead(pdf, "3.1.3		提升度(Lift):");
		pdf.add(para);
		tools.InsertParagh(pdf, " 提升度比率越高，规则越强，可信度越高。");
		pdf.add(para);
		
		String[] subject = new String[]{"语文","数学","英语"};
		
		
		for( int i=0; i<subject.length; i++)
		{
			String subjectInEnglish = FileTools.translateSomeChineseIntoEnglish(subject[i]);
			String scopelnEnglish = FileTools.translateSomeChineseIntoEnglish(scope);
			String dirPath = FileTools.getApplicationRootPath()+ "relation/" + subjectInEnglish + "/"+scopelnEnglish+"/";
			
			if( new File(dirPath+"-1.txt").exists()){
				continue;
			}
			
			// fsets文件存在
			if( new File(dirPath+"fsets.csv").exists()){
				tools.InsertTitle(pdf, "3."+(i+2) +"	"+ subject[i]);
				tools.InsertSubhead(pdf, "3."+(i+2) + ".1	知识点频数集分析");
				List< String[]> fsetData = CSVUtil.importCsv(dirPath+"fsets.csv");
				List< String[]> insertData = new ArrayList< String[]>();
				
				
				pdf.add(para);

				if( 20 < fsetData.size()){
					fsetData = fsetData.subList(0, 20);
				}
				tools.InsertTable(pdf, new float[]{70,30}, 100, fsetData, 15, Font.NORMAL, Color.BLACK);
				fsetData.clear();
			}
			int page1 = pdf.getPageNumber();
			if(new File(dirPath+"fsetsSup.png").exists()){
				tools.InsertImg(pdf, dirPath+"fsetsSup.png");
			}
			pdf.newPage();
			if(new File(dirPath+"fsetsLift.png").exists()){
				tools.InsertImg(pdf, dirPath+"fsetsLift.png");
			}
			pdf.newPage();
			
			
			tools.InsertSubhead(pdf, "3."+(i+3) + ".2	知识点关联规则分析");
			if(new File(dirPath+"rule.csv").exists()){
				List< String[]> rulesData = CSVUtil.importCsv(dirPath+"rule.csv");
				pdf.add(para);
				if( 20 < rulesData.size()){
					rulesData = rulesData.subList(0, 20);
				}
				tools.InsertTable(pdf, new float[]{55,15,15,15}, 100, rulesData, 15, Font.NORMAL, Color.BLACK);
			}
//			if(new File(dirPath+"ScottPlot.png").exists()){
//				tools.InsertImg(pdf, dirPath+"ScottPlot.png");
//			}
//			pdf.newPage();
			if(new File(dirPath+"Graph.png").exists()){
				ImagUtil.resizeImage(dirPath, "Graph.png","Graph(1).png",400,550);
				tools.InsertImg(pdf, dirPath+"Graph(1).png");
			}
			pdf.newPage();
			if(new File(dirPath+"Matrix.png").exists()){
				ImagUtil.resizeImage(dirPath, "Matrix.png","Matrix(1).png",500,750);
				tools.InsertImg(pdf, dirPath+"Matrix(1).png");
			}
			pdf.newPage();
		}
		
		pdf.newPage();
	}
	/**

	 * 报告完整版
	 * @param stu
	 * @param scope
	 * @throws Exception
	 */
	public static void PdfForAll( Student stu, String scope) throws Exception{
//		JFileChooser fileChooser = new JFileChooser("D:\\");
//		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		int returnVal = fileChooser.showOpenDialog(fileChooser);
//		if(returnVal == JFileChooser.APPROVE_OPTION){
//	    
//		}
		// fileChooser.getSelectedFile().getAbsolutePath()//选择的路径
		PDFUtil pdftool = new PDFUtil();
		Document pdf = new Document();
		pdf = pdftool.createDocument(//fileChooser.getSelectedFile().getAbsolutePath()//选择的路径
										"D:/"+stu.getName()
										+scope
										+"分析报告.pdf");
		pdf.open();
		// 封面制作
		pdftool.makePDFCover(pdf, 
							scope+"\n综合分析报 告", 
							"\n学号："+ stu.getStudentID() + "\n姓名：" +stu.getSname());
		// 第一个模块
		PdfUpdateService.GreadeAnalasisForAll(stu, scope, pdf);
		
		// 第二个模块
		PdfUpdateService.TestAnalasisForAll(stu, scope, pdf);
		
		// 第三个模块
		PdfUpdateService.RelationAnalasisForAll(stu, scope, pdf);
		pdf.close();
		
	}
	
//	public static void main(String[] args) throws Exception {
//		PdfUpdateService pus = new PdfUpdateService();
//		Student stu = new Student();
//		stu.setMemberID("2560");
//		StudentDao dao = new StudentDao();
//		stu = dao.studentInfo(stu);
//		pus.PdfForAll(stu, "二年级（下）");
//	}

}
