package service;

import java.awt.Point;
import java.util.List;
import entity.Car;
import util.DoublePoint;

/**
 * @filename TestCarSystem.java
 * @author Reece
 * @description  汽车运行系统的总测试
 * @date 2015年10月21日下午7:56:54
 * @version 1.0
 */
public class TestCarSystem extends SimService{
	
	
	@Override
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
	@Override 
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
		car.setSpeed(rand.nextInt(10)+2);
		//设置x y 移动速度
		car.setMoveXY(this.calcMoveXY(car));
	}
	@Override
	public int getNextWay(Car car){
		return this.dto.getMapPath()[car.getHome()][car.getFinalDest()];
	}
}
