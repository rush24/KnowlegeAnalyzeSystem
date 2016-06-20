package edu.fjnu.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.fjnu.dao.CustomerMadeDao;
import edu.fjnu.domain.KeywordNode;
import edu.fjnu.util.CSVUtil;
import edu.fjnu.util.CollectionUtil;
import edu.fjnu.util.DoubleFormat;
import edu.fjnu.util.FileTools;
import edu.fjnu.util.RelationResult;

/**
 * 处理用户定制知识地图相关数据
 * @author zhangzhiyong
 *
 */
public class CustomerMadeMapService {

	private static final String[] GRADES = {"一年级","二年级","三年级","四年级","五年级","六年级","初一","初二","初三"};
	private static final double START_POSITION = 50;	//地图第一个点开始的坐标大小
	private static final int MAX_WIDTH = 10;	//地图上同纬度两节点最长的距离
	private static final int MAX_COUNT_PER_ROW = 8;//地图上同纬度最多有8个节点
	private static final int MAX_COUNT_OF_CUSTOMER_CACHE_DATA = 20;//最大缓存用户数据为20个用户，超过则清空缓存
	private CustomerMadeDao cmDao = new CustomerMadeDao();
	private static Map<String,List<KeywordNode>[]> _keywordNodeList = new HashMap<String,List<KeywordNode>[]>();//知识节点列表
	private static Map<String,List<KeywordNode[]>[]> _keywordNodeLineList = new HashMap<String,List<KeywordNode[]>[]>();//知识节点连线列表
	private static Map<String,List<List<String>>[]> _KeywordNodePositions = new HashMap<String,List<List<String>>[]>();//存放节点位置排列信息
	private static Map<String,Map<String,List<KeywordNode>>[]> _keywordNodeFindSourceRelationMap = new HashMap();//<键：userID，值：<键：知识节点，值：与之关联的所有节点列表>的数组>
	private static Map<String,Map<String,List<KeywordNode>>[]> _keywordNodePredictRelationMap = new HashMap();//<键：userID，值：<键：知识节点，值：与之关联的所有节点列表>的数组>
	private static Map<String,List<String[]>[]> _keywordLineList = new HashMap<String,List<String[]>[]>();
	
	public List<KeywordNode> get_keywordNodeList(String userID,String course){
		if(_keywordNodeList.size() > MAX_COUNT_OF_CUSTOMER_CACHE_DATA)
			_keywordNodeList.clear();
		if(_keywordNodeList.get(userID) == null){
			List<KeywordNode>[] knArr = new ArrayList[3];
			knArr[RelationResult.getIndexByCourseName(course)] = getCMKeywordNodeList(userID, course);
			_keywordNodeList.put(userID, knArr);
			return knArr[RelationResult.getIndexByCourseName(course)];
		}else if(_keywordNodeList.get(userID)[RelationResult.getIndexByCourseName(course)] == null){
			List<KeywordNode>[] knArr = _keywordNodeList.get(userID);
			knArr[RelationResult.getIndexByCourseName(course)] = getCMKeywordNodeList(userID, course);
			_keywordNodeList.put(userID, knArr);
			return knArr[RelationResult.getIndexByCourseName(course)];
		}else{
			return _keywordNodeList.get(userID)[RelationResult.getIndexByCourseName(course)];
		}
	}
	
