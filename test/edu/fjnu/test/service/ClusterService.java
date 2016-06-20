package edu.fjnu.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.fjnu.domain.Cluster;
import edu.fjnu.domain.VScope;

public class ClusterService {
	
	private edu.fjnu.dao.ClusterDao clusterDao = new edu.fjnu.dao.ClusterDao();
	
	@Test
	public void testGetIDsBylevel(){
		edu.fjnu.service.ClusterService cs = new edu.fjnu.service.ClusterService();
		ArrayList<Integer> idList = cs.getIDsBylevel("三年级（上）", "数学","middle");
//		System.out.println(idList);
	}
	
	@Test
	public void testetKeywordsByGoodLevel(){
		edu.fjnu.service.ClusterService cs = new edu.fjnu.service.ClusterService();
//		ArrayList<Integer> idList = cs.getIDsBylevel("三年级（上）", "数学","middle_bad");
//		System.out.println(idList);
//		List<Cluster> clusterList = cs.getKeywordsByLevel("四年级（上）", "数学", "middle_bad");
//		for(Cluster cluster : clusterList){
//			System.out.println(cluster);
//		}
		List<Cluster> allList = cs.getAllKeywordsByLevel("数学", "good");
		System.out.println(allList);
	}
}
