package game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * С���ƶ���
 * @author Administrator
 *
 */
public class Bird {
	BufferedImage img;//С��ͼƬ����
	int x,y,w,h;//С���λ���Լ����
	List<BufferedImage> list;//С�����ɺܶ���ͼƬ��ͣ�л���ʵ�ַ����Ч���ģ����������Ǹ�һ�����϶���
	//ʹ��ģ�������˶���ʵ��С��������˶�
	double v0;//ģ�����һ�µĳ��ٶ�
	double s;//ģ�������ľ���
	double t;//ģ��ʱ��
	double g;//ģ������
	
	public Bird() {
		img = Tools.getImg("../image/0.png");//�õ�С��ͼƬ����һ��
		w = img.getWidth()/2;//�õ�С��Ŀ�͸�
		h = img.getHeight()/2;
		x = 100;//������Ϸһ��ʼС���λ��
		y = 200;
		list = new ArrayList<BufferedImage>();//list�д�źܶ��ͼƬ��ʹ����һ��ͼƬ���������
		for(int i=0;i<8;i++){//��ͼƬ���뵽��ֵ��
			list.add(Tools.getImg("../image/"+i+".png"));
		}
		
		//��ʼ��ģ�����
		v0 = 15;
		t = 0.25;
		s = 0;
		g = 4.5;
	}
	
	/*С��ɿ�����ķ���*/
	int index = 0;
	public void fly(){
		img = list.get(index%list.size());
		index++;
	}
	
	/*С�������˶�����*/
	public void move(){
		s = v0 * t;//���׵ľ���
		y = (int)(y-s);//���׽����ǵĸ߶�
		double v2 = v0 - g * t;//����ĳ��ٶ�
		v0 = v2;//������ʹ���λ������ף�֮��ͻ�Խ��Խ��
	}
	
	/*С�����Ϸ��跽��*/
	public void moveUp(){
		v0 = 15;
	}
	
	/*С��ײ�����ڵײ��򶥲�ʱ������Ϸ����*/
	public boolean hit(){
		if(y<=0||y>=644-146-h){
			return true;
		}
		return false;
	}
	
	/*С��ײ����*/
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
