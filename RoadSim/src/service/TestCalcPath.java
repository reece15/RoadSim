package service;

import java.awt.Point;
import java.util.List;
import entity.Car;
import util.DoublePoint;

/**
 * @filename TestCalcPath.java
 * @author Reece
 * @description  测试计算路径（弗洛伊德算法求最短路径） 
 * @date 2015年10月21日下午5:19:36
 * @version 1.0
 */
public class TestCalcPath  extends SimService{
	
	static int count2 = 0;
	@Override
	public void initCarList(){
		List<Car> carList = this.dto.getCarList();
		
		Car car = new Car(0);
		planCarLife(car);
		car.setLife(true);
		carList.add(car);
	}
	
	@Override
	public int getNextWay(Car car){
		return this.dto.getMapPath()[car.getHome()][car.getFinalDest()];
	}
	@Override 
	public void planCarLife(Car car){
		List<Integer> birthList = this.dto.getBirthList();
		List<Integer> destList = this.dto.getDestList();
		
		if (count2 >= 36) {
			count2 = 0;
		}
		car.setType(0);
		car.setHome(birthList.get(count2/6));
		car.setFinalDest(destList.get(count2%6));
		car.setDest(this.dto.getMapPath()[birthList.get(count2/6)][destList.get(count2%6)]);
		Point point = dto.getNode().get(car.getHome()).getPoint();
		car.setPoint(new DoublePoint(point.x, point.y));
		car.setTg(this.calcTarget(car));
		car.setSpeed(3);
		car.setMoveXY(this.calcMoveXY(car));
		count2 += 1;
		//car.setLife(true);
	}
}
