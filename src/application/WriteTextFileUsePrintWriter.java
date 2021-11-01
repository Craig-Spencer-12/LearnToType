package application;

//This program demonstrates how to write into a text file by using the PrintWriter class

import java.io.*;

public class WriteTextFileUsePrintWriter
{
public static void main (String[] args) throws Exception
 {
   //create a text file which you will write data to
   File outputFile = new File("ooo.txt");

   //check whether the file's name already exists in the current directory
   if(outputFile.exists())
   {
		 System.out.println("File already exists");
		 System.exit(0);
	 }

	 //create a PrintWriter object
	 PrintWriter output = new PrintWriter(outputFile);

	 //write to above output file by using print() & println() methods
	 output.print("CSE205 Object Oriented ");
	 output.println("Programming & Data Structure");

	 output.println();

	 for (int i=1; i <= 10; i++)
	 {
	     output.print ("The value is " + i );
	     output.println ();
	 }

	 //close the file
	 output.close();
}
}
