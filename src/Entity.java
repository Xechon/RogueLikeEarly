import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;

/**
 * Created by Xechon on 7/4/2014.
 */
public class Entity {
    public Sprite sprite;
    public Room room;
    Rectangle bounds = new Rectangle();
    Area hitbox = new Area();
    double rotationX, rotationY;

    public Point target;

    public Entity(double x, double y, Room room){
        sprite = new Sprite(x,y);
        setSpriteByFilename(getClass().getSimpleName());
        this.room = room;
    }

    public void act(ArrayList<Entity> entities){
        rotationX = bounds.getCenterX();
        rotationY = bounds.getCenterY();

        for(Entity e: entities){
            if(intersects(e.hitbox)){
                System.out.println(e.getClass().getSimpleName());
            }
        }
        //check for collision
        //do stuff
        //update image
    }

    public void draw(Graphics2D g2){
        g2.draw(hitbox);
        g2.draw(bounds);
    }

    public void move(double xSpeed, double ySpeed){
        moveTo(getDecimalX() + xSpeed, getDecimalY() + ySpeed);
    }

    public void move(double speed){
        move(speed*Math.cos(getAngle()), speed*Math.sin(getAngle()));
    }

    public void moveTo(double x, double y){
        sprite.x = x;
        sprite.y = y;
        bounds.setLocation(getX(),getY());
        hitbox.transform(getRotation());
    }

    public boolean intersects(Area other){
        return false;
    }

    /*
    Getter Methods
     */

    public Sprite getSprite(){
        return sprite;
    }

    public int getX(){
        return sprite.getX();
    }

    public int getY(){
        return sprite.getY();
    }

    public double getDecimalX(){
        return sprite.x;
    }

    public double getDecimalY(){
        return sprite.y;
    }

    public double getAngle(){
        return sprite.angle;
    }

    public AffineTransform getRotation(){
        return sprite.at;
    }

    /*
    Setter Methods
     */

    public void setAngle(Point p, double anchorX, double anchorY){
        if(p != null) {
            double deltaX = p.x - anchorX;
            double deltaY = p.y - anchorY;
            sprite.angle = Math.atan2(deltaY, deltaX);
            getRotation().setToRotation(getAngle(), anchorX, anchorY);
        }
    }

    public void setAngle(Point p){
        if(p != null) {
            double deltaX = p.x - rotationX;
            double deltaY = p.y - rotationY;
            setAngle(Math.atan2(deltaY, deltaX));
        }
    }

    public void setAngle(double angle){
        sprite.angle = angle;
        getRotation().setToRotation(getAngle(), rotationX, rotationY);
    }

    public void setSpriteByFilename(String filename){
        sprite.setSpriteByFilename(filename,getClass());
        bounds = new Rectangle(getX(),getY(),sprite.width, sprite.height);
    }
}
