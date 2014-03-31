import com.sun.imageio.plugins.common.ImageUtil;
import sun.awt.image.ToolkitImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Dustin
 * Date: 3/30/14
 * Time: 4:01 PM
 */
public abstract class Actor {
    private Image sprite;
    private int x;
    private int y;
    public Actor(int i, int j){
        y = i*Main.DESIRED_HEIGHT/Room.ROWS;
        x = j*Main.DESIRED_WIDTH/Room.COLUMNS;
        sprite = new BufferedImage(Main.DESIRED_HEIGHT/Room.ROWS,Main.DESIRED_WIDTH/Room.COLUMNS, BufferedImage.TYPE_3BYTE_BGR);
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(sprite,x,y,null);
    }
    public void setSprite(BufferedImage spr){
       Image spriteImage = spr.getScaledInstance(Main.DESIRED_WIDTH/Room.COLUMNS,Main.DESIRED_HEIGHT/Room.ROWS, Image.SCALE_SMOOTH);
       sprite = spriteImage;
    }
    public void setSpriteByFilename(String filename){
        BufferedImage temp = null;
        try{
            temp = ImageIO.read(new File("Sprites/" +filename));
            setSprite(temp);
            //System.out.println(temp instanceof BufferedImage);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void act() {

    }
}
