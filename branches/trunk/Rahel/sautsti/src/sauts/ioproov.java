package sauts;

//Vaike demo failist ridade kaupa lugemise kohta,
//mis lihtsalt kopeerib faili.
//
//NB! Vt teisi naiteid Ecki opik sektsioon 10.3
//
//Olulist tahele panna:
//- Programm loeb kasurealt sisendfaili ja valjundfaili nimed
//- Valjundfaili kirjutatakse read, tapselt nagu sisendist loetud
//- Kui valjundfail oli varem juba olemas, siis programm ei tee koopiat ja peatub.
//- Rea kaupa lugemiseks kasutab BufferedReader klassist funktsiooni readLine
//- Trykkimiseks kasutatav println on sama, mis System.out jaoks println, ainult
//  et valjund laheb avatud faili, mis on System.out asemel.
//- Avatud failid tuleb peale nende kasutamist sulgeda


//Must import java.io.* in order to get access to libraries

import java.io.*;

public class ioproov {

public static void main(String[] args) {
 
 String sourceName;
 String copyName;
 
 /* Get two file names from the command line.
    If the command line does not have a legal form,
    print an error message and end this program. */
 
 if (args.length == 2) {
   sourceName = args[0];
   copyName = args[1];      
 }
 else {
   System.out.println("Usage:  java ioproov <source-file> <copy-name>");      
   return;
 }
 
 
 /* If the output file already exists,
    print an error message and end the program. */
   
 File file = new File(copyName);
 if (file.exists()) {
   System.out.println("Output file exists. Exiting.");
   return;  
 }
 
 // All file I/O operations must be done within a try/catch statement
 
 try {

   // Set up the proper readers and writers
   FileReader file_reader = new FileReader(sourceName);
   FileWriter file_writer = new FileWriter(copyName);
   BufferedReader br_reader = new BufferedReader(file_reader);
   PrintWriter pr_writer = new PrintWriter(file_writer);

   // Get the first line of the file
   String line = br_reader.readLine(); 
  
   // check to see if it is null - end of file
   while (line != null) {

     // print line to output file
     pr_writer.println(line);

     // get next line of input file
     line = br_reader.readLine();

   } // while loop

   // Finally you must close input and output streams!

   file_reader.close();
   file_writer.close();

 } //try

 // catch any exceptions thrown
 catch (IOException e) {
   //print error and exit program
   System.out.println(e);
   System.exit(1);
 }
}
}

