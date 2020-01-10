package audio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class SoundMainDemo extends Application {

	private Sound sound;/* 注意，我这里是为了防止被JVM的垃圾回收给回收掉 */

	@Override
	public void start(Stage primaryStage) throws Exception {
		/* 直接复制下来没效果可能是因为连接地址失效了 */
		String name="1.mp3";
		File file = new File("mp3/"+name);
        String filePath = file.getAbsolutePath();
        System.out.println(filePath);
        String path = "file:///"+filePath; 
        path="C:/Users/songlb/eclipse-workspace/QiangcaimaoGupiao/mp3/1.mp3";
        System.out.println(path);
		sound = new Sound(path, true);
		sound.loop();/*循环播放*/
	}

	public static void main(String[] args) {
		//launch(args);/* 初始化 */
		
		//File file = new File("C:/Users/songlb/eclipse-workspace/QiangcaimaoGupiao/mp3/1.mp3");
		
		
		String name="1.mp3";
		File file = new File("mp3/"+name);
        String filePath = file.getAbsolutePath();
        System.out.println(filePath);

		
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			BufferedInputStream stream = new BufferedInputStream(fis);
			Player player = new Player(stream);
			player.play();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}