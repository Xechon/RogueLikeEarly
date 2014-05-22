import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
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
    public int x;
    public int y;
    private int imgWidth = Main.DESIRED_WIDTH/Room.COLUMNS;
    private int imgHeight = Main.DESIRED_HEIGHT/Room.ROWS;

    private double angle = 0;
    private AffineTransform at = new AffineTransform();

    public Rectangle hitbox;
    public Shape viewbox;

    public Actor target;

    public Actor(int i, int j){
        y = i*imgHeight;
        x = j*imgWidth;
        sprite = new BufferedImage(imgWidth,imgHeight, BufferedImage.TYPE_3BYTE_BGR);
        hitbox = new Rectangle((int)(x + 1.5*Room.COLUMNS) ,(int)(y + 1.5*Room.ROWS),Main.DESIRED_WIDTH/(2*Room.COLUMNS),Main.DESIRED_HEIGHT/(2*Room.ROWS));
        viewbox = new Ellipse2D.Double(x - (250 + imgWidth/2),y - (250 + imgHeight/2),500,500);
    }
    public void draw(Graphics2D g2) {
        g2.setTransform(at);
        g2.drawImage(sprite,x,y,null);
        g2.draw(hitbox);
        g2.draw(viewbox);
    }
    public void setSprite(BufferedImage spr){
       sprite = spr.getScaledInstance(imgWidth,imgHeight, Image.SCALE_FAST);
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
        at.setToRotation(angle + Math.PI/2, x + (imgWidth)/2 , y + (imgHeight)/2);
        hitbox = new Rectangle((int)(x + 1.5*Room.COLUMNS) ,(int)(y + 1.5*Room.ROWS),Main.DESIRED_WIDTH/(2*Room.COLUMNS),Main.DESIRED_HEIGHT/(2*Room.ROWS));
        viewbox = new Ellipse2D.Double(x - (250 - imgWidth/2),y - (250 - imgHeight/2),500,500);
    }

    public void setAngle(Point p){
        int deltaY = p.y - (y + imgHeight/2);
        int deltaX = p.x - (x + imgWidth/2);
        angle = Math.atan2(deltaY, deltaX);
    }

    public void interact(){

    }

    public void move(int xSpeed, int ySpeed){
        x += xSpeed;
        y += ySpeed;
    }

    //Move to AI
    public void getTarget(Actor[][] things){
        for(Actor[] a: things){
            for(Actor b: a){
                if(b instanceof Player && viewbox.intersects(b.hitbox)){
                    target = b;
                    return;
                }
            }
        }
        target = null;
    }
}
