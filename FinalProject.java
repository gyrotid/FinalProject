import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class InputValidator {
 
    public static int getUserInput() {
        System.out.print("Enter an integer index: ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println(e + "\nYou did not give me an integer value.");
            System.out.println("Try again!");
            return getUserInput(); // Recursive call
        }
    }
}

public class FileHandler {
 
    public static void saveData(ArrayList<String> data, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String item : data) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
 
    public static ArrayList<String> loadData(String filename) {
        ArrayList<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
        return data;
    }
}