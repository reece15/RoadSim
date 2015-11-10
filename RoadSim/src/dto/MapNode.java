package dto;

import java.awt.Point;

/**
 * @filename TreeNode.java
 * @author Reece
 * @description  保存整个地图的节点结构
 * @date 2015年10月15日下午5:10:52
 * @version 1.0
 */
public class MapNode {
	/**
	 * 节点所在坐标
	 */
	protected Point point;
	/**
	 * 右转可到达的下一个节点
	 */
	protected int rightChild = -1;
	/**
	 * 直走可到达的下一个节点
	 */
	protected int faceChild;
	/**
	 *节点类型
	 */
	protected int type;
	/**
	 * 节点编号
	 */
	protected int code;
	
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public int getRightChild() {
		return rightChild;
	}
	public void setRightChild(int rightChild) {
		this.rightChild = rightChild;
	}
	public int getFaceChild() {
		return faceChild;
	}
	public void setFaceChild(int faceChild) {
		this.faceChild = faceChild;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}


}
