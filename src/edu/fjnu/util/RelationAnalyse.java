package edu.fjnu.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 输入知识点，进行关联分析
 * 
 * @author vengeance
 *
 */
public class RelationAnalyse {
	private static boolean endTag = false;// 判断结束的标志
	private static final double MIN_SUP = 0.001;// 设定最小支持度，表示两类知识点同时出现的概率
	private static final double MIN_CONF = 0.005;// 设定最小置信度，表示前一类知识点对后一类知识点的影响

	private static List<List<String>> record; // 数据记录表
	private static List<List<String>> confItemset = new ArrayList<List<String>>(); // 保存满足支持度的项集
	private static Map<Integer, Integer> dCountMap = new HashMap<Integer, Integer>(); // K-1级项集C元素出现次数统计
	private static Map<Integer, Integer> dkCountMap = new HashMap<Integer, Integer>(); // K级项集C元素出现次数统计
	private static List<List<String>> relationList = new ArrayList<List<String>>(); // 存储知识点关系列表

	/**
	 * 得到一级项集C，每份试卷以及各自对应的知识点
	 * 
	 * @param targetList
	 */
	private void getRecord(List<List<String>> targetList) {
		record = new ArrayList<List<String>>(targetList);
//		System.out.println("原始数据：" + record);
	}

	/**
	 * 输入知识点二维列表，处理知识点之间的关联程度
	 * 
	 * @param targetList
	 */
	public List<List<String>> calRelation(List<List<String>> targetList) {
		getRecord(targetList);
		List<List<String>> cItemset = findFirstCandidate();// 得到第一个备选项集C
		List<List<String>> lItemset = getSupportItemset(cItemset);// 得到第一个满足最小支持度的频繁项集L

		while (endTag != true) {// 如果当前K级频繁项集L只有一个项集时循环结束
			List<List<String>> cKItemset = getNextCandidate(lItemset);// 得到第K项项集C
			List<List<String>> lKItemset = getSupportItemset(cKItemset);// 得到第K项频繁项集L

			getConfidencedItemset(lKItemset, lItemset, dkCountMap, dCountMap);// 得到满足最小置信度的项集

			if (confItemset.size() != 0)
				getRelationList(confItemset);

			confItemset.clear();
			cItemset = cKItemset;
			lItemset = lKItemset;
			dCountMap.clear();
			dCountMap.putAll(dkCountMap);// 保存当前频繁项集次数，为下一级项集做基础
		}
		
		return getList();
	}

	/**
	 * 从指定的数据源获取第一级项集Candidate
	 * @return 一项集
	 */
	private List<List<String>> findFirstCandidate(){
		List<List<String>> firstList = new ArrayList<List<String>>();	//第一项集，项集存储所有出现过的知识点
		List<String> lineList = new ArrayList<String>();	//添加出现过的知识点
		int size = 0;
		
		for(int i=1; i<record.size(); i++){	//每一份试卷
			for(int j=1; j<record.get(i).size(); j++){	//每个知识点
				if(lineList.isEmpty()){
					lineList.add(record.get(i).get(j));
				}else{
					boolean haveThisItem = false;	//判断知识点是否重复出现
					size = lineList.size();
					
					for(int k=0; k<size; k++){
						if(lineList.get(k).equals(record.get(i).get(j))){	//一个知识点只添加一次
							haveThisItem = true;
							break;
						}
					}
					
					if(haveThisItem == false){	//如果知识点已经添加过就不再添加
						lineList.add(record.get(i).get(j));
					}
				}
			}
		}
		/*
		 * 把处理过的知识点赋给一级项集Candidate
		 */
		for(int i=0; i<lineList.size(); i++){
			List<String> tempList = new ArrayList<String>();
			tempList.add(lineList.get(i));
			firstList.add(tempList);
		}
		
		return firstList;
	}
	
