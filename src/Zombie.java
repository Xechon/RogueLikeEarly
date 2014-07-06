import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Dustin on 3/31/14.
 */
public class Zombie extends Actor {
    private boolean first;
    public String normal = "zombie";
    public String act = "zombie_lunge";
    public double speed = 1.5;
    public double range = 200;
    public double delay = 1000;
    public double time = delay;
    Actor enemy;

    public Zombie(int i, int j, Room room){
        super(i,j, room);
        first = false;
        setSpriteByFilename(normal);
        //heldItem = new Gun(x,y,room);
    }

    @Override
    public void act(ArrayList<Entity> entities){
        super.act(entities);
        enemy = room.getPlayer();
        target = setTarget(new Point((int)enemy.bounds.getCenterX(), (int)enemy.bounds.getCenterY()));

        if(target != null){
            setAngle(target);
            setSpriteByFilename((first) ? normal : act);
            move(speed*Math.cos(getAngle()), speed*Math.sin(getAngle()));

            if(Math.sqrt(Math.pow(target.getX() - bounds.getCenterX(), 2) + Math.pow(target.getY() - bounds.getCenterY(), 2)) < range){
                if(System.currentTimeMillis() - time >= delay) {
                    time = System.currentTimeMillis();
                    use();
                }
            }

            if(bounds.intersects(enemy.bounds)){
                enemy.takeDamage(1);
            }
        }
        else{
            setSpriteByFilename(normal);
        }
    }

    public Point setTarget(Point a) {
        if(view.contains(a)){
            return a;
        }
        return null;
    }
}
