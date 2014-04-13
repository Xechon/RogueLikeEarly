/**
 * Created with IntelliJ IDEA.
 * User: Dustin
 * Date: 3/30/14
 * Time: 4:07 PM
 */
public class StartingRoom extends Room {
    public StartingRoom(){
        super();
        int row = (Room.ROWS)/2;
        int column = (Room.COLUMNS)/2;
        for (int i = 0; i < Room.ROWS; i++){
            for (int j = 0; j < Room.COLUMNS; j++){
                double rand = Math.random();
                if(rand < .05){
                    setActor(i,j,new Zombie(i,j));
                }
                else{
                    setActor(i,j,new BlankActor(i,j));
                }
            }
        }
        setActor(row,column,new Player(row,column));
    }
}
