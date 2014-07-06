import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Xechon on 6/23/2014.
 */
public class Sprite {

    public Image sprite;
    public double x, y;
    int width, height;
    public double angle = 0;
    public AffineTransform at = new AffineTransform();

    public Sprite(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2){
        at.setToRotation(angle - Math.PI / 2, x + width/2, y + height/2);
        g2.setTransform(at);
        g2.drawImage(sprite, getX(), getY(), null);
        at.setToIdentity();
    }

    public void setSpriteByFilename(String filename, Class c){
        String path = "";
        while (!c.getSimpleName().equals("Entity")) {
            path = c.getSimpleName() + "/" + path;
            c = c.getSuperclass();
        }
        try{
            sprite = ImageIO.read(new File("Sprite/" + path + filename + ".png"));
            width = sprite.getWidth(null);
            height = sprite.getHeight(null);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public int getX(){
        return (int)x;
    }

    public int getY(){
        return (int)y;
    }
}