	/**
	 * 
	 * @param cItemset 某一级的C项集
	 * @return C项集中满足最小置信度的L项集
	 */
	private List<List<String>> getSupportItemset(List<List<String>> cItemset){
		boolean isEnd = true;
		List<List<String>> supportedItem = new ArrayList<List<String>>();	//满足最小支持度的项集L
		int k = 0;
		
		for(int i=0; i<cItemset.size(); i++){
			int count = countFrequent(cItemset.get(i));	//获取某个知识点出现的次数
			
			if(count >=MIN_SUP*(record.size()-1)){	//判断该知识点是否满足最小支持度
				if(cItemset.get(0).size() == 1){
					dCountMap.put(k++, count);		//K-1级项集L++
				}else{
					dkCountMap.put(k++, count);		//K级项集L++
				}
				supportedItem.add(cItemset.get(i));
				isEnd = false;
			}
		}
		endTag = isEnd;
		
		return supportedItem;
		
	}
	
	/**
	 * 计算某知识点出现的次数
	 * @param list
	 * @return 出现次数
	 */
	private int countFrequent(List<String> list){
		int count = 0;	//某知识点A出现的次数
		
		for(int i=1; i<record.size(); i++){	//1-n题
			boolean notHaveThisList = false;	//判断该题是否含有某知识点A
			
			for(int k=0; k<list.size(); k++){	//该题总共的知识点个数
				boolean thisRecordHave = false;	//记录在该题某知识点A是否出现过
				
				for(int j=1; j<record.get(i).size(); j++){
					if(list.get(k).equals(record.get(i).get(j))){	//如果这个某知识点A与某题目中的知识点A匹配成功，thisRecordHave为真
						thisRecordHave = true;
					}
				}
				
				// 扫描一遍记录表的一行，发现list.get(i)不在记录表的第j行中，即list不可能在j行中
				if(!thisRecordHave){	
					notHaveThisList = true;	//该知识点A没有在该题出现
					break;
				}
			}
			
			if(notHaveThisList == false){	//该知识点A在该题出现
				count++;
			}
		}
		
		return count;
	}
	
	/**
	 * 求出满足最低置信度的项集
	 * @param lkItemset
	 * @param lItemset
	 * @param dkCountMap2
	 * @param dCountMap2
	 * @return	如果满足，在confItemset中添加满足条件项集
	 * 			否则返回null
	 */
	private List<List<String>> getConfidencedItemset(List<List<String>> lkItemset, List<List<String>> lItemset,
			Map<Integer, Integer> dkCountMap2, Map<Integer, Integer> dCountMap2){
		for(int i=0; i<lkItemset.size(); i++){
			getConfItem(lkItemset.get(i), lItemset, dkCountMap2.get(i), dCountMap2);
		}
		
		return null;
	}
	
	/**
	 * 检查项集list是否满足最小置信度；
	 * 如果满足，在全局变量confItemset中添加list
	 * 否则返回null
	 * @param list
	 * @param lItemset
	 * @param count
	 * @param dCountMap2
	 * @return 频繁项集L
	 */
	private List<String> getConfItem(List<String> list, List<List<String>> lItemset, 
			Integer count, Map<Integer, Integer> dCountMap2){
		for(int i=0; i<list.size(); i++){
			List<String> tempList = new ArrayList<String>();
			
			for(int j=0; j<list.size(); j++){
				if(i != j){
					tempList.add(list.get(j));
				}
			}
			int index = findConf(tempList, lItemset);	//查找testList中的内容在lItemset的位置
			Double conf = count*1.0 / dCountMap2.get(index);
			
			if(conf > MIN_CONF){	//如果满足最低置信度
				tempList.add(list.get(i));
				Double relativeSupport = count*1.0 / (record.size() - 1);
				tempList.add(relativeSupport.toString());
				tempList.add(conf.toString());
				confItemset.add(tempList);	//添加到满足置信度的项集中
			}
		}
		return null;
	}
	