	public List<KeywordNode[]> get_keywordNodeLineList(String userID,String course){
		if(_keywordNodeLineList.size() > MAX_COUNT_OF_CUSTOMER_CACHE_DATA)
			_keywordNodeLineList.clear();
		if(_keywordNodeLineList.get(userID) == null){
			List<KeywordNode[]>[] knArr = new ArrayList[3];
			knArr[RelationResult.getIndexByCourseName(course)] = getCMKeywordNodeLineList(userID, course);
			_keywordNodeLineList.put(userID, knArr);
			return knArr[RelationResult.getIndexByCourseName(course)];
		}else if(_keywordNodeLineList.get(userID)[RelationResult.getIndexByCourseName(course)] == null){
			List<KeywordNode[]>[] knArr = _keywordNodeLineList.get(userID);
			knArr[RelationResult.getIndexByCourseName(course)] = getCMKeywordNodeLineList(userID, course);
			_keywordNodeLineList.put(userID, knArr);
			return knArr[RelationResult.getIndexByCourseName(course)];
		}else{
			return _keywordNodeLineList.get(userID)[RelationResult.getIndexByCourseName(course)];
		}
	}
	
	/**
	 * 此方法对外开放，用于初始化知识节点朔源前的准备工作
	 * @param course
	 */
	public void initFindSourceAndPredictPath(String userId,String course){
		if(_keywordNodeFindSourceRelationMap.get(userId) == null){
			createAssocaiteNodesMap(userId,course);
		}else if(_keywordNodeFindSourceRelationMap.get(userId)[RelationResult.getIndexByCourseName(course)] == null){
			createAssocaiteNodesMap(userId,course);
		}
	}
	
	private List<KeywordNode> getFindSourceRelationKeywordNodeList(String userId,String course,KeywordNode keywordNode){
		if(_keywordNodeFindSourceRelationMap.get(userId) == null){
			createAssocaiteNodesMap(userId,course);
		}else if(_keywordNodeFindSourceRelationMap.get(userId)[RelationResult.getIndexByCourseName(course)] == null){
			createAssocaiteNodesMap(userId,course);
		}
//		Map<String,List<KeywordNode>> map = _keywordNodeFindSourceRelationMap.get(userId)[RelationResult.getIndexByCourseName(course)];
//		List<KeywordNode> k = map.get(keywordNode.getName());
//		System.out.println(k.get(0).getName());
		return _keywordNodeFindSourceRelationMap.get(userId)[RelationResult.getIndexByCourseName(course)].get(keywordNode.getName());
	}
	
	private List<KeywordNode> getPredictRelationKeywordNodeList(String userId,String course,KeywordNode keywordNode){
		if(_keywordNodePredictRelationMap.get(userId) == null){
			createAssocaiteNodesMap(userId,course);
		}else if(_keywordNodePredictRelationMap.get(userId)[RelationResult.getIndexByCourseName(course)] == null){
			createAssocaiteNodesMap(userId,course);
		}
		return _keywordNodePredictRelationMap.get(userId)[RelationResult.getIndexByCourseName(course)].get(keywordNode.getName());
	}
	
	private List<String[]> get_keywordLineList(String userID,String course){
		if(_keywordLineList.size() > MAX_COUNT_OF_CUSTOMER_CACHE_DATA)
			_keywordLineList.clear();
		if(_keywordLineList.get(userID) == null){
			List<String[]>[] klListArr = new ArrayList[3];
			klListArr[RelationResult.getIndexByCourseName(course)] = getCMKeywordLineList(userID, course);
			_keywordLineList.put(userID, klListArr);
			return klListArr[RelationResult.getIndexByCourseName(course)];
		}else if(_keywordLineList.get(userID)[RelationResult.getIndexByCourseName(course)] == null){
			List<String[]>[] klListArr = _keywordLineList.get(userID);
			klListArr[RelationResult.getIndexByCourseName(course)] = getCMKeywordLineList(userID, course);
			_keywordLineList.put(userID, klListArr);
			return klListArr[RelationResult.getIndexByCourseName(course)];
		}else{
			return _keywordLineList.get(userID)[RelationResult.getIndexByCourseName(course)];
		}
	}
	
