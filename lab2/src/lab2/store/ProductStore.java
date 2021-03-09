package lab2.store;
import lab2.model.Timber;

import java.util.Arrays;

public class ProductStore {
    Timber[] arr = new Timber[5];
    int count = 0;

    public void add(Timber newTimber){
        if(arr.length == count)
            arr  = Arrays.copyOf(arr, count + count/2);
        arr[count++] = newTimber;
    }

    public Timber[] getArr() {
        return Arrays.copyOf(arr, count);
    }

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
