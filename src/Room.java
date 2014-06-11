import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Dustin
 * Date: 3/30/14
 * Time: 3:59 PM
 */
public abstract class Room {

    public static final int ROWS = 9;
    public static final int COLUMNS = 16;
    private Actor[][] actors;
    ArrayList<Actor> actList = new ArrayList<Actor>();
    public Player player;

    public Room(){
        actors = new Actor[ROWS][COLUMNS];
    }

    public void setActor(int i, int j, Actor actor){
        actors[i][j] = actor;
    }

    public void setActList(){
        for(Actor[] e: actors){
            for(Actor f: e){
                if(!(f instanceof BlankActor)){
                    actList.add(f);
                }
            }
        }
    }
    public Actor[][] getActors() {
        return actors;
    }
    public ArrayList<Actor> getActList(){
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
}
