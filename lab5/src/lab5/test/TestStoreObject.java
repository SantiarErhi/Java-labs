package lab5.test;

import lab5.model.Wood;
import lab5.store.WoodDirectory;

import java.io.*;

public class TestStoreObject {
    public static void main(String[] args) {
        WoodDirectory wd = new WoodDirectory();
        wd.add(new Wood(4, "Dub", 2));
        Serialize(wd, "wd");
    }
    public static void Serialize(Object object, String objectName){
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
}
