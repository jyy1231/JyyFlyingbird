package game;

import javax.swing.JFrame;

/**
 * ��Ϸ������
 * @author Administrator
 *
 */
public class GameFrame extends JFrame{
	
	public GameFrame() {
		/*���ڻ�������*/
		setSize(432,644);//���ô��ڴ�С���ͱ���ͼһ�£�
		setLocationRelativeTo(null);//���ô�������Ļ������ʾ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���õ�����ڰ�ť�����ɹرճ���Ľ��̣������̨����
		setTitle("�����С�� 2.0");//���ô��ڱ���
		setIconImage(Tools.getImg("../image/0.png"));
	}
	
	/*����������������,������������Ӷ���*/
	public static void main(String[] args) {
		GameFrame frame = new GameFrame();//�������ڶ���
		GamePanel panel = new GamePanel();//����������
		frame.add(panel);//����Ϸ�����뵽��Ϸ���ڵ���
		frame.setResizable(false);//���ò��ɸı��С
		frame.setVisible(true);//����ƪ����Ϊ�ɼ�
	}
}
