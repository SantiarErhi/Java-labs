package lab1.test;

import lab1.model.Timber;
import lab1.model.Wood;
import lab1.store.ProductStore;
import lab1.store.WoodDirectory;

import java.util.Scanner;

public class TestAppConsole {
    Scanner input = new Scanner(System.in);
    private WoodDirectory wd = new WoodDirectory();
    private ProductStore ps = new ProductStore();

    public static void main(String[] args) {
        TestAppConsole app = new TestAppConsole();
        app.startApp();
    }
    public void startApp(){
        while(true){
            printMenu();
            Choose();
        }
    }
    public void printMenu(){

        System.out.println("1. Add wood");
        System.out.println("2. Add timber");
        System.out.println("3. Count all weight");
        System.out.println("4. Exit");
    }
    private void Choose(){
        int num =  input.nextInt();
        switch (num){
            case 1:
                addWoodFromConsole();
                break;
            case 2:
                addTimberFromConsole();
                break;
            case 3:
                printAllWeight();
                break;
            case 4:
                Runtime.getRuntime().exit(0);
                break;
        }
    }
    private void printAllWeight(){
        System.out.println("All weight: " + calcWeight() + "\n");
    }
    private float calcWeight(){
        float fullWeight = 0;
        for(Timber timber : ps.getArr()){
            fullWeight += timber.weight();
        }
        return fullWeight;
    }
    private void addTimberFromConsole(){
        System.out.println("Enter data in format: (int)woodId (float)length (float)height (float)width");
        ps.add(new Timber(wd.get(input.nextInt()), input.nextFloat(),input.nextFloat(),input.nextFloat()));
    }
    private void addWoodFromConsole(){
        System.out.println("Enter data in format: (int)id (string)name (float)density");
        wd.add(new Wood(input.nextInt(), input.next(), input.nextFloat()));
    }
}
