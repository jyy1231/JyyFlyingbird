package game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * 小鸟移动类
 * @author Administrator
 *
 */
public class Bird {
	BufferedImage img;//小鸟图片对象
	int x,y,w,h;//小鸟的位置以及宽高
	List<BufferedImage> list;//小鸟是由很多张图片不停切换来实现飞翔的效果的，所以声明那个一个集合对象
	//使用模拟落体运动来实现小鸟的落体运动
	double v0;//模拟起飞一下的初速度
	double s;//模拟起跳的距离
	double t;//模拟时间
	double g;//模拟重力
	
	public Bird() {
		img = Tools.getImg("../image/0.png");//得到小鸟图片，第一张
		w = img.getWidth()/2;//得到小鸟的宽和高
		h = img.getHeight()/2;
		x = 100;//设置游戏一开始小鸟的位置
		y = 200;
		list = new ArrayList<BufferedImage>();//list中存放很多的图片，使用了一个图片数组来存放
		for(int i=0;i<8;i++){//将图片加入到数值中
			list.add(Tools.getImg("../image/"+i+".png"));
		}
		
		//初始化模拟参数
		v0 = 15;
		t = 0.25;
		s = 0;
		g = 4.5;
	}
	
	/*小鸟煽动翅膀的方法*/
	int index = 0;
	public void fly(){
		img = list.get(index%list.size());
		index++;
	}
	
	/*小鸟落体运动方法*/
	public void move(){
		s = v0 * t;//上抛的距离
		y = (int)(y-s);//上抛结束是的高度
		double v2 = v0 - g * t;//下落的初速度
		v0 = v2;//这样会使初次会往上抛，之后就会越落越快
	}
	
	/*小鸟向上飞翔方法*/
	public void moveUp(){
		v0 = 15;
	}
	
	/*小鸟撞到窗口底部或顶部时，，游戏结束*/
	public boolean hit(){
		if(y<=0||y>=644-146-h){
			return true;
		}
		return false;
	}
	
	/*小鸟撞柱子*/
	public boolean hit(Column column){
		if(x>=column.x-w&&x<=column.x+column.w){
			if(y>column.h/2+column.y-column.gap/2&&y<column.h/2+column.y+column.gap/2-h){
				return false;
			}
			return true;
		}
		return false;
	}
}
