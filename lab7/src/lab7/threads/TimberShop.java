package lab7.threads;

import lab7.model.IWeight;
import lab7.model.Timber;
import lab7.model.Wood;
import lab7.store.ProductStore;
import lab7.store.WoodDirectory;

import java.util.Random;

public class TimberShop extends Thread{
    WoodDirectory wd;
    ProductStore ps;
    Random rnd = new Random();
    int n;


    public TimberShop(WoodDirectory wd, ProductStore ps, int n) {
        this.wd = wd;
        this.ps = ps;
        this.n = n;
    }
    IWeight createProduct() throws Exception {
        int woodId = rnd.nextInt(3)+1;
        Wood wood = wd.get(woodId);
        float length = 1 + rnd.nextFloat() * 10;
        float height = 0.1f + rnd.nextFloat();
        float width = 0.1f + rnd.nextFloat();
        Timber timber = new Timber(wood, length, height, width);
        return  timber;
    }
    int fibo(int n){
        if(n < 2)
            return 1;
        return  fibo(n - 1) + fibo((n - 2));
    }
    public void run(){
        for(int i = 0; i < n; i++){
            fibo(40 + rnd.nextInt(5));
            try {
                IWeight timber = createProduct();
                System.out.println(this.getName() + " create " + timber);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
