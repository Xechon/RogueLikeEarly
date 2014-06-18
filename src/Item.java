import java.awt.*;

/**
 * Created by shann_000 on 5/22/2014.
 */
public class Item extends Actor{
    Actor user;
    boolean tossed;

    public Item(double x, double y, Room room){
        super(x,y,room);
    }

    public Item(int i, int j, Room room){
        super(i,j, room);
    }

    public Item(Actor user){
        this.user = user;
        setSpriteByFilename(getClass().getSimpleName());
        x = user.hitbox.getCenterX();
        y = user.hitbox.getCenterY();
        hitbox = new Rectangle((int)x, (int)y, width, height);
        room = user.room;
        angle = user.angle;
    }

    @Override
    public void draw(Graphics2D g2){
        g2.setTransform(at);
        g2.drawImage(sprite,(int)x,(int)y, width, height, null);
        g2.draw(hitbox);
    }

    public void use(){
        //swing
    }

    public void toss(){
        tossed = true;
    }

    @Override
    public void act(){
        super.act();
        if(tossed && speed > 0){
            move(speed);
            speed /= 1.5;
        }
    }
}
