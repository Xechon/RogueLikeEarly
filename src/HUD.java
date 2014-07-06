import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Xechon on 6/11/2014.
 */
public class HUD {
    public BufferedImage heart;
    public Actor user;

    public HUD(Actor user){
        this.user = user;
        try{
            heart = ImageIO.read(new File("Sprite/HUD/heart.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        g2.setTransform(new AffineTransform());
        for(int i = 0; i < user.health; i++) {
            g2.drawImage(heart, 30*i, 0, 25, 25, null);
        }

        if(user.health <= 0){
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 72));
            g2.drawString("Oof! That looked like it hurt.", (Main.SCREEN_WIDTH)/4, (Main.SCREEN_HEIGHT)/2);
        }
    }
}
