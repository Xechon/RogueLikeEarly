/**
 * Created by Xechon on 6/10/2014.
 */
public class Knife extends Item{
    Actor user;
    double radius;
    Player player;

    public Knife(int i, int j, Room room){
        super(i,j,room);
    }

    public Knife(Actor user){
        super(user);
        radius = user.hitbox.getWidth()*1.5;
        player = (Player) user;
    }

    public void use(){

    }

    public void act(){
        super.act();
        for(Actor a: room.actList){
            if (!(a instanceof BlankActor) && a != this && hitbox.intersects(a.hitbox)) {
                a.takeDamage((int)speed/50);
                if(tossed) {
                    room.queueRemove(this);
                }
            }
        }
    }
}
