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
 * ��Ϸ�����
 * @author Administrator
 *
 */
public class GamePanel extends JPanel{
	/*����һЩȫ�ֵĶ���*/
	BufferedImage bg;//����ͼ����
	Ground ground;//�������
	Column column1,column2;//���Ӷ���
	Bird bird;//С�����
	boolean start;//��Ϸ׼��״̬
	boolean gameOver;//��Ϸ����״̬
	int score;//��Ϸ�÷�
	/*��Ϸ���������*/
	public GamePanel() {
		Image imgCursor = new ImageIcon(Tools.getImg("../image/mouse.png")).getImage();//������Ϸ�������ʽ
		Point hotSpot = new Point(18,16);//���������Ч�ĵ㣬�����ͼƬ��λ��
		Cursor cursor = Tools.Tool_Kit.createCustomCursor(imgCursor, hotSpot, "myCursor");//�����ϵ�����������һ����������
		setCursor(cursor);//�������������ʽ
		
		bg = Tools.getImg("../image/bg.png");//�������õ�ͼƬ
		ground = new Ground();//��ʼ������
		column1 = new Column(1);//��ʼ������
		column2 = new Column(2);
		bird = new Bird();//��ʼ��С��
		//��ʼ��״̬
		start = false;
		gameOver = false;
		score = 0;
		
		/*ʵ�������һ������һ�Σ���Ҫ������������*/
		MouseAdapter adapter = new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(start == false){
					start = true;
					start();
				}
				else if(gameOver == true){
					//���³�ʼ�����
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
		this.addMouseListener(adapter);//�������������������
	}
	
	
	
	/*��ͼ��������������л������*/
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bg, 0, 0, null);//��������ͼ
		g.drawImage(column1.img,column1.x,column1.y,column1.w,column1.h,null);//��������
		g.drawImage(column2.img,column2.x,column2.y,column2.w,column2.h,null);
		g.drawImage(ground.img,ground.x,ground.y,null);//��������
		g.drawImage(bird.img,bird.x,bird.y,bird.w,bird.h,null);//������
		
		//��Ϸ��ʼʱ
		if(start==false){
			g.drawImage(Tools.getImg("../image/start.png"), 0, 0,null);
		}
		//��Ϸ����
		if(gameOver==true){
			g.drawImage(Tools.getImg("../image/gameover.png"), 0, 0,null);
		}
		
		Font f = new Font("����",200,30);//����������󣬲���С
		g.setFont(f);
		g.setColor(Color.red);
		g.drawString("�÷� ��"+score, 30, 50);//ָ��λ�ú�����
	}
	
	/*��Ϸ��ʼ����*/
	public void start(){
		MyThread mt = new MyThread();//ʹ���߳�
		Thread t = new Thread(mt);
		t.start();//�����start���̵߳���������
	}
	
	/*Ϊ��ʵ��С���ڷɵ�ͬʱ��������С�������Ϸɣ���Ҫ�����̣߳���ʵ�֣����߻�������*/
	class MyThread implements Runnable{

		@Override
		public void run() {
			while(true){
				ground.move();//�����ƶ�
				column1.move();//�����ƶ�
				column2.move();
				bird.fly();//С���ȶ����
				bird.move();
				
				//��ײ���
				boolean bool1 = bird.hit();
				boolean bool2 = bird.hit(column1);
				boolean bool3 = bird.hit(column2);
				if(bool1||bool2||bool3){
					gameOver = true;
					break;
				}
				
				//�����Ӽӷ�
				if(bird.x==column1.x+column1.w||bird.x==column2.x+column2.w){
					score++;
				}
				
				try {
					Thread.sleep(20);//�ӳ�20����
					repaint();//ˢ��ҳ��
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
}
