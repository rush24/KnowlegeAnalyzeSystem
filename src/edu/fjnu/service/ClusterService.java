package edu.fjnu.service;

import java.util.ArrayList;
import java.util.List;

import edu.fjnu.dao.ClusterDao;
import edu.fjnu.domain.Cluster;
import edu.fjnu.domain.Testmain;
import edu.fjnu.domain.VScope;

/**
 * 聚类分析
 * @author zhangzhiyong
 *
 */
public class ClusterService {
	
	private ClusterDao clusterDao = new ClusterDao();
	
	/**
	 * 通过指定科目指定学生水平得到该水平常模（聚类实体List）
	 * @param subject
	 * @param studentLevel
	 * @return
	 */
	public List<Cluster> getAllKeywordsByLevel(String subject,String studentLevel){
		List<Cluster> allList = new ArrayList<Cluster>();
		//得到每个学期的聚类实体
		List<Cluster> oneUpList = getKeywordsByLevel("一年级（上）", subject, studentLevel);
		List<Cluster> oneDownList = getKeywordsByLevel("一年级（下）", subject, studentLevel);
		List<Cluster> twoUpList = getKeywordsByLevel("二年级（上）", subject, studentLevel);
		List<Cluster> twoDwonList = getKeywordsByLevel("二年级（下）", subject, studentLevel);
		List<Cluster> threeUpList = getKeywordsByLevel("三年级（上）", subject, studentLevel);
		List<Cluster> threeDownList = getKeywordsByLevel("三年级（下）", subject, studentLevel);
		List<Cluster> fourUpList = getKeywordsByLevel("四年级（上）", subject, studentLevel);
		List<Cluster> fourDownList = getKeywordsByLevel("四年级（下）", subject, studentLevel);
		List<Cluster> fiveUpList = getKeywordsByLevel("五年级（上）", subject, studentLevel);
		List<Cluster> fiveDownList = getKeywordsByLevel("五年级（下）", subject, studentLevel);
		List<Cluster> sixUpList = getKeywordsByLevel("六年级（上）", subject, studentLevel);
		List<Cluster> sixDownList = getKeywordsByLevel("六年级（下）", subject, studentLevel);
		
		//遍历每个学期聚类实体，为其所属学期赋值（用于图表归类），并以此添加到一个最终要返回的List
		if(oneUpList != null){
			for(Cluster cluster : oneUpList){
				cluster.setGradeNum(1);
				allList.add(cluster);
			}
		}
		if(oneDownList != null){
			for(Cluster cluster : oneDownList){
				cluster.setGradeNum(2);
				allList.add(cluster);
			}
		}
		if(twoUpList != null){
			for(Cluster cluster : twoUpList){
				cluster.setGradeNum(3);
				allList.add(cluster);
			}
		}
		if(twoDwonList != null){
			for(Cluster cluster : twoDwonList){
				cluster.setGradeNum(4);
				allList.add(cluster);
			}
		}
		if(threeUpList != null){
			for(Cluster cluster : threeUpList){
				cluster.setGradeNum(5);
				allList.add(cluster);
			}
		}
		if(threeDownList != null){
			for(Cluster cluster : threeDownList){
				cluster.setGradeNum(6);
				allList.add(cluster);
			}
		}
		if(fourUpList != null){
			for(Cluster cluster : fourUpList){
				cluster.setGradeNum(7);
				allList.add(cluster);
			}
		}
		if(fourDownList != null){
			for(Cluster cluster : fourDownList){
				cluster.setGradeNum(8);
				allList.add(cluster);
			}
		}
		if(fiveUpList != null){
			for(Cluster cluster : fiveUpList){
				cluster.setGradeNum(9);
				allList.add(cluster);
			}
		}
		if(fiveDownList != null){
			for(Cluster cluster : fiveDownList){
				cluster.setGradeNum(10);
				allList.add(cluster);
			}
		}
		if(sixUpList != null){
			for(Cluster cluster : sixUpList){
				cluster.setGradeNum(11);
				allList.add(cluster);
			}
		}
		if(sixDownList != null){
			for(Cluster cluster : sixDownList){
				cluster.setGradeNum(12);
				allList.add(cluster);
			}
		}
		
		return allList;
		
	}
	
