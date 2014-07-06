/**
 * Created by Xechon on 6/16/2014.
 */
public class Gun extends Item {
    public Gun(Actor user){
        super(user);
    }

    public Gun(double x, double y, Room room){
        super(x,y,room);
    }

    public Gun(int i, int j, Room room){
        super(i, j, room);
    }

    public void use(){
        room.queueAdd(new Bullet(this));
    }
}
