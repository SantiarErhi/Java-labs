package lab6.test;

import lab6.model.Cylinder;
import lab6.model.IWeight;
import lab6.model.Timber;
import lab6.model.Wood;
import lab6.store.ProductStore;
import lab6.store.ProductStoreTest;
import lab6.store.WoodDirectory;

import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TestAppConsole {
    Scanner input = new Scanner(System.in);
    private WoodDirectory wd = new WoodDirectory();
    private ProductStore ps = new ProductStore();
    DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    static Logger LOGGER;
    static {
        try (FileInputStream ins = new FileInputStream("E:\\Java-labs\\lab3_2\\src\\lab3\\log.config")) { //полный путь до файла с конфигами
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(TestAppConsole.class.getName());
            LOGGER.addHandler(new FileHandler("E:\\Java-labs\\lab3_2\\Log.txt"));
        }
        catch (Exception e) {
            e.printStackTrace();

        }

    }


    public static void main(String[] args) {
        TestAppConsole app = new TestAppConsole();
        app.startApp();
    }
    public void startApp(){
        LOGGER.log(Level.INFO, "Starting app by:" + System.getProperty("user.name"));
        System.out.println(wd.toString());
        while(true){
            printMenu();
            mainChoose();
        }
    }
    private void printMenu(){

        System.out.println("1. Add wood");
        System.out.println("2. Add timber");
        System.out.println("3. Add cylinder");
        System.out.println("4. Count all weight");
        System.out.println("5. Serialize");
        System.out.println("6. Deserialize");
        System.out.println("7. Exit");
    }
    private void printSerializeMenu(){
        System.out.println("1. Serialize WoodDirectory");
        System.out.println("2. Serialize ProductStore");
        System.out.println("3. Back");
    }
    private void printDeserializeMenu(){
        System.out.println("1. Deserialize WoodDirectory");
        System.out.println("2. Deserialize ProductStore");
        System.out.println("3. Back");
    }
    private void serializeChoose()
    {
        int num =  input.nextInt();
        switch (num) {
            case 1:
                Serialize(wd, wd.getClass().getName());
                break;
            case 2:
                Serialize(ps, ps.getClass().getTypeName());
                break;
            case 3:
                printMenu();
                mainChoose();
            default:
                System.out.println("Something wrong");
                break;
        }
    }
    private void deserializeChoose()
    {
        int num =  input.nextInt();
        switch (num) {
            case 1:
                wd = deserializeWoodDirectory();
                break;
            case 2:
                ps = deserializeProductStore();
                break;
            case 3:
                printMenu();
                mainChoose();
            default:
                System.out.println("Something wrong");
                break;
        }
    }
    private void mainChoose(){
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
                printSerializeMenu();
                serializeChoose();
                break;
            case 6:
                printDeserializeMenu();
                deserializeChoose();
                break;
            case 7:
                Runtime.getRuntime().exit(0);
                break;
            default:
                ProductStoreTest test= new ProductStoreTest();
                test.remove();
                test.doForAll();
                test.doOnlyFor();
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

    //region Serialize/Deserialize methods
    private WoodDirectory deserializeWoodDirectory(){
        JFileChooser dialog = new JFileChooser();
        dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
        dialog.setDialogTitle("Choose file for deserializing");
        dialog.setApproveButtonText("Open");
        dialog.setMultiSelectionEnabled(false);
        dialog.showOpenDialog(null);
        File f = dialog.getSelectedFile();
        WoodDirectory wd;
        try{
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            wd = (WoodDirectory) ois.readObject();
            LOGGER.log(Level.INFO, "Deserialize WoodDirectory " + wd.toString());
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
    private ProductStore deserializeProductStore(){
        JFileChooser dialog = new JFileChooser();
        dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
        dialog.setDialogTitle("Choose file for deserializing");
        dialog.setApproveButtonText("Open");
        dialog.setMultiSelectionEnabled(false);
        dialog.showOpenDialog(null);
        File f = dialog.getSelectedFile();
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
    //endregion

    //region Adding methods
    private void addTimberFromConsole(){
        try{
            System.out.println("Enter data in format: (int)woodId (float)length (float)height (float)width");
            ps.add(new Timber(wd.get(input.nextInt()), input.nextFloat(), input.nextFloat(), input.nextFloat()));
            LOGGER.log(Level.INFO, "Added timber " + ps.getLastElement().toString());
        }
        catch (Exception e){
            LOGGER.log(Level.INFO, "Error happened while trying to add timber " + e.getMessage());
            addTimberFromConsole();
        }
    }
    private void addCylinderFromConsole(){
        try {
            System.out.println("Enter data in format: (int)woodId (float)length (float)diameter");
            ps.add(new Cylinder(wd.get(input.nextInt()), input.nextFloat(), input.nextFloat()));
            LOGGER.log(Level.INFO, "Added cylinder " + ((Cylinder)ps.getLastElement()).toString());
        }
        catch (Exception e){
            LOGGER.log(Level.WARNING, "Error happened while trying to add cylinder " + e.getMessage());
            addCylinderFromConsole();
        }
    }
    private void addWoodFromConsole(){
        System.out.println("Enter data in format: (string)name (float)density");
        wd.add(new Wood(wd.getCount(), input.next(), input.nextFloat()));
        LOGGER.log(Level.INFO, "Added wood" + wd.getLastElement().toString());
    }
    //endregion

}
