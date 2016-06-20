package edu.fjnu.util;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 格式化double的小数位数
 * 
 * @author vengeance
 *
 */
public class DoubleFormat {
	private static final int FORMAT_MODE = BigDecimal.ROUND_HALF_UP;// 取值方式(这里选择四舍五入)
	private static BigDecimal bd;

	/**
	 * 格式化double
	 * @param doubleInput 
	 * @param keep 保留几位小数
	 * @return
	 */
	public static double getDoubleFormat(double doubleInput,int keep) {
		bd = new BigDecimal(doubleInput);
		return bd.setScale(keep, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 两个BigDecimal之间相除
	 * 
	 * @param doubleInputS
	 * @param doubleInputT
	 * @return 结果
	 */
	public double getDivideFormat(double doubleInputS, double doubleInputT) {
		bd = new BigDecimal(doubleInputS);
		BigDecimal bdT = new BigDecimal(doubleInputT);
		double result = bd.divide(bdT, 2, FORMAT_MODE).doubleValue();
		bd = null;
		bdT = null;
		return result;
	}
	
	/**
	 * 得到某范围的随机数
	 * @param from 最小数
	 * @param to 最大数
	 * @return
	 */
	public static double getRandom(double from,double to){
		BigDecimal db = new BigDecimal(Math.random() * (to - from) + from);  
		return db.doubleValue();
	}
}
