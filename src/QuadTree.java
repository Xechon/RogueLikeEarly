import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Xechon on 7/4/2014.
 */
public class QuadTree {
    QuadTree parent;
    QuadTree[] children;
    ArrayList<Entity> things = new ArrayList<Entity>();
    Rectangle bounds;
    public int limit = 3;

    public QuadTree(QuadTree parent, int index){
        bounds = new Rectangle(parent.bounds.x + (parent.bounds.width/2)*(index%2), parent.bounds.y +
                (parent.bounds.height/2)*(index/2), parent.bounds.width/2, parent.bounds.height/2);

        for(Entity a: parent.things){
            if(bounds.contains(a.bounds)){
                things.add(a);
            }
        }
        for(Entity a: things){
            parent.remove(a);
        }
    }

    public QuadTree(Room room){
        bounds = new Rectangle(0,0,room.background.getWidth()*room.scale, room.background.getHeight()*room.scale);
        things = new ArrayList<Entity>(room.actList);
    }

    public void split(){
        if(children == null){
            children = new QuadTree[4];
            for(int i = 0; i < 4; i++){
                children[i] = new QuadTree(this, i);
            }
        }
    }

    public void merge(){
        if(children != null && parent != null){
            children = null;
        }
    }

    public void update(){
        if(things.size() >= limit && children == null){
            split();
        }
        else if(things.size() < limit && parent != null){
            merge();
        }
        int a = things.size();
        for(int i = 0; i < a; i++){
            insert(things.get(i));
        }
        if(children != null){
            for(QuadTree e: children){
                e.update();
            }
        }
    }

    public void insert(Entity a){
        if(!bounds.contains(a.bounds) && parent != null){
            parent.things.add(a);
        }else if(children != null){
            for(QuadTree b: children){
                if(b.bounds.contains(a.bounds)){
                    b.insert(a);
                }
            }
        }else{
            things.add(a);
        }
    }

    public void remove(Entity a){
        if(things.indexOf(a) != -1){
            things.remove(a);
        }
        else if(children != null){
            for(QuadTree b: children){
                b.remove(a);
            }
        }
    }

    public ArrayList<Entity> getSiblings(){
        ArrayList<Entity> siblings = new ArrayList<Entity>();
        siblings.addAll(things);
        if(children != null){
            for(QuadTree a: children){
                siblings.addAll(a.getSiblings());
            }
        }
        return siblings;
    }

    public void draw(Graphics2D g2){
        if(children != null){
            for(QuadTree e: children){
                e.draw(g2);
            }
        }
        else{
            g2.setColor(Color.BLACK);
            g2.draw(bounds);
        }
    }
}