	/**
	 * 得到知识节点List
	 * @param userID
	 * @param course
	 * @return
	 */
	private List<KeywordNode> getCMKeywordNodeList(String userID, String course){
		List<KeywordNode> keywordNodeList = new ArrayList<KeywordNode>();//存放知识节点对象
		
		List<String> keywordList = getCMKeywordList(userID, course);//得到所有知识点
		keywordList = sortListDESC(keywordList,userID);//排序
		Map<Integer,List<String>> map = classifyKeyword(keywordList,userID);//归类
		
		double currentLongtitude = START_POSITION;//当前经度
		double currentLatitude = START_POSITION;//当前纬度
		//遍历处理过的知识点列表，为每个节点构造经纬度
		List<List<String>> allPositonList = new ArrayList<List<String>>();//用来存放所有的知识点位置列表
		for(int i = GRADES.length - 1;i >= 0;i--){
			if(map.get(i) == null || map.get(i).size() == 0){
				continue;
			}
			
			List<List<String>> positonList = calculateRowAndCol(map.get(i), userID,course);//计算每个知识点所处的行和列
			
			allPositonList.addAll(positonList);
			for(List<String> list : positonList){//根据知识点所处的行和列，为其分配合适的经纬度
				for(String keywordName : list){
					currentLongtitude += DoubleFormat.getRandom(0.4, 1);
					currentLongtitude = DoubleFormat.getDoubleFormat(currentLongtitude,2);//保留两位小数
					
					KeywordNode keywordNode = new KeywordNode(keywordName, currentLongtitude, currentLatitude,i+1);
					keywordNodeList.add(keywordNode);
				}
				if(currentLongtitude > START_POSITION + MAX_WIDTH)
					currentLongtitude -= MAX_WIDTH;
				currentLatitude -= DoubleFormat.getRandom(0.2, 1);
				currentLatitude = DoubleFormat.getDoubleFormat(currentLatitude,2);//保留两位小数
			}
		}
		
		//将位置存储起来
		if(_KeywordNodePositions.size() > MAX_COUNT_OF_CUSTOMER_CACHE_DATA)
			_KeywordNodePositions.clear();
		List<List<String>>[] listArr = null;
		if(_KeywordNodePositions.get(userID) == null){
			listArr = new ArrayList[3];
			_KeywordNodePositions.put(userID, listArr);
		}
		else{
			listArr = _KeywordNodePositions.get(userID);
		}
		listArr[RelationResult.getIndexByCourseName(course)] = allPositonList;
		
		return keywordNodeList;
	}
	
	/**
	 * 得到知识节点连线list
	 * @param userID
	 * @param course
	 * @return
	 */
	private List<KeywordNode[]> getCMKeywordNodeLineList(String userID,String course){
		// 存储结果集
		List<KeywordNode[]> keywordNodeLineList = new ArrayList<KeywordNode[]>();
				
		//构建知识节点连线列表
		List<KeywordNode> keywordNodeList = get_keywordNodeList(userID, course);
		List<String[]> keywordLineList = get_keywordLineList(userID, course);
		for (String[] keywordArr : keywordLineList) {
			//遍历所有知识节点对象列表，发现节点名称一样，就存储该节点对象进入节点连线列表
			KeywordNode[] knArr = new KeywordNode[2];
			KeywordNode knA = getCMKeywordNodeByKeywordName(keywordArr[0], userID, course);
			KeywordNode knB = getCMKeywordNodeByKeywordName(keywordArr[1], userID ,course);
			//如果有一个节点找不到，就不加进连线List,并把与之关联的另一个节点也删掉，否则会出现单独一个节点没有连线的情况
			if(knA == null || knB == null){
				if(knA != null){
					for(KeywordNode keywordNode : keywordNodeList){
						if(keywordNode.getName().equals(knA.getName())){
							if(OnlyOneToAssociate(keywordNode.getName(),userID,course))
								keywordNodeList.remove(keywordNode);	
							break;
						}
					}
				}
				if(knB != null){
					for(KeywordNode keywordNode : keywordNodeList){
						if(keywordNode.getName().equals(knB.getName())){
							if(OnlyOneToAssociate(keywordNode.getName(),userID,course))
								keywordNodeList.remove(keywordNode);	
							break;
						}
					}
				}
				
				continue;
			}
			
			knArr[0] = knA;
			knArr[1] = knB;
			keywordNodeLineList.add(knArr);
		}

		return keywordNodeLineList;
	}
	
