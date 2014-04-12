import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Xechon on 4/10/14.
 */
//Sometimes pauses or stops when switching directions.
public class Controller {
    RoomContainer rc;
    Player player;
    static int yMove = 0;
    static int xMove = 0;
    static final int speed = 5;
    public Controller(RoomContainer c){
        rc = c;
        player = rc.getPlayer();
        InputMap im = rc.getInputMap(rc.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = rc.getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "space-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "space-released");

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

        am.put("w-pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yMove = -speed;
            }
        });

        am.put("w-released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yMove = 0;
            }
        });

        am.put("a-pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xMove = -speed;
            }
        });

        am.put("a-released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xMove = 0;
            }
        });

        am.put("s-pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yMove = speed;
            }
        });

        am.put("s-released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yMove = 0;
            }
        });

        am.put("d-pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xMove = speed;
            }
        });

        am.put("d-released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xMove = 0;
            }
        });

        class MouseControls extends MouseAdapter{
            @Override
            public void mousePressed(MouseEvent e){
                if(e.getButton() == e.BUTTON1){
                    player.shoot();
                }
            }
            public void mouseReleased(MouseEvent e){
                player.shoot();
            }
        }
        rc.addMouseListener(new MouseControls());

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
