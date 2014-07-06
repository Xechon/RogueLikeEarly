import java.awt.*;
import java.util.ArrayList;

/**
 * Created by shann_000 on 5/22/2014.
 */
public class Item extends Entity{
    Entity user;
    boolean tossed;

    public double speed;

    public Point target;

    public Item(double x, double y, Room room){
        super(x,y,room);
    }

    public Item(Entity user){
        this(user.bounds.getCenterX(), user.bounds.getCenterY(), user.room);
        this.user = user;
        target = user.target;
    }

    public void use(){
        //swing
    }

    public void toss(){
        if(target != null) {
            setAngle(target);
        }
        tossed = true;
    }

    @Override
    public void act(ArrayList<Entity> entities){
        super.act(entities);
        if(user != null){
            target = user.target;
        }
        if(tossed && speed > 0){
            move(speed);
            speed /= 1.5;
        }
    }

    public void setUser(Entity user){
        this.user = user;
    }
}
