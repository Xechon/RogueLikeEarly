import java.awt.*;

/**
 * Created by Dustin on 3/31/14.
 */
public class Zombie extends Actor {
    private boolean first;
    public String normal = "zombie_idle.png";
    public String act = "zombie_lunge.png";
    public double speed = 1.5;

    public Zombie(int i, int j, Room room){
        super(i,j, room);
        first = false;
        setSpriteByFilename(normal);
    }

    @Override
    public void act(){
        super.act();

        if(target != null){
            setAngle(new Point((int)target.hitbox.getCenterX(), (int)target.hitbox.getCenterY()));
            setSpriteByFilename((first) ? normal : act);
            move(speed*Math.cos(angle), speed*Math.sin(angle));
            if(hitbox.intersects(target.hitbox)){
                target.takeDamage(1);
            }
        }
        else{
            setSpriteByFilename(normal);
        }
    }
}
