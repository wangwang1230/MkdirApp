package app;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 * �����ļ���1
 * 
 * @author wanghuan
 * @email 874317631@qq.com
 * @date 2020-02-23
 */
public class Main {
	public static void main(String[] args) throws AWTException {
		JFrame jf = new JFrame("�ڵ�ǰλ�ô����ļ���");
		jf.setSize(600, 250);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.setResizable(false);
		
		JPanel panel = new JPanel(new FlowLayout());
		
		// ����һ�� 5 �� 10 �е��ı�����
		final JTextArea textArea = new JTextArea(5, 35);
		// �����Զ�����
		textArea.setLineWrap(true);
		textArea.setFont(new Font(null, Font.PLAIN, 20));
		// ��ӵ��������
		panel.add(textArea);
		JLabel label = new JLabel();
        label.setText("�������ļ������ֻ�༶·�����磺src\\main\\java                                                  ");
        label.setFont(new Font(null, Font.PLAIN, 15));  // �������壬null ��ʾʹ��Ĭ������
        label.setForeground(Color.red);
        panel.add(label);
		// ����һ���ύ��ť�������ť��ȡ�����ı�
		JButton btn = new JButton("�ύ");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 System.out.println("�ύ: " + textArea.getText());
				String path = textArea.getText();
				if(path.substring(0, 2).contains(":")||path.contains(".")||path.substring(0, 2).contains("��")) {
					JOptionPane.showMessageDialog(panel, "�ļ���·�����Ϸ�������\":\"����Ϣ","����",JOptionPane.WARNING_MESSAGE);
					return;
				}
				boolean res = mkdirs(path);
				if(res) {
					JOptionPane.showMessageDialog(panel, "������ɣ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(panel, "����ʧ�ܣ�","����",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panel.add(btn);
		// ����һ���ύ��ť�������ť��ȡ�����ı�
		JButton btnc = new JButton("���");
		btnc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		panel.add(btnc);
		
		jf.setContentPane(panel);
		jf.setVisible(true);
	}
	
	/**
	 * �����ļ��л�༶�ļ���
	 * @param path �༶·�����ļ�������
	 * @return
	 * @author wanghuan  
	 * @date 2020-02-23 
	 */
	public static boolean mkdirs(String path) {
		path = path.startsWith("/")||path.startsWith("\\")? path : "\\" + path;
		path.replace("/", "\\");
//		File file = new File(System.getProperty("user.dir"));
		// �ڵ�ǰλ���ϼ�Ŀ¼����
//		String dirPath = file.getParent() + path;
		String dirPath = System.getProperty("user.dir") + path;
		System.out.println(dirPath);
		File dir = new File(dirPath);
		return dir.mkdirs();
	}
}
