
package graphics;

import java.awt.image.BufferedImage;
import tanks.com.thebuteguru.display.utils.ResourseLouder;

public class TecstureAtlas {
    
    BufferedImage image;
    
    public TecstureAtlas(String imageName){
        
        image = ResourseLouder.loadImage(imageName);
        
    }
    
    public BufferedImage cut(int x, int y, int w, int h){
        return image.getSubimage(x, y, w, h);
        
    }
    
}
