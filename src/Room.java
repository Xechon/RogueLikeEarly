import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Dustin
 * Date: 3/30/14
 * Time: 3:59 PM
 */

public abstract class Room {

    /*
    Instance Fields
     */

    //Tile information
    public static final int ROWS = 9;
    public static final int COLUMNS = 16;
    public double tileWidth, tileHeight;

    //Level information
    String levelName;
    String roomName;

    //Object information
    private Actor[][] actors;
    private Item[][] items;
    private BufferedImage[][] tiles;
    public BufferedImage background;
    public Player player;

    //Queues to avoid concurrency exceptions
    ArrayList<Entity> actList = new ArrayList<Entity>();
    ArrayList<Entity> removeList = new ArrayList<Entity>();
    ArrayList<Entity> addList = new ArrayList<Entity>();

    //Camera offset information
    AffineTransform at = new AffineTransform();
    double camX, camY, offsetMinX, offsetMaxX, offsetMinY, offsetMaxY;

    int scale = 2;
    BufferedImage currentScreen;

    QuadTree entities;

    public static boolean debug;

    /*
    Constructors
     */

    public Room(String levelName){
        actors = new Actor[ROWS][COLUMNS];
        tiles = new BufferedImage[ROWS][COLUMNS];
        items = new Item[ROWS][COLUMNS];
        tileWidth = Main.SCREEN_WIDTH/Room.COLUMNS;
        tileHeight = Main.SCREEN_HEIGHT/Room.ROWS;
        roomName = getClass().getSimpleName();
        this.levelName = levelName;
        setBackground();

        offsetMaxX = background.getWidth()*scale - Main.SCREEN_WIDTH;
        offsetMaxY = background.getHeight()*scale - Main.SCREEN_HEIGHT;
        offsetMinX = 0;
        offsetMinY = 0;
    }

    /*
    Methods
     */

    public void layoutTiles(String filename){
        //try{

        //}catch(FileNotFoundException e){
        //    e.printStackTrace();
        //}
    }

    public void setBackground(){
        try{
            background = ImageIO.read(new File("Sprite/Level/" + levelName + "/" + roomName + ".png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setActor(int i, int j, Actor actor){
        actors[i][j] = actor;
        actor.moveTo(j*tileHeight, i*tileWidth);
        if(player == null && actor instanceof Player){
            player = (Player) actor;
        }
    }

    public void setItem(int i, int j, Item item){
        items[i][j] = item;
        item.moveTo(j*tileHeight, i*tileWidth);
    }

    public void setActList(){
        for(Actor[] e: actors){
            for(Actor f: e){
                if(f != null) {
                    actList.add(f);
                }
            }
        }
        for(Item[] e: items){
            for(Item f: e){
                if(f != null) {
                    actList.add(f);
                }
            }
        }
    }

    public Actor[][] getActors() {
        return actors;
    }

    public ArrayList<Entity> getActList(){
        return actList;
    }

    public Player getPlayer(){
        if(player == null){
            for(Actor[] a : actors){
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

    public void updateCamera(){
        camX = player.getDecimalX() - Main.SCREEN_WIDTH / 2;
        camY = player.getDecimalY() - Main.SCREEN_HEIGHT / 2;

        if (camX > offsetMaxX)
            camX = offsetMaxX;
        else if (camX < offsetMinX)
            camX = offsetMinX;
        if(camY > offsetMaxY)
            camY = offsetMaxY;
        else if(camY < offsetMinY)
            camY = offsetMinY;
    }

    public void draw(Graphics2D g){
        updateCamera();
        entities.update();
        currentScreen = new BufferedImage(background.getWidth()*scale, background.getHeight()*scale, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2 = currentScreen.createGraphics();
        g2.setColor(Color.BLACK);

        g2.drawImage(background, 0, 0, background.getWidth()*scale, background.getHeight()*scale, null);

        for (Entity a: actList){
            a.sprite.draw(g2);
            if(debug) {
                a.draw(g2);
            }
        }

        if(debug) {
            g2.setTransform(new AffineTransform());
            entities.draw(g2);
        }
        g.drawImage(currentScreen,(int)-camX, (int)-camY, background.getWidth()*scale, background.getHeight()*scale, null);

    }

    public void queueRemove(Entity a){
        removeList.add(a);
    }

    public void queueAdd(Entity a){
        addList.add(a);
    }

    public Dimension getTileDimensions(){
        return new Dimension((int)tileWidth, (int)tileHeight);
    }

    public String getLevelName(){
        return levelName;
    }

    public String getRoomName(){
        return roomName;
    }

    public int getTileWidth(){
        return (int)tileWidth;
    }

    public int getTileHeight(){
        return (int)tileHeight;
    }
}
