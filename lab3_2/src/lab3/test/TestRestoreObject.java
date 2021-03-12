package lab3.test;

import lab3.model.Wood;
import lab3.store.WoodDirectory;

import java.io.*;

public class TestRestoreObject {
    public static void main(String[] args) {
        WoodDirectory wd = deserialize("wd.object");
        if(wd != null){
            for (Object w : wd.getArr()){
                System.out.println(w.toString());
            }
        }
    }
    public static WoodDirectory deserialize(String fileName){
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
}
