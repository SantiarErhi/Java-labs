package lab2.store;
import lab2.model.IWeight;

import java.util.Arrays;

public class AbstractStore {
    protected Object[] arr = new Object[3];
    protected int count = 0;

    protected void add(Object newObject){
        if(arr.length == count)
            arr  = Arrays.copyOf(arr, count + count/2);
        arr[count++] = newObject;
    }

    public Object[] getArr() {
        return Arrays.copyOf(arr, count);
    }

    public Object getLastElement() { return arr[count - 1]; }

    public int getCount() {
        return count;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("Timber catalog: \n");
        for (int i = 0; i < count; i++) {
            sb.append(arr[i]).append("\n");
        }
        return sb.toString();
    }
}
