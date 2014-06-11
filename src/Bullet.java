import java.awt.*;

/**
 * Created by Xechon on 6/9/2014.
 */
public class Bullet extends Actor{
    double speed = 20;
    int width = 5;
    int height = 10;

    public Bullet(Actor user){
        setSpriteByFilename("bullet.png");
        x = user.hitbox.getCenterX();
        y = user.hitbox.getCenterY();
        hitbox = new Rectangle((int)x, (int)y, width, height);
        room = user.room;
        angle = user.angle;
        move(user.hitbox.getWidth()*1.5);
    }

    @Override
    public void act(){
        move(speed);
        hitbox.setLocation((int)x,(int)y);
        at.setToRotation(angle - Math.PI/2, hitbox.getCenterX(), hitbox.getCenterY());
        for(Actor[] a: room.getActors()){
            for(Actor b: a) {
                if (!(b instanceof BlankActor) && hitbox.intersects(b.hitbox)) {
                    b.takeDamage(1);
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2){
        g2.setTransform(at);
        g2.drawImage(sprite,(int)x,(int)y, width, height, null);
        g2.draw(hitbox);
    }
}
