/**
 * Created by Xechon on 4/10/14.
 */
public class Player extends Actor {
    private boolean first;
    public String normal = "sprite1.png";
    public String act = "sprite2.png";

    public Player(int i, int j){
        super(i,j);
        first = false;
        setSpriteByFilename(normal);
    }
    @Override
    public void act(){
        super.act();
        move(Controller.xMove, Controller.yMove);
    }

    public void shoot(){
        setSpriteByFilename((first) ? normal : act);
        first = !first;
    }

    @Override
    public void getTarget(Actor[][] things){

    }
}
