/**
 * Created with IntelliJ IDEA.
 * User: Dustin
 * Date: 3/30/14
 * Time: 4:07 PM
 */
public class StartingRoom extends Room {
    public StartingRoom(){
        super();
        int row = (Room.ROWS - 1)/2;
        int column = (Room.COLUMNS - 1)/2;
        for (int i = 0; i < Room.ROWS; i++){
            for (int j = 0; j < Room.COLUMNS; j++){
                setActor(i,j,new BlankActor(i,j));//Possible unnecessary redundancy in setActor
            }
        }
        setActor(row,column,new Player(row,column));
    }
}
