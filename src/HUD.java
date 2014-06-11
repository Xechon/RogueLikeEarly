import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Xechon on 6/11/2014.
 */
public class HUD {
    public BufferedImage display;

    public HUD(){
       try{
           display = ImageIO.read(new File("Sprites/hud.png"));
       }catch (IOException e){
           e.printStackTrace();
       }
    }

    public void draw(Graphics2D g2){
        g2.setTransform(new AffineTransform());
        g2.drawImage(display,0,0,Main.DESIRED_WIDTH, Main.DESIRED_HEIGHT, null);
    }
}
