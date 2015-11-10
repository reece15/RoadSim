package entity;

import util.DoublePoint;

public class Car {
	/**
	 * 小车所在坐标 注意 此坐标使用前需要强制类型转换
	 */
	private DoublePoint point;
	/**
	 * 小车类型
	 */
	private int type;
	/**
	 * 小车是否具有惯性;
	 */
	private boolean inertia;
	/**
	 * 速度
	 */
	private int speed;
	/**
	 * 小车探路距离
	 */
	private int wayLength;
	/**
	 * 小车生命状态 
	 */
	private boolean life;
	/**
	 * 小车启始位置
	 */
	private int home;
	/**
	 * 小车短期目的地
	 */
	private int dest;
	/**
	 * 小车最终目的地
	 */
	private int finalDest;
	
	/**
	 * 小车的方向 状态（顺时针8个方向： 上 上偏右 右 右偏下 下 下偏左 左 左偏上）
	 */
	private Target tg;
	/**
	 * XY坐标移动速度
	 * 
	 */
	private DoublePoint moveXY;
	/**
	 * 车辆编号
	 * 
	 */
	private int code;
	/**
	 * 红灯停止标记
	 */
	private boolean flagStop = false;
	
	public Car(int code){
		this.code = code;
	}
	public void move(){
		this.point.x += moveXY.x;
		this.point.y += moveXY.y;
	}
	
	public DoublePoint getPoint() {
		return point;
	}

	public void setPoint(DoublePoint point) {
		this.point = point;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


	public boolean isInertia() {
		return inertia;
	}

	public void setInertia(boolean inertia) {
		this.inertia = inertia;
	}

	public int getWayLength() {
		return wayLength;
	}

	public void setWayLength(int wayLength) {
		this.wayLength = wayLength;
	}

	public boolean isLife() {
		return life;
	}

	public void setLife(boolean life) {
		this.life = life;
	}



	public Target getTg() {
		return tg;
	}

	public void setTg(Target tg) {
		this.tg = tg;
	}

	public int getHome() {
		return home;
	}

	public void setHome(int home) {
		this.home = home;
	}

	public int getDest() {
		return dest;
	}

	public void setDest(int dest) {
		this.dest = dest;
	}

	public DoublePoint getMoveXY() {
		return moveXY;
	}

	public void setMoveXY(DoublePoint moveXY) {
		this.moveXY = moveXY;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getFinalDest() {
		return finalDest;
	}

	public void setFinalDest(int finalDest) {
		this.finalDest = finalDest;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public boolean isFlagStop() {
		return flagStop;
	}
	public void setFlagStop(boolean flagStop) {
		this.flagStop = flagStop;
	}
	
}


