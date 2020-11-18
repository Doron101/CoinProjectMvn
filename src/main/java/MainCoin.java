import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import java.util.Locale;
import java.util.Scanner;
import java.io.IOException;

public class MainCoin {
    static CoinFactory CFactory = new CoinFactory();
    static Scanner scanner = new Scanner(System.in);
    static String choice;
    static int ListIdx = 0;

    static ArrayList<Double> CoinList = new ArrayList<Double>();

    public static void main(String[] args) throws IOException, InterruptedException, Exception {
        Coin cur_coin;
        boolean convert = true;

        System.out.print(">>>>> Welcome to currency converter <<<<<\n");

        while (convert) {
            cur_coin = firstScreen();
            convert = thirdScreen(cur_coin, secondScreen());
        }

        fourthScreen();


    }
    /***********************************************************************\
    // The First Screen
    \***********************************************************************/
     static public Coin firstScreen(){
        System.out.println("Please choose an option (1/2):");
        System.out.println("                              1. Shekels to Dollars");
        System.out.println("                              2. Dollars to Shekels");
        //Scanner scanner = new Scanner(System.in);
        // initialize "choice" in order to get-by the while-condition
        choice = "1";
          while(!choice.equals("1")||!choice.equals("2"))
        {  choice = scanner.next();

           switch (choice)
            {
                case "1":
                    System.out.println("You choose to convert ILS to USD");
                    return CFactory.getCoin("usd");
                case "2":
                    System.out.println("You choose to convert USD to ILS");
                    return CFactory.getCoin("ils");
                default:
                    System.out.println("Wrong choice. Pls enter 1 or 2 only");
            }
        } // of the while
        // The method flow will never get here, but it is needed to "relax" the compiler....
        return null;
    }; // of firstScreen

    /***********************************************************************\
    // The Second Screen
    \***********************************************************************/
     static double  secondScreen(){
         String amount;
         int exit = 1;
         Double d = Double.parseDouble("0");
         System.out.println("Please enter the amount to convert: ");

         while(exit==1){
              amount  = scanner.next();
              exit =0;
              try {
                   d = Double.parseDouble(amount);
                  }
              catch (NumberFormatException ex) {
                       System.out.println("Pls enter the amount in digits only");
                       exit=1;
                  }
         }// of while

         return d;
     }// of secondScreen

    /***********************************************************************\
     // The Third Screen
    \***********************************************************************/
    static boolean  thirdScreen(Coin a,double amt) {

        saveList(a,amt);

        System.out.println("The converted amount is: " +a.calculate(amt));
        System.out.println("\n Would you like to convert some more? y/n");
        choice="y";
        while(!choice.equalsIgnoreCase("y")||!choice.equalsIgnoreCase("n")){
            choice = scanner.next();
            //switch (choice)

            if(choice.equalsIgnoreCase("Y"))
               return true;
            if(choice.equalsIgnoreCase("N"))
                return false;

            System.out.println("Wrong choice. Pls enter Y or N only");

        } // of while

        return false;
    }// of thirdScreen

    /***********************************************************************\
     // The Fourth Screen
    \***********************************************************************/
    static void fourthScreen() {
       try {
            printList();
       }catch(Exception e) {
           e.printStackTrace();
       }
        System.out.println("\n A file called \"content.txt\" containing all the convertions you asked");
        System.out.println(">>>>> Thanks for using our currency converter <<<<<");
    }
    /***********************************************************************\
    // Save to list
   \************************************************************************/
    static void saveList(Coin a,double amt){

        switch (a.getClass().getSimpleName()) {
            case "USD":
                CoinList.add(new Double(1));
                CoinList.add(new Double(amt));
                CoinList.add(new Double(a.calculate(amt)));
                break;
            case "ILS":
                CoinList.add(new Double(2));
                CoinList.add(new Double(amt));
                CoinList.add(new Double(a.calculate(amt)));
                break;
            default:
                break;
        }

    } // of saveList
    /***********************************************************************\
     // Print list
    \***********************************************************************/
    static void printList() throws Exception{
           String line ;
           line=" ";
           Files.write(Paths.get("content.txt"), line.getBytes());
           //FileWriter fileWritter = new FileWriter("content.txt", true);
           //BufferedWriter bw = new BufferedWriter(fileWritter);

           for(int i=0; i < CoinList.size();){
               switch ((int) Math.round(CoinList.get(i++))) {
                   case 1: line = "Convert from " +CoinList.get(i++) + " ILS to " +CoinList.get(i++) + " USD. ";
                           break;
                   case 2: line = "Convert from " +CoinList.get(i++) + " USD to " +CoinList.get(i++) + " ILS. ";
                           break;

               }
               System.out.println(line);
               line = line.concat("\n");
               Files.write(Paths.get("content.txt"), line.getBytes(), StandardOpenOption.APPEND);

           }// of for
    }

}
