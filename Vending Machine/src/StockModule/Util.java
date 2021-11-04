package StockModule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
    Stores all function utilities for use in the VM
*/

public class Util {

//Methods Related to Item info
    private static boolean Found;
    public static boolean itemExists(String itemCode)
    { // Just checks if an item Exists already
        try{
            Found = false;
            BufferedReader br = new BufferedReader(new FileReader("nustVender.txt"));
            String record;
            while((record = br.readLine()) != null){

                StringTokenizer st = new StringTokenizer(record, ",");
                String code = st.nextToken();

                if(itemCode.equalsIgnoreCase(code)){
                    //Duplicate Item Found or Item Has been Found.
                    Found = true;
                    br.close();
                    break;
                }
            }
        }catch(IOException io){
            System.out.printf("Error File %s was not found.", "nustVender.txt");
        }
        return Found;
    }

    public static String createItemCode(String item, double unitPrice)
    {
        String price = "";
        DecimalFormat dfc = new DecimalFormat("##C");
        DecimalFormat df = new DecimalFormat("##");
        if(unitPrice < 1){
            price = dfc.format((unitPrice * 100));
        }else{
            price = df.format(unitPrice);
        }
        //Uses item name and the first 2 digits of the unit price to create the item code.
        item = item.toUpperCase();

        char[] characters = item.toCharArray();

        String itemCode = String.format("%s%s%s%s", characters[0], characters[1], characters[2], price);
        return itemCode;
    }

    public static double Price;

    public static double getItemPrice(String fileName, String identifier)
    {
        //Gets an item given that it has an identifier and stores its data in an array
        try{
            String record;

            BufferedReader br = new BufferedReader(new FileReader("nustVender.txt"));
            record = br.readLine();
            while(record != null){
                if(record.contains(identifier)){
                    StringTokenizer st = new StringTokenizer(record, ",");
                    String itemCode = st.nextToken();
                    String itemName = st.nextToken();
                    double itemPriceST = Double.parseDouble(st.nextToken());
                    String itemAmount = st.nextToken();

                    /*Debugging
                    System.out.printf("Getting %s %s %s %s\n", itemCode, itemName, itemPriceST, itemAmount);
                    */
                    
                    Price += itemPriceST;
                    break;
                }
            }
            br.close();
        }catch(IOException ioe){
            System.out.println("Error Finding Database");
        }
        return Price;
    }

//Misc
    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();               
        /*This clears the console before a new screen is displayed 
        this makes it look like you are navigating between pages*/
    }

    public static void wait(int time){
        //Just a normal sleep function
        try{
            Thread.sleep(time);
        }catch(InterruptedException ie){
            System.out.println("... ... ...");
        }
    }

    public static String Capitalize(String word)
    {
        //Caps stuff
        String word2 = word.toUpperCase();
        char l = word2.charAt(0);
        String capsWord = word.replace(word.charAt(0), l);
        return capsWord;
    }

    public static int Limit;
    public static void countLines()
    {
        try{
            int amount = 0;
            Limit = 30;
            File db = new File("nustVender.txt");
            Scanner sc = new Scanner(db); // create an object of Scanner associated with the file

            // read each line and count number of lines
            while (sc.hasNextLine()) {
                sc.nextLine();
                amount++;
            }
            sc.close();

            if (amount <= Limit){
                System.out.println("");
                AddStock.addNewStock();
            }else {
                System.out.println("Stock limit reached");
                System.out.printf("Stock limit: %d", Limit);
            }
        }catch(IOException ioe){
            System.out.println("I Really do keey losing my storage Huh?");
            System.out.println("Or Maybe I'm Just Empty?");
        }
    }
    public static void genReceipt(String Title, String identifier, double total, double change)
    {
        //Generates a receipt given an identifier and a custom Title
        try{
            String record;
            BufferedReader br = new BufferedReader(new FileReader("nustVender.txt"));
            record = br.readLine();
            while(record != null){
                if(record.contains(identifier)){
                    StringTokenizer st = new StringTokenizer(record, ",");
                    String itemCode = st.nextToken();
                    String itemName = st.nextToken();
                    String itemPrice = st.nextToken();
                    String itemAmount = st.nextToken();

                    System.out.println("----------------------------------------------------------------");
                    System.out.printf("%31s\n", Title);
                    System.out.println("----------------------------------------------------------------\n\n");
                    System.out.printf("%-20s%-15s%-15s%-15s\n", "Item", "Code", "Amount", "Price per item");
                    System.out.printf("%-20s%-15s%-15s%-15s\n\n", "---------", "---------", "---------", "--------------");
                    System.out.printf("%-20s%-15s%-15s%-15s\n\n\n", itemName, itemCode, itemAmount, itemPrice);
                    System.out.println("----------------------------------------------------------------");
                    System.out.printf("%-20s%36.2f\n", "Total:", total);
                    System.out.printf("%-20s%36.2f\n", "Change:", change);
                    System.out.println("----------------------------------------------------------------");
                    break;
                }
            }
            br.close();
        }catch(IOException ioe){
            System.out.println("Oops, must've lost the database again 🕵️‍♂️");
        }
    }

//Animations or the fun stuff
    public static void customLoadScreen(String string, int iterations)
    {
        // Just some console loading animation?
        int displays = 0;
        while(displays < iterations){
            clear();
            System.out.println(string + ".");
            wait(2500);
            clear();
            System.out.println(string + "..");
            wait(2500);
            clear();
            System.out.println(string + "...");
            wait(2500);
            displays += 1;
        }
    }

    //For debugging
    
    /*
    public static void main(String[] args)
    {
        genReceipt("Test", "CHI12", 100.00);
    }
    */
}
