package PurchasesModule;

import java.io.*;
import java.util.*;
public class Disbursements
{
    //Parses change into dollar/cent segments
    public static void calcDisbursements(double money)
    {
        double[] moneyValues = new double[13];
        try{
            BufferedReader br = new BufferedReader(new FileReader("cashDB.txt"));
            String record = br.readLine();
            while(record != null){
                int index = 0;
                StringTokenizer st = new StringTokenizer(record, ",");
                double cashType = Double.parseDouble(st.nextToken());
                int amount = Integer.parseInt(st.nextToken());

                if(cashType != 0){
                    moneyValues[index] = cashType;
                }     
                index += 1;
            }
        }catch(IOException ioe){
            System.out.println("Failed to load cash storage");
        }
        //money values

        //Stores all the cash used
        double[] cashUsed = new double[13];

        //Number of times each dollar/cent will be handed out
        int[] times = new int[moneyValues.length];

        int upperBound = moneyValues.length;
        double deduction;

        for(int i = 0; i < moneyValues.length; i++){
            double x = money / moneyValues[i];
            deduction = (int)x * moneyValues[i];
            times[i] = (int)x;
            money -= deduction;
            if((money > 0.048) && (money <= 0.049)){
                money = 0.05;
            }
        }

        try{
            for(int i = 0; i < upperBound; i++){
                if((moneyValues[i] * times[i]) != 0){
                    System.out.printf("N$%.2f x %d\n", moneyValues[i], times[i]);
                    cashUsed[i] = moneyValues[i];
                    StockModule.Util.wait(1000);
                }
            }
            StockModule.CashStockModule.Cash.decreaseCash(cashUsed);
        }catch(IndexOutOfBoundsException iobe){
            System.out.println("Something went wrong while printing out your change");
        }
    }
}