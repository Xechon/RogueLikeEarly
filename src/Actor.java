import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Dustin
 * Date: 3/30/14
 * Time: 4:01 PM
 */
public abstract class Actor extends Entity{

    /*
    Instance Fields
     */

    //Stats //Both Item and Actor
    public int health = 3;
    public double speed;

    //Holding/storing items //Some items
    public Item[] inventory = new Item[10];
    public Item heldItem;
    public Item highlightedItem;

    //Some items
    public Shape view = new Rectangle();//Not all//Should make a cone

    /*
    Constructors
     */

    public Actor(double x, double y, Room room){
        super(x,y,room);
    }

    public void act(ArrayList<Entity> entities) {
        super.act(entities);
        view = new Ellipse2D.Double(bounds.getCenterX() - 250, bounds.getCenterY() - 250,500,500);
        if(heldItem != null) {
            heldItem.setAngle(getAngle());
            heldItem.moveTo(bounds.getCenterX(), bounds.getCenterY());
            heldItem.move(1.5 * bounds.getWidth());
        }
    }

    public void interact(){
        if(view != null && highlightedItem != null && view.intersects(highlightedItem.bounds)){
            putInInventory(heldItem);
            heldItem = highlightedItem;
            heldItem.setUser(this);
        }
    }

    public void takeDamage(int amt){
        health -= amt;
        if(health <= 0){
            room.queueRemove(this);
        }
    }

    public void putInInventory(Item item){
        for(int i = 0; i < inventory.length; i++){
            if(inventory[i] == null){
                inventory[i] = item;
                if(item == heldItem){
                    heldItem = null;
                }
                return;
            }
        }
        toss();
        heldItem = item;
    }

    public void putInInventory(Item item, int index){

    }

    public void use(){
        if(heldItem != null) {
            heldItem.use();
        }
    }

    public void toss(){
        if(heldItem != null){
            heldItem.speed += 100;
            heldItem.toss();
            heldItem = null;
        }
    }

    @Override
    public void draw(Graphics2D g2){
        super.draw(g2);
        g2.draw(view);
    }
}
