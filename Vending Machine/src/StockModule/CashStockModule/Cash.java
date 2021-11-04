package StockModule.CashStockModule;


import java.io.*;
import java.util.*;

import StockModule.AdminNav;

public class Cash {

    public static void viewAllCash(){
        try{
            BufferedReader br = new BufferedReader( new FileReader("cashDB.txt") );

            String record;


            System.out.printf("%-20s%-20s\n", "Cash Type", "Amount Stored");
            System.out.println("-------------------------------------");

            //print the columns
            while( ( record = br.readLine() ) != null ) {

                //get the value of each column separated by a comma
                StringTokenizer st = new StringTokenizer(record,","); //breaks up the column into tokens

                String cashType = st.nextToken(); //assign column one to itemCode
                String cashAmount = st.nextToken(); //assign column two to itemName

                //prints the row
                System.out.printf("%-20s%-20s\n", cashType, cashAmount);

            }
            br.close(); //close reader
        }catch(IOException ioe){
            System.out.println("Could Not Find Cash Database");
        }

    }

    public static void main(String[] args)
    {
        viewAllCash();
    }

    public static void decreaseCash(double[] cashUsed)
    {
        // Gets all the cash used during disbursement
        String record;
        try{
            File db = new File("cashDB.txt");
            File tempDB = new File("cashDBTemp.txt");
            BufferedReader br = new BufferedReader(new FileReader(db));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));
            record = br.readLine();
            while(record != null){

                // Finds all instances of each value within the cashUsed Array
                StringTokenizer st = new StringTokenizer(record, ",");
                double cashType = Double.parseDouble(st.nextToken());
                int amount = Integer.parseInt(st.nextToken());

                // If value is found reduce it.
                for(int i = 0; i < cashUsed.length; i++){
                    if(cashType == cashUsed[i]){
                        amount -= cashUsed[i];
                    }
                }

                String newRecord = String.format("%.2f,%d", cashType, amount);
                bw.write(newRecord);
                bw.flush();
                bw.newLine();
            }
            br.close();
            bw.close();
            db.delete();
            tempDB.renameTo(db);

        }catch(IOException ioe){
            System.out.println("Failed to find Cash Storage");
        }
    }

    public static void alterCash()
    {
        //Changes the amount of cash in cashDB
        double limit = 200;
        Scanner in = new Scanner(System.in);
        String record;

        System.out.println("Enter the cash type you would like to change");

        try{

            double cash = in.nextDouble();
            if(cash <= 0){
                System.out.println("Cash type can't be 0 or negative");
                Cash.alterCash();
            }

            else if(cash > limit){
                System.out.println("That cash type is over your currency's limit!");
                Cash.alterCash();
            }else{

                System.out.println("Enter new cash amount: ");
                int cashAmount = in.nextInt();

                if(cashAmount <= 0){
                    System.out.println("Cash amount can't be 0 or negative");
                    Cash.alterCash();
                }

                try{
                    File db = new File("cashDB.txt");
                    File tempDB = new File("cashDBTemp.txt");
                    BufferedReader br = new BufferedReader(new FileReader(db));
                    BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));
                    while((record = br.readLine()) != null){
        
                        StringTokenizer st = new StringTokenizer(record, ",");
                        double cashType = Double.parseDouble(st.nextToken());
                        int amount = Integer.parseInt(st.nextToken());

                        if(cash == cashType){
                            amount = cashAmount;
                            String newRecord = String.format("%.2f,%d", cashType, amount);
                            bw.write(newRecord);
                            bw.flush();
                            bw.newLine();
                        }
                        bw.write(record);
                        bw.flush();
                        bw.newLine();
                    }
                    br.close();
                    bw.close();
                    db.delete();
                    tempDB.renameTo(db);

                }catch(IOException ioe){
                    System.out.println("Failed to find Cash Storage");
                }
            }
        }catch(InputMismatchException ime){
            System.out.println("Please enter a cash type");
            Cash.alterCash();
        }
    }
}

