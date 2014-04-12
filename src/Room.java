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
    public Room(){
        actors = new Actor[ROWS][COLUMNS]; //ArrayList might be better to use here.
    }
    public void setActor(int i, int j, Actor actor){
        actors[i][j] = actor;
    }
    public Actor[][] getActors() {
        return actors;
    }
}
