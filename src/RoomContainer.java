import javax.swing.*;
import java.awt.*;
import java.util.ConcurrentModificationException;

/**
 * Created with IntelliJ IDEA.
 * User: Dustin
 * Date: 3/30/14
 * Time: 3:41 PM
 */
public class RoomContainer extends JPanel {
    public Room room;
    private HUD hud;

    public RoomContainer(Room r){
        super();
        room = r;
        r.setActList();
        hud = new HUD(room.player);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Actor a: room.getActList()){
            a.draw(g2);
        }
        hud.draw(g2);
    }

    public void act(){
        for (Actor a : room.getActList()) {
            a.act();
        }
        for(int i = 0; i < room.removeList.size(); i++){
            Actor a = room.removeList.get(i);
            room.getActList().remove(a);
            room.removeList.remove(a);
        }
        repaint();
    }
}
