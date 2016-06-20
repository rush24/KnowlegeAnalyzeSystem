package edu.fjnu.service;

import java.util.ArrayList;
import java.util.List;

import edu.fjnu.dao.ClusterDao;
import edu.fjnu.domain.Cluster;

public class ClusterPdfService 
{
	public List<String[]> getClusterMatrix( int stuID, String scope, String subject)
	{
		ClusterService cs = new ClusterService();
		ClusterDao     cd = new ClusterDao();
		List<String[]> clusterList = new ArrayList<String[]>();
		String[] point = new String[4];
		String level1 = cs.getLevelOfStudent(stuID, subject, scope+"(上)");
		String level2 = cs.getLevelOfStudent(stuID, subject, scope+"（下）");
		int[] gradeNum = new int[2];
		
		if("一年级" == scope){
			gradeNum[0] = 1;
			gradeNum[1] = 2;
		}else if( "二年级" == scope){
			gradeNum[0] = 3;
			gradeNum[1] = 4;
		}else if( "三年级" == scope){
			gradeNum[0] = 5;
			gradeNum[1] = 6;
		}else if( "四年级" == scope){
			gradeNum[0] = 7;
			gradeNum[1] = 8;
		}else if("五年级" == scope){
			gradeNum[0] = 9;
			gradeNum [1] = 10;
		}else if("六年级" == scope){
			gradeNum[0] = 11;
			gradeNum[1] = 12;
		}
		
		List<Cluster> cluster1 = cd.findByScope(level1, subject, gradeNum[0]);
		List<Cluster> cluster2 = cd.findByScope(level2, subject, gradeNum[1]);
		
		if( cluster1.size() > cluster2.size())
		{
			int i=0;
			for( ; i<cluster2.size(); i++)
			{
				point[0] = cluster1.get(i).getName();
				point[1] = String.valueOf(cluster1.get(i).getArea());
				point[2] = cluster2.get(i).getName();
				point[3] = String.valueOf(cluster2.get(i).getArea());
				clusterList.add(point);
			}
			for( ; i<cluster1.size(); i++)
			{
				point[0] = cluster1.get(i).getName();
				point[1] = String.valueOf(cluster1.get(i).getArea());
				point[2] = "   ";
				point[3] = "   ";
				clusterList.add(point);
			}
		}else{
			int i=0;
			for( ; i<cluster1.size(); i++)
			{
				point[0] = cluster1.get(i).getName();
				point[1] = String.valueOf(cluster1.get(i).getArea());
				point[2] = cluster2.get(i).getName();
				point[3] = String.valueOf(cluster2.get(i).getArea());
				clusterList.add(point);
			}
			for( ; i<cluster2.size(); i++)
			{
				point[0] = "   ";
				point[1] = "   ";
				point[2] = cluster2.get(i).getName();
				point[3] = String.valueOf(cluster2.get(i).getArea());
				clusterList.add(point);
			}
			
		}
		
		
		
		return clusterList;
	}
	
	public static void main( String[] args)
	{
		ClusterPdfService cps = new ClusterPdfService();
		List<String[]> str = cps.getClusterMatrix(2563, "三年级", "语文");
	
		for( int i=0; i<str.size(); i++)
		{
			for( int j=0; j<str.get(i).length; j++)
			{
				System.out.println(str.get(i)[j]);
			}
		}
	}
}
