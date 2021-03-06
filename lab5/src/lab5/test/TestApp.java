package lab5.test;

import lab5.model.Cylinder;
import lab5.model.IWeight;
import lab5.model.Timber;
import lab5.model.Waste;
import lab5.store.ProductStore;
import lab5.store.WoodDirectory;

import java.util.ListIterator;
import java.util.function.Predicate;

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
            ps.add(new Waste(5));
            ps.add(new Waste(2));


            System.out.println(ps.toString());
            System.out.printf("Weight: %1.3f \n", calcWeight());
            ListIterator<Object> iter = ps.listIterator();

            IWeight obj1 = (IWeight) iter.next();
            iter.add(new Timber(wd.get(2), 1, 4, 1));

            while (iter.hasNext()){
                IWeight obj = (IWeight) iter.next();
                System.out.println(obj.toString() + " " + obj.weight());
            }
            while (iter.hasPrevious()){
                IWeight obj = (IWeight) iter.previous();
                System.out.println(obj.toString() + " " + obj.weight());
            }

            float maxWeight = 2;
            Predicate<Object> prd = new Predicate<Object>() {
                @Override
                public boolean test(Object o) {
                    return o instanceof Waste && ((IWeight) o).weight() > maxWeight;
                }
            };
            ps.forEach(System.out::println);
            ps.remove(prd);
            //ps.remove(o -> o instanceof Waste && ((IWeight) o).weight() > maxWeight);
            System.out.println(ps.toString());
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
