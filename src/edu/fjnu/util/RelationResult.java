package edu.fjnu.util;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import edu.fjnu.dao.KeywordDao;
import edu.fjnu.domain.KeywordNode;

/**
 * 得到关联分析数据
 * 
 * @author vengeance，zhangzhiyong
 *
 */
@SuppressWarnings("unchecked")
public class RelationResult {
	private static final String[] GRADES = {"一年级","二年级","三年级","四年级","五年级","六年级","初一","初二","初三"};
	private static final double START_POSITION = 50;	//地图第一个点开始的坐标大小
	private static final int MAX_WIDTH = 10;	//地图上同纬度两节点最长的距离
	private static final int MAX_COUNT_PER_ROW = 8;//地图上同纬度最多有8个节点
	private static final double MIN_LONGTITUDE_DISTANCE = 0.4;//地图上两点间最小的经度距离
	private static final double MAX_LONGTITUDE_DISTANCE = 1;//地图上两点间最大的经度距离
	private static final double MIN_LANTITUDE_DISTANCE = 0.4;//地图上两点间最小的纬度距离
	private static final double MAX_LANTITUDE_DISTANCE = 1;//地图上两点间最大的纬度距离
	private KeywordDao keywordDao = new KeywordDao();
	private static List<KeywordNode>[] _keywordNodeList = new ArrayList[3];//知识节点数据
	private static List<KeywordNode[]>[] _keywordLineList = new ArrayList[3];//知识点连线数据
	private static List<String[]>[] _keywordLineData = new ArrayList[3];//知识连线原始数据
	private static List<List<String>>[] _rowColOfAllKeywords = new ArrayList[3];//所有知识点的行列位置
	//键：知识节点，值：与之关联并且位置在其下方的所有节点列表（用于朔源）
	private static Map<String,List<KeywordNode>>[] _keywordNodeFindSourceRelationMap = new HashMap[3];
	//键：知识节点，值：与之关联并且位置在其下方的所有节点列表（用于预测）
	private static Map<String,List<KeywordNode>>[] _keywordNodePredictRelationMap = new HashMap[3];

	/**
	 * 清理缓存，用于更新数据后
	 */
	public static void clearCache(){
		for(int i = 0; i < 3;i++){
			_keywordNodeList[i] = null;
			_keywordLineList[i] = null;
			_keywordLineData[i] = null;
			_keywordNodeList[i] = null;
			_keywordNodeFindSourceRelationMap[i] = null;
			_keywordNodePredictRelationMap[i] = null;
		}
	}
	
	/**
	 * 得到知识节点对象List（懒加载），此方法同时对外开放获取数据
	 * @param course
	 * @return
	 */
	public List<KeywordNode> get_keywordNodeList(String course){
		int courseIndex = getIndexByCourseName(course);
		if(_keywordNodeList[courseIndex] == null){
			_keywordNodeList[courseIndex] = getKeywordNodeList(course);
		}
		return _keywordNodeList[courseIndex];
	}
	
	/**
	 * 得到知识连线对象List（懒加载），此方法同时对外开放获取数据
	 * @param course
	 * @return
	 */
	public List<KeywordNode[]> get_keywordLineList(String course){
		int courseIndex = getIndexByCourseName(course);
		if(_keywordLineList[courseIndex] == null){
			_keywordLineList[courseIndex] = getKeywordLineList(course);
		}
		return _keywordLineList[courseIndex];
	}
	
	/**
	 * 获取关联原始数据（懒加载）
	 * @param course
	 * @return
	 */
	private List<String[]> get_keywordLineData(String course){
		int courseIndex = getIndexByCourseName(course);
		if(_keywordLineData[courseIndex] == null){
			_keywordLineData[courseIndex] = getKeywordLineData(course);
		}
		return _keywordLineData[courseIndex];
	}
	
	/**
	 * 此方法对外开放，用于初始化知识节点朔源前的准备工作
	 * @param course
	 */
	public void initFindSourcePath(String course){
		if(_keywordNodeFindSourceRelationMap[getIndexByCourseName(course)] == null)
			createAssocaiteNodesMap(course);
	}
	
