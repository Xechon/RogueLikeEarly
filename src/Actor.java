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
    public Image sprite;
    public double x, y;
    protected int tileWidth = Main.SCREEN_WIDTH/Room.COLUMNS;
    protected int tileHeight = Main.SCREEN_HEIGHT/Room.ROWS;

    int width, height;

    int scale = 1;

    public int health = 3;
    public double speed;

    public boolean acting;

    public Room room;

    public double angle = 0;
    public AffineTransform at = new AffineTransform();

    public Rectangle hitbox = new Rectangle();
    public Shape viewbox = new Rectangle();

    public Actor target;

    public Item[] inventory = new Item[10];
    public Item heldItem;
    public Item highlightedItem;

    public Actor(){

    }

    public Actor(double x, double y, Room room){
        this.x = x;
        this.y = y;
        this.room = room;
        setSpriteByFilename(getClass().getSimpleName());
        viewbox = new Ellipse2D.Double(x - (250 + width/2),y - (250 + height/2),500,500);
    }

    public Actor(int i, int j, Room room){
        y = i*tileHeight;
        x = j*tileWidth;
        this.room = room;
        setSpriteByFilename(getClass().getSimpleName());
        viewbox = new Ellipse2D.Double(x - (250 + width/2),y - (250 + height/2),500,500);
    }

    public void draw(Graphics2D g2) {
        g2.setTransform(at);
        g2.drawImage(sprite,(int)x,(int)y,null);
        g2.draw(hitbox);
        g2.draw(viewbox);
        at.setToIdentity();
    }

    public void setSprite(BufferedImage spr){
        sprite = spr.getScaledInstance(spr.getWidth()*scale, spr.getHeight()*scale, Image.SCALE_FAST);
        if(width == 0) {
            width = sprite.getWidth(null);
            height = sprite.getHeight(null);
            hitbox = new Rectangle((int)x, (int)y, width, height);
        }
    }

    public void setSpriteByFilename(String filename){
        BufferedImage temp;
        try{
            temp = ImageIO.read(new File("Sprites/" + filename + ".png"));
            setSprite(temp);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void act() {
        at.setToRotation(angle - Math.PI/2, x + (width)/2 , y + (height)/2);
        hitbox.setLocation((int)x, (int)y);
        viewbox = new Ellipse2D.Double(x - (250 - width/2),y - (250 - height/2),500,500);
        if(heldItem != null) {
            heldItem.moveTo(hitbox.getCenterX(), hitbox.getCenterY());
            heldItem.setAngle(angle);
            heldItem.move(1.5 * width);
        }
    }

    public void setAngle(Point p){
        double deltaY = p.y - (y + height/2);
        double deltaX = p.x - (x + width/2);
        angle = Math.atan2(deltaY, deltaX);
    }

    public void setAngle(double angle){
        this.angle = angle;
    }

    public void interact(){
        if(heldItem != null) {
            toss();
        }
        if(viewbox != null && highlightedItem != null && viewbox.intersects(highlightedItem.hitbox)){
            //putInInventory(heldItem);
            heldItem = highlightedItem;
        }
    }

    public void move(double xSpeed, double ySpeed){
        x += xSpeed;
        y += ySpeed;
    }

    public void move(double speed){
        move(speed*Math.cos(angle), speed*Math.sin(angle));
    }

    public void takeDamage(int amt){
        health -= amt;
        if(health <= 0){
            room.queueRemove(this);
        }
    }

    public boolean hitboxCollide(Actor other){
        if (!(other instanceof BlankActor) && hitbox.intersects(other.hitbox)) {
            return true;
        }
        return false;
    }

    public Actor getCollision() {
        for (Actor a : room.actList) {
            if (hitboxCollide(a)) {
                return a;
            }
        }
        return null;
    }

    public void putInInventory(Item item){
        for(int i = 0; i < inventory.length; i++){
            if(inventory[i] == null){
                inventory[i] = item;
                if(item == heldItem){
                    heldItem = null;
                }
                return;
            }
        }
        //GUI.notify("Not enough space.");
    }

    public void moveTo(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void use(){
        if(heldItem != null) {
            heldItem.use();
        }
    }

    public void toss(){
        if(heldItem != null){
            heldItem.speed += 100;
            heldItem.toss();
            heldItem = null;
        }
    }
}
