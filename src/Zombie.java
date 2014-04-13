
/**
 * Created by Dustin on 3/31/14.
 */
public class Zombie extends Actor {
    private boolean first;
    public String normal = "zombie_idle.png";
    public String act = "zombie_lunge.png";

    public Zombie(int i, int j){
        super(i,j);
        first = false;
        setSpriteByFilename(normal);
    }
    @Override
    public void act(){
        super.act();

        if(target != null){
            //setAngle(target.x, target.y);
            setSpriteByFilename((first) ? normal : act);
            //first = !first;
        }
    }
}
