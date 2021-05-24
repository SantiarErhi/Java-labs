package lab6.store;
import lab6.model.IWeight;

import java.util.Arrays;

public class ProductStore extends AbstractStore {

    public void add(IWeight newIWeight){
        if(arr.length == count)
            arr  = Arrays.copyOf(arr, count + count/2);
        arr[count++] = newIWeight;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(arr[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
