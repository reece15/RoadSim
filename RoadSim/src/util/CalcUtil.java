package util;

import java.awt.Point;

/**
 * @filename SubRetNum.java
 * @author Reece
 * @description  比较两个数大小 返回0 1 2分别代表小于 等于 大于
 * @date 2015年10月16日下午3:31:35
 * @version 1.0
 */
public class CalcUtil {
	public static int compareRetNum(int a, int b){
		return a > b ? 2 : (a == b ? 1:0);
	}
	
	public static double howLong(Point src, Point dst){
			return Math.sqrt((double)((dst.x - src.x)*(dst.x - src.x) + (dst.y - dst.y)*(dst.y - dst.y)));
	}
}
