package StockModule;

import java.io.IOException;
import java.util.*;

public class AdminNav {

    public static void runAdminNav() throws IOException
    {

        Scanner input = new Scanner(System.in);
        boolean done = false;
        String answer = "";
        String choice = "y";

        while (!done){

            System.out.println("1) Add New Stock");
            System.out.println("2) Change Stock Price");
            System.out.println("3) Remove Stock");
            System.out.println("4) Show Stock");
            System.out.println("5) Show Cash in Stock");
            System.out.println("6) Change Cash Amount");

            System.out.println("Please select your choice");
            choice = input.nextLine();

            if (choice.equals("1")){
                Util.countLines();
            }
            else if (choice.equals("2")){
                ChangeStock.changePrice();
            }
            else if (choice.equals("3")){
                DeleteStock.DeleteRecordByID();
            }
            else if(choice.equals("4")) {
                ViewStock.viewAllStock();
            }
            else if(choice.equals("5")) {
                StockModule.CashStockModule.Cash.viewAllCash();
            }
            else if(choice.equals("6")) {
                StockModule.CashStockModule.Cash.alterCash();
            }
            else{
                System.out.println("Sorry I don't understand?");
            }

            Util.wait(1000);
            System.out.println("Would you like to continue? (y/n): ");
            answer = input.nextLine();

            if(answer.equalsIgnoreCase("y")){
                continue;
            }else {
                done = true;
            }
        }
        RunVender.Main.mainProgram();
    }

    public static void main(String[] args) throws IOException{
        runAdminNav();
    }
}
