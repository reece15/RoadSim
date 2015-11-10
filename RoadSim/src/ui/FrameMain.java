package ui;

import javax.swing.JFrame;

import util.FrameUtil;
/** 
 * 主窗体类
 */
@SuppressWarnings("serial")
public class FrameMain extends JFrame{
	
	@SuppressWarnings("unused")
	private PanelMain panel;
	public static final int WIDTH = 865;
	public static final int HEIGHT = 520;
	public FrameMain(PanelMain panel){
		this.panel = panel;
		this.setTitle("交通仿真v1.0");
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		//居中
		FrameUtil.setFrameCenter(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(panel);
		this.setVisible(true);
	}
	

}
