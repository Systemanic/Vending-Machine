package StockModule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ViewStock {

    public static void viewAllStock() throws IOException {
        BufferedReader br = new BufferedReader( new FileReader("nustVender.txt") );

        String record;


        System.out.printf("%-20s%-20s%-20s%-20s\n", "Item Code", "Item Name", "Item Price", "Stock Amount");
        System.out.println("-----------------------------------------------------------------------------");

        //print the columns
        while( ( record = br.readLine() ) != null ) {

            //get the value of each column separated by a comma
            StringTokenizer st = new StringTokenizer(record,","); //breaks up the column into tokens

            String itemCode = st.nextToken(); //assign column one to itemCode
            String itemName = st.nextToken(); //assign column two to itemName
            double itemPrice = Double.parseDouble(st.nextToken()); //assign column three to itemPrice
            String stockAmount = st.nextToken(); //assign column four to stockAmount

            //prints the row
            System.out.printf("%-20s%-20s%-20.2f%-20s\n", itemCode, itemName, itemPrice, stockAmount);

        }
        br.close(); //close reader

    }

    public static void main(String[] args) throws IOException {
        ViewStock.viewAllStock();
    }
}
