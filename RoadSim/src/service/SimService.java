package service;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dto.MapNode;
import dto.SimDto;
import entity.Car;
import entity.Light;
import entity.Target;
import util.CalcUtil;
import util.DoublePoint;
/**
 * 
 * @author Reece
 * @description 仿真系统的一些逻辑
 * @date 2015年10月16日上午10:51:44
 */
public class SimService implements ServiceInterface{
	
	protected SimDto dto;
	
	protected Random rand = new Random();

	protected final static int MIN_SPEED = 10;
	/**
	 * 斜方向上的单位速度分量
	 */
	protected final static double SQRT = 0.7071;
	/**
	 * 速度
	 */
	protected final static double SPEED = 0.6;
	/**
	 * 方向数组
	 */
	public final static int[][] targetArray = new int[][]{
		{7, 6, 5},
		{0, -1, 4},
		{1, 2, 3}
	};
	protected final static int MAX_NUM= 9999;
	protected static int count = 0;
	
	/**
	 * 
	 * @author Reece
	 * @description 初始化汽车列表 
	 * @date 2015年10月16日下午2:52:13
	 */
	public void initCarList(){
		List<Car> carList = this.dto.getCarList();

		for(int i = 0; i < this.dto.getMaxNum(); i++){
			//随机生成汽车的类型和  最终目的地 启始地
			Car car = new Car(i);
			planCarLife(car);
			car.setLife(false);
			carList.add(car);
		}
	}
    /**
     * 
     * @author Reece
     * @description 初始化地图配置  得到出生地节点表 和目的地节点表
     * @date 2015年10月16日下午4:17:53
     */
    public void initMap(){
    	List<Integer> birthList = new ArrayList<Integer>();
    	List<Integer> destList = new ArrayList<Integer>();
    	
    	for(MapNode node:this.dto.getNode()){
    		int type = node.getType() ;
    		if(type == 4){
    			birthList.add(node.getCode());
    		}else if (type == 2) {
				destList.add(node.getCode());
			}
    	}
    	this.dto.setBirthList(birthList);
    	this.dto.setDestList(destList);
    }
    /**
     * 
     * @author Reece
     * @description 初始化红绿灯信息
     * @date 2015年10月16日下午8:20:36
     */
    public void initLight(){
    	List<Light> lightList = this.dto.getLightList();
    	for(Light light:lightList){
    		light.setHRGY(0);
    		light.setWRGY(1);
    		light.setCountH(Light.getTableTime()[light.getHRGY()]);
    		light.setCountW(Light.getTableTime()[light.getWRGY()]);
    	}
    }
    /**
     * 
     * @author Reece
     * @description 所有汽车移动一下
     * @date 2015年10月16日下午8:35:55
     */
	public void carRun() {
		
		count += 1;
		List<Car> carList = this.dto.getCarList();
		for(Car car:carList){
			//判断车的生命状态
			if(car.isLife()){
				if(count % car.getSpeed() == 0){
					double newX = car.getMoveXY().x + car.getPoint().x;
					double newY = car.getMoveXY().y + car.getPoint().y;
					//目标坐标
					Point destPoint = this.dto.getNode().get(car.getDest()).getPoint();
		 			//判断是否到达目标
					if(((int)(newX) == destPoint.x )&& ((int)(newY) == destPoint.y)){
						MapNode node = this.dto.getNode().get(car.getDest());
						if((node.getType() == 0 || node.getType() == 3) && getNextWay(car.getDest(),car.getFinalDest()) == node.getFaceChild()){
							for(Light light:this.dto.getLightList()){
								int faceCode = node.getFaceChild();
								
								if(faceCode == light.getNodeCodeHA() ||faceCode == light.getNodeCodeHB()){
									if(light.getHRGY() != 1){
										car.setFlagStop(true);
									}
									else{
										car.setFlagStop(false);
										
									}
								}else if(faceCode == light.getNodeCodeWA() ||faceCode == light.getNodeCodeWB()){
									if(light.getWRGY() != 1){
										car.setFlagStop(true);
										
									}else{
										car.setFlagStop(false);
									}
								}
							}
						}
						if(car.getDest() == car.getFinalDest()){
							//System.out.println( Integer.toString(car.getCode())+"Dead");
							//到达就先设置死亡
							car.setLife(false);
							planCarLife(car);
						}else{
							if(!car.isFlagStop()){
								//汽车信息读取和修改的同步锁
								synchronized(car){
									//重新设置目标
									car.setHome(car.getDest());
									car.setPoint(new DoublePoint(this.dto.getNode().get(car.getHome()).getPoint()));
									car.setDest(getNextWay(car));
									car.setTg(calcTarget(car));
									car.setMoveXY(calcMoveXY(car));
								}
							}
							//System.out.println(car.getHome()+" "+car.getDest());
						}
		 			}else if(canMove(car, newX, newY, carList)){
		 				//车移动
		 				car.move();
		 			}
				}
				
			}else{
				if(count % dto.getMaxNum() == 0){
					if(canMove(car, car.getPoint().x, car.getPoint().y, carList)){
						car.setLife(true);
						//System.out.println( Integer.toString(car.getCode())+"Refresh!");
					}
				}
			}
		}	
		count = (count > MAX_NUM ?0:count);
	}
	/**
	 * 
	 * @author Reece
	 * @description 料理车的投胎事宜
	 * @date 2015年10月21日下午7:05:35
	 * @param car 已死亡的小车
	 */
	public void planCarLife(Car car){
		List<Integer> birthList = this.dto.getBirthList();
		List<Integer> destList = this.dto.getDestList();
		
		int type = rand.nextInt(6);
		car.setType(type);
		car.setHome(birthList.get(rand.nextInt(birthList.size())));
		car.setFinalDest(destList.get(type));
		//设置目标
		car.setDest(getNextWay(car));
		//计算放置小车的坐标
		Point point = dto.getNode().get(car.getHome()).getPoint();
		//放置
		car.setPoint(new DoublePoint(point.x, point.y));
		//设置方向
		car.setTg(this.calcTarget(car));
		//设置速度
		car.setSpeed(rand.nextInt(MIN_SPEED )+2);
		//设置x y 移动速度
		car.setMoveXY(this.calcMoveXY(car));
	}
	/**
	 * 
	 * @author Reece
	 * @description 所有灯计数
	 * @date 2015年10月16日下午8:36:04
	 */
	public void lightRun() {
		List<Light> lightList = this.dto.getLightList();
		for(Light light:lightList){
			if(light.getCountH() == 0){
				light.HChange();
			}
			if(light.getCountW() == 0){
				light.WChange();
			}
			light.shining();
		}
	}
	/**
	 * 
	 * @author Reece
	 * @description 判断小车移动后是否会撞到其他小车 
	 * @date 2015年10月18日上午11:13:30
	 * @param theCar  本小车
	 * @param carList 其他小车
	 * @return 是否会能移动
	 */
    public  boolean canMove(Car theCar, Double newX, Double newY,List<Car> carList){
    	boolean flagHead = true;
    	
    	DoublePoint oldTheCarPoint = theCar.getPoint();
    	int size = this.dto.SIZE;
    	
    	if(theCar.isFlagStop()){
    		return false;
    	}
    	for(Car car:carList){
    		//排除本小车
    		if(!car.equals(theCar) && car.isLife()){
    			//得到两车坐标
    			
        		DoublePoint oldCarPoint = car.getPoint();
        		//如果两车已经撞了
        		if(Math.abs(oldTheCarPoint.x - oldCarPoint.x)  < size && Math.abs(oldTheCarPoint.y - oldCarPoint.y) < size ){
        			//当前车向前挪动半个车长 还相撞吗
        			if((Math.abs(oldTheCarPoint.x + (this.dto.SIZE>>1)*theCar.getTg().x - oldCarPoint.x )  < size && Math.abs(oldTheCarPoint.y + (this.dto.SIZE>>1)*theCar.getTg().y - oldCarPoint.y) < size)){
        				flagHead = false;
        			}
        			
        		}
        		//如果当前车当前安全，而移动后相撞
        		else if(Math.abs(newX - oldCarPoint.x)  < size && Math.abs(newY - oldCarPoint.y) < size){
        			return false;
        		}
    		}
    		
    	}
    	return flagHead;
    }
    /**
     * 
     * @author Reece
     * @description 计算 在此速度下坐标x y 分别需要移动的距离
     * @date 2015年10月18日上午1:38:46
     * @param car 汽车
     * @param speed 速度
     * @return 在此速度下坐标x y 分别需要移动的距离
     */
    public DoublePoint calcMoveXY(Car car){
    	//方向
		double targetX = car.getTg().x;
		double targetY = car.getTg().y;
		//处理45度方向移动时 以此速度前进的距离在x y轴上的分量
		if(targetX == targetY){
			targetX*= SQRT;
			targetY*= SQRT;
		}
		//以此速度前进 其在x y轴上的分量
		return new DoublePoint(targetX, targetY);
    }
    /**
	 * 
	 * @author Reece
	 * @description 计算汽车方向
	 * @date 2015年10月16日下午2:19:28
	 * @return 方向类
	 */
    public Target calcTarget(Car car){
    	Point here = this.dto.getNode().get(car.getHome()).getPoint();
    	Point there = this.dto.getNode().get(car.getDest()).getPoint();
    	int targetX = CalcUtil.compareRetNum(there.x, here.x);
    	int targetY = CalcUtil.compareRetNum(there.y, here.y);
    	return new Target(targetX - 1, targetY - 1, targetArray[targetX][targetY]);
    }
    /**
     * 
     * @author Reece
     * @description 计算目的点在当前点哪个方向
     * @date 2015年10月19日上午12:45:21
     * @param x1 目的点x
     * @param y1 目的点y
     * @param x2 当前点x
     * @param y2 当前点y
     * @return 返回方向类
     */
    public Target calcTarget(int x1, int y1, int x2, int y2){
    	int targetX = CalcUtil.compareRetNum(x1, x2);
    	int targetY = CalcUtil.compareRetNum(y1, y2);
    	return new Target(targetX - 1, targetY - 1, targetArray[targetX][targetY]);
    }
    /**
	 * 
	 * @author Reece
	 * @description 测试方法 //TODO 只用于测试  后续将实现函数:根据出生地和最终目的地给出前进序列
	 * @date 2015年10月17日上午12:41:08
	 * @param index
	 * @return 目的地节点
	 */
    public int getNextWay(Car car){
		return this.dto.getMapPath()[car.getHome()][car.getFinalDest()];
	}
    public int getNextWay(int home, int dest){
		return this.dto.getMapPath()[home][dest];
	}
    