	/**
	 *  得到知识点列表
	 * @param userID
	 * @param course
	 * @return
	 */
	private List<String> getCMKeywordList(String userID, String course) {
		// 调用得到规则列表的方法，获得剔除知识对的关联规则列表
		List<String[]> rulesData = getCMRulesData(userID, course);
		// 如果为空就返回空
		if (rulesData == null || rulesData.size() == 0) {
			return null;
		}

		List<String> keywordList = new ArrayList<String>();// 存储一个个知识点
		int beginA, beginB, endA, endB; // 控制截取知识点的下标
		// 保存不重复的知识点列表
		for (int i = 0; i < rulesData.size(); i++) {
			// 得到前一个知识点A
			beginA = rulesData.get(i)[0].indexOf("{");
			endA = rulesData.get(i)[0].indexOf("}");
			// 得到后一个知识点B
			beginB = rulesData.get(i)[0].indexOf(" {");
			endB = rulesData.get(i)[0].indexOf("}\"");
			
			keywordList.add(rulesData.get(i)[0].substring(beginA + 1, endA));
			keywordList.add(rulesData.get(i)[0].substring(beginB + 2, endB));
		}

		return CollectionUtil.removeDuplicate(keywordList);//去重
	}
	
	/**
	 * 得到知识点连线列表
	 * @param userID
	 * @param course
	 * @return
	 */
	private List<String[]> getCMKeywordLineList(String userID,String course){
		// 存储结果集
		List<String[]> keywordLineList = new ArrayList<String[]>();

		// 得到所有的关联规则数据List
		List<String[]> relationDataList = getCMRulesData(userID, course);
				
		// 遍历整个知识点规则列表
		int beginA, beginB, endA, endB; // 控制截取知识点的下标
		String keyA,keyB;
		for (int i = 0; i < relationDataList.size(); i++) {
			beginA = relationDataList.get(i)[0].indexOf("{");
			endA = relationDataList.get(i)[0].indexOf("}");
			beginB = relationDataList.get(i)[0].indexOf(" {");
			endB = relationDataList.get(i)[0].indexOf("}\"");
			keyA = relationDataList.get(i)[0].substring(beginA + 1, endA);
			keyB = relationDataList.get(i)[0].substring(beginB + 2, endB);
			
			String[] keyArr = new String[2];
			keyArr[0] = keyA;
			keyArr[1] = keyB;
			
			boolean isExit = false;
			//判断这对知识点是否已经存在，存在就不再加入
			for(String[] klArr : keywordLineList){
				if((klArr[0].equals(keyA) && klArr[1].equals(keyB))
						|| (klArr[0].equals(keyB) && klArr[1].equals(keyA))){
					isExit = true;
					break;
				}
			}
			if(!isExit)
				keywordLineList.add(keyArr);
		}
		return keywordLineList;
	}
	
	/**
	 * 通过知识点名称得到知识节点对象
	 * @param keywordName
	 * @param userID
	 * @param course
	 * @return
	 */
	public KeywordNode getCMKeywordNodeByKeywordName(String keywordName,String userID,String course){
		List<KeywordNode> keywordNodeList = get_keywordNodeList(userID, course);
		for(KeywordNode keywordNode : keywordNodeList){
			if(keywordNode.getName().equals(keywordName)){
				return keywordNode;
			}
		}
		return null;
	}
	
	/**
	 * 通过模糊知识点名称匹配得到知识节点对象
	 * @param keywordName
	 * @param userID
	 * @param course
	 * @return
	 */
	public KeywordNode getCMKeywordNodeByBlurKeywordName(String keywordName,String userID,String course){
		List<KeywordNode> keywordNodeList = get_keywordNodeList(userID, course);
		for(KeywordNode keywordNode : keywordNodeList){
			if(keywordNode.getName().contains(keywordName)){
				return keywordNode;
			}
		}
		return null;
	}
	
