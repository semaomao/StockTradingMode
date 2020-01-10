package ui;


import java.awt.BorderLayout;

import javax.swing.*;

import chrriis.common.UIUtils;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;


public class BrowsFrame extends JPanel {

 

    private JPanel webBrowserPanel;

 

    private JWebBrowser webBrowser;

 

    public BrowsFrame(String url) {

        super(new BorderLayout());

        webBrowserPanel = new JPanel(new BorderLayout());

        webBrowser = new JWebBrowser();

        webBrowser.navigate(url);

        webBrowser.setButtonBarVisible(false);

        webBrowser.setMenuBarVisible(false);

        webBrowser.setBarsVisible(false);

        webBrowser.setStatusBarVisible(false);

        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);

        add(webBrowserPanel, BorderLayout.CENTER);

        //执行Js代码

      //  webBrowser.executeJavascript("alert('关注【抢财猫】并订阅节目,您的支持是我不断前进的最大动力!')");

    }

 

 

    /**

     * 在swing里内嵌浏览器

     * @param url  要访问的url

     * @param title    窗体的标题

     */

    public  static void  openForm(final String url,final String title){

        UIUtils.setPreferredLookAndFeel();

        NativeInterface.open();

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                JFrame frame = new JFrame(title);

                //设置窗体关闭的时候不关闭应用程序

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                frame.getContentPane().add(new BrowsFrame(url), BorderLayout.CENTER);

                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

                frame.setLocationByPlatform(true);

                //让窗体可见

                frame.setVisible(true);

                //重置窗体大小

                frame.setResizable(true);

                // 设置窗体的宽度、高度

                frame.setSize(1400, 700);

                // 设置窗体居中显示

                frame.setLocationRelativeTo(frame.getOwner());

            }

        });

        NativeInterface.runEventPump();

    }

    public static void openGUI() {
    	 openForm("https://www.ximalaya.com/zhubo/71863989/","关注【抢财猫】并订阅节目,您的支持是我不断前进的最大动力!");

    }
 

    public static void main(String[] args) {

        openForm("https://www.ximalaya.com/zhubo/71863989/","关注【抢财猫】并订阅节目,您的支持是我不断前进的最大动力!");

    }

}