	/**
	 * 
	 * @author Reece
	 * @description 弗洛伊德算法求最短路径 
	 * @date 2015年10月21日下午4:08:18
	 */
	public void initPath(){
		
		
		List<MapNode> nodes = this.dto.getNode();
		int nodeNum = nodes.size();
		// 邻接矩阵
		//最短权值和矩阵
		int [][] matrixD = new int[nodeNum][nodeNum];
		//最短路径前驱矩阵
		int [][] matrixP = new int[nodeNum][nodeNum];
		
		//初始化 邻接矩阵 最短权值和矩阵 最短路径前驱矩阵
		for(int i = 0; i < nodeNum; i++){
			for(int j = 0; j < nodeNum; j++){
				if(i == j){
					matrixD[i][j] = 0;
				}else{
					matrixD[i][j] = MAX_NUM;
				}
				matrixP[i][j] = j;
				
			}
		}
		//初始化邻接矩阵
		for(int i = 0; i < nodeNum; i++){
			int j = nodes.get(i).getFaceChild();
			int k = nodes.get(i).getRightChild();
			//如果找到直走节点
			if(j != -1){
				int result = (int) CalcUtil.howLong(nodes.get(j).getPoint(), nodes.get(i).getPoint());
				matrixD[nodes.get(i).getCode()][j] = result;
			}
			//如果找到右走节点
			if(k != -1){
				int result = (int) CalcUtil.howLong(nodes.get(k).getPoint(), nodes.get(i).getPoint());
				matrixD[nodes.get(i).getCode()][k] = result;
			}
			
		}
		//计算最短路径
		for(int i = 0; i < nodeNum; i++){
			for(int j = 0; j < nodeNum; j++){
				for(int k = 0; k < nodeNum; k++){
					if(matrixD[j][k] > matrixD[j][i] + matrixD[i][k]){
						matrixD[j][k] = matrixD[j][i] + matrixD[i][k];
						matrixP[j][k] = matrixP[j][i];
					}
				}
			}
		}
		this.dto.setMapPath(matrixP);
	}
	public SimDto getDto() {
		return dto;
	}

	public void setDto(SimDto dto) {
		this.dto = dto;
	}
	
}
