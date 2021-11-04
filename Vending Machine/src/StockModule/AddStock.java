package StockModule;

import java.io.*;
import java.util.*;

public class AddStock {

    public static void addNewStock()
    {
        try{
        BufferedWriter bw = new BufferedWriter(new FileWriter("nustVender.txt", true)); //used to output actions do textfile
        Scanner input = new Scanner(System.in);
        //generate 3 digit item code
        double appPrice = 100; //No item price should exceed this. //appPrice --> Appropriate price

        int rowLimit = 30; //Amount of items per row
        boolean done = false;
        String itemCode = "";

        //declare variables for user input
        double itemPrice = 0;
        String itemName = "";
        int stockAmount = 0;

        String itemDetails = "";

        while(!done){
            //get user's input: item name, price and stock amount
            while(true){
                System.out.println("Enter the item: ");
                itemName = input.nextLine();

                //Item Checking..
                if(itemName.isEmpty()){
                    System.out.println("Give me an item name!");
                }
                else if(itemName.length() < 4){
                    System.out.println("Item name should be more than 3 characters!");
                }else{
                    //Caps the item name
                    itemName = Util.Capitalize(itemName);
                    break;
                }
            }

            //Price checking..
            while(true){
                try {
                    System.out.println("Enter the item's price: ");
                    itemPrice = input.nextDouble();
                    if (itemPrice < 0){
                        System.out.println("No negative prices!");
                    }
                    else if(itemPrice > appPrice){
                        System.out.println("Item Price is too high!");
                    }else break;
                }
                catch(InputMismatchException e){
                    System.out.println("Please Enter a price!");
                    addNewStock();
                }
            }

            //Amount Checking..
            while(true) {
                try {
                    System.out.println("Enter stock amount: ");
                    stockAmount = input.nextInt();
                    if (stockAmount > rowLimit) {
                        System.out.println("Too many items for that row!");
                    }
                    else if(stockAmount <= 0){
                        System.out.println("How many items do you want?!");
                    }else break;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an amount!");
                    addNewStock();
                }
            }

            //Confirm insertion
            itemCode = Util.createItemCode(itemName, itemPrice);

            boolean exists = Util.itemExists(itemCode);

            if(exists){
                System.out.printf("%s already is in storage.\n\n", itemName);
                ViewStock.viewAllStock();
                continue;
            }else{
                itemDetails = String.format("%s,%s,%.2f,%d", itemCode, itemName, itemPrice, stockAmount);
                System.out.printf("Confirm item insertion of --->[ %s ] (Y/N):", itemDetails);
                String confirm = input.next();

                if (confirm.equalsIgnoreCase("y")) {
                    done = true;
                }
            }
        }

        //save the user's input in text file
        bw.write(itemDetails);
        System.out.println(itemName+" Saved with an item code of "+itemCode);
        bw.flush();
        bw.newLine();
        bw.close();
        }catch(IOException ioe){
            System.out.println("Failed to Load DB for Alteration");
        }
    }


    public static void main(String[] args) throws IOException
    {
        addNewStock();
    }
}
