package basics;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private String filepath;

    public FileHandler(String str) {
        this.filepath = str;
    }

    // make dir and file if not exists;
    private void fileExist() {
        File file = new File(this.filepath);
        File dir = file.getParentFile();
        try {
            if (!dir.exists()) {
                dir.mkdirs(); // Create directories if not exist
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            Print.out("Error creating file: " + e.getMessage());
        }
    }

    public String[] read() {
        this.fileExist();
        List<String> linesList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(this.filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                linesList.add(line); // Add each line to the list
            }
        } catch (IOException e) {
            Print.out("Error occurred: " + e.getMessage());
        }

        // Convert the List to an array and return it
        return linesList.toArray(new String[0]);
    }

    public void write(String content) {
        try (FileWriter writer = new FileWriter(this.filepath, false)) { // false to overwrite
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    
}
