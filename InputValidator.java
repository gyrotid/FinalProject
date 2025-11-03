import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class InputValidator 
{
 
    public static int getUserInput() 
    {
        System.out.print("Enter an integer index: ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        try 
        {
            return Integer.parseInt(userInput);
        } 
        catch (NumberFormatException e) 
        {
            System.out.println(e + "\nYou did not give me an integer value.");
            System.out.println("Try again!");
            return getUserInput(); // Recursive call
        }
    }
}