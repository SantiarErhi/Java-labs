package lab3.test;

import lab3.model.Cylinder;
import lab3.model.IWeight;
import lab3.model.Timber;
import lab3.model.Wood;
import lab3.store.ProductStore;
import lab3.store.WoodDirectory;

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
        System.out.println(wd.toString());
        while(true){
            printMenu();
            Choose();
        }
    }
    public void printMenu(){

        System.out.println("1. Add wood");
        System.out.println("2. Add timber");
        System.out.println("3. Add cylinder");
        System.out.println("4. Count all weight");
        System.out.println("5. Exit");
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
                addCylinderFromConsole();
                break;
            case 4:
                printAllWeight();
                break;
            case 5:
                Runtime.getRuntime().exit(0);
                break;
        }
    }
    private void printAllWeight(){
        System.out.println("All weight: " + calcWeight() + "\n");
    }
    private float calcWeight(){
        float fullWeight = 0;
        for(Object timber : ps.getArr()){
            fullWeight += ((IWeight)timber).weight();
        }
        return fullWeight;
    }
    private void addTimberFromConsole(){
        try{
            System.out.println("Enter data in format: (int)woodId (float)length (float)height (float)width");
            ps.add(new Timber(wd.get(input.nextInt()), input.nextFloat(), input.nextFloat(), input.nextFloat()));
            System.out.println("Weight of added timber is: " + ((IWeight) ps.getLastElement()).weight());
        }
        catch (Exception e){
            e.printStackTrace();
            addTimberFromConsole();
        }
    }
    private void addCylinderFromConsole(){
        try {
            System.out.println("Enter data in format: (int)woodId (float)length (float)diameter");
            ps.add(new Cylinder(wd.get(input.nextInt()), input.nextFloat(), input.nextFloat()));
            System.out.println("Weight of added cylinder is: " + ((IWeight) ps.getLastElement()).weight());
        }
        catch (Exception e){
            e.printStackTrace();
            addCylinderFromConsole();
        }
    }
    private void addWoodFromConsole(){
        System.out.println("Enter data in format: (int)id (string)name (float)density");
        wd.add(new Wood(input.nextInt(), input.next(), input.nextFloat()));
    }
}
