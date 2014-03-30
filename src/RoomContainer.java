import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Dustin
 * Date: 3/30/14
 * Time: 3:41 PM
 */
public class RoomContainer extends JPanel {
    private Room room;
    public RoomContainer(Room r){
        super();
        room = r;
    }
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        Actor[][] actors = room.getActors();
        for (int i = 0; i < actors.length; i++){
            for (int j = 0; j < actors[i].length; j++){
                actors[i][j].draw(g2);
            }
        }
    }
    public void act(){
        Actor[][] actors = room.getActors();
        for (int i = 0; i < actors.length; i++){
            for (int j = 0; j < actors[i].length; j++){
                actors[i][j].act();
            }
        }
    }
}
