package entity;

import java.awt.Color;

public class Light {
	/**
	 * 每个灯的等待时间
	 */
	private  static int[] tableTime = new int[]{6,5,1};
	/**
	 * 计数器
	 */
	private int countW = 0;
	/**
	 * 时钟计数器
	 */
	private int countH  = 0;
	/**
	 * 红绿灯状态 
	 */
	private int WRGY = 1;
	/**
	 * 红绿灯状态 
	 */
	private int HRGY = 0;
	public final static  Color[] COLORTABLE = new Color[]{new Color(255,0,0),new Color(0,255,0),new Color(255,255,0)};
	//TODO 还需要处理同一路口不同灯和相同灯的属性 
	/**
	 * 宽带方向灯的编码
	 */
	private int nodeCodeWA;
	/**
	 * 宽度方向灯的编码
	 */
	private int nodeCodeWB;
	/**
	 * 高度方向灯的编码
	 */
	private int nodeCodeHA;
	/**
	 * 高度方向灯的编码
	 */
	private int nodeCodeHB;
	
	public Light(){
		//TODO 未实现 应在灯生成时传递灯的参数
	}
	
	public void shining() {
		this.countW -= 1;
		this.countH -= 1;
	}
	
	public void WChange(){
		this.WRGY = (this.WRGY + 1)%3;
		this.countW = tableTime[this.WRGY];
	}
	public void HChange(){
		this.HRGY = (this.HRGY + 1)%3;
		this.countH= tableTime[this.HRGY];
	}
	public int getNodeCodeWA() {
		return nodeCodeWA;
	}

	public void setNodeCodeWA(int nodeCodeWA) {
		this.nodeCodeWA = nodeCodeWA;
	}

	public int getNodeCodeWB() {
		return nodeCodeWB;
	}

	public void setNodeCodeWB(int nodeCodeWB) {
		this.nodeCodeWB = nodeCodeWB;
	}

	public int getNodeCodeHA() {
		return nodeCodeHA;
	}

	public void setNodeCodeHA(int nodeCodeHA) {
		this.nodeCodeHA = nodeCodeHA;
	}

	public int getNodeCodeHB() {
		return nodeCodeHB;
	}

	public void setNodeCodeHB(int nodeCodeHB) {
		this.nodeCodeHB = nodeCodeHB;
	}

	public int getHRGY() {
		return HRGY;
	}

	public void setHRGY(int hRGY) {
		HRGY = hRGY;
	}

	public int getWRGY() {
		return WRGY;
	}

	public void setWRGY(int wRGY) {
		WRGY = wRGY;
	}

	public int getCountW() {
		return countW;
	}

	public void setCountW(int countW) {
		this.countW = countW;
	}

	public int getCountH() {
		return countH;
	}

	public void setCountH(int countH) {
		this.countH = countH;
	}

	public static int[] getTableTime() {
		return tableTime;
	}

	public static void setTableTime(int[] tableTime) {
		Light.tableTime = tableTime;
	}

	
}
