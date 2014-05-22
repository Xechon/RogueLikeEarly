import java.awt.*;

/**
 * Created by Xechon on 4/10/14.
 */
public class Player extends Actor {
    //Animation stuff. Maybe Actor should have default methods for these.
    private boolean first;
    public String normal = "sprite1.png";
    public String act = "sprite2.png";

    private Point mouseLocation = new Point();

    public Player(int i, int j){
        super(i,j);
        first = false;
        setSpriteByFilename(normal);
    }

    @Override
    public void act(){
        super.act(); //recalculates hitbox, viewbox, and angle
        setAngle(mouseLocation);
        move(Controller.xMove, Controller.yMove);
    }

    public void shoot(){
        setSpriteByFilename((first) ? normal : act);
        first = !first;
    }

    @Override
    public void getTarget(Actor[][] things){
        //player's get target could be a lockon feature like in hotline miami, middle click to always aim there.
    }

    public void setMouseLocation(Point p){
        mouseLocation = p;
    }
}
