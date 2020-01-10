import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/***
 * http://www.e512.net/tqeditor/demo.htm  不错的在线html编辑器
 * @author songlb
 *
 */
public class Main {
	private static boolean DEBUG=true;//打包模式还是开发模式
	private JInternalFrame internalLeftFrame ,internalFrame ;

	private JPanel pNorth,pSouth,subMenuContainer;
	private JScrollPane pCenter;
	private JEditorPane editorPane ;
	private JButton[] subBtnArray_STUDY = null;//交易模式子菜单数组
	

	private JLabel label;
	private Container container = null;

	
	private PageUrlThread game;
	
	private String APP_NAME="股票交易模式训练软件V0.2";
	private String FRAME_LEFT_NAME="功能导航";
	private String FRAME_RIGHT_NAME="欢迎来到股票交易模式训练大本营, 这里又很多模式学习爱好者和全职交易者, 关注微信公共号:抢财猫, 波哥微信:greenmaomao";
	
	private String STRING_ROOT="软件功能目录";
	private String STRING_NODE_ERRORS="股市新手必看";
	private String STRING_NODE_INTRO="交易模式介绍";
	private String STRING_NODE_STUDY="交易基础学习";
	private String STRING_NODE_SOFTWARE="交易软件使用";
	private String STRING_NODE_TRAIN="交易模式训练";
	private String STRING_NODE_CHART="交易模式交流";
	private String STRING_NODE_ABOUTME="软件说明";
	
	
	private String[]  STRING_ARRAY_MODE_STYUDY=new String[] {
			"1前言",
			"2K线是个啥",
			"3K线有啥用",
			"4K线周期是个什么鬼",
			"5红K阳线_T字线",
			"5红K阳线_大阳线",
			"5红K阳线_倒锤子字线",
			"5红K阳线_纺锤阳线",
			"5红K阳线_光脚上影线阳线",
			"5红K阳线_光头光脚阳线",
			"5红K阳线_光头下影线阳线",
			"5红K阳线_上吊阳线",
			"5红K阳线_十字线",
			"5红K阳线_一字线",
			"6黑K阴线_大阴线",
			"6黑K阴线_倒T字阴线",
			"6黑K阴线_倒锤子字阴线",
			"6黑K阴线_纺锤阴线",
			"6黑K阴线_光脚上影线阴线",
			"6黑K阴线_光头光脚阴线",
			"6黑K阴线_光头下影线阴线",
			"6黑K阴线_上吊阴线",
			"6黑K阴线_十字线",
			"6黑K阴线_一字线",
			"7K线组合结构_晨星",
			"7K线组合结构_反拖线",
			"7K线组合结构_贯穿线",
			"7K线组合结构_迫切线",
			"7K线组合结构_入首线",
			"7K线组合结构_上舍子线",
			"7K线组合结构_吞噬线",
			"7K线组合结构_下舍子线",
			"7K线组合结构_夜星",
			"7K线组合结构_孕育线",
			"8K线形态_M头",
			"8K线形态_V型反转",
			"8K线形态_W底部形态",
			"8K线形态_岛型反转",
			"8K线形态_旗形和菱形",
			"8K线形态_三角形",
			"8K线形态_头肩底",
			"8K线形态_头肩顶",
			"8K线形态_箱体反转",
			"8K线形态_楔形",
			"8K线形态_圆弧底",
			"8K线形态_圆弧顶",
			"9K线与成交量_成交量是个啥？",
			"9K线与成交量_量价关系_量价齐升",
			"9K线与成交量_量价关系_量价齐降",
			"9K线与成交量_量价关系_量价背离",
			"10K线与均线系统_均线系统是个啥？",
			"10K线与均线系统_均线系统实战",
			"11K线与MACD指标系统_MACD指标是个啥？",
			"11K线与MACD指标系统_MACD指标 实战",
			"12多空指标_聪明人和大傻猫",
			"13 50分位箱体指标_高低位切换",
			"14黄金分割指标_比比谁是硬汉",
			"15突破指标_过高和下降趋势指标",
			"16K线与趋势线_趋势线是个啥？",
			"16K线与趋势线_趋势线实战",
			//"17趋势的中继和转折_缺口是个啥？",
			//"17趋势的中继和转折_趋势的中继是个啥？",
			//"17趋势的中继和转折_趋势的转折是个啥？",
			//"18K线与轨道线_轨道线是个啥？",
			//"18K线与轨道线_轨道线实战",
			//"19K线与波浪理论_波浪理论是个啥？",
			//"19K线与波浪理论_波浪理论实战"
			}; 
	


	
	private String[]  STRING_ARRAY_SOFTWARE=new String[] {
			"前言",
			"软件准备工作",
			"学会看主要指数",
			"学会看板块指数",
			"学会看个股_集合竞价",
			"学会看个股_K线",
			"学会看个股_分时",
			"学会看个股_盘口",
			"学会看个股_个股资料",
			"学会看个股_指标设定",
			"学会股票池_创建",
			"学会股票池_导入",
			"学会股票池_导出",
			"学会股票池_使用(初级颜色标注)",
			"学会选股策略_创建",
			"学会选股策略_使用",
			"学会策略股票池_使用(高级)",
			"学会自定义指数_创建",
			"学会对比指数_跟踪",
			"学会预警_公式编写",
			"学会预警_设定",
			"学会预警_使用",
			"学会定制版面_设定",
			"更高阶的功能(策略回测,训练模式)"
	};
	
