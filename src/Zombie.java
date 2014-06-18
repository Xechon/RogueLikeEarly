import java.awt.*;

/**
 * Created by Dustin on 3/31/14.
 */
public class Zombie extends Actor {
    private boolean first;
    public String normal = "zombie";
    public String act = "zombie_lunge";
    public double speed = 1.5;
    public double range = 200;

    public Zombie(int i, int j, Room room){
        super(i,j, room);
        first = false;
        setSpriteByFilename(normal);
        heldItem = new Gun(x,y,room);
    }

    @Override
    public void act(){
        super.act();

        target = setTarget(room.player);

        if(target != null){
            setAngle(new Point((int)target.hitbox.getCenterX(), (int)target.hitbox.getCenterY()));
            setSpriteByFilename((first) ? normal : act);
            move(speed*Math.cos(angle), speed*Math.sin(angle));

            if(Math.sqrt(Math.pow(target.hitbox.getCenterX() - hitbox.getCenterX(), 2) + Math.pow(target.hitbox.getCenterY() - hitbox.getCenterY(), 2)) < range){
                //use();
            }

            if(hitbox.intersects(target.hitbox)){
                target.takeDamage(1);
            }
        }
        else{
            setSpriteByFilename(normal);
        }
    }

    public Actor setTarget(Actor a) {
        if(a instanceof Player && viewbox.intersects(a.hitbox)){
            return a;
        }
        return null;
    }
}