	/**
	 * 根据指定年级指定科目和指定学生水平得到聚类实体List
	 * @param grade
	 * @param subject
	 * @param studentLevel
	 * @return
	 */
	public List<Cluster> getKeywordsByLevel(String grade,String subject,String studentLevel){
		ArrayList<Integer> idList = getIDsBylevel(grade, subject,studentLevel);
		//如果该年级查不到学生idList，返空，不再执行
		if(idList.size() == 0){
			return null;
		}
		List<VScope> vScopeList = null;
		List<Cluster> clusterList = new ArrayList<Cluster>();
		int y = 0;
		switch (studentLevel) {
		case "good":
			vScopeList = clusterDao.getKeywordsByGoodLevel(grade, subject, idList);
			for(int i = 0; i < vScopeList.size();i++){
				VScope vScope = vScopeList.get(i);
				Cluster cluster = new Cluster();
				cluster.setName(vScope.getName());
				cluster.setArea(vScope.getKeywordCount());
				cluster.setyAxis(y);
				y = y + vScope.getKeywordCount()*3;
				clusterList.add(cluster);
			}
			return clusterList;
		case "bad":
			vScopeList = clusterDao.getKeywordsByBadLevel(grade, subject, idList);
			for(int i = 0; i < vScopeList.size();i++){
				VScope vScope = vScopeList.get(i);
				Cluster cluster = new Cluster();
				cluster.setName(vScope.getName());
				cluster.setArea(vScope.getKeywordCount());
				cluster.setyAxis(y);
				y = y + vScope.getKeywordCount()*3;
				clusterList.add(cluster);
			}
			return clusterList;
		case "middle_good":
			vScopeList = clusterDao.getKeywordsByGoodLevel(grade, subject, idList);
			for(int i = 0; i < vScopeList.size();i++){
				VScope vScope = vScopeList.get(i);
				Cluster cluster = new Cluster();
				cluster.setName(vScope.getName());
				cluster.setArea(vScope.getKeywordCount());
				cluster.setyAxis(y);
				y = y + vScope.getKeywordCount()*3;
				clusterList.add(cluster);
			}
			return clusterList;
		case "middle_bad":
			vScopeList = clusterDao.getKeywordsByBadLevel(grade, subject, idList);
			for(int i = 0; i < vScopeList.size();i++){
				VScope vScope = vScopeList.get(i);
				Cluster cluster = new Cluster();
				cluster.setName(vScope.getName());
				cluster.setArea(vScope.getKeywordCount());
				cluster.setyAxis(y);
				y = y + vScope.getKeywordCount()*3;
				clusterList.add(cluster);
			}
			return clusterList;
		default:
			return null;
		}	
	}
	
	/**
	 * 根据学生水平,年级，科目得到该层次学生ID列表
	 * @param grade
	 * @param subject
	 * @param sl
	 * @return
	 */
	public ArrayList<Integer> getIDsBylevel(String grade,String subject,String studentLevel){
		
		int count = clusterDao.count(grade, subject);
		int num = Math.round(count/4);
		int from;
		List<Testmain> testmainList = null;
		ArrayList<Integer> idList = new ArrayList<Integer>();
		switch (studentLevel) {
		case "good":
			from = 1;
			testmainList = clusterDao.rangeOfIDs(grade, subject, from, num);
			for(int i = 0;i < testmainList.size();i++){
				idList.add(testmainList.get(i).getStudent_memberId());
			}
			return idList;		
		case "bad":
			from = count - num;
			testmainList = clusterDao.rangeOfIDs(grade, subject, from, num);
			for(int i = 0;i < testmainList.size();i++){
				idList.add(testmainList.get(i).getStudent_memberId());
			}
			return idList;		
		case "middle_good":
			from = num + 1;
			num = num * 2;
			testmainList = clusterDao.rangeOfIDs(grade, subject, from, num);
			for(int i = 0;i < testmainList.size();i++){
				idList.add(testmainList.get(i).getStudent_memberId());
			}
			return idList;
		case "middle_bad":
			from = num + 1;
			num = num * 2;
			testmainList = clusterDao.rangeOfIDs(grade, subject, from, num);
			for(int i = 0;i < testmainList.size();i++){
				idList.add(testmainList.get(i).getStudent_memberId());
			}
			return idList;
		default:
			return null;
		}	
	}
	
	/**
	 * 根据科目，年级，学生ID得到该学生水平
	 * @param studentID
	 * @param subject
	 * @param grade
	 * @return
	 */
	public String getLevelOfStudent(int studentID,String subject,String grade){
		List<Testmain> testmainList = null;
		int count = clusterDao.count(grade, subject);
		int num = Math.round(count/4);//将总数划分为四分之一作间隔
		testmainList = clusterDao.rangeOfIDs(grade, subject, 1, count);
		int i;
		for(i = 0;i < testmainList.size();i++){
			if(testmainList.get(i).getStudent_memberId() == studentID){
				break;
			}
		}
		//没找到这个学生，可能是每参加过考试，就暂定他为差生
		if(i == testmainList.size()){
			return "bad";
		}else{
			if(i < num){
				return "good";
			}else if(i > count){
				return "bad";
			}else{
				return "middle";
			}
		}
	}
	
	/**
	 * 通过年级，科目，学生ID得到所需水平知识点List（实际返回ClusterList,可取其keyword属性）
	 * @param grade
	 * @param subject
	 * @param studentID
	 * @return
	 */
	public List<Cluster> getKeywordListOfStudent(String grade,String subject,int studentID){
		String level = getLevelOfStudent(studentID, subject, grade);
		switch (level) {
		case "good":
			List<Cluster> goodClusterList = getKeywordsByLevel(grade, subject, "good");
			return goodClusterList;
		case "bad":
			List<Cluster> badClusterList = getKeywordsByLevel(grade, subject, "bad");
			return badClusterList;
		case "middle":
			List<Cluster> middle_goodClusterList = getKeywordsByLevel(grade, subject, "middle_good");
			List<Cluster> middle_badClusterList = getKeywordsByLevel(grade, subject, "middle_bad");
			for(int i = 0;i < middle_badClusterList.size();i++){
				middle_goodClusterList.add(middle_badClusterList.get(i));
			}
			return middle_goodClusterList;
		default:
			return null;
		}
	}
	
	/**
	 * 添加cluster
	 * @param cluster
	 */
	public void add(Cluster cluster,String level,String subject) {
		clusterDao.add(cluster,level,subject);
	}
	
	/**
	 * 根据level和subject查询聚类List
	 * @return
	 */
	public List<Cluster> findAll(String level,String subject) {
		return clusterDao.findAll(level, subject);
	}
}
