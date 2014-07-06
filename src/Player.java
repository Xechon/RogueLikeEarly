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

    public Player(int i, int j, Room room){
        super(i,j, room);
        setSpriteByFilename(normal);
        speed = 5;
        putInInventory(new Gun(this));
    }

    @Override
    public void act(ArrayList<Entity> entities){
        super.act(entities);
        setMouseLocation(MouseInfo.getPointerInfo().getLocation());
        setAngle(target);
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
        target = p;
        target.setLocation(target.getX() + room.camX, target.getY() + room.camY);
        highlightItem();
    }

    public void highlightItem(){
        for(Entity e: room.actList){
            if(e instanceof Item && ((Item) e).bounds.contains(target)){
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