	/**
	 * 判断是否只有一个节点与之关联
	 * @param keywordName
	 * @param userID
	 * @param course
	 * @return
	 */
	private boolean OnlyOneToAssociate(String keywordName,String userID,String course){
		int AssociationCount = 0;
		List<String[]> keywordLineList = get_keywordLineList(userID, course);
		for(String[] keyArr : keywordLineList){
			if(keyArr[0].equals(keywordName) || keyArr[1].equals(keywordName))
				AssociationCount++;
			if(AssociationCount == 2)
				return false;
		}
		return true;
	}
	
	/**
	 * 计算节点行列位置
	 * @param list
	 * @param userID
	 * @param course
	 * @return
	 */
	private 	List<List<String>> calculateRowAndCol(List<String> list,String userID, String course){
		List<List<String>> resultList = new ArrayList<List<String>>();//存储知识点的行列关系
		for(String keyword : list){
			if(resultList == null || resultList.size() == 0){//如果没有列，创建第一列
				List<String> firstList = new ArrayList<String>();
				firstList.add(keyword);
				resultList.add(firstList);
				continue;
			}
			
			Boolean isLeave = false;//是否跳出双层for标志，即直接进行下一个知识点的排列
			
			for(int i = 0;i < resultList.size();i++){//遍历每一行
				int j;
				for(j = 0;j < resultList.get(i).size();j++){//判断知识点是否和当前行所有知识点都没有关联
					if(isAssociated(resultList.get(i).get(j), keyword,userID,course)){//两个知识点有关联，不放入当前行
						break;
					}else if(resultList.get(i).size() < MAX_COUNT_PER_ROW){//前行知识点没超出限制个数才能放入
						continue;
					}else{
						break;
					}
				}
				
				if(j == resultList.get(i).size()){//和当前行所有知识点都没有关联并且不超过最大个数，可以加入当前行
					resultList.get(i).add(keyword);
					isLeave = true;
				}
				
				if(isLeave)
					break;//离开，判断下一个知识点
				
				//如果下一行还没有，就创建，能执行到这行表示该知识点和之前所有行都有关联，只能存入新一行
				if(i == (resultList.size() - 1)){
					List<String> sl = new ArrayList<String>();
					sl.add(keyword);
					resultList.add(sl);
					break;
				}
			}
		}
			
		return resultList;
	}
	
	/**
	 * 判断两个知识点是否有关联
	 * @param keywordA
	 * @param keywordB
	 * @param userID
	 * @param course
	 * @return
	 */ 
	private boolean isAssociated(String keywordA,String keywordB,String userID,String course){		
		List<String[]> keywordLineList = get_keywordLineList(userID, course);
		for(String[] keywordArr : keywordLineList){
			if((keywordArr[0].equals(keywordA) && keywordArr[1].equals(keywordB))
				|| (keywordArr[0].equals(keywordB) && keywordArr[1].equals(keywordA)))		
				return true;
		}
		return false;
	}
	
	/**
	 * 对知识点按照所属年级归类
	 * @param keywordList
	 * @return
	 */
	private Map<Integer,List<String>> classifyKeyword(List<String> keywordList,String userId){
		Map<Integer,List<String>> map = new HashMap<Integer,List<String>>();
		for(String keywordName : keywordList){
			String grade = cmDao.getGradeByKeywordName(keywordName,userId);//得到这个知识点所在年级
			for(int i = 0; i < GRADES.length; i++){
				if(grade == null || "null".equals(grade)){//数据库查询不到所在年级
					break;
				}
				if(grade.contains(GRADES[i])){
					List<String> list;
					if(map.get(i) == null || map.get(i).size() == 0){
						list = new ArrayList<String>();
					}else{
						list = map.get(i);
					}
					list.add(keywordName);
					map.put(i,list);
					break;
				}
			}
		}
		return map;
	}
	
