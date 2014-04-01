
/**
 * Created by Dustin on 3/31/14.
 */
public class TestActor extends Actor {
    private boolean first;
    public TestActor(int i, int j){
        super(i,j);
        first = false;
        setSpriteByFilename("sprite1.png");
    }
    @Override
    public void act(){
        setSpriteByFilename((first)?"sprite1.png":"sprite2.png");
        first = !first;
    }
}
