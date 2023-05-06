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
 * 创建文件夹1
 * 
 * @author wanghuan
 * @email 874317631@qq.com
 * @date 2020-02-23
 */
public class Main {
	public static void main(String[] args) throws AWTException {
		JFrame jf = new JFrame("在当前位置创建文件夹");
		jf.setSize(600, 250);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.setResizable(false);
		
		JPanel panel = new JPanel(new FlowLayout());
		
		// 创建一个 5 行 10 列的文本区域
		final JTextArea textArea = new JTextArea(5, 35);
		// 设置自动换行
		textArea.setLineWrap(true);
		textArea.setFont(new Font(null, Font.PLAIN, 20));
		// 添加到内容面板
		panel.add(textArea);
		JLabel label = new JLabel();
        label.setText("请输入文件夹名字或多级路径，如：src\\main\\java                                                  ");
        label.setFont(new Font(null, Font.PLAIN, 15));  // 设置字体，null 表示使用默认字体
        label.setForeground(Color.red);
        panel.add(label);
		// 创建一个提交按钮，点击按钮获取输入文本
		JButton btn = new JButton("提交");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 System.out.println("提交: " + textArea.getText());
				String path = textArea.getText();
				if(path.substring(0, 2).contains(":")||path.contains(".")||path.substring(0, 2).contains("：")) {
					JOptionPane.showMessageDialog(panel, "文件夹路径不合法，包含\":\"等信息","警告",JOptionPane.WARNING_MESSAGE);
					return;
				}
				boolean res = mkdirs(path);
				if(res) {
					JOptionPane.showMessageDialog(panel, "创建完成！","提示",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(panel, "创建失败！","警告",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panel.add(btn);
		// 创建一个提交按钮，点击按钮获取输入文本
		JButton btnc = new JButton("清空");
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
	 * 创建文件夹或多级文件夹
	 * @param path 多级路径或文件夹名字
	 * @return
	 * @author wanghuan  
	 * @date 2020-02-23 
	 */
	public static boolean mkdirs(String path) {
		path = path.startsWith("/")||path.startsWith("\\")? path : "\\" + path;
		path.replace("/", "\\");
//		File file = new File(System.getProperty("user.dir"));
		// 在当前位置上级目录创建
//		String dirPath = file.getParent() + path;
		String dirPath = System.getProperty("user.dir") + path;
		System.out.println(dirPath);
		File dir = new File(dirPath);
		return dir.mkdirs();
	}
}