	private Color COLOR_NODE_BG=Color.CYAN;//导航菜单的背景色
    
	private void init() {
		try {
		JFrame jf = new JFrame();
        //jf.setLocationRelativeTo(null);
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        jf.setTitle(APP_NAME); //设置标题处的文字
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗体关闭时的操作 退出程序

        int width = Toolkit.getDefaultToolkit().getScreenSize().width; //得到当前屏幕分辨率的高
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;//得到当前屏幕分辨率的宽
        jf.setSize((int)width,(int)height);//设置大小
        jf.setLocation(0,0); //设置窗体居中显示
        jf.setResizable(true);//禁用最大化按钮


        // 创建 桌面面板
        JDesktopPane desktopPane = new JDesktopPane();

        // 创建 内部窗口
        JInternalFrame internalLeftFrame = createInternalLeftFrame(330,height,0,0);
        JInternalFrame internalRightFrame = createInternalRightFrame(width-330,height,330,0);
        // 添加 内部窗口 到 桌面面板
        desktopPane.add(internalLeftFrame);
        desktopPane.add(internalRightFrame);

        // 把 桌面面板 作为 内容面板 设置到窗口并显示
        jf.setContentPane(desktopPane);
        jf.setVisible(true);

        try {
            // 设置 内部窗口 被选中
        	internalLeftFrame.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
    public static void main(String[] args) {
        new Main().init();
    }

    
    private void initTree(Container container) {
    	this.container=container;
    	
    	//创建根节点和子节点
    			DefaultMutableTreeNode root = new DefaultMutableTreeNode(this.STRING_ROOT);
    			
    			
    			DefaultMutableTreeNode node1 = new DefaultMutableTreeNode(this.STRING_NODE_INTRO);
    			DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(this.STRING_NODE_ERRORS);
    			DefaultMutableTreeNode node3 = new DefaultMutableTreeNode(this.STRING_NODE_STUDY);
    			
    			DefaultMutableTreeNode node4 = new DefaultMutableTreeNode(this.STRING_NODE_SOFTWARE);
    			DefaultMutableTreeNode node5 = new DefaultMutableTreeNode(this.STRING_NODE_TRAIN);
    			DefaultMutableTreeNode node6 = new DefaultMutableTreeNode(this.STRING_NODE_CHART);
    			DefaultMutableTreeNode node7 = new DefaultMutableTreeNode(this.STRING_NODE_ABOUTME);
    			//利用根节点创建TreeModel
    			DefaultTreeModel treeModel = new DefaultTreeModel(root);
    			//插入子节点node1,node2,node3,node4,node5
    			treeModel.insertNodeInto(node1,root,root.getChildCount());
    			treeModel.insertNodeInto(node2,root,root.getChildCount());
    			treeModel.insertNodeInto(node3,root,root.getChildCount());
    			treeModel.insertNodeInto(node4,root,root.getChildCount());
    			treeModel.insertNodeInto(node5,root,root.getChildCount());
    			treeModel.insertNodeInto(node6,root,root.getChildCount());
    			treeModel.insertNodeInto(node7,root,root.getChildCount());
    			
    			DefaultMutableTreeNode leafnode = null;
    			
    			//创建节点node2的子节点并插入
/*    			File file = new File("mp3/errors");
    			if(file.exists()) {
    				File[] files=file.listFiles();
					for(File errorF:files) {
						leafnode = new DefaultMutableTreeNode(errorF.getName());
						treeModel.insertNodeInto(leafnode,node2,node2.getChildCount());
					}
				}else {
					System.out.println("mp3 dir not found!");
				}*/
    			


    			
    			
    			//创建节点node3的子节点并插入

    			
    	        for(int i=0;i<subBtnArray_STUDY.length;i++){
        			leafnode=new DefaultMutableTreeNode(this.STRING_ARRAY_MODE_STYUDY[i]);
        			treeModel.insertNodeInto(leafnode,node3,node3.getChildCount());
    	        }
    	        
    	        
    			//创建节点node4的子节点并插入

    			
    	        for(int i=0;i<STRING_ARRAY_SOFTWARE.length;i++){
        			leafnode=new DefaultMutableTreeNode(this.STRING_ARRAY_SOFTWARE[i]);
        			treeModel.insertNodeInto(leafnode,node4,node4.getChildCount());
    	        }
    	        


    	 
    			//创建节点node5的子节点并插入
    			leafnode = new DefaultMutableTreeNode("交易模式选择");
    			treeModel.insertNodeInto(leafnode,node5,node5.getChildCount());
    			leafnode = new DefaultMutableTreeNode("交易笔记");
    			treeModel.insertNodeInto(leafnode,node5,node5.getChildCount());
    			leafnode = new DefaultMutableTreeNode("交易计划");
    			treeModel.insertNodeInto(leafnode,node5,node5.getChildCount());
    			leafnode = new DefaultMutableTreeNode("交割单");
    			treeModel.insertNodeInto(leafnode,node5,node5.getChildCount());
    	 
    			//创建树对象
    			JTree tree = new JTree(treeModel);
    			//设置Tree的选择为一次只能选择一个节点
    			tree.getSelectionModel().setSelectionMode(
    								TreeSelectionModel.SINGLE_TREE_SELECTION);
    			//注册监听器
    			tree.addTreeSelectionListener(new TreeSelectionListener() {

					@Override
					public void valueChanged(TreeSelectionEvent event) {
						
						try {
						JTree tree = (JTree)event.getSource();
						//获取目前选取的节点
						DefaultMutableTreeNode selectionNode =
							(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
						String nodeName = selectionNode.toString();
						label.setText("你当前选取的节点为： "+nodeName);
						System.out.println("你当前选取的节点为： "+nodeName);
						internalLeftFrame.setTitle(nodeName);
						
						if(nodeName.equals(STRING_NODE_INTRO)) {
							//创建网页线程对象
							game = new PageUrlThread("intro.htm");
							game.cancel(true);
							game.execute();

							//setUrlHtml("player_demo.html");
						}else if(nodeName.equals(STRING_NODE_CHART)) {
							//创建网页线程对象
							PageUrlThread game = new PageUrlThread("chart.html");
							game.cancel(true);
							game.execute();
						}else if(nodeName.equals(STRING_NODE_ABOUTME)) {
							//创建网页线程对象
							PageUrlThread game = new PageUrlThread("aboutme.html");
							game.cancel(true);
							game.execute();
						}else if(nodeName.equals(STRING_NODE_ERRORS)) {
							//创建网页线程对象
							PageUrlThread game = new PageUrlThread("newhand.html");
							game.cancel(true);
							game.execute();
						}else if(nodeName.equals("交易笔记")) {
							//创建网页线程对象
							PageUrlThread game = new PageUrlThread("train_note.html");
							game.cancel(true);
							game.execute();
						}else if(nodeName.equals("交易计划")) {
							//创建网页线程对象
							PageUrlThread game = new PageUrlThread("train_plan.html");
							game.cancel(true);
							game.execute();
						}else if(nodeName.equals("交割单")) {
							//创建网页线程对象
							PageUrlThread game = new PageUrlThread("train_order.html");
							game.cancel(true);
							game.execute();
						}else if(nodeName.equals("交易模式选择")) {
							//创建网页线程对象
							PageUrlThread game = new PageUrlThread("train_choose.html");
							game.cancel(true);
							game.execute();
						}else {
							//判断是否是K线职业教程里的子页面
							
							for(int i=0;i<STRING_ARRAY_MODE_STYUDY.length;i++) {
								if(nodeName.equals(STRING_ARRAY_MODE_STYUDY[i])) {
									//创建网页线程对象
									PageUrlThread game = new PageUrlThread("lesson/"+nodeName+".html");
									game.cancel(true);
									game.execute();
									
									break;
								}
							}
						}
						}catch(Exception e) {
							e.printStackTrace();
						}
						
					}});
    	 
    			tree.setRowHeight(20);
    	 
    			//创建节点绘制对象
    			DefaultTreeCellRenderer cellRenderer =
    								(DefaultTreeCellRenderer)tree.getCellRenderer();
    	 
    			//设置字体
    			cellRenderer.setFont(new Font("Serif",Font.PLAIN,14));
    			cellRenderer.setBackgroundNonSelectionColor(Color.white);
    			cellRenderer.setBackgroundSelectionColor(Color.yellow);
    			cellRenderer.setBorderSelectionColor(Color.GRAY);
    	 
    			//设置选或不选时，文字的变化颜色
    			cellRenderer.setTextNonSelectionColor(Color.black);
    			cellRenderer.setTextSelectionColor(Color.blue);
    			
    			//把树对象添加到内容面板
    			container.setLayout(new BorderLayout(5,5));
    			

    	 
    			//创建标签
    			label = new JLabel("你当前选择的节点为：",JLabel.CENTER);
    			label.setFont(new Font("Serif",Font.PLAIN,14));
    			//container.add(label,BorderLayout.CENTER);

    			//container.add("East",new JEdit("West \n <br/> east"));
    			//container.add("South",new JButton("West \n south"));
    			//container.add("West",  new JButton("West \n west"));

    				final JButton btn = new JButton("");
    				if(DEBUG) {
    					btn.setIcon(new ImageIcon("html/img/letchart.png"));
    				}else {
    					//打成jar包必须使用下面的方法
	    				java.net.URL imgURL = this.getClass().getClassLoader().getResource("html/img/letchart.png");
	    				System.out.println("imgURL path:"+imgURL.getPath());
	    				//this.internalLeftFrame.setTitle("imgURL:"+imgURL.getPath());
	    				btn.setIcon(new ImageIcon(imgURL));
    				}
    				
					container.add("South",  btn);

    			container.add("Center",new JScrollPane(tree));
    }
    
    
    /***
     * 创建左边窗体
     * @param width
     * @param height
     * @param loc_x
     * @param loc_y
     * @return
     */
    private  JInternalFrame createInternalLeftFrame(int width,int height,int loc_x,int loc_y) {
        // 创建一个内部窗口
    	internalLeftFrame = new JInternalFrame(
    			FRAME_LEFT_NAME,  // title
                true,       // resizable
                true,       // closable
                true,       // maximizable
                true        // iconifiable
        );

        // 设置窗口的宽高
    	internalLeftFrame.setSize(width, height);
        // 设置窗口的显示位置
    	internalLeftFrame.setLocation(loc_x, loc_y);
        // 内部窗口的关闭按钮动作默认就是销毁窗口，所有不用设置
        // internalFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // 创建内容面板
        JPanel panel = new JPanel();

        
      /*  node_intro_btn=new JButton(STRING_NODE_INTRO);
        node_intro_btn.setBackground(COLOR_NODE_BG);
        node_study_btn=new JButton(STRING_NODE_STUDY);
        node_study_btn.setBackground(COLOR_NODE_BG);
        node_train_btn=new JButton(this.STRING_NODE_TRAIN);
        node_train_btn.setBackground(COLOR_NODE_BG);
        

        
        node_chart_btn=new JButton(this.STRING_NODE_CHART);
        node_chart_btn.setBackground(COLOR_NODE_BG);
        node_chart_aboutme=new JButton(this.STRING_NODE_ABOUTME);
        node_chart_aboutme.setBackground(COLOR_NODE_BG);
        
        
        
        node_intro_btn.addActionListener(new ActionHandler());
        node_study_btn.addActionListener(new ActionHandler());
        node_chart_btn.addActionListener(new ActionHandler());
        node_chart_aboutme.addActionListener(new ActionHandler());*/
        
        pNorth=new JPanel();
        pNorth.setLayout(new GridLayout(3,1));
        pSouth=new JPanel();
        pSouth.setLayout(new GridLayout(2,1));
        subMenuContainer=new JPanel();
        subMenuContainer.setLayout(new GridLayout(STRING_ARRAY_MODE_STYUDY.length,1));
     
        //添加子菜单
        subBtnArray_STUDY=new JButton[STRING_ARRAY_MODE_STYUDY.length];        
        for(int i=0;i<subBtnArray_STUDY.length;i++){
        	subBtnArray_STUDY[i]=new JButton(STRING_ARRAY_MODE_STYUDY[i]);
        	subBtnArray_STUDY[i].setBackground(Color.WHITE);
        }
        
        
        panel.setLayout(new BorderLayout());
        
/*        pNorth.add(node_intro_btn);
        pNorth.add(node_study_btn); 
        pNorth.add(node_train_btn); */
        
        for(int i=0;i<subBtnArray_STUDY.length;i++){
            subMenuContainer.add(subBtnArray_STUDY[i]);
        }
        
        
        pCenter=new JScrollPane(subMenuContainer);
        
/*        pSouth.add(node_chart_btn);
        pSouth.add(node_chart_aboutme);*/
        //panel.add(pNorth,"North");
        //panel.add(pCenter,"Center");
        //panel.add(pSouth,"South");
       
        
        
        
        //this.setVisible(true);
        //this.setSize(500,600);
        //this.setResizable(false);
        //this.setLocationRelativeTo(null);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initTree(panel);

        // 设置内部窗口的内容面板
        internalLeftFrame.setContentPane(panel);

        /*
         * 对于内部窗口，还可以不需要手动设置内容面板，直接把窗口当做普通面板使用，
         * 即直接设置布局，然后通过 add 添加组件，如下代码:
         *     internalFrame.setLayout(new FlowLayout());
         *     internalFrame.add(new JLabel("Label001"));
         *     internalFrame.add(new JButton("JButton001"));
         */

        // 显示内部窗口
        internalLeftFrame.setVisible(true);

        return internalLeftFrame;
    }
    
/*	private void close() {
		System.out.println("=close==");
		pNorth.setLayout(new GridLayout(3,1));
        pNorth.remove(node_chart_btn);
        pNorth.remove(node_chart_aboutme);
        
        pSouth.add(node_chart_btn);
        pSouth.add(node_chart_aboutme);
        
        for(int i=0;i<subBtnArray_STUDY.length;i++){
            subMenuContainer.add(subBtnArray_STUDY[i]);
        }     
        internalLeftFrame.validate();
        internalLeftFrame.getContentPane().repaint();
        expand=false;
	}
	
	
	private void open() {
		for(int i=0;i<subBtnArray_STUDY.length;i++){
            subMenuContainer.remove(subBtnArray_STUDY[i]);
        }
        pSouth.removeAll();
        pNorth.setLayout(new GridLayout(5,1));
        pNorth.add(node_chart_btn);
        pNorth.add(node_chart_aboutme);
        pNorth.repaint();
        pCenter.repaint();
        pSouth.repaint();
        internalLeftFrame.validate();
        internalLeftFrame.getContentPane().repaint();
        expand=true;
	}*/
    
        
   
    /**	 * 字符串转换成Color对象	 * @param colorStr 16进制颜色字符串	 * @return Color对象	 * */	
    public static Color toColorFromString(String colorStr){		
    	colorStr = colorStr.substring(4);		
    	Color color =  new Color(Integer.parseInt(colorStr, 16)) ;		
    	//java.awt.Color[r=0,g=0,b=255]		
    	return color;	
    }


    private  JInternalFrame createInternalRightFrame(int width,int height,int loc_x,int loc_y) {
        // 创建一个内部窗口
        internalFrame = new JInternalFrame(
        		FRAME_RIGHT_NAME,  // title
                true,       // resizable
                true,       // closable
                true,       // maximizable
                true        // iconifiable
        );

        // 设置窗口的宽高
        internalFrame.setSize(width, height);
        // 设置窗口的显示位置
        internalFrame.setLocation(loc_x, loc_y);
        // 内部窗口的关闭按钮动作默认就是销毁窗口，所有不用设置
        // internalFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // 创建内容面板
/*        JPanel panel = new JPanel();

        // 添加组件到面板
        panel.add(new JLabel("Label001"));
        panel.add(new JButton("JButton001"));*/
        
        BorderLayout borderLayout = new BorderLayout();
        JPanel panel = new JPanel();
        panel.setLayout(borderLayout);
        
        
        editorPane = new JEditorPane();  
        //editorPane.setBackground(toColorFromString("#E3EDCD"));
        //放到滚动窗格中才能滚动查看所有内容  
        JScrollPane scrollPane = new JScrollPane();  
        scrollPane.setBackground(Color.BLACK);
        scrollPane.getViewport().add(editorPane,borderLayout);
        //设置是显示网页 html 文件,可选项  
        editorPane.setContentType("text/html");  
        //设置成只读，如果是可编辑，你会看到显示的样子也是不一样的，true显示界面  
        editorPane.setEditable(false);  

        
      //设置垂直水平滚动条时刻显示
        scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

       
        //要能响应网页中的链接，则必须加上超链监听器  
        //editorPane.addHyperlinkListener(this);  
        
        
        
        panel.add(scrollPane);
        
        
        
        // 设置内部窗口的内容面板
        internalFrame.setContentPane(panel);

        /*
         * 对于内部窗口，还可以不需要手动设置内容面板，直接把窗口当做普通面板使用，
         * 即直接设置布局，然后通过 add 添加组件，如下代码:
         *     internalFrame.setLayout(new FlowLayout());
         *     internalFrame.add(new JLabel("Label001"));
         *     internalFrame.add(new JButton("JButton001"));
         */

        // 显示内部窗口
        internalFrame.setVisible(true);

        return internalFrame;
    }

    
    private  void openUrlHtml(String name) {
    	
    	System.out.println(Thread.currentThread().getContextClassLoader().getResource( " ")); 

    	System.out.println(Main.class.getClassLoader().getResource( " ")); 

    	System.out.println(ClassLoader.getSystemResource( " ")); 
    	System.out.println(Main.class.getResource( " ")); 
    	System.out.println(Main.class.getResource( "/ ")); //Class文件所在路径 
    	System.out.println(new File( "/ ").getAbsolutePath()); 
    	System.out.println(System.getProperty( "user.dir ")); 
    	
    	//internalLeftFrame.setTitle("out:"+name);
    	
    	String path;
    	

    	
    	if(DEBUG) {
    	//File file = new File("html/test1.html");
    	
	    	File file = new File("html/"+name);
	        String filePath = file.getAbsolutePath();
	        System.out.println(filePath);
	        path = "file:///"+filePath; 
	        try {
				editorPane.setPage(path);
				System.out.println(" debug url path:"+path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else {
    		URL url=getClass().getClassLoader().getResource("html/"+name);
        	path=url.getPath();
        	System.out.println(" jar url path:"+path);
        	//internalLeftFrame.setTitle("out1:"+path);
    		//this.internalLeftFrame.setTitle("in1:");
	    	//path="file://"+path+"html/"+name;
	    	System.out.println(" jar url path:"+path);
	    	//this.internalLeftFrame.setTitle("in2:"+path);
	    	try {
				editorPane.setPage(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
        // editorPane.setPage("http://www.baidu.com");  
           System.out.println("read path:"+path+",name:"+name );
             
           editorPane.validate();  
    }
    
    




     class PageUrlThread extends SwingWorker<String, Object> {
    	private   String name;
    	
        public   PageUrlThread(String filename) {
        	name=filename;
		}

		@Override
        public String doInBackground() {
            return "noting";
        }
     
        @Override
        protected void done() {
            try {
            	openUrlHtml(name);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }

	

  
}

