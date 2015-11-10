package service;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import dto.MapNode;
import entity.Car;
import util.DoublePoint;

/**
 * @filename TestDrawCar.java
 * @author Reece 
 * @description  测试绘制汽车 SimService的一个测试  在所有节点随机生成车 车在节点的选择也随机 
 * @date 2015年10月15日下午1:06:14
 * @version 1.0
 */
public class TestDrawCar extends SimService{
	@Override
	public void initCarList(){
		//测试 获取地图节点数据是否常  汽车绘制是否正常 汽车方向计算是否正确   
		List<Car> carList = dto.getCarList();
		int len = dto.getNode().size();
		for(int i = 0; i < len; i++){
			int num = dto.getNode().get(i).getRightChild();
			if(num == -1){
				num = dto.getNode().get(i).getFaceChild();
			}
			Car car = new Car(i);
			car.setType(i%6);
			car.setHome(i);
			car.setFinalDest(-99);
			car.setDest(this.getNextWay(car));
			Point point = dto.getNode().get(car.getHome()).getPoint();
			car.setPoint(new DoublePoint(point.x, point.y));
			car.setTg(this.calcTarget(car));
			car.setSpeed(rand.nextInt(10)+1);
			car.setMoveXY(this.calcMoveXY(car));
			car.setLife(true);
			carList.add(car);
		}
	}
	@Override
	public int getNextWay(Car car) {
		MapNode node =  this.dto.getNode().get(car.getHome());
		//return node.getRightChild() != -1?node.getRightChild():node.getFaceChild();
		if(node.getFaceChild() != -1 && node.getRightChild() != -1){
			return new Random().nextBoolean()?node.getRightChild():node.getFaceChild();
		}
		else if(node.getFaceChild() != -1){
			return node.getFaceChild();
		}else{
			return node.getRightChild();
		}
	}
	public void planCarLife(Car car){
		
	}
}
