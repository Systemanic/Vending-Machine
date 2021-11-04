package StockModule;

import java.io.*;
import java.util.*;
public class ChangeStock {

    public static void changePrice(){
        try{
            String record, record2;

            //flag --> if item is there
            boolean found = false;
            double newPrice = 0;
    
            File db = new File("nustVender.txt"); //get the text file
            File tempDB = new File("nustVenderTemp.txt"); //get a temporary text file for the operations
    
            // create a reader and writer for the text files
            BufferedReader br = new BufferedReader( new FileReader(db) ); //reads the text file
            BufferedWriter bw = new BufferedWriter( new FileWriter(tempDB) ); // writes to the temporary text file
    
            Scanner input = new Scanner(System.in);
    
            //get item the user is looking for
            System.out.println("Enter the item code: ");
            String searchCode = input.next();
    
            // display the full details of the item
            while( ( record = br.readLine() ) != null ) {
    
                StringTokenizer st = new StringTokenizer(record,",");
                String tempCode = st.nextToken();// pos 1
                String itemName = st.nextToken();// pos 2
                double tempPrice = Double.parseDouble(st.nextToken());// pos 3
                String stockAmount = st.nextToken();// pos 4
    
                //Checks if the search code is in the record
                if( record.contains(searchCode) ) {
                    System.out.printf("%-20s%-20s%-20.2f%-20s\n", tempCode, itemName, tempPrice, stockAmount);
                    found = true;
                }
            }
            br.close();
    
            if(found){
                //get the new price
                System.out.println("Enter the new price: ");
                newPrice = input.nextDouble();
            }else{
                System.out.println("Item does not exist");
                System.out.println("Here are the available list of items:\n");
                ViewStock.viewAllStock();
    
                changePrice();
            }
    
            BufferedReader br2 = new BufferedReader( new FileReader(db) ); //read the text file
    
            //save the new price
            while( (record2 = br2.readLine() ) != null ) {
                StringTokenizer st = new StringTokenizer(record2,",");
                String tempCode = st.nextToken();
                String itemName = st.nextToken();
                String tempPrice = st.nextToken();
                String stockAmount = st.nextToken();
    
                // save the changes made and print them
                String newItemCode = Util.createItemCode(itemName, newPrice);
                if(record2.contains(searchCode)) {
                    bw.write(newItemCode+","+itemName+","+newPrice+","+stockAmount);
                    System.out.printf("%-20s%-20s%-20.2f%-20s\n", newItemCode, itemName, newPrice, stockAmount);
                } else {
                    bw.write(record2);
                }
                bw.flush();
                bw.newLine();
            }
    
            bw.close();
            br2.close();
            db.delete();
            boolean success = tempDB.renameTo(db);
            System.out.println(success);
        }catch(IOException ioe){
            System.out.println("Where is the storage room so that I can change the prices??");
        }

    }

    public static String ItemCode2, ItemName, ItemPrice, NewRecord;
    public static String tempCode, tempName, tempPrice, tempAmount;
    public static int ItemAmount;

    public static void decreaseItemAmount(String identifier, int decrease)
    {
        //Method that decreases the amount of items and returns whether ot not they are available
        //Should return item availability based on amount remaining

        try{
            String record;


            File db = new File("nustVender.txt"); //get the text file
            File tempDB = new File("nustVenderTemp.txt"); //get a temporary text file for the operations

            // create a reader and writer for the text files
            BufferedReader br = new BufferedReader( new FileReader(db) ); //reads the text file
            BufferedWriter bw = new BufferedWriter( new FileWriter(tempDB) ); // writes to the temporary text file

            //get item the user is looking for
            while( ( record = br.readLine() ) != null ) {

                StringTokenizer st = new StringTokenizer(record,",");
                tempCode = st.nextToken();// pos 1
                tempName = st.nextToken();// pos 2
                tempPrice = st.nextToken();// pos 3
                int itemAmount = Integer.parseInt(st.nextToken());// pos 4

                //Checks if the search code is in the record
                if( record.contains(identifier) ) {
                    itemAmount = itemAmount - decrease;
                    tempAmount = String.valueOf(itemAmount);

                    if(itemAmount <= 0){
                        tempAmount = "Unavailable";
                    }
                    String newRecord = String.format("%s,%s,%s,%s", tempCode, tempName, tempPrice, tempAmount);
                    bw.write(newRecord);
                    
                }else{
                    bw.write(record);
                }
                bw.flush();
                bw.newLine();
            }
            br.close();
            bw.close();
            db.delete();
            Util.wait(2500);

            boolean successful = tempDB.renameTo(db);

            System.out.println("Paid:" + successful);
            System.out.println("Loading...");
            Util.wait(2500);

        }catch(IOException ioe){
            System.out.println("Oops I forgot where the storage room is 😋");
        }
    }

    public static void main(String[] args){
        ChangeStock.changePrice();
    }
}
