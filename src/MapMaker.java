import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Xechon on 6/30/2014.
 */
public class MapMaker {
    public static ArrayList<BufferedImage> tiles = new ArrayList<BufferedImage>();
    public static String loc;

    public MapMaker(String fileName){
        loc = fileName;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Input the folder location of the tiles and layout images, appropriately named.");
        loc = in.next();

        //locating tile images
        retrieveTileImages();

        //assigning colors to tiles, and creating and saving images.

    }

    public BufferedImage[][] readTiles(){
        BufferedImage[][] a = new BufferedImage[1][1];

        return a;
    }

    public static void retrieveTileImages(){
        for(int i = 0; true; i++){
            try{
                tiles.add(i, ImageIO.read(new File(loc + "/" + i + ".png")));
            }catch (IOException e){
                e.printStackTrace();
            }
            if(tiles.get(i) == null){
                System.out.println(i + " tiles were found.");
                break;
            }
        }
    }

    public BufferedImage mapColor(int pixel, Color color, BufferedImage tile){
        if(pixel == color.getRGB()){
            return tile;
        }
        return null;
    }
}