	/**
	 * 按知识点id对知识点列表进行排序
	 * @param keywordList
	 * @return
	 */
	private List<String> sortListDESC(List<String> keywordList,String userId){
		Map<String, String> hashMap = new HashMap<String,String>();
		for(String keywordName : keywordList){
			String id = cmDao.getKeywordIdByName(keywordName,userId);
			if(id == null){//如果没有找到就舍弃
				continue;
			}
			hashMap.put(id,keywordName);
		}
		
		keywordList.clear();
		Object[] key = hashMap.keySet().toArray();    
		Arrays.sort(key);    
		for(int i = key.length - 1; i >= 0; i--)  
		{    
			keywordList.add(hashMap.get(key[i]));
		}  
		return keywordList;
	}
	
	/**
	 * 获得关联规则数据
	 * @param userID 当前用户ID
	 * @param course 学科
	 * @return
	 */
	private List<String[]> getCMRulesData(String userID, String course) {
		List<String[]> rulesData = new ArrayList<String[]>();
		// 读取CSV文件，获得规则
		try {		
			//拼接路径
			course = FileTools.translateSomeChineseIntoEnglish(course);
		    String path = FileTools.getApplicationRootPath() + "R/csv/customerMade/" + userID + "_" + course + "_origin_rule.csv";
			System.out.println("path: " + path);
		    rulesData = CSVUtil.importCsv(path);
		} catch (FileNotFoundException e) {
			System.err.println("路径文件不存在！");
			return null;
		}

		if (rulesData.size() == 0 || rulesData == null) {
			return rulesData;
		}
		rulesData.remove(0);// 删除第一行

		// 对知识对的剔除，只需要两两关系的知识对
		for (int i = 0; i < rulesData.size(); i++) {
			if (rulesData.get(i)[0].contains(",")) {
				rulesData.remove(i); // 删除多个知识点的元素
				i--;// 因为List的动态变化特性，需要--
			}
		}
		return rulesData;
	}
	
	/**
	 * 得到朔源路径List
	 * @param keywordName
	 * @param userID
	 * @param course
	 * @return
	 */
	public List<KeywordNode[]> getFindSourcePaths(String keywordName,String userID,String course){
		KeywordNode keywordNode = getCMKeywordNodeByKeywordName(keywordName,userID,course);
		List<KeywordNode[]> sourcePathList = new ArrayList<KeywordNode[]>();
		findSourcePaths(keywordNode, sourcePathList,userID,course);
		//去重
		Map<Integer,KeywordNode[]> map = new HashMap<Integer,KeywordNode[]>();
		Integer hashCode = 0;
		for(KeywordNode[] knArr : sourcePathList){
			hashCode = knArr[0].hashCode() * 3 + knArr[1].hashCode() * 5;
			map.put(hashCode, knArr);
		}
		sourcePathList.clear();
		for(KeywordNode[] knArr : map.values())
			sourcePathList.add(knArr);
		
		return sourcePathList;
	}
	/**	
	 * 得到预测路径List
	 * @param keywordName
	 * @param userID
	 * @param course
	 * @return
	 */
	public List<KeywordNode[]> getPredictPaths(String keywordName,String userID,String course){
		KeywordNode keywordNode = getCMKeywordNodeByKeywordName(keywordName,userID,course);
		List<KeywordNode[]> predictPathList = new ArrayList<KeywordNode[]>();
		predictPaths(keywordNode, predictPathList,userID,course);
		//去重
		Map<Integer,KeywordNode[]> map = new HashMap<Integer,KeywordNode[]>();
		Integer hashCode = 0;
		for(KeywordNode[] knArr : predictPathList){
			hashCode = knArr[0].hashCode() * 3 + knArr[1].hashCode() * 5;
			map.put(hashCode, knArr);
		}
		predictPathList.clear();
		for(KeywordNode[] knArr : map.values())
			predictPathList.add(knArr);
		
		return predictPathList;
	}
	
