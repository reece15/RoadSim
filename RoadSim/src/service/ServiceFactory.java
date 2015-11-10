package service;



/**
 * @filename ServiceFactory.java
 * @author Reece
 * @description  service的简单工厂类 主要为方便测试和修改service
 * @date 2015年10月19日下午11:00:56
 * @version 1.1
 */
public class ServiceFactory {

	public static SimService creator(int i) {
		switch (i) {
		case 1:
			return new TestDrawCar();
		case 2:
			return new TestCalcPath();
		case 3:
			return new TestCarSystem();
		default:
			return new SimService();
		}
	}

	
}
