package edu.fjnu.test.util;

import java.util.List;
import org.junit.Test;

import edu.fjnu.domain.KeywordNode;
import edu.fjnu.util.RelationResult;

public class TestRelationResult {

	private RelationResult rr = new RelationResult();
		
	@Test
	public void testGetKeywordLineList(){
		List<KeywordNode[]> list = rr.getKeywordLineList("语文");
		for(KeywordNode[] k : list){
			System.out.println("知识点A：" + k[0].getName() + " 经度：" + k[0].getLongtitude() + " 纬度：" + k[0].getLatitude());
			System.out.println("知识点B：" + k[1].getName() + " 经度：" + k[1].getLongtitude() + " 纬度：" + k[1].getLatitude());
			System.out.println();
		}
	}
	
//	@Test
//	public void testClassifyKeyword(){
//		Map<Integer,List<String>>  map = rr.classifyKeyword(rr.getAllKeywordList("语文"));
//		for(int i = map.size() - 1;i >=0;i--){
//			List<String> list = map.get(i);
//			for(String s : list){
//				System.out.print(s + ",");
//			}
//			System.out.println();
//		}
//	}
//	
//	@Test
//	public void testAllKeywordList(){
//		List<String> list = rr.getAllKeywordList("语文");
//		for(String s : list){
//			System.out.println(s);
//		}
//	}
//	
//	@Test
//	public void testGetKeywordNodeList(){
//		List<KeywordNode> list = rr.getKeywordNodeList("语文");
//		for(KeywordNode k : list){
//			System.out.println("知识点：" + k.getName() + " 经度：" + k.getLongtitude() + " 纬度：" + k.getLatitude());
//		}
//	}
}
