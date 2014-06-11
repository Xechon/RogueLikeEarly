import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Xechon on 4/10/14.
 */
public class Player extends Actor {
    //Animation stuff. Maybe Actor should have default methods for these.
    public boolean first;
    public String normal = "sprite1.png";
    public String act = "sprite2.png";

    public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    public Knife knife = new Knife(this);

    private Point mouseLocation = new Point();

    public Player(int i, int j, Room room){
        super(i,j, room);
        first = false;
        setSpriteByFilename(normal);
    }

    @Override
    public void act(){
        super.act(); //recalculates hitbox, viewbox, and angle
        setAngle(mouseLocation);
        move(Controller.xMove, Controller.yMove);
        for(Bullet bullet: bullets) {
            if (bullet != null) {
                bullet.act();
            }
        }
    }

    @Override
    public void draw(Graphics2D g2){
        super.draw(g2);
        for(Bullet bullet: bullets) {
            if (bullet != null) {
                bullet.draw(g2);
            }
        }
        if(acting){
            knife.draw(g2);
        }
    }

    public void shoot(){
        setSpriteByFilename((first) ? normal : act);
        if(!first) {
            bullets.add(new Bullet(this));
        }
        first = !first;
    }

    public void stab(){
        if(!acting) {
            knife.stab();
            acting = true;
        }
        else{
            setSpriteByFilename(normal);
            knife.stab();
        }
    }

    @Override
    public void getTarget(Actor[][] things){
        //player's get target could be a lockon feature like in hotline miami, middle click to always aim there.
    }

    public void setMouseLocation(Point p){
        mouseLocation = p;
    }
}
