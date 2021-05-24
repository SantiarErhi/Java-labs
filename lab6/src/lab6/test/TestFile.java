package lab6.test;

import javax.swing.*;
import java.io.File;

public class TestFile {
    public static void main(String[] args) {
        JFileChooser dialog = new JFileChooser();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        dialog.showOpenDialog(null);
        File f = dialog.getSelectedFile();
        if(f != null){
            System.out.println(f.getName());
            System.out.println(f.getAbsolutePath());
        }
    }
}
