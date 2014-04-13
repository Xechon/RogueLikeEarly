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
    private Player player;
    public RoomContainer(Room r){
        super();
        room = r;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
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
                actors[i][j].getTarget(actors);
                actors[i][j].act();
            }
        }
    }
    public Player getPlayer(){
        if(player == null){
            for(Actor[] a : room.getActors()){
                for(Actor b : a){
                    if(b instanceof Player){
                        player = (Player) b;
                        break;
                    }
                }
            }
        }
        return player;
    }
}
