import java.awt.*;

/**
 * Created by Xechon on 6/10/2014.
 */
public class Knife extends Actor{
    Actor user;
    double speed = 10;
    int width = 10;
    int height = 20;
    double radius;

    public Knife(Actor user){
        //setSpriteByFilename("knife.png");
        this.user = user;
        x = user.hitbox.getCenterX();
        y = user.hitbox.getCenterY();
        hitbox = new Rectangle((int)x, (int)y, width, height);
        room = user.room;
        angle = user.angle;
        //at.setToRotation(angle, hitbox.getCenterX(), hitbox.getCenterY()); //Rotation causes massive displacement.
        radius = user.hitbox.getWidth();
    }

    public void stab(){
        if(!user.acting) {
            move(radius, angle);
            System.out.println("shng!");
        }
        else{
            move(radius, -angle);
        }
    }

    @Override
    public void draw(Graphics2D g2){
        g2.setTransform(at);
        //g2.drawImage(sprite,(int)x,(int)y, width, height, null);
        g2.draw(hitbox);
    }

    @Override
    public void act(){
        for(Actor[] a: room.getActors()){
            for(Actor b: a) {
                if (b != user && !(b instanceof BlankActor) && hitbox.intersects(b.hitbox)) {
                    b.takeDamage(1);
                }
            }
        }
    }
}
