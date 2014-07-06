import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ConcurrentModificationException;

/**
 * Created with IntelliJ IDEA.
 * User: Dustin
 * Date: 3/30/14
 * Time: 3:41 PM
 */
public class RoomContainer extends JComponent {
    public Room room;
    private HUD hud;

    public RoomContainer(Room r){
        super();
        room = r;
        r.setActList();
        room.entities = new QuadTree(room);
        hud = new HUD(room.player);
        new Controller(this);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        room.draw(g2);
        hud.draw(g2);
    }

    public void act(){
        for(int i = 0; i < room.addList.size(); i++){
            Entity a = room.addList.get(i);
            room.actList.add(a);
            room.entities.insert(a);
            room.addList.remove(i);
            i--;
        }
        for (Entity a : room.getActList()) {
            if(a != null) {
                a.act(room.entities.getSiblings());
            }
        }
        for(int i = 0; i < room.removeList.size(); i++){
            Entity a = room.removeList.get(i);
            room.getActList().remove(a);
            room.entities.remove(a);
            room.removeList.remove(a);
            i--;
        }
        repaint();
    }
}
