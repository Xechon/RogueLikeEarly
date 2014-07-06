import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Xechon on 6/22/2014.
 */
public class RoomReader {
    Room room;
    int[][] tiles;
    int[][] actors;

    public RoomReader(Room room){
        changeRoom(room);
    }

    public RoomReader(){

    }



    public Actor[][] readActors(){
        Actor[][] a = new Actor[1][1];

        return a;
    }

    public Actor mapColor(int i, int j, Color color, Actor actor){
        if(actors[i][j] == color.getRGB()){
            return actor;
        }
        return null;
    }

    public void changeRoom(Room room){
        this.room = room;
        BufferedImage temp = new BufferedImage(room.getTileWidth(), room.getTileHeight(), BufferedImage.TYPE_3BYTE_BGR);
        try{
            temp = ImageIO.read(new File("Levels/" + room.getLevelName() + "/" + room.getRoomName() + "/actors.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        if(temp != null){
            for(int i = 0; i < temp.getWidth(); i++){
                for(int k = 0; k < temp.getHeight(); k++){
                    actors[i][k] = temp.getRGB(i,k);
                }
            }
            readActors();
        }
    }
}
