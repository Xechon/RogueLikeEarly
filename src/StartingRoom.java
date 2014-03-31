/**
 * Created with IntelliJ IDEA.
 * User: Dustin
 * Date: 3/30/14
 * Time: 4:07 PM
 */
public class StartingRoom extends Room {
    public StartingRoom(){
        super();
        for (int i = 0; i < Room.ROWS; i++){
            for (int j = 0; j < Room.COLUMNS; j++){
                setActor(i,j,new TestActor(i,j));
            }
        }
    }
}
