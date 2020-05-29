package game;

import javax.swing.JFrame;

/**
 * 游戏窗口类
 * @author Administrator
 *
 */
public class GameFrame extends JFrame{
	
	public GameFrame() {
		/*窗口基础设置*/
		setSize(432,644);//设置窗口大小（和背景图一致）
		setLocationRelativeTo(null);//设置窗口在屏幕居中显示
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置点击窗口按钮，即可关闭程序的进程，不会后台运行
		setTitle("飞翔的小鸟 2.0");//设置窗口标题
		setIconImage(Tools.getImg("../image/0.png"));
	}
	
	/*主方法：程序的入口,即往窗口里添加东西*/
	public static void main(String[] args) {
		GameFrame frame = new GameFrame();//申明窗口对象
		GamePanel panel = new GamePanel();//声明面板对象
		frame.add(panel);//将游戏面板加入到游戏窗口当中
		frame.setResizable(false);//设置不可改变大小
		frame.setVisible(true);//窗口篇设置为可见
	}
}
