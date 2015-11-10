package util;

import java.awt.Point;

/**
 * @filename Point.java
 * @author Reece
 * @description  浮点型坐标
 * @date 2015年10月16日下午9:21:10
 * @version 1.0
 */
public class DoublePoint {
	public double x;
	public double y;
	public DoublePoint(double x, double y){
		this.x = x;
		this.y = y;
	}
	public DoublePoint(int x, int y){
		this.x = (double)x;
		this.y = (double)y;
	}
	public DoublePoint(Point point) {
		this.x = (double)point.x;
		this.y = (double)point.y;
	}
}
