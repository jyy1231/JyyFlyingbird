package game;

import java.awt.image.BufferedImage;

/**
 * 移动地面类
 * @author Administrator
 *
 */
public class Ground {
	int x,y,w,h;//地面的坐标和宽高
	BufferedImage img;//地面图片对象
	
	/*地面对象构造器,设置地面的初始属性*/
	public Ground() {
		img = Tools.getImg("../image/ground.png");
		h = img.getHeight();//得到图片的宽高
		w = img.getWidth();
		x = 0;//设置地面放置的位置
		y = 644 - h;
	}
	
	/*地面移动方法*/
	public void move() {
		if(x <= -(w - 432)) x=0;
		x--;
	}
}