	/**
	 * 对一个知识点进行朔源
	 * @param keywordNode
	 * @param sourcePathList
	 * @param userID
	 * @param course
	 */
	private void findSourcePaths(KeywordNode keywordNode,List<KeywordNode[]> sourcePathList,String userID,String course){
		List<KeywordNode> keywordNodeList = getFindSourceRelationKeywordNodeList(userID,course,keywordNode);
		if(keywordNodeList.size() == 0)
			return;
		for(KeywordNode kn : keywordNodeList){
			KeywordNode[] knArr = new KeywordNode[2];
			knArr[0] = keywordNode;
			knArr[1] = kn;
			sourcePathList.add(knArr);
			findSourcePaths(kn,sourcePathList,userID,course);
		}
	}
	
	/**
	 * 对一个知识点进行预测
	 * @param keywordNode
	 * @param sourcePathList
	 * @param userID
	 * @param course
	 */
	private void predictPaths(KeywordNode keywordNode,List<KeywordNode[]> predictList,String userID,String course){
		List<KeywordNode> keywordNodeList = getPredictRelationKeywordNodeList(userID,course,keywordNode);
		if(keywordNodeList.size() == 0)
			return;
		for(KeywordNode kn : keywordNodeList){
			KeywordNode[] knArr = new KeywordNode[2];
			knArr[0] = keywordNode;
			knArr[1] = kn;
			predictList.add(knArr);
			predictPaths(kn,predictList,userID,course);
		}
	}
	
	/**
	 * 创建存放每个知识点和与之关联的map
	 * @param userID
	 * @param course
	 */
	private void createAssocaiteNodesMap(String userID,String course){
		Map<String,List<KeywordNode>> findSourceMap = new HashMap<String, List<KeywordNode>>();//存放键值对应关系
		Map<String,List<KeywordNode>> predictMap = new HashMap<String, List<KeywordNode>>();//存放键值对应关系

		List<KeywordNode> knList = get_keywordNodeList(userID, course);
		List<KeywordNode[]> knLineList = getCMKeywordNodeLineList(userID, course);
		for(KeywordNode keywordNode : knList){//遍历所有节点
			List<KeywordNode> findSourcekeywordNodeList = new ArrayList<KeywordNode>();//存放与该节点有关联的所有节点，且位置在其下方
			List<KeywordNode> predictKeywordNodeList = new ArrayList<KeywordNode>();//存放与该节点有关联的所有节点，且位置在其上方

			for(KeywordNode[] knArr : knLineList){//遍历节点连线关系，找出关联节点
				if(knArr[0].getName().equals(keywordNode.getName())){//如果找到这个节点，
					if(isKnAAboveKnB(keywordNode, knArr[1],userID,course)){//并且与它关联的另一个节点位置在其下方
						findSourcekeywordNodeList.add(knArr[1]);//加入与这个节点有关联的节点列表
						continue;
					}
					if(isKnABelowKnB(keywordNode, knArr[1],userID,course)){//并且与它关联的另一个节点位置在其上方
						predictKeywordNodeList.add(knArr[1]);//加入与这个节点有关联的节点列表
						continue;
					}
				}
				if(knArr[1].getName().equals(keywordNode.getName())){
					if(isKnAAboveKnB(keywordNode, knArr[0],userID,course))
						findSourcekeywordNodeList.add(knArr[0]);
					if(isKnABelowKnB(keywordNode, knArr[0],userID,course))
						predictKeywordNodeList.add(knArr[0]);
				}
			}
			findSourceMap.put(keywordNode.getName(), findSourcekeywordNodeList);
			predictMap.put(keywordNode.getName(), predictKeywordNodeList);
		}
		
		//存放至全局变量
		if(_keywordNodeFindSourceRelationMap.size() > MAX_COUNT_OF_CUSTOMER_CACHE_DATA)
			_keywordNodeFindSourceRelationMap.clear();
		if(_keywordNodeFindSourceRelationMap.get(userID) == null){
			Map<String,List<KeywordNode>>[] mapArr = new HashMap[3];
			mapArr[RelationResult.getIndexByCourseName(course)] = findSourceMap;
			_keywordNodeFindSourceRelationMap.put(userID,mapArr);
		}else{
			_keywordNodeFindSourceRelationMap.get(userID)[RelationResult.getIndexByCourseName(course)] = findSourceMap;
		}
		
		if(_keywordNodePredictRelationMap.size() > MAX_COUNT_OF_CUSTOMER_CACHE_DATA)
			_keywordNodePredictRelationMap.clear();
		if(_keywordNodePredictRelationMap.get(userID) == null){
			Map<String,List<KeywordNode>>[] mapArr = new HashMap[3];
			mapArr[RelationResult.getIndexByCourseName(course)] = predictMap;
			_keywordNodePredictRelationMap.put(userID,mapArr);
		}else{
			_keywordNodePredictRelationMap.get(userID)[RelationResult.getIndexByCourseName(course)] = predictMap;
		}
	}
	
