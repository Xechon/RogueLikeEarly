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
    private static final int DESIRED_WIDTH = 1920;
    private static final int DESIRED_HEIGHT = 1080;
    private final RoomContainer roomContainer;
    public Main(){
        super("Test");
        setSize(new Dimension(DESIRED_WIDTH,DESIRED_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(0,0);
        //setUndecorated(true);
        setLayout(new BorderLayout());


        roomContainer = new RoomContainer(new StartingRoom());

        add(roomContainer,BorderLayout.CENTER);
    }
    public RoomContainer getRoomContainer(){
        return roomContainer;
    }
    public static void main(String[] args){
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    final Main main = new Main();
                    main.setVisible(true);
                    Timer t = new Timer(1, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            main.getRoomContainer().act();
                            main.getRoomContainer().repaint();
                        }
                    });
                    t.start();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
