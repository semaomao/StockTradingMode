package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;


public class MainFrame extends JFrame {

	// 定义组件

	private JPanel jp1, jp2;
	private JButton jb1, jb2, jb3, jb4, jb5;

	private JTextArea textArea = new JTextArea(10, 10);
	 private JProgressBar jpb;  

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		MainFrame demo = new MainFrame();

	}

	// 构造函数
	public MainFrame() {

		// 创建按组件

		// JPanel布局默认的是FlowLayOut
		// 设置自动换行
		textArea.setLineWrap(true);

		jp1 = new JPanel();

		jp2 = new JPanel();

		jb1 = new JButton("生成语音");

		jb2 = new JButton("喜马拉雅官网");

		jb3 = new JButton("菠萝");

		jb4 = new JButton("苹果");

		jb5 = new JButton("退出");


		jpb = new JProgressBar();  
        jpb.setMinimum(1);  
        

		// 设置布局

		// 将按钮添加到JPanel

		jp1.add(jb1);

		jp1.add(jb2);

		jp2.add(jb3);

		jp2.add(jb4);

		jp2.add(jb5);

		// 将Panel加入JFrame
		this.add(jp1, BorderLayout.NORTH);
		//this.add(jp2, BorderLayout.SOUTH);
		this.add(jpb,BorderLayout.SOUTH);
		//jpb.setVisible(false);
		
		this.add(textArea, BorderLayout.CENTER);

		// 窗体的设置
		this.setTitle("欢迎使用【喜马拉雅】主播专用工具");
		this.setSize(700, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(200, 200);
		
		String info="  本软件用于图片文字识别后自动生成语音文件\n\n";
		info+="具体用法:\n";
		info+="1、在电脑C盘创建目录 ximalaya_voice,例如: C:\\ximalaya_voice\\   \n";
		info+="2、点击[生成语音] 按钮,将在C:\\ximalaya_voice\\目录下生成mp3文件和相关文字 \n";
		info+="将生成的文件移走,重新在目录里添加要识别的图片重复生成\n";
		info+="\n";
		info+="  如果有任何使用问题或者新的需求可以联系作者,请注明来意,\n作者微信:greenmaomao\n";
		textArea.setText(info);
		 Font x = new Font("Serif",0,20);
		 textArea.setFont(x);
		 jb1.setFont(x);
		 jb2.setFont(x);
		 
		 
		 // 给按钮添加事件处理
		jb1.addActionListener(new ButtonGenerateMp3ActionListener());
		jb2.addActionListener(new ButtonOpenBrowserActionListener());
			
		 

		// 显示
		this.setVisible(true);

	}
	public class ButtonOpenBrowserActionListener implements ActionListener{
		  public void actionPerformed(ActionEvent e) {
			//这个根据一个目录里的多个图片生成多个MP3文件，然后通过MP3合并软件，生成一个MP3
			  try {
				  BrowsFrame.openGUI();
			  }catch(Exception error) {
				  error.printStackTrace();
			  }
		  }
		}
	public class ButtonGenerateMp3ActionListener implements ActionListener{
		  public void actionPerformed(ActionEvent e) {
			new Progress().start();
		  }
		}
	
	class Progress extends Thread{				
		//private int[] num = {1,10,20,30,40,50,60,70,80,90,100};		
		//private JProgressBar bar;		
		//private JButton button;				
		public Progress() 
		{			
			//this.bar = progressBar;			
			//this.num = num;		
			}				
		public void run() {			
			jpb.setStringPainted(true);			
			jpb.setIndeterminate(false);//采用确定的进度条样式		
			jpb.setValue(0);


			//这个根据一个目录里的多个图片生成多个MP3文件，然后通过MP3合并软件，生成一个MP3
			  try {
				String filepath="c:/ximalaya_voice";
				File dir = new File(filepath);
		        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
				System.out.println("total files size:"+files.length);
				
				
				if(files.length==0) {
					JOptionPane.showMessageDialog(null, "文件夹里是空的,你没搞错吧?", "提示信息",JOptionPane.INFORMATION_MESSAGE);
				}else {
					jpb.setMaximum(files.length);  
					jpb.setVisible(true);
				}
				int count=1;
		        for(File file:files) {
		        	String path=file.getAbsolutePath();
		        	
		        	//检查是否是图片
		        	if(path.endsWith("png")||path.endsWith("jpg")||path.endsWith("gif")||path.endsWith("jpeg")||path.endsWith("bmp") ||
		        	path.endsWith("PNG")||path.endsWith("JPG")||path.endsWith("GIF")||path.endsWith("JPEG")||path.endsWith("BMP"))
		        	{
		        		//OcrBaiduUtils.generateMp3(path,false);//批量生产不朗读语音
		        		jpb.setValue(count);
		        	}else {
		        		//JOptionPane.showMessageDialog(null, "放入的图片格式最好是png的后缀名", "INFORMATION_MESSAGE",JOptionPane.INFORMATION_MESSAGE);
		        	}
		        	
		        	count++;
		        }
		        
		        JOptionPane.showMessageDialog(null, "mp3生成完毕!", "提示信息",JOptionPane.INFORMATION_MESSAGE);
		        jpb.setMaximum(files.length);
		        jpb.setMinimum(1);
			  }catch(Exception error) {
				  error.printStackTrace();
				  JOptionPane.showMessageDialog(null, "请检查目录是否创建,你一定是没有好好看使用方法", "错误信息",JOptionPane.ERROR_MESSAGE);
			  }
			
			  
			
			//bar.setString("系统升级结束");			//给按钮添加事件监听器，升级结束后退出系统			button.setText("升级结束");			button.addActionListener(new ActionListener() {								@Override				public void actionPerformed(ActionEvent e) {					// TODO Auto-generated method stub					System.exit(0);				}			});					}			}
		}
	}


	



}
