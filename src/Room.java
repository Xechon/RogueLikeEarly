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
        actors = new Actor[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLUMNS; j++){
                actors[i][j] = new BlankActor(i,j);
            }
        }
    }
    public void setActor(int i, int j,Actor actor){
        actors[i][j] = actor;
    }
    public Actor[][] getActors() {
        return actors;
    }
}
