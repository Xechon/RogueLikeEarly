import java.util.ArrayList;

/**
 * Created by Xechon on 6/9/2014.
 */
public class Bullet extends Item{
    double speed = 50;

    public Bullet(Entity user){
        super(user);
        setAngle(user.getAngle());
        move(user.bounds.getWidth()*1.5);
        target = user.target;
    }

    @Override
    public void act(ArrayList<Entity> entities){
        super.act(entities);
        move(speed);
        getRotation().setToRotation(getAngle() - Math.PI/2, user.bounds.getCenterX(), user.bounds.getCenterY());

        for(Entity a: room.getActList()){
            if (a instanceof Actor && bounds.intersects(a.bounds)) {
                Actor temp = (Actor) a;
                temp.takeDamage(1);
                room.queueRemove(this);
            }
        }

        //Sprite collider = getCollision();
        //if(collider != null) {
        //    if(collider instanceof Actor) {
        //        Actor temp = (Actor) collider;
        //        temp.takeDamage(1);
        //    }
        //    room.queueRemove(this);
        //}
    }
}
