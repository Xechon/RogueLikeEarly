/**
 * Created with IntelliJ IDEA.
 * User: Dustin
 * Date: 3/30/14
 * Time: 4:07 PM
 */
public class StartingRoom extends Room {
    public StartingRoom(){
        super();

        setActor(0,0,new TestActor(0,0));
    }
}