	/**
	 * 根据输入的科目、年级获得处理过的关联规则列表，再得到去重的知识点列表
	 * 
	 * @param year
	 * @param course
	 * @return 知识点列表
	 */
	private List<String> getKeywordList(String year, String course) {
		// 调用得到规则列表的方法，获得剔除知识对的关联规则列表
		List<String[]> rulesData = getRulesData(year, course);
		// 如果未空就返回空
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
	 * 根据输入的课程、年级返回source-target-value结构列表
	 * 
	 * @param year
	 * @param course
	 * @return s-t-v列表
	 */
	public List<List<Object>> getStvList(String year, String course) {
		// 调用得到规则列表的方法，获得剔除知识对的关联规则列表
		List<String[]> rulesData = getRulesData(year, course);
		// 如果为空就返回空
		if (rulesData == null || rulesData.size() == 0) {
			return null;
		}

		// 调用得到知识点列表的方法，获得所有不重复的知识点
		List<String> keywordList = getKeywordList(year, course);
		if (keywordList == null || keywordList.size() == 0) {
			return null;
		}

		// 存储结果集，格式类型为source,target,value(源知识点，目标知识点，关联值)
		List<List<Object>> resultList = new ArrayList<List<Object>>();
		// 遍历整个知识点规则列表
		int indexA = 0, indexB = 0;// 保存知识点A下标为source,知识点B下标为target
		int beginA, beginB, endA, endB; // 控制截取知识点的下标
		String keyA, keyB; // 保存知识点A，知识点B
		for (int i = 0; i < rulesData.size(); i++) {
			beginA = rulesData.get(i)[0].indexOf("{");
			endA = rulesData.get(i)[0].indexOf("}");
			beginB = rulesData.get(i)[0].indexOf(" {");
			endB = rulesData.get(i)[0].indexOf("}\"");

			keyA = rulesData.get(i)[0].substring(beginA + 1, endA);
			keyB = rulesData.get(i)[0].substring(beginB + 2, endB);

			for (int j = 0; j < keywordList.size(); j++) {
				if (keyA.equals(keywordList.get(j))) {
					indexA = j;
				}
			}
			for (int j = 0; j < keywordList.size(); j++) {
				if (keyB.equals(keywordList.get(j))) {
					indexB = j;
				}
			}
			List<Object> tmpList = new ArrayList<Object>();
			tmpList.add(indexA);
			tmpList.add(indexB);
			tmpList.add(rulesData.get(i)[2]);

			resultList.add(tmpList);
		}
		return resultList;
	}

	/**
	 * 根据输入的科目年级获得关联规则列表
	 * 
	 * @param year
	 * @param course
	 * @return 两两知识对的关联规则列表
	 */
	private List<String[]> getRulesData(String year, String course) {
		List<String[]> rulesData = new ArrayList<String[]>();
		// 读取CSV文件，获得规则
		try {		
			//拼接路径
			course = FileTools.translateSomeChineseIntoEnglish(course);
			year = FileTools.translateSomeChineseIntoEnglish(year);
		    String path = FileTools.getApplicationRootPath() + "relation/" + course + "/" + year + "/rule.csv";
			rulesData = CSVUtil.importCsv(path);
		} catch (FileNotFoundException e) {
			System.err.println("路径文件不存在！");
			return null;
		}

		// 如果文件内容为空或者文件为空，返回空
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
	 * 格式转换，把知识点List转换成String数组
	 * 
	 * @param year
	 * @param course
	 * @return 知识点数组
	 */
	public String[] getKeywordArr(String year, String course) {
		List<String> tmpKeywordList = getKeywordList(year, course);
		if (tmpKeywordList == null || tmpKeywordList.size() == 0) {
			return null;
		}
		String[] keywordArr = new String[tmpKeywordList.size()];
		for (int i = 0; i < keywordArr.length; i++) {
			keywordArr[i] = tmpKeywordList.get(i);
		}

		return keywordArr;
	}

	/**
	 * 格式化，把知识点关系网转换成Object[][]二维数组
	 * 
	 * @param year
	 * @param course
	 * @return 关系网二维数组
	 */
	public Object[][] getStvArr(String year, String course) {
		List<List<Object>> tmpStvList = getStvList(year, course);
		if (tmpStvList == null || tmpStvList.size() == 0) {
			return null;
		}
		Object[][] stvArr = new Object[tmpStvList.size()][tmpStvList.get(0).size()];
		for (int i = 0; i < stvArr.length; i++) {
			Object[] tmpArr = tmpStvList.get(i).toArray(new Object[tmpStvList.get(0).size()]);
			stvArr[i] = tmpArr;
		}

		return stvArr;
	}
	
/*以下为学历方知识地图模块核心处理代码**********************************************************************/
	
	/**
	 * 得到知识点连线列表
	 * 形如 List[ [KeywordNodeA,KeywordNodeB] , [KeywordNodeC,KeywordNodeD] ...]
	 * @param course
	 * @return keywordLineList
	 */
	public List<KeywordNode[]> getKeywordLineList(String course){
		// 存储结果集
		List<KeywordNode[]> keywordLineList = new ArrayList<KeywordNode[]>();
		if(course == ""){
			return keywordLineList;
		}
				
		//构建知识节点连线列表
		for (String[] keywordArr : get_keywordLineData(course)) {
			//遍历所有知识节点对象列表，发现节点名称一样，就存储该节点对象进入节点连线列表
			KeywordNode[] knArr = new KeywordNode[2];
			KeywordNode knA = getKeywordNodeByKeywordName(keywordArr[0], course);
			KeywordNode knB = getKeywordNodeByKeywordName(keywordArr[1], course);
			//如果有一个节点找不到，就不加进连线List,并把与之关联的另一个节点也删掉，否则会出现单独一个节点没有连线的情况
			if(knA == null || knB == null){
				List<KeywordNode> keywordNodeList = get_keywordNodeList(course);
				if(knA != null){
					for(KeywordNode keywordNode : keywordNodeList){
						if(keywordNode.getName().equals(knA.getName())){
							if(OnlyOneToAssociate(keywordNode.getName(),course))
								keywordNodeList.remove(keywordNode);	
							break;
						}
					}
				}
				if(knB != null){
					for(KeywordNode keywordNode : keywordNodeList){
						if(keywordNode.getName().equals(knB.getName())){
							if(OnlyOneToAssociate(keywordNode.getName(),course))
								keywordNodeList.remove(keywordNode);	
							break;
						}
					}
				}
				
				continue;
			}
			
			knArr[0] = knA;
			knArr[1] = knB;
			keywordLineList.add(knArr);
		}

		return keywordLineList;
	}
	
	/**
	 * 根据知识点名称得到其对应的知识节点对象
	 * @param keywordName
	 * @param course
	 * @return
	 */
	public KeywordNode getKeywordNodeByKeywordName(String keywordName,String course){
		for(KeywordNode keywordNode : get_keywordNodeList(course)){
			if(keywordNode.getName().equals(keywordName)){
				return keywordNode;
			}
		}
		return null;
	}
	
	/**
	 * 根据知识点名称得到其对应的知识节点对象（模糊匹配，用于知识地图搜索知识节点）
	 * @param keywordName
	 * @param course
	 * @return
	 */
	public KeywordNode getKeywordNodeByBlurKeywordName(String keywordName,String course){		
		for(KeywordNode keywordNode : get_keywordNodeList(course)){
			if(keywordNode.getName().contains(keywordName)){
				return keywordNode;
			}
		}
		return null;
	}
	
	/**
	 * 得到知识结点对象List
	 * @param course
	 * @return
	 */
	private List<KeywordNode> getKeywordNodeList(String course){
		List<KeywordNode> keywordNodeList = new ArrayList<KeywordNode>();//存放知识节点对象
		if(course == ""){
			return keywordNodeList;
		}
		
		List<String> allKeywordList = getAllKeywordList(course);//得到所有知识点
		allKeywordList = sortListDESC(allKeywordList);//排序
		Map<Integer,List<String>> map = classifyKeyword(allKeywordList);//归类
		
		double currentLongtitude = START_POSITION;//当前经度
		double currentLatitude = START_POSITION;//当前纬度
		//遍历处理过的知识点列表，为每个节点构造经纬度
		List<List<String>> allPositonList = new ArrayList<List<String>>();//用来存放所有的知识点位置列表
		for(int i = GRADES.length - 1;i >= 0;i--){
			if(map.get(i) == null || map.get(i).size() == 0){
				continue;
			}
			
			List<List<String>> positonList = calculateRowAndCol(map.get(i), course);//计算每个知识点所处的行和列
			
			allPositonList.addAll(positonList);
			for(List<String> list : positonList){//根据知识点所处的行和列，为其分配合适的经纬度
				for(String keywordName : list){
					currentLongtitude += DoubleFormat.getRandom(MIN_LONGTITUDE_DISTANCE, MAX_LONGTITUDE_DISTANCE);
					currentLongtitude = DoubleFormat.getDoubleFormat(currentLongtitude,2);//保留两位小数
					
					KeywordNode keywordNode = new KeywordNode(keywordName, currentLongtitude, currentLatitude,i+1);
					keywordNodeList.add(keywordNode);
				}
				if(currentLongtitude > START_POSITION + MAX_WIDTH)
					currentLongtitude -= MAX_WIDTH;
				currentLatitude -= DoubleFormat.getRandom(MIN_LANTITUDE_DISTANCE, MAX_LANTITUDE_DISTANCE);
				currentLatitude = DoubleFormat.getDoubleFormat(currentLatitude,2);//保留两位小数
			}
		}
		_rowColOfAllKeywords[getIndexByCourseName(course)] = allPositonList;

		return keywordNodeList;
	}
	
	/**
	 * 根据名称判断两个知识点是否有关联
	 * @param keywordA
	 * @param keywordB
	 * @param course
	 * @return
	 */
	private boolean isAssociated(String keywordA,String keywordB,String course){		
		for(String[] keywordArr : get_keywordLineData(course)){
			if((keywordArr[0].equals(keywordA) && keywordArr[1].equals(keywordB))
				|| (keywordArr[0].equals(keywordB) && keywordArr[1].equals(keywordA)))		
				return true;
		}
		return false;
	}
	
	/**
	 * 得到知识关联连线数据
	 * @param course
	 * @return
	 */
	private List<String[]> getKeywordLineData(String course){
		// 存储结果集
		List<String[]> keywordLineData = new ArrayList<String[]>();
		if(course == ""){
			return keywordLineData;
		}
		
		// 得到所有的关联规则数据List
		List<String[]> allRelationDataList = new ArrayList<String[]>();
		for(int i = 0;i < GRADES.length;i++){	//添加1-9年级知识点关联规则列表
			allRelationDataList.addAll(getRulesData(GRADES[i], course));
		}
		allRelationDataList.addAll(getRulesData("跨年级", course));//添加跨年级知识点列表
				
		// 遍历整个知识点规则列表
		int beginA, beginB, endA, endB; // 控制截取知识点的下标
		String keyA,keyB;
		for (int i = 0; i < allRelationDataList.size(); i++) {
			beginA = allRelationDataList.get(i)[0].indexOf("{");
			endA = allRelationDataList.get(i)[0].indexOf("}");
			beginB = allRelationDataList.get(i)[0].indexOf(" {");
			endB = allRelationDataList.get(i)[0].indexOf("}\"");
			keyA = allRelationDataList.get(i)[0].substring(beginA + 1, endA);
			keyB = allRelationDataList.get(i)[0].substring(beginB + 2, endB);
			
			String[] keyArr = new String[2];
			keyArr[0] = keyA;
			keyArr[1] = keyB;
			
			boolean isExit = false;
			//判断这对知识点是否已经存在，存在就不再加入
			for(String[] klArr : keywordLineData){
				if((klArr[0].equals(keyA) && klArr[1].equals(keyB))
						|| (klArr[0].equals(keyB) && klArr[1].equals(keyA))){
					isExit = true;
					break;
				}
			}
			if(!isExit)
				keywordLineData.add(keyArr);
		}
		return keywordLineData;
	}
	
	/**
	 * 得到所有年级的知识点列表
	 * @param course
	 * @return
	 */
	private List<String> getAllKeywordList(String course){
		List<String> allKeywordList = new ArrayList<String>();
		for(int i = 0;i < GRADES.length;i++){	//添加1-9年级知识点列表
			if(getKeywordList(GRADES[i], course) != null)
				allKeywordList.addAll(getKeywordList(GRADES[i], course));
		}
		if(getKeywordList("跨年级", course) != null)
			allKeywordList.addAll(getKeywordList("跨年级", course));//添加跨年级知识点列表
		
		return allKeywordList;
	}
	
	/**
	 * 对知识点根据所属年级进行归类
	 * 结构形如 List[一年级List，二年级List...]
	 * @param keywordList
	 * @return 归类后的List<List<String>>
	 */
	private Map<Integer,List<String>> classifyKeyword(List<String> keywordList){
		Map<Integer,List<String>> map = new HashMap<Integer,List<String>>();
		for(String keywordName : keywordList){
			String grade = keywordDao.getGradeByKeywordName(keywordName);//得到这个知识点所在年级
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
	 * 对知识点List按照知识点ID进行倒序排序
	 * @param keywordList
	 * @return keywordList
	 */
	private List<String> sortListDESC(List<String> keywordList){
		Map<Long, String> hashMap = new HashMap<Long,String>();
		for(String keywordName : keywordList){
			Long id = keywordDao.getKeywordIdByName(keywordName);
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
	 * 通过学科名称获得下标
	 * 对应关系为：[语文：0，数学：1，英文：2]
	 * @param course
	 * @return 学科对应下标
	 */
	public static int getIndexByCourseName(String course){
		switch (course) {
		case "语文":
			return 0;
		case "数学":
			return 1;
		case "英文":
			return 2;
		default:
			return -1;
		}
	}
	
	/**
	 * 传入一个知识点列表，计算每个知识点所在的行和列
	 * @param list 知识点列表
	 * @param course 学科
	 * @return List<List<String>> 用二维List表示行列
	 */
	private 	List<List<String>> calculateRowAndCol(List<String> list,String course){
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
					if(isAssociated(resultList.get(i).get(j), keyword, course)){//两个知识点有关联，不放入当前行
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
	 * 通过知识点名称判断与该知识点有关联的知识点是否只有一个
	 * @param keywordName
	 * @param course
	 * @return
	 */
	private boolean OnlyOneToAssociate(String keywordName,String course){
		int AssociationCount = 0;
		for(String[] keyArr : get_keywordLineData(course)){
			if(keyArr[0].equals(keywordName) || keyArr[1].equals(keywordName))
				AssociationCount++;
			if(AssociationCount == 2)
				return false;
		}
		return true;
	}
	
	/**
	 * 得到朔源路径list
	 * @param keywordName 知识名字
	 * @param course 学科
	 * @return
	 */
	public List<KeywordNode[]> getFindSourcePaths(String keywordName,String course){
		KeywordNode keywordNode = getKeywordNodeByKeywordName(keywordName, course);
		List<KeywordNode[]> sourcePathList = new ArrayList<KeywordNode[]>();
		findSourcePaths(keywordNode, sourcePathList, course);
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
	 * 得到预测路径list
	 * @param keywordName 知识名字
	 * @param course 学科
	 * @return
	 */
	public List<KeywordNode[]> getPredictPaths(String keywordName,String course){
		KeywordNode keywordNode = getKeywordNodeByKeywordName(keywordName, course);
		List<KeywordNode[]> predictPathList = new ArrayList<KeywordNode[]>();
		predictPaths(keywordNode, predictPathList, course);
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
	 * 对一个知识节点进行朔源
	 * 由于采用递归，所以返回的结果集需要靠传入集合进行存储，取结果集即取传入的集合
	 * 结果集形如[[KeywordNodeA,KeywordNodeB],[KeywordNodeC,KeywordNodeD]]
	 * @param keywordNode 要查找的知识节点对象
	 * @param sourcePathList 存储结果集
	 * @param course 学科
	 */
	private void findSourcePaths(KeywordNode keywordNode,List<KeywordNode[]> sourcePathList,String course){
		List<KeywordNode> keywordNodeList = _keywordNodeFindSourceRelationMap[getIndexByCourseName(course)].get(keywordNode.getName());
		if(keywordNodeList.size() == 0)
			return;
		for(KeywordNode kn : keywordNodeList){
			KeywordNode[] knArr = new KeywordNode[2];
			knArr[0] = keywordNode;
			knArr[1] = kn;
			sourcePathList.add(knArr);
			findSourcePaths(kn,sourcePathList,course);
		}
	}
	
	/**
	 * 对一个知识节点进行预测
	 * 由于采用递归，所以返回的结果集需要靠传入集合进行存储，取结果集即取传入的集合
	 * 结果集形如[[KeywordNodeA,KeywordNodeB],[KeywordNodeC,KeywordNodeD]]
	 * @param keywordNode 要查找的知识节点对象
	 * @param sourcePathList 存储结果集
	 * @param course 学科
	 */
	private void predictPaths(KeywordNode keywordNode,List<KeywordNode[]> predictPathList,String course){
		List<KeywordNode> keywordNodeList = _keywordNodePredictRelationMap[getIndexByCourseName(course)].get(keywordNode.getName());
		if(keywordNodeList.size() == 0)
			return;
		for(KeywordNode kn : keywordNodeList){
			KeywordNode[] knArr = new KeywordNode[2];
			knArr[0] = keywordNode;
			knArr[1] = kn;
			predictPathList.add(knArr);
			predictPaths(kn,predictPathList,course);
		}
	}
		
	/**
	 * 创建节点与和它有关联的所有节点Map（且与之关联节点位置需在其下方）
	 * Map键为该节点名称，值为与该节点有关系的所有节点List，并且位置在该节点下方
	 * @param course
	 * @return
	 */
	private void createAssocaiteNodesMap(String course){
		Map<String,List<KeywordNode>> findSourceMap = new HashMap<String, List<KeywordNode>>();//存放键值对应关系(朔源)
		Map<String,List<KeywordNode>> predictMap = new HashMap<String, List<KeywordNode>>();//存放键值对应关系(预测)
		for(KeywordNode keywordNode : get_keywordNodeList(course)){//遍历所有节点
			List<KeywordNode> findSourceKeywordNodeList = new ArrayList<KeywordNode>();//存放与该节点有关联的所有节点，且位置在其下方
			List<KeywordNode> predictKeywordNodeList = new ArrayList<KeywordNode>();//存放与该节点有关联的所有节点，且位置在其上方
			for(KeywordNode[] knArr : get_keywordLineList(course)){//遍历节点连线关系，找出关联节点
				if(knArr[0].getName().equals(keywordNode.getName())){//如果找到这个节点，
					if(isKnAAboveKnB(keywordNode, knArr[1],course)){	//并且与它关联的另一个节点位置在其下方
						findSourceKeywordNodeList.add(knArr[1]);//加入与这个节点有关联的下方节点列表
						continue;
					}
					if(isKnABelowKnB(keywordNode, knArr[1],course)){	//并且与它关联的另一个节点位置在其上方
						predictKeywordNodeList.add(knArr[1]);	//加入与这个节点有关联的上方节点列表
						continue;
					}
				}
				if(knArr[1].getName().equals(keywordNode.getName())){
					if(isKnAAboveKnB(keywordNode, knArr[0],course))
						findSourceKeywordNodeList.add(knArr[0]);
					if(isKnABelowKnB(keywordNode, knArr[0],course))
						predictKeywordNodeList.add(knArr[0]);	
				}
			}
			findSourceMap.put(keywordNode.getName(), findSourceKeywordNodeList);
			predictMap.put(keywordNode.getName(), predictKeywordNodeList);
		}
		_keywordNodeFindSourceRelationMap[getIndexByCourseName(course)] = findSourceMap;
		_keywordNodePredictRelationMap[getIndexByCourseName(course)] = predictMap;
	}
	
	/**
	 * 判断知识点A的位置是否在知识点B的上方
	 * @param knA
	 * @param knB
	 * @param course
	 * @return
	 */
	private static Boolean isKnAAboveKnB(KeywordNode knA,KeywordNode knB,String course){
		boolean isFindKnA = false;
		boolean isFindKnB = false;
		for(List<String> sl : _rowColOfAllKeywords[getIndexByCourseName(course)]){
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
	 * 判断知识点A的位置是否在知识点B的下方
	 * @param knA
	 * @param knB
	 * @param course
	 * @return
	 */
	private static Boolean isKnABelowKnB(KeywordNode knA,KeywordNode knB,String course){
		boolean isFindKnA = false;
		boolean isFindKnB = false;
		for(List<String> sl : _rowColOfAllKeywords[getIndexByCourseName(course)]){
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
		
}