	/**
	 * 知识点A是否在知识点B位置上方
	 * @param knA
	 * @param knB
	 * @param userID
	 * @param course
	 * @return
	 */
	private static Boolean isKnAAboveKnB(KeywordNode knA,KeywordNode knB,String userID,String course){
		boolean isFindKnA = false;
		boolean isFindKnB = false;
		List<List<String>> positionList = _KeywordNodePositions.get(userID)[RelationResult.getIndexByCourseName(course)];
		for(List<String> sl : positionList){
			for(String s : sl){
				if(isFindKnB && !isFindKnA)//找到知识点B却还没找到知识点A，说明A在B下方
					return false;
				if(isFindKnA && !isFindKnB)//找到知识点A却还没找到知识点B，说明A在B上方
					return true;
				if(s.equals(knA.getName()))
					isFindKnA = true;
				if(s.equals(knB.getName()))
					isFindKnB = true;
			}
		}
		return false;//能到这里表明两个知识点都没有找到，也就没有所谓的谁在谁下面
	}
	
	/**
	 * 知识点A是否在知识点B位置上方
	 * @param knA
	 * @param knB
	 * @param userID
	 * @param course
	 * @return
	 */
	private static Boolean isKnABelowKnB(KeywordNode knA,KeywordNode knB,String userID,String course){
		boolean isFindKnA = false;
		boolean isFindKnB = false;
		List<List<String>> positionList = _KeywordNodePositions.get(userID)[RelationResult.getIndexByCourseName(course)];
		for(List<String> sl : positionList){
			for(String s : sl){
				if(isFindKnB && !isFindKnA)//找到知识点B却还没找到知识点A，说明A在B下方
					return true;
				if(isFindKnA && !isFindKnB)//找到知识点A却还没找到知识点B，说明A在B上方
					return false;
				if(s.equals(knA.getName()))
					isFindKnA = true;
				if(s.equals(knB.getName()))
					isFindKnB = true;
			}
		}
		return false;//能到这里表明两个知识点都没有找到，也就没有所谓的谁在谁下面
	}
	
	/**
	 * 用户是否定制过地图
	 * @param userId
	 * @param courseName
	 * @return
	 */
	public boolean hasCustomerMade(String userId,String courseName){
		return cmDao.hascustomerMade(userId, courseName);
	}
	
	/**
	 * 保存用户定制地图类型
	 * @param userId
	 * @param courseName
	 * @return
	 */
	public void saveCustomerMapType(String userId,String courseName){
		cmDao.saveCustomerMapType(userId, courseName);
	}
}
