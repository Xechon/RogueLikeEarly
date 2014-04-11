import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Xechon on 4/10/14.
 */
public class Controller {
    RoomContainer rc;
    Player player;
    public Controller(RoomContainer c){
        rc = c;
        player = rc.getPlayer();
        InputMap im = rc.getInputMap(rc.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = rc.getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "space-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "space-released");

        //unused, in place for movement
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "w-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "w-released");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "a-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "a-released");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "s-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "s-released");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "d-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "d-released");

        am.put("space-pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //player.interact();
            }
        });


        class MouseControls extends MouseAdapter{
            @Override
            public void mousePressed(MouseEvent e){
                if(e.getButton() == e.BUTTON1){
                    player.act();
                }
            }
            public void mouseReleased(MouseEvent e){
                player.act();
            }
        }
        rc.addMouseListener(new MouseControls());

        //doesn't seem to update properly
        class MouseTracking implements MouseMotionListener{

            @Override
            public void mouseDragged(MouseEvent e) {
                player.setAngle(e.getX(), e.getY());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                player.setAngle(e.getX(), e.getY());
            }
        }
        rc.addMouseMotionListener(new MouseTracking());
    }
}
