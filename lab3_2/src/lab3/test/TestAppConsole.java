package lab3.test;

import lab3.model.Cylinder;
import lab3.model.IWeight;
import lab3.model.Timber;
import lab3.model.Wood;
import lab3.store.ProductStore;
import lab3.store.WoodDirectory;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TestAppConsole {
    Scanner input = new Scanner(System.in);
    private WoodDirectory wd = new WoodDirectory();
    private ProductStore ps = new ProductStore();
    DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    BufferedWriter writer;


    public static void main(String[] args) {
        TestAppConsole app = new TestAppConsole();
        app.startApp();
    }
    public void startApp(){
        try {
            writer = new BufferedWriter(new FileWriter("Log.txt",true));
            writeString("Starting app");
            writeString("App started by Svyatoslav Slobodyan");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(wd.toString());
        while(true){
            printMenu();
            Choose();
        }
    }
    private void writeString(String s){
        try {
            writer.write(dateFormat.format(System.currentTimeMillis()) + " ");
            writer.write(s);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private WoodDirectory deserializeWoodDirectory(String fileName){
        File f = new File(fileName);
        WoodDirectory wd;
        try{
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            wd = (WoodDirectory) ois.readObject();
            ois.close();
            return wd;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    private ProductStore deserializeProductStore(String fileName){
        File f = new File(fileName);
        ProductStore ps;
        try{
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ps = (ProductStore) ois.readObject();
            ois.close();
            return ps;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void Serialize(Object object, String objectName){
        File f = new File(objectName + ".object");
        try{
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void printMenu(){

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
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Runtime.getRuntime().exit(0);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + num);
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
            writeString("Added timber with weight - " + ((IWeight) ps.getLastElement()).weight());
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
            writeString("Added cylinder with weight - " + ((IWeight) ps.getLastElement()).weight());

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
