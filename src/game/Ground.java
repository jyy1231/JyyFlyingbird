package game;

import java.awt.image.BufferedImage;

/**
 * �ƶ�������
 * @author Administrator
 *
 */
public class Ground {
	int x,y,w,h;//���������Ϳ��
	BufferedImage img;//����ͼƬ����
	
	/*�����������,���õ���ĳ�ʼ����*/
	public Ground() {
		img = Tools.getImg("../image/ground.png");
		h = img.getHeight();//�õ�ͼƬ�Ŀ��
		w = img.getWidth();
		x = 0;//���õ�����õ�λ��
		y = 644 - h;
	}
	
	/*�����ƶ�����*/
	public void move() {
		if(x <= -(w - 432)) x=0;
		x--;
	}
}
