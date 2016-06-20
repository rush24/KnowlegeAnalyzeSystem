package edu.fjnu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import edu.fjnu.dao.StudentPaperDao;
import edu.fjnu.domain.VQuestion;
import edu.fjnu.domain.VTestMain;

/**
 * 试卷分析pdf
 * @author Administrator
 *
 */
public class TestPdfService 
{
	public List<String[]> getWrongList( int stuID, String scope, String subject)
	{
		List<String[]> wrongList = new ArrayList< String[]>();
		
		StudentPaperDao spd = new StudentPaperDao();
		StudentPaperService sps = new StudentPaperService();
		List<VTestMain> testList = spd.findTestMainId(subject, stuID, scope+"（上）"); 
		testList.addAll(spd.findTestMainId(subject, stuID, scope+"（下）"));
		
		// 获取范围
		String[] testScope = new String[testList.size()];
		TreeSet<String> tr = new TreeSet<String>();
		for( int i=0; i<testScope.length; i++)
		{
			testScope[i] = sps.getScope(testList.get(i).getPk_test_main_ID());
			tr.add(testScope[i]);
		}
		
		testScope = new String[tr.size()];
		for( int i=0; i<testScope.length; i++)
		{
			testScope[i] = tr.pollFirst();
		}
		
		// 得到知识点错误率
		List<VQuestion> vq = new ArrayList<VQuestion>();
		for( int i=0; i<testScope.length; i++)
		{
			vq.addAll(sps.getKeywordAndWrongByScope(testScope[i]));
		}
		
		String[] temp = new String[2];
		for( int i=1; i<vq.size(); i++)
		{
			temp[0] = vq.get(i).getKeyword();
			temp[1] = String.valueOf(vq.get(i).getRate());
			wrongList.add(temp);
		}
		
		return wrongList;
	}
	
	public static void main( String[] args)
	{
		TestPdfService tsp = new TestPdfService();
		List<String[]> str =tsp.getWrongList(2560, "三年级", "数学");
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
