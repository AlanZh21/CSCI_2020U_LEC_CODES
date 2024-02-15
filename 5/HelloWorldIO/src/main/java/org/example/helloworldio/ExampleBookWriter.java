package org.example.helloworldio;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ExampleBookWriter {
    public void createBookFile(File directory, String filename, String filecontent){
        File newFile = new File(directory, filename);
        try {
            if(newFile.createNewFile()){
                System.out.println("File created at: " + newFile.getPath());
            }
            else{
                System.out.println("File already exists at: " + newFile.getPath());
            }
            if (newFile != null){
                PrintWriter output = new PrintWriter(newFile);
                output.print(filecontent);
                output.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
