import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
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
    private double angle = 0;
    private AffineTransform at = new AffineTransform();
    public Actor(int i, int j){
        y = i*Main.DESIRED_HEIGHT/Room.ROWS;
        x = j*Main.DESIRED_WIDTH/Room.COLUMNS;
        sprite = new BufferedImage(Main.DESIRED_HEIGHT/Room.ROWS,Main.DESIRED_WIDTH/Room.COLUMNS, BufferedImage.TYPE_3BYTE_BGR);
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(sprite,x,y,null);
    }
    public void setSprite(BufferedImage spr){
       Image spriteImage = spr.getScaledInstance(Main.DESIRED_WIDTH/Room.COLUMNS,Main.DESIRED_HEIGHT/Room.ROWS, Image.SCALE_FAST);
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
        at.rotate(angle, x + (Main.DESIRED_WIDTH/Room.COLUMNS)/2 , y + (Main.DESIRED_HEIGHT/Room.ROWS)/2);
    }
    public void setAngle(int x2, int y2){
        int deltaY = y2 - y + (Main.DESIRED_HEIGHT/Room.ROWS)/2;
        int deltaX = x2 - x + (Main.DESIRED_WIDTH/Room.COLUMNS)/2;
        if(deltaX != 0){
            angle = Math.atan(deltaY/deltaX);
        }
        else{
            angle = 0;
        }
    }
    public void interact(){

    }
    public void move(int xSpeed, int ySpeed){
        x += xSpeed;
        y += ySpeed;
    }
}
