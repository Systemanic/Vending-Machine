package StockModule;

import java.io.*;
import java.util.*;
public class DeleteStock 
{

    public static void DeleteRecordByID()
    {
        try{
            Scanner input =  new Scanner(System.in);
            String itemCode, record;

            //flag for checking if item exists
            boolean found = false;

            File tempDB = new File("nustVenderTemp.txt"); //get the text file
            File db = new File("nustVender.txt"); //get a temporary text file for the operations

            // create a reader and writer for the text files
            BufferedReader br = new BufferedReader( new FileReader( db ) ); //reads the text file
            BufferedWriter bw = new BufferedWriter( new FileWriter( tempDB ) ); // writes to the temporary text file

            // get the item to be deleted
            System.out.println("Enter the item's code ");
            itemCode =  input.next();

            while( ( record = br.readLine() ) != null ) {

                // deletes the stock from the database
                if( record.contains(itemCode) ){
                    found = true;
                }else{
                    bw.write(record);
                    bw.flush();
                    bw.newLine();
                }

            }
            if (found){
                System.out.printf("Deleting all with item code: %s\n", itemCode);
            }else{
                System.out.printf("Could not find key %s for record %s, as it does not exist.\n", itemCode, record);
                AdminNav.runAdminNav();
            }
            br.close();
            bw.close();
            db.delete();
            tempDB.renameTo(db);
        }catch(IOException ioe){
            System.out.println("Error could not find database, to make changes");
        }
    }

    public static void main(String[] args)
    {
        DeleteStock.DeleteRecordByID();
    }

}
