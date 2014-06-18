/**
 * Created by Xechon on 6/9/2014.
 */
public class Bullet extends Item{
    double speed = 20;

    public Bullet(Actor user){
        super(user);
        move(user.hitbox.getWidth()*1.5);
    }

    @Override
    public void act(){
        move(speed);
        hitbox.setLocation((int)x,(int)y);
        at.setToRotation(angle - Math.PI/2, hitbox.getCenterX(), hitbox.getCenterY());


        for(Actor a: room.actList){
            if (!(a instanceof BlankActor) && a != this && hitbox.intersects(a.hitbox)) {
                a.takeDamage(1);
                room.queueRemove(this);
            }
        }

        //Actor collider = getCollision();
        //if(collider != null) {
        //    collider.takeDamage(1);
            //room.queueRemove(this);
        //}
    }
}
