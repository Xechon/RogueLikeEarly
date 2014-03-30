/**
 * Created with IntelliJ IDEA.
 * User: Dustin
 * Date: 3/30/14
 * Time: 3:59 PM
 */
public abstract class Room {

    private static final int ROWS = 9;
    private static final int COLUMNS = 16;
    private Actor[][] actors;
    public Room(){
        actors = new Actor[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLUMNS; j++){
                actors[i][j] = new BlankActor();
            }
        }
    }
    public Actor[][] getActors() {
        return actors;
    }
}
