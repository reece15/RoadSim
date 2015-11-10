package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;


import javax.swing.JPanel;

import control.SimControl;
import dto.MapNode;
import dto.SimDto;
import entity.Car;
import entity.Light;
/**
 *  主panel类  实现了绘制小车 绘制红绿灯 绘制地图等方法
 * 
 */
@SuppressWarnings("serial")
public class PanelMain extends JPanel implements MouseMotionListener ,Runnable{
	
	@SuppressWarnings("unused")
	private SimControl ctl;
	
	private SimDto dto;
	//绘制坐标线的坐标 和字体设置
	private Point point = new Point(0, 0);
	private final int FONTH  = 25;
	private final int FONTW  = 110;
	private final int FPS = 50;
	private static int count = 0;
	
	
	public PanelMain(){addAllLister();}
	
	public PanelMain(SimDto dto, SimControl ctl){
		this.dto = dto;
		this.ctl = ctl;
		addAllLister();
	}
	public void addAllLister(){
		this.addMouseMotionListener(this);
	}
	@Override
	public void paint(Graphics g){
		//super.paintComponent(g);
		g.drawImage(ImageHome.MAP, 0, 0, null);
		//drawPoint(g);
		drawLight(g);
		drawCar(g);
	}
	
	private void drawPoint(Graphics g) {
		g.setFont(new Font("微软雅黑", Font.BOLD, 20));
		int x = this.point.x;
		int y = this.point.y;
		g.drawLine(this.point.x, 0, this.point.x, 510);
		g.drawLine(0, this.point.y, 860, this.point.y);
		g.drawString("("+ x +"," + y +")", (x > (FrameMain.WIDTH- FONTW)?x - FONTW:x), (y <FONTH ? y + FONTH:y));
	}

	
	public void drawCar(Graphics g){
		//获得车辆列表
		List<Car> carList = this.dto.getCarList();
		for (Car car:carList){
			if (!car.isLife()) {
				continue;
			}
			//count+=1;
			
			//汽车信息读取和修改的同步锁
			synchronized(car){
				//汽的绘制在panel上的xy坐标 
				int x = (int) (car.getPoint().x - dto.SIZE/2);
				int y = (int) (car.getPoint().y - dto.SIZE/2);
				//汽车类型
				int type = car.getType();
				//汽车方向状态在pic上所在坐标
				int targetX = car.getTg().pic*dto.SIZE;
				int targetY = type*dto.SIZE;
				g.drawImage(ImageHome.CAR, x, y, x + dto.SIZE, y + dto.SIZE, targetX, targetY, targetX + dto.SIZE,targetY + dto.SIZE, null);
			}

		}
		//System.out.println(count);
		//count = 0;
	}
	
	
	public void drawLight(Graphics g){
		g.setFont(new Font("微软雅黑", Font.BOLD, 20));
		//获得红绿灯列表
		List<Light> lightList = this.dto.getLightList();
		List<MapNode> mapNodes = this.dto.getNode();
		for (Light light:lightList){
			//灯绘制在panel上的xy坐标
			Point WAPoint = mapNodes.get(light.getNodeCodeWA()).getPoint();
			Point WBPoint = mapNodes.get(light.getNodeCodeWB()).getPoint();
			Point HAPoint = mapNodes.get(light.getNodeCodeHA()).getPoint();
			Point HBPoint = mapNodes.get(light.getNodeCodeHB()).getPoint();
			g.setColor(Light.COLORTABLE[light.getWRGY()]);
			g.fillOval(WAPoint.x - (dto.SIZE>>1), WAPoint.y -(dto.SIZE>>1), dto.SIZE, dto.SIZE);
			g.fillOval(WBPoint.x - (dto.SIZE>>1), WBPoint.y - (dto.SIZE>>1), dto.SIZE, dto.SIZE);
			g.setColor(Light.COLORTABLE[light.getHRGY()]);
			g.fillOval(HAPoint.x - (dto.SIZE>>1), HAPoint.y - (dto.SIZE>>1), dto.SIZE, dto.SIZE);
			g.fillOval(HBPoint.x - (dto.SIZE>>1), HBPoint.y - (dto.SIZE>>1), dto.SIZE, dto.SIZE);
			g.setColor(new Color(0, 0, 0));
			g.drawString(Integer.toString(light.getCountW()), WAPoint.x- (dto.SIZE>>1) + 3, WAPoint.y+ (dto.SIZE>>1) - 2);
			g.drawString(Integer.toString(light.getCountW()), WBPoint.x- (dto.SIZE>>1) + 3, WBPoint.y+ (dto.SIZE>>1) - 2);
			g.drawString(Integer.toString(light.getCountH()), HAPoint.x- (dto.SIZE>>1) + 3, HAPoint.y+ (dto.SIZE>>1) - 2);
			g.drawString(Integer.toString(light.getCountH()), HBPoint.x- (dto.SIZE>>1) + 3, HBPoint.y+ (dto.SIZE>>1) - 2);
		}
	}
	
	public SimDto getDto() {
		return dto;
	}

	public void setDto(SimDto dto) {
		this.dto = dto;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.point.x = e.getX();
		this.point.y = e.getY();
		
	}

	@Override
	public void run() {
		//每帧图像的时间
		long fpsTime = (long) ((1000/(double)FPS) * 1000000);
		//现在时间
		long nowTime = 0;
		//绘制用时后
		long lastTime = 0;
		
		while(!dto.isFlagOver()){
			nowTime = System.nanoTime();
			repaint();
			lastTime = System.nanoTime() - nowTime;
			if(lastTime > fpsTime){
				continue;
			}
			try {
				Thread.sleep((fpsTime - (System.nanoTime()- nowTime)) / 1000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
