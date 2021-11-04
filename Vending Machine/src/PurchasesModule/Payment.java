/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PurchasesModule;

import java.util.*;

public class Payment
{
    public static double Price;
    public static void runPurchase(String itemCode, int amount)
    {
        //Runs interface before final payment
        Scanner input = new Scanner(System.in);;

        Price = StockModule.Util.getItemPrice("nustVender.txt", itemCode);

        if(Price == 0){
            System.out.println("Purchase Failed, price is null");
            VendingMachine.runMachine();
        }else{
            //Total Price
            Price = Price * amount;
            System.out.printf("Amount Due: N$%.2f", Price);
            System.out.println("\nEnter Payment: "); 
            try{
                double amountTendered = input.nextDouble();
                if(Price > amountTendered){
                    System.out.println("Money is not enough, I'd lend you some ,but I'm broke");
                }else{
                    //Payment is done and item amount is decreased.
                    double change = amountTendered - Price;
                    System.out.println("Purchase Successful🤑");
                    System.out.println("Loading Receipt...");
                    StockModule.Util.wait(1000);
                    //Generate Receipt
                    StockModule.Util.genReceipt("🧊VM🧊", itemCode, Price, change);

                    StockModule.Util.wait(1000);
                    StockModule.ChangeStock.decreaseItemAmount(itemCode, amount);
                    
                    //Change to be displayed on receipt
                    StockModule.Util.wait(2000);
                    if(change > 0){
                        System.out.println("Giving Change...");
                    }else{
                        System.out.println("No change for you. 😂");
                    }
                    StockModule.Util.wait(2000);
                    Disbursements.calcDisbursements(change);

                    System.out.println("\n\n\n\n\n");
                    StockModule.Util.wait(2500);
                }
            }catch(InputMismatchException ime){
                System.out.println("Look Pal, We don't use that currency here 😑");
            }
        }
    }
}
