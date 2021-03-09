package lab2.store;
import lab2.model.IWeight;

import java.util.Arrays;

public class ProductStore extends AbstractStore{

    public void add(IWeight newIWeight){
        if(arr.length == count)
            arr  = Arrays.copyOf(arr, count + count/2);
        arr[count++] = newIWeight;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("Timber catalog: \n");
        for (int i = 0; i < count; i++) {
            sb.append(super.toString()).append("\n");
        }
        return sb.toString();
    }
}
