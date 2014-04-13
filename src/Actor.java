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
    private boolean debug  = true;

    public Actor target;

    public Actor(int i, int j){
        y = i*imgHeight;
        x = j*imgWidth;
        sprite = new BufferedImage(imgHeight,imgWidth, BufferedImage.TYPE_3BYTE_BGR);
        hitbox = new Rectangle((int)(x + 1.5*Room.COLUMNS) ,(int)(y + 1.5*Room.ROWS),Main.DESIRED_WIDTH/(2*Room.COLUMNS),Main.DESIRED_HEIGHT/(2*Room.ROWS));
        viewbox = new Ellipse2D.Double(x - (250 + imgWidth/2),y - (250 + imgHeight/2),500,500);
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(sprite,x,y,null);
        if(debug){
            g2.draw(hitbox);
            g2.draw(viewbox);
        }
        g2.setTransform(at);
    }
    public void setSprite(BufferedImage spr){
       Image spriteImage = spr.getScaledInstance(imgWidth,imgHeight, Image.SCALE_FAST);
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
        at.rotate(angle, x + (imgWidth)/2 , y + (imgHeight)/2);
        hitbox = new Rectangle((int)(x + 1.5*Room.COLUMNS) ,(int)(y + 1.5*Room.ROWS),Main.DESIRED_WIDTH/(2*Room.COLUMNS),Main.DESIRED_HEIGHT/(2*Room.ROWS));
        viewbox = new Ellipse2D.Double(x - (250 - imgWidth/2),y - (250 - imgHeight/2),500,500);
    }

    public void setAngle(int x2, int y2){
        int deltaY = y2 - (y + imgHeight/2);
        int deltaX = x2 - (x + imgWidth/2);
        if(deltaX != 0){
            angle = Math.atan(deltaY/deltaX);
        }
    }

    public void interact(){

    }

    public void move(int xSpeed, int ySpeed){
        x += xSpeed;
        y += ySpeed;
    }

    public void getTarget(Actor[][] things){
        for(Actor[] a: things){
            for(Actor b: a){
                if(b instanceof Player && viewbox.intersects(b.hitbox)){
                    target = b;
                    break;
                }
            }
        }
    }
}
