package edu.fjnu.service;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.fjnu.domain.Grade;
import edu.fjnu.domain.Student;
import edu.fjnu.util.CSVUtil;

public class GradesPdfService 
{
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
		GradeService sgs = new GradeService();
		List<Grade> grades = new ArrayList<>();
		
		// 平均分
		grades = sgs.getPreGrade(stuName, scope+"（上）", "语文");
		grades.addAll(sgs.getPreGrade(stuName, scope+"（下）", "语文"));
		for( int i=0; i<grades.size(); i++)
		{
			chineseAvgScore = chineseAvgScore + grades.get(i).getScore();
		}
		chineseAvgScore = chineseAvgScore / grades.size();
		
		grades.clear();
		grades = sgs.getPreGrade(stuName, scope+"（上）", "数学");
		grades.addAll(sgs.getPreGrade(stuName, scope+"（下）", "数学"));
		for( int i=0; i<grades.size(); i++)
		{
			mathAvgScore = mathAvgScore + grades.get(i).getScore();
		}
		mathAvgScore = mathAvgScore / grades.size();
		
		grades.clear();
		grades = sgs.getPreGrade(stuName, scope+"（上）", "英语");
		grades.addAll(sgs.getPreGrade(stuName, scope+"（下）", "英语"));
		for( int i=0; i<grades.size(); i++)
		{
			englishAvgScore = englishAvgScore + grades.get(i).getScore();
		}
		englishAvgScore = englishAvgScore / grades.size();
		
		// 总平均分
		sumAvgScore = (chineseAvgScore+mathAvgScore+englishAvgScore) / 3;
		
		// 插入
		String[] str = new String[3];
		str[0] = "语文";
		str[1] = String.valueOf(chineseAvgScore);
		str[2] = String.valueOf(chineseAvgScore/sumAvgScore);
		avgScoreTable.add(str);
		str[0] = "数学";
		str[1] = String.valueOf(mathAvgScore);
		str[2] = String.valueOf(mathAvgScore/sumAvgScore);
		avgScoreTable.add(str);
		str[0] = "英语";
		str[1] = String.valueOf(englishAvgScore);
		str[2] = String.valueOf(englishAvgScore/sumAvgScore);
		avgScoreTable.add(str);
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
		GradeService sgs = new GradeService();
		List<Grade> grades = new ArrayList<>();
		
		// 平均分
		grades = sgs.getLatGrade(stuName, scope+"（上）", "语文");
		grades.addAll(sgs.getLatGrade(stuName, scope+"（下）", "语文"));
		for( int i=0; i<grades.size(); i++)
		{
			chineseAvgScore = chineseAvgScore + grades.get(i).getScore();
		}
		chineseAvgScore = chineseAvgScore / grades.size();
		
		grades.clear();
		grades = sgs.getLatGrade(stuName, scope+"（上）", "数学");
		grades.addAll(sgs.getLatGrade(stuName, scope+"（下）", "数学"));
		for( int i=0; i<grades.size(); i++)
		{
			mathAvgScore = mathAvgScore + grades.get(i).getScore();
		}
		mathAvgScore = mathAvgScore / grades.size();
		
		grades.clear();
		grades = sgs.getLatGrade(stuName, scope+"（上）", "英语");
		grades.addAll(sgs.getLatGrade(stuName, scope+"（下）", "英语"));
		for( int i=0; i<grades.size(); i++)
		{
			englishAvgScore = englishAvgScore + grades.get(i).getScore();
		}
		englishAvgScore = englishAvgScore / grades.size();
		
		// 总平均分
		sumAvgScore = (chineseAvgScore+mathAvgScore+englishAvgScore) / 3;
		
		// 插入
		String[] str = new String[3];
		str[0] = "语文";
		str[1] = String.valueOf(chineseAvgScore);
		str[2] = String.valueOf(chineseAvgScore/sumAvgScore);
		avgScoreTable.add(str);
		str[0] = "数学";
		str[1] = String.valueOf(mathAvgScore);
		str[2] = String.valueOf(mathAvgScore/sumAvgScore);
		avgScoreTable.add(str);
		str[0] = "英语";
		str[1] = String.valueOf(englishAvgScore);
		str[2] = String.valueOf(englishAvgScore/sumAvgScore);
		avgScoreTable.add(str);
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
	public List<String[]> getPreScore( String stuName, 
												String scope,
												String subject)
						throws SQLException
	{
		List<String[]> preScoreTable = new ArrayList< String[]>();
		String[] preScoreMes = new String[3];
		GradeService sgs = new GradeService();
		List<Grade> grades = sgs.getPreGrade(stuName, scope+"（上）", subject);
		grades.addAll(sgs.getPreGrade(stuName, scope+"（下）", subject));
		for( int i=0; i<grades.size(); i++)
		{
			preScoreMes[0] = grades.get(i).getProgress();
			preScoreMes[1] = grades.get(i).getExamtype();
			preScoreMes[2] = String.valueOf(grades.get(i).getScore());
			preScoreTable.add(preScoreMes);
		}
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
	public List<String[]> getLastScore( String stuName, 
												String scope,
												String subject)
						throws SQLException
	{
		List<String[]> lastScoreTable = new ArrayList< String[]>();
		String[] lastScoreMes = new String[3];
		GradeService sgs = new GradeService();
		List<Grade> grades = sgs.getLatGrade(stuName, scope+"（上）", subject);
		grades.addAll(sgs.getLatGrade(stuName, scope+"（下）", subject));
		for( int i=0; i<grades.size(); i++)
		{
			lastScoreMes[0] = grades.get(i).getProgress();
			lastScoreMes[1] = grades.get(i).getExamtype();
			lastScoreMes[2] = String.valueOf(grades.get(i).getScore());
			lastScoreTable.add(lastScoreMes);
		}
		return lastScoreTable;
	}
	
	
	public static void main( String[] args) throws SQLException
	{
//		GradeService gs = new GradeService();
//		System.out.println(gs.getPreGrade("陈梦熙", "二年级（下）", "数学").toString());
		GradesPdfService gps = new GradesPdfService();
		GradeService sgs = new GradeService();
		List<Grade> grades = sgs.getPreGrade("陈梦熙", "二年级"+"（下）", "数学");
		System.out.println();
		System.out.println(grades);
		System.out.println(grades.get(0).getScore());
		
		System.out.println();
		System.out.println(gps.getPreScore("陈梦熙", "二年级", "数学"));
		List<String[]> str = gps.getPreScore("陈梦熙", "二年级", "数学");
		
		System.out.println();
		System.out.println();
		for( int i=0; i<str.size(); i++)
		{
			for( int j=0; j<str.get(i).length; j++)
			{
				
				System.out.println(str.get(i)[j]);
			}
		}
	}
}
