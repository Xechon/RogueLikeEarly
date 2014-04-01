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
    //Since Room is an abstract class(A generic "Room" will never be used), you don't need to fill the array with BlankActors, do you?
    //Calling the Room constructor in StartingRoom was making the tiles all have BlankActors, then making them TestActors, I think.
    //Doesn't really seem to help with speed though.
    //I'm not actually sure why there is a BlankActor class anyway, care to explain?
    public Room(){
        actors = new Actor[ROWS][COLUMNS];
        //for (int i = 0; i < ROWS; i++){
        //    for (int j = 0; j < COLUMNS; j++){
        //        actors[i][j] = new BlankActor(i,j);
        //    }
        //}
    }
    public void setActor(int i, int j,Actor actor){
        actors[i][j] = actor;
    }
    public Actor[][] getActors() {
        return actors;
    }
}
