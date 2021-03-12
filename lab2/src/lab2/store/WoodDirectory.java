package lab2.store;
import  lab2.model.Wood;

public class WoodDirectory extends AbstractStore {

    {
        arr[0] = new Wood(0, "Larch", 1.1f);
        arr[1] = new Wood(1, "Tree", 0.9f);
        arr[2] = new Wood(2, "Pine-tree", 0.7f);
    }

    public Wood get(int id){
        for (int i = 0; i < count; i++) {
            Wood wood = (Wood) arr[i];
            if(wood.getId() == id)
                return wood;
        }
        return null;
    }
    public boolean add(Wood newWood){
        if(get(newWood.getId()) != null)
            return false;
        super.add(newWood);
        return true;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder("Wood catalog: \n");
        for (int i = 0; i < count; i++) {
            sb.append(arr[i]).append("\n");
        }
        return sb.toString();
    }
}
