package dto;

//import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import entity.Car;
import entity.Light;

public class SimDto {
	
	/**
	 * 汽车表
	 */
	private List<Car> carList = new ArrayList<Car>();
	/**
	 * 红绿灯表
	 */
	private List<Light> lightList;
	/**
	 * 所有节点
	 */
	private List<MapNode> node;
	/**
	 * 出生地节点数组
	 */
	private List<Integer> birthList;
	/**
	 * 目的地节点数组
	 */
	private List<Integer> destList;
	
	/**
	 * 地图最短路径前驱矩阵
	 */
	private int[][] mapPath;
	/**
	 * 线程同步锁
	 */
	final private Object lock = new Object();
	 //TODO 硬编码
//	/**
//	 * 消失点颜色对应
//	 */
//	private final static Color[] destColor = new Color[]{
//			new Color(255, 0, 0),
//			new Color(255, 255, 0),
//			new Color(0, 255, 0),
//			new Color(0, 0, 255),
//			new Color(255, 0, 255),
//			new Color(128, 128, 128)
//	};
	public final  int SIZE = 20;
	private boolean flagOver = false;
	private  int maxNum = 50;
	
	
	
	public boolean isFlagOver() {
		return flagOver;
	}

	public void setFlagOver(boolean flagOver) {
		this.flagOver = flagOver;
	}

	

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}

	public List<Car> getCarList() {
		return carList;
	}

	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}

	public List<Light> getLightList() {
		return lightList;
	}

	public void setLightList(List<Light> lightList) {
		this.lightList = lightList;
	}

	public List<MapNode> getNode() {
		return node;
	}

	public void setNode(List<MapNode> node) {
		this.node = node;
	}

	public List<Integer> getBirthList() {
		return birthList;
	}

	public void setBirthList(List<Integer> birthList) {
		this.birthList = birthList;
	}

	public List<Integer> getDestList() {
		return destList;
	}

	public void setDestList(List<Integer> destList) {
		this.destList = destList;
	}

	public int[][] getMapPath() {
		return mapPath;
	}

	public void setMapPath(int[][] mapPath) {
		this.mapPath = mapPath;
	}

	public Object getLock() {
		return lock;
	}


	
	
}
