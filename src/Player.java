import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Created by Xechon on 4/10/14.
 */
public class Player extends Actor {
    //Animation stuff. Maybe Actor should have default methods for these.
    public boolean first;
    public String normal = "player";
    public String act = "sprite2";

    private Point mouseLocation = new Point();

    public Player(int i, int j, Room room){
        super(i,j, room);
        setSpriteByFilename(normal);
        speed = 5;
    }

    @Override
    public void act(){
        super.act(); //recalculates hitbox, viewbox, and angle
        setAngle(mouseLocation);
        move(Controller.xMove, Controller.yMove);
        if(heldItem != null){
            heldItem.speed = speed;
        }
    }

    @Override
    public void use(){
        setSpriteByFilename((first) ? normal : act);
        if(!first && heldItem != null) {
            heldItem.use();
        }
        first = !first;
    }

    public void setMouseLocation(Point p){
        mouseLocation = p;
    }

    public void highlightItem(){
        for(Actor e: room.actList){
            if(e instanceof Item && ((Item) e).hitbox.contains(mouseLocation)){
                highlightedItem = (Item)e;
                return;
            }
        }
        highlightedItem = null;
    }

    public void selectInventorySlot(int i){
        heldItem = inventory[i];
    }
}
