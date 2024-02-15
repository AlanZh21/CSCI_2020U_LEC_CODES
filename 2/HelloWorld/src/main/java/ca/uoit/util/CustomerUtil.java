package ca.uoit.util;

import java.io.IOException;
import java.nio.file.Files;
public class CustomerUtil {

    public static String readFileContents(String filename)
    {
        try{
            java.nio.file.Path file = java.nio.file.Path.of(
                    String.valueOf(CustomerUtil.class.getResource(filename)).substring(6)/*looks for file with filename in resources*/
            ); // has prefix file:/, gets dropped by substring
            return Files.readString(file); //return contents of file
        } catch(IOException e) {
            return "An error occurred. \n" +
                    e.toString();
        }
    }

}
