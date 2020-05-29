package game;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 工具类，用于获取图片
 * @author Administrator
 *
 */
public class Tools {
	public static final Toolkit Tool_Kit = Toolkit.getDefaultToolkit();//获取工具类对象，用于使用自定义的鼠标样式
	
	/*定义获取图片资源的方法*/
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
