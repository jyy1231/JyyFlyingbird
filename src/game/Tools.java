package game;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * �����࣬���ڻ�ȡͼƬ
 * @author Administrator
 *
 */
public class Tools {
	public static final Toolkit Tool_Kit = Toolkit.getDefaultToolkit();//��ȡ�������������ʹ���Զ���������ʽ
	
	/*�����ȡͼƬ��Դ�ķ���*/
	public static BufferedImage getImg(String path){
		BufferedImage img = null;
		try{
			img = ImageIO.read(Tools.class.getResource(path));
		}catch(IOException e){
			e.printStackTrace();
		}
		return img;
	}
}
