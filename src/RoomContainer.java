import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Dustin
 * Date: 3/30/14
 * Time: 3:41 PM
 */
public class RoomContainer extends JPanel {
    public Room room;
    private Player player;
    private HUD hud = new HUD();

    public RoomContainer(Room r){
        super();
        room = r;
        r.setActList();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Actor[][] actors = room.getActors();
        for (Actor[] actor : actors) {
            for (Actor anActor : actor) {
                anActor.draw(g2);
            }
        }
        hud.draw(g2);
    }

    public void act(){
        Actor[][] actors = room.getActors();
        for (Actor[] actor : actors) {
            for (Actor anActor : actor) {
                anActor.getTarget(actors);
                anActor.act();
            }
        }
        repaint();
    }
}
