package lab2.test;

import lab2.model.Timber;
import lab2.store.ProductStore;
import lab2.store.WoodDirectory;

public class TestApp {
    private WoodDirectory wd = new WoodDirectory();
    private ProductStore ps = new ProductStore();

    public static void main(String[] args) {
        TestApp app = new TestApp();
        app.startApp();
    }

    private void startApp(){
        ps.add(new Timber(wd.get(0), 5f,0.5f, 0.4f));
        ps.add(new Timber(wd.get(1), 10f,0.5f, 0.4f));
        System.out.println(wd);
        System.out.println(ps);
        System.out.printf("Weight: %1.3f", calcWeight());
    }
    private float calcWeight(){
        float fullWeight = 0;
        for(Timber timber : ps.getArr()){
            fullWeight += timber.weight();
        }
        return fullWeight;
    }
}
