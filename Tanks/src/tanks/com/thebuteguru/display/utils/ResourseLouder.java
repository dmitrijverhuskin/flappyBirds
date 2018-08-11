
package tanks.com.thebuteguru.display.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ResourseLouder {
    
    public static final String PATH = "res/";
    
    public static BufferedImage loadImage(String fileName){
        BufferedImage image = null;
        
        try{
            image = ImageIO.read(new File(PATH + fileName));
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
}
