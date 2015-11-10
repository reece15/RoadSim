package dao;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dto.MapNode;
import ui.ImageHome;

/**
 * @filename TestDataInterface.java
 * @author Reece
 * @description   测试数据访问对象
 * @date 2015年10月16日上午12:16:32
 * @version 1.0
 */
public class TestDataInterface {
	public static void main(String[] args){
		
		JFrame jf = new JFrame("TestDataInter");
		jf.setSize(860, 510);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		
		TestPanel jp = new TestPanel();
		jf.setContentPane(jp);
		jf.setVisible(true);
	}
}
/**
 * 
 * @author Reece
 * @description  提供测试的简单panel类
 * @date 2015年10月16日上午12:55:25
 */
@SuppressWarnings("serial")
class TestPanel extends JPanel{
	
	private int x;
	private int y;
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(ImageHome.MAP, 0, 0, null);
		drawPoint(g);
	}
	public void drawPoint(Graphics g){
		List<MapNode> mapNodes = new MapData().loadMap();
		for(MapNode node : mapNodes){
			this.x = (node.getPoint().x);
			this.y = (node.getPoint().y);
			x = (x < 0? x + 10 : x);
			y = (y < 0? y + 10 : y);
			x = (x >= 850? x - 20 : x);
			y = (y >= 480? y - 20 : y);
			g.fillRect (x, y, 10, 10);
		}
		
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
