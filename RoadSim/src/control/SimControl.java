package control;

import dao.MapData;
import dto.SimDto;
import service.ServiceFactory;
import service.SimService;
import ui.FrameMain;
import ui.PanelMain;
/**
 * @author Reece
 * @description 主窗体类
 * @date 2015年10月15日下午12:08:57
 * @version 1.0
 */
public class SimControl {
	
	//默认panel
	private PanelMain panel;
	//主窗体
	@SuppressWarnings("unused")
	private FrameMain frame;
	//数据传输对象
	private SimDto dto;
	//业务逻辑
	private SimService service;
	//得到地图信息
	private MapData mapInter;

	
	public SimControl(){
		//安装数据传输对象
		this.dto = new SimDto();
		//安装业务逻辑 
		this.service = ServiceFactory.creator(0);
		this.service.setDto(dto);
		//安装数据接口对象   从文件获得地图信息
		this.mapInter = new MapData();
		//装载地图 红绿灯信息
		this.dto.setNode(mapInter.loadMap());
		this.dto.setLightList(mapInter.loadLight());
		//安装界面
		this.panel = new PanelMain(dto, this);
		this.frame = new FrameMain(this.panel);
		
		//启动仿真
		this.go();
	}
	public void go(){
		Thread sim = new Thread(new Runnable() {			
			@Override
			public void run() {
				//程序仿真线程
					//初始化地图信息
					service.initMap();
					//初始化路径
					service.initPath();
					//初始化汽车列表
					service.initCarList();
					//初始化红绿灯信息
					service.initLight();
					//界面刷新线程
					Thread threadPanel = new Thread(panel);
					//灯的线程
					Thread threadLight = new Thread(){
						@Override
						public void run() {
							while(!dto.isFlagOver()){
								service.lightRun();
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
						}
					};
					//车的线程
					Thread threadCar = new Thread(){
						@Override
						public void run() {
							while(!dto.isFlagOver()){
								service.carRun();
								
								try {
									Thread.sleep(1);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					};
					//启动线程
					threadPanel.start();
					threadLight.start();
					threadCar.start();
				}
				
		});
		//启动仿真线程
		sim.start();
	}
}
