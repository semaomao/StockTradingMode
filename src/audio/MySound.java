package audio;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MySound extends Thread {
	 
    private Player player;
    private String musicName;
 
    /*加载音乐*/
    public MySound(String musicName) {
        this.musicName = musicName;
    }
 
    /*播放音乐*/
    public void play() {
        try {
            /*我这里的这一种写法要求音频文件要和这个类在同一个包里面*/
            new Player(getClass().getResourceAsStream(musicName)).play();
        } catch (JavaLayerException ex) {
           // Logger.getLogger(MySound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    /*循环播放音乐*/
    public void loop() {
        this.start();
    }
 
    @Override
    public void run() {
        while (true) {
            try {
                new Player(getClass().getResourceAsStream(musicName)).play();
            } catch (JavaLayerException ex) {
               // Logger.getLogger(MySound.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
 
}