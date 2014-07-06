import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA.
 * User: Dustin
 * Date: 3/30/14
 * Time: 1:22 PM
 */

public class Main extends JFrame{
    static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    public static final int SCREEN_WIDTH = gd.getDisplayMode().getWidth();
    public static final int SCREEN_HEIGHT = gd.getDisplayMode().getHeight();

    public static boolean end;

    RoomContainer roomContainer;

    public Main(){
        super("StartingLevel");
        setSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationByPlatform(true);
        setLocation(0,0);
        setUndecorated(true);
        setLayout(new BorderLayout());

        roomContainer = new RoomContainer(new StartingRoom());

        add(roomContainer,BorderLayout.CENTER);
    }

    public static void main(String[] args){
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    final Main main = new Main();
                    Timer t = new Timer(10, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(!end) {
                                main.roomContainer.act();
                            }
                            else{
                                System.exit(0);
                            }
                        }
                    });
                    t.start();
                    main.setVisible(true);
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void end(){
        end = true;
    }
}
