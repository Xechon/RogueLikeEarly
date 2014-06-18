import javax.swing.*;
import java.awt.event.*;
import java.security.Key;

/**
 * Created by Xechon on 4/10/14.
 */
public class Controller {
    //imports for simplicity's sake
    RoomContainer rc;
    Player player;

    //vector stuff
    static int yMove = 0;
    static int xMove = 0;
    static final int speed = 5;

    public Controller(RoomContainer c){
        rc = c;
        player = rc.room.player;
        InputMap im = rc.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
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
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, false), "q-pressed");

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0, false), "1-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0, false), "2-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0, false), "3-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0, false), "4-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0, false), "5-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0, false), "6-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0, false), "7-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0, false), "8-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0, false), "9-pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_0, 0, false), "0-pressed");

        am.put("space-pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

        am.put("q-pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.toss();
            }
        });

        for(int i = 0; i < 9; i++) {
            final int num = i;
            am.put("i-pressed", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    player.selectInventorySlot(num);
                }
            });
        }

        class MouseControls extends MouseAdapter{
            @Override
            public void mousePressed(MouseEvent e){
                if(!player.first && e.getButton() == MouseEvent.BUTTON1){
                    player.use();
                }
                else if(e.getButton() == MouseEvent.BUTTON3){
                    player.highlightItem();
                    player.interact();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e){
                if(e.getButton() == MouseEvent.BUTTON1){
                    player.use();
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                player.setMouseLocation(e.getPoint());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                player.setMouseLocation(e.getPoint());
            }
        }

        MouseControls mc = new MouseControls();
        rc.addMouseListener(mc);
        rc.addMouseMotionListener(mc);
    }
}
