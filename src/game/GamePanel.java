package game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 游戏面板类
 * @author Administrator
 *
 */
public class GamePanel extends JPanel{
	/*声明一些全局的对象*/
	BufferedImage bg;//背景图对象
	Ground ground;//地面对象
	Column column1,column2;//柱子对象
	Bird bird;//小鸟对象
	boolean start;//游戏准备状态
	boolean gameOver;//游戏结束状态
	int score;//游戏得分
	/*游戏面板类设置*/
	public GamePanel() {
		Image imgCursor = new ImageIcon(Tools.getImg("../image/mouse.png")).getImage();//设置游戏的鼠标样式
		Point hotSpot = new Point(18,16);//设置鼠标生效的点，在鼠标图片的位置
		Cursor cursor = Tools.Tool_Kit.createCustomCursor(imgCursor, hotSpot, "myCursor");//将以上的设置作用在一个鼠标对象上
		setCursor(cursor);//设置面板的鼠标样式
		
		bg = Tools.getImg("../image/bg.png");//地面对象得到图片
		ground = new Ground();//初始化地面
		column1 = new Column(1);//初始化柱子
		column2 = new Column(2);
		bird = new Bird();//初始化小鸟
		//初始化状态
		start = false;
		gameOver = false;
		score = 0;
		
		/*实现鼠标点击一下上抛一次，需要引入鼠标监听器*/
		MouseAdapter adapter = new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(start == false){
					start = true;
					start();
				}
				else if(gameOver == true){
					//重新初始化组件
					start = false;
					gameOver = false;
					score= 0;
					ground = new Ground();
					column1 = new Column(1);
					column2 = new Column(2);
					bird = new Bird();
					repaint();
				}
				else{
					bird.moveUp();
				}
			}
		};
		this.addMouseListener(adapter);//将鼠标监听器加入面板中
	}
	
	
	
	/*画图方法，即向面板中画入对象*/
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bg, 0, 0, null);//画出背景图
		g.drawImage(column1.img,column1.x,column1.y,column1.w,column1.h,null);//画出柱子
		g.drawImage(column2.img,column2.x,column2.y,column2.w,column2.h,null);
		g.drawImage(ground.img,ground.x,ground.y,null);//画出地面
		g.drawImage(bird.img,bird.x,bird.y,bird.w,bird.h,null);//画出鸟
		
		//游戏开始时
		if(start==false){
			g.drawImage(Tools.getImg("../image/start.png"), 0, 0,null);
		}
		//游戏结束
		if(gameOver==true){
			g.drawImage(Tools.getImg("../image/gameover.png"), 0, 0,null);
		}
		
		Font f = new Font("宋体",200,30);//创建字体对象，并大小
		g.setFont(f);
		g.setColor(Color.red);
		g.drawString("得分 ："+score, 30, 50);//指定位置和内容
	}
	
	/*游戏开始方法*/
	public void start(){
		MyThread mt = new MyThread();//使用线程
		Thread t = new Thread(mt);
		t.start();//这里的start是线程的启动方法
	}
	
	/*为了实现小鸟在飞的同时，点击鼠标小鸟又向上飞，需要引入线程，来实现，两者互不干扰*/
	class MyThread implements Runnable{

		@Override
		public void run() {
			while(true){
				ground.move();//地面移动
				column1.move();//柱子移动
				column2.move();
				bird.fly();//小鸟扇动翅膀
				bird.move();
				
				//碰撞检测
				boolean bool1 = bird.hit();
				boolean bool2 = bird.hit(column1);
				boolean bool3 = bird.hit(column2);
				if(bool1||bool2||bool3){
					gameOver = true;
					break;
				}
				
				//过柱子加分
				if(bird.x==column1.x+column1.w||bird.x==column2.x+column2.w){
					score++;
				}
				
				try {
					Thread.sleep(20);//延迟20毫秒
					repaint();//刷新页面
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
}
