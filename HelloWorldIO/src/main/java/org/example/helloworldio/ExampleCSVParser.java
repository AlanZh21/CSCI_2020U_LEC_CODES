package org.example.helloworldio;

import java.io.*;

public class ExampleCSVParser {
    File file = null;
    ExampleCSVParser(File file){
        this.file = file;
    }

    public String getColumnAverage(String colName){
        String result = "Error";
        FileReader fileInput = null;
        try {
            fileInput = new FileReader(file);
            BufferedReader input = new BufferedReader(fileInput);
            String line = input.readLine();
            String[] colNames = line.split(","); //split Headers

            int colIndex = -1;
            for(int i = 0; i<colNames.length; i++){
                if(colNames[i].equals(colName)){
                    colIndex = i;
                    break;
                }
            }
            if(colIndex < 0){
                System.err.println("Error: Column not fond in grades.csv");
                System.exit(0);
            }
            float colTotal = 0f;
            int rowCount = 0;
            while((line = input.readLine()) != null){
                String[] colValues = line.split(",");
                float val = Float.parseFloat(colValues[colIndex]);
                colTotal += val;
                rowCount++;
            }
            result = "The average of column " + colName + " is: " + (colTotal/rowCount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
