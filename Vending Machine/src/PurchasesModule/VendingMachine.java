/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PurchasesModule;

import java.io.IOException;
import java.util.*;
import java.io.*;

/**
 *
 * @author petri
 */
public class VendingMachine 
{
    public static final String MASTERKEYCODE = ""; 
    //The code for admin, if stored externally use this variable to give access to
    //Admin Nav

    public static void runMachine()
    {
        //StockModule.Util.customLoadScreen("Loading", 1);
        Scanner input = new Scanner(System.in);
        StockModule.Util.customLoadScreen("Loading", 2);
        while(true){
        String itemCode = "";
        int itemAmount = 0;


        try{
            //Asks User For Input
            StockModule.ViewStock.viewAllStock();
            System.out.println("\n\n\nHello There!!\nIm Vendy ♨\n\nWhat would you like to buy? 😊");
            System.out.println("Item Code:"); 
            try{
                itemCode = input.next();

                if(itemCode.equalsIgnoreCase("help")){
                    System.out.println("Commands:\n- exit: exit vending machine\n- help: help menu\n\n\n");
                }
                else if(itemCode.equalsIgnoreCase(MASTERKEYCODE)){
                    try{
                        StockModule.AdminNav.runAdminNav();
                    }catch(IOException ioe){
                        System.out.println("I think I lost my items, check back again later");
                    }
                }
                else if(itemCode.equalsIgnoreCase("exit")){
                    System.out.println("GoodBye!! 😆🖐");
                    StockModule.Util.wait(2000);
                    RunVender.Main.mainProgram();
                }
                else{
                    boolean found = StockModule.Util.itemExists(itemCode);

                    //Get item and check if it exists
                    if(found){
                        System.out.println("How many would you like?: ");
                        try{
                            itemAmount = input.nextInt();

                            //Check if what is asked is more than what is available
                            String record;
                            BufferedReader br = new BufferedReader(new FileReader("nustVender.txt"));
                            record = br.readLine();
                            while(record != null){
                                StringTokenizer st = new StringTokenizer(record, ",");
                                String ItemCode2 = st.nextToken();
                                
                                if(record.contains(itemCode)){
                                    String ItemName = st.nextToken();
                                    String ItemPrice = st.nextToken();
                                    String stockAmount = st.nextToken();

                                    if(stockAmount.equalsIgnoreCase("Unavailable")){
                                        System.out.printf("Sorry, %s is currently Unavailable\n", ItemName);
                                        StockModule.Util.wait(2000);
                                        runMachine();
                                    }else{
                                        int ItemAmount = Integer.parseInt(stockAmount);
                                        if(ItemAmount < itemAmount){
                                            System.out.println("Sorry, You are asking for more than what is available!");
                                            StockModule.Util.wait(2000);
                                            br.close();
                                            break;
                                        }
                                        else{
                                            br.close();
                                            Payment.runPurchase(itemCode, itemAmount);
                                            break;
                                        }
                                    }
                                }
                            }

                        }catch(InputMismatchException ime){
                            System.out.println("Please tell me how many in numbers please! Specifically 1,2,3,4...");
                            StockModule.Util.wait(2000);
                            runMachine();
                        }
                    }else{
                        System.out.println("Sorry That Item Is Not Available.");
                        StockModule.Util.wait(2000);
                    }
                }
            }catch(InputMismatchException ime){
                System.out.println("Please enter and item code from the display");
                System.out.println("If you need any help just say 'help' and I'll assist you! 😁");
            }
        }catch(IOException io){
            System.out.println("Failed To Show The Goods, Perhaps we are out of stock?");
        }
        }
    }

    public static void main(String[] args){
        runMachine();
    }
}


