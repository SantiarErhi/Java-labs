package lab3.test;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class TestFileSettings {
    public static void main(String[] args) {
        JFileChooser dialog = new JFileChooser();
        dialog.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        dialog.setDialogTitle("Choose files and directories");
        dialog.setApproveButtonText("Open");
        dialog.setMultiSelectionEnabled(true);
        dialog.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if(f != null){
                    return f.isDirectory() || f.toString().endsWith(".txt");
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "Txt files (*.txt)";
            }
        });
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
        File[] files = dialog.getSelectedFiles();
        if(files != null){
            for(File file : files) {
                System.out.println(file.getName());
                System.out.println(file.getAbsolutePath());
            }
        }
    }
}
