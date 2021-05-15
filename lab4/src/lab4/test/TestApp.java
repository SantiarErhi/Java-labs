package lab4.test;

import lab4.model.Cylinder;
import lab4.model.IWeight;
import lab4.model.Timber;
import lab4.model.Waste;
import lab4.store.ProductStore;
import lab4.store.WoodDirectory;

import java.util.Iterator;
import java.util.ListIterator;

public class TestApp {
    private WoodDirectory wd = new WoodDirectory();
    private ProductStore ps = new ProductStore();

    public static void main(String[] args) {
        TestApp app = new TestApp();
        app.startApp();
    }

    private void startApp(){
        try {
            ps.add(new Timber(wd.get(0), 5f, 0.5f, 0.4f));
            ps.add(new Timber(wd.get(1), 10f, 0.5f, 0.4f));
            ps.add(new Cylinder(wd.get(1), 1f, 2f));
            System.out.println(ps.toString());
            System.out.printf("Weight: %1.3f \n", calcWeight());
            ListIterator<Object> iter = ps.listIterator();
            while (iter.hasNext()){
                IWeight obj = (IWeight) iter.next();
                System.out.println(obj.toString() + " " + obj.weight());
            }
            while (iter.hasPrevious()){
                IWeight obj = (IWeight) iter.previous();
                System.out.println(obj.toString() + " " + obj.weight());
            }

        }

        catch (Exception e){
            e.printStackTrace();
        }
    }
    private float calcWeight(){
        float fullWeight = 0;
        for(Object timber : ps.getArr()){
            fullWeight += ((IWeight)timber).weight();
        }
        return fullWeight;
    }
}
