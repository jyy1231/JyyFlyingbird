package game;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * �ƶ�������
 * @author Administrator
 *
 */
public class Column {
	int x,y,w,h;//���ӵ�λ�úͿ��
	BufferedImage img;//����ͼƬ����
	int distance = 300;//�������Ӿ���
	int gap;//�����м����ͨ���ļ�϶�ĸ߶�
	Random ran = new Random();//Ҫʹ�����γɴ���Ĳ���ģ�����ʹ���������ʵ�֣��������������
	
	/*���ӹ���������Ϊ���������ӣ����Դ���һ���������ֱ�ͬ������*/
	public Column(int i) {
		img = Tools.getImg("../image/column.png");//�õ�����ͼƬ
		w = img.getWidth()/2;//����ͼƬ�е�����Եõ�����ߵ�һ��
		h = img.getHeight()/2;
		x = 300 + distance*(i-1);
		y = -ran.nextInt(h+146-644+1);//ͨ������õ�
		gap = 95;
	}
	
	/*�����ƶ�����*/
	public void move(){
		x--;
		if(x < -w){
			x = 300 + distance;
			y = -ran.nextInt(h+146-644+1);
		}
	}
}
