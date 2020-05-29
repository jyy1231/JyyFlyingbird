package game;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 移动柱子类
 * @author Administrator
 *
 */
public class Column {
	int x,y,w,h;//柱子的位置和宽高
	BufferedImage img;//柱子图片对象
	int distance = 300;//两个柱子距离
	int gap;//柱子中间可以通过的间隙的高度
	Random ran = new Random();//要使柱子形成错落的不齐的，所以使用随机数来实现，声明随机数对象
	
	/*柱子构造器，因为有两根柱子，所以传入一个参数来分别不同的柱子*/
	public Column(int i) {
		img = Tools.getImg("../image/column.png");//得到柱子图片
		w = img.getWidth()/2;//由于图片有点大，所以得到它宽高的一半
		h = img.getHeight()/2;
		x = 300 + distance*(i-1);
		y = -ran.nextInt(h+146-644+1);//通过计算得到
		gap = 95;
	}
	
	/*柱子移动方法*/
	public void move(){
		x--;
		if(x < -w){
			x = 300 + distance;
			y = -ran.nextInt(h+146-644+1);
		}
	}
}
