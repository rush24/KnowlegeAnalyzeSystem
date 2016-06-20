package edu.fjnu.util;

import java.util.HashSet;
import java.util.List;

public class CollectionUtil {

	/**
	 * list去重，只支持String类型
	 * @param list
	 * @return
	 */
	public static List<String> removeDuplicate(List<String> list) 
	{        
		 HashSet<String> hashSet = new HashSet<String>(list);        
		 list.clear();        
		 list.addAll(hashSet);        
		 return list;     
	}  
	
	
}
