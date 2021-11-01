package application;

//This program demonstrates how to read-in a text file by using the FileRead Buffer class

import java.io.*;
import java.util.Scanner;

public class ReadTextFileUseScanner
{
public static void main (String[] args)
 {
   String line;
   File file = null;

   //It's always a good practice to catch I/O exception when reading/writing from/to a file
   try
   {
		 //Open the file: create a File object from input.txt
   	 file = new File("input1.txt");

		 //create a Scanner object from the file
		 Scanner input = new Scanner(file);

       while (input.hasNext())
       {
         line = input.nextLine();
         System.out.println(line);

       }

       //Close the file: by closing the Scanner object created
       input.close();

     }
    catch (FileNotFoundException exception)
    {
       System.out.println ("The file " + file.getPath() + " was not found.");
    }
    catch (IOException exception)
    {
       System.out.println (exception);
    }
 }
}