	/**
	 * 查找tempList中的内容在lItemset中的位置
	 * @param tempList
	 * @param lItemset
	 * @return	存在该内容，返回下标
	 * 			否则，返回-1
	 */
	private int findConf(List<String> tempList, List<List<String>> lItemset){
		for(int i=0; i<lItemset.size(); i++){
			boolean notHaveTag = false;
			
			for(int j=0; j<tempList.size(); j++){
				if(haveThisItem(tempList.get(j), lItemset.get(i)) == false){
					notHaveTag = true;
					break;
				}
			}
			
			if(notHaveTag == false){
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * 判断list项集中是否含有某string
	 * @param string
	 * @param list
	 * @return 存在，返回true
	 * 		   否则，返回false
	 */
	private boolean haveThisItem(String string, List<String> list){
		for(int i=0; i<list.size(); i++){
			if(string.equals(list.get(i))){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 根据cItemset求出下一级的备选项集C，得到的C项集中的每个集合元素个数都比cItemset中的集合元素大1
	 * 也就是连接操作，从Lk-1频繁项集中得到Ck项集
	 * @param cItemset
	 * @return 项集C
	 */
	private List<List<String>> getNextCandidate(List<List<String>> cItemset){
		List<List<String>> nextItemset = new ArrayList<List<String>>();	//存储下一级的C项集
		
		for(int i=0; i<cItemset.size(); i++){
			List<String> tempList = new ArrayList<String>();
			
			for(int k=0; k<cItemset.get(i).size(); k++){
				tempList.add(cItemset.get(i).get(k));
				
				for(int h=i+1; h<cItemset.size(); h++){	//下一级项集C比当前项集L的元素多一位
					for(int j=0; j<cItemset.get(h).size(); j++){
						tempList.add(cItemset.get(h).get(j));
						
						if(isSubsetInC(tempList, cItemset)){	//判断该集合是否是当前项集的子集
							List<String> copyValueHelpList = new ArrayList<String>();
							
							for(int p=0; p<tempList.size(); p++){
								copyValueHelpList.add(tempList.get(p));
							}
							
							if(isHave(copyValueHelpList, nextItemset)){
								nextItemset.add(copyValueHelpList);
							}
						}
						tempList.remove(tempList.size() - 1);
					}
				}
			}
		}
		
		return nextItemset;
	}
	
	/**
	 * 检测tempList是否是cItemset的子集
	 * @param tempList
	 * @param cItemset
	 * @return true : 是它的子集
	 * 		   false : 不是它的子集
	 */
	private boolean isSubsetInC(List<String> tempList, List<List<String>> cItemset){
		boolean haveTag = false;
		
		for(int i=0; i<tempList.size(); i++){	//集合tempList的子集是否都存在于K-1级项集L中
			List<String> testList = new ArrayList<String>();
			
			for(int j=0; j<tempList.size(); j++){
				if(i != j){
					testList.add(tempList.get(j));
				}
			}
			
			for(int k=0; k<cItemset.size(); k++){
				if(testList.equals(cItemset.get(k))){	//子集存在于K-1项集L中
					haveTag = true;
					break;
				}
			}
			
			if(haveTag == false){	//存在一个子集不在L-1频繁项集中
				return false;
			}
		}
		
		return haveTag;
	}
	
	/**
	 * 检测nextItemset中是否包含集合copyValueHelpList
	 * @param copyValueHelpList
	 * @param nextItemset
	 * @return true : 包含
	 * 		   false : 不包含
	 */
	private boolean isHave(List<String> copyValueHelpList, List<List<String>> nextItemset){
		for(int i=0; i<nextItemset.size(); i++){
			if(copyValueHelpList.equals(nextItemset.get(i))){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 打印输出知识点之间的关系
	 * @param confItemset2
	 */
	public void printConfItemset(List<List<String>> confItemset2){
		System.out.println("关联分析结果：");
		
		for(int i=0; i<confItemset2.size(); i++){
			int j = 0;
			for(j=0; j<confItemset2.get(i).size() -3; j++){
				System.out.print(confItemset2.get(i).get(j) + " ");
			}
			
			System.out.print("-->");
			System.out.print(confItemset2.get(i).get(j++));
			System.out.print("相对支持度：" +  confItemset2.get(i).get(j++));
			System.out.print("置信度：" +  confItemset2.get(i).get(j++) + "\n");
		}
	}
	
	/**
	 * 将得到的关系列表保存到待获取的列表中
	 * @param confItemset2
	 */
	private void getRelationList(List<List<String>> confItemset2){
		relationList.clear();
		for(int i=0; i<confItemset2.size(); i++){
			List<String> temp = new ArrayList<String>(confItemset2.get(i));
			relationList.add(temp);
		}
			
	}
	
	/**
	 * @return 返回关系列表
	 */
	private List<List<String>> getList(){
		return relationList;
	}
}

