import java.util.ArrayList;

/**
 * Created by Xechon on 6/10/2014.
 */
public class Knife extends Item{
    double radius;
    Player player;

    public Knife(int i, int j, Room room){
        super(i,j,room);
    }

    public Knife(Entity user){
        super(user);
        radius = user.bounds.getWidth()*1.5;
        player = (Player) user;
    }

    public void use(){
        for(Entity a: room.getActList()){
            if (a instanceof Actor && bounds.intersects(a.bounds)) {
                Actor temp = (Actor) a;
                temp.takeDamage(3);
                if (tossed) {
                    room.queueRemove(this);
                }
            }
        }
    }

    public void act(ArrayList<Entity> entities){
        super.act(entities);

    }
}
