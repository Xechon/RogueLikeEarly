import java.util.ArrayList;

/**
 * Created by Xechon on 7/2/2014.
 */
public class Wall extends Entity {

    public Wall(double x, double y, Room room){
        super(x,y,room);
    }

    @Override
    public void act(ArrayList<Entity> entities){
        for(Entity a: room.getActList()){
            if (a.intersects(hitbox)){
                //a.stop;
            }
        }
    }
}
