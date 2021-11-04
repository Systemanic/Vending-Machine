package RunVender;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main{
    //public static String[] AdminDetails = new String[2];

    public static void mainProgram(){
        try{
            Scanner sc = new Scanner(System.in);
            //getting functions from different classes
            transition change =new transition();
            pageOne screenLayout1 = new pageOne();
            pageTwo screenLayout2 = new pageTwo();
            pageThree screenLayout3 = new pageThree();
    
            screenLayout1.landing();//Displaying the header for landing page
    
            Scanner input = new Scanner(System.in);
            String choice = input.next();
            //input.close();
    
            //of statement to allow user to navigate to either customer or admin page
            if(choice.equalsIgnoreCase("BUY")){
                change.clearing();
                screenLayout2.customer();
                PurchasesModule.VendingMachine.runMachine();
    
            }else if (choice.equalsIgnoreCase("admin")){
    
                System.out.print("AdminID: ");
                String adminID = input.next();
    
                System.out.print("Password: ");
                String password = input.next();
                
                //AdminDetails[0] = adminID;
                //AdminDetails[1] = password;

                if (adminID.equals("nustVenderAdmin") && password.equals("PRG521S%")){
                    change.clearing();
                    screenLayout3.admin();
                    StockModule.AdminNav.runAdminNav();
                }else{
                    System.out.println("Your Not and Admin Bye");
                    change.clearing();
                }
    
            }
            else{
                System.out.println("doesnt work");
    
            }
        }catch(IOException ioe){
            System.out.println("Oops Something went wrong");
            System.out.println("Where'd my storage room go?");
        }
        //return AdminDetails;
    }

    public static void main(String[] args){
        mainProgram();
    }

}
