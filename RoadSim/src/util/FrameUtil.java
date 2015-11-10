package util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameUtil {
	/**
	 * @author Reece
	 * @description 窗体居中
	 * @date 2015年10月15日
	 * @param frame
	 */
	public static void setFrameCenter(JFrame frame){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = ((screen.width - frame.getWidth()) / 2);
		int y = ((screen.height - frame.getHeight()) / 2) - 10;
		frame.setLocation(x, y);
	}
}