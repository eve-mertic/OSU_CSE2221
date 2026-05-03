import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Eve Mertic
 *
 */
public final class CoinChange1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange1() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here
         */

        System.out.print("Enter the amount in cents to make change for: ");

        int cents = in.nextInteger();
        int originalAmount = cents;

        int dollars = cents / 100;
        cents = cents % 100;

        int halfDollars = cents / 50;
        cents = cents % 50;

        int quarters = cents / 25;
        cents = cents % 25;

        int dimes = cents / 10;
        cents = cents % 10;

        int nickels = cents / 5;
        cents = cents % 5;

        int pennies = cents;

        out.print("Change for " + originalAmount + " cents: ");
        out.print(" Dollars: " + dollars);
        out.print(" Half-dollars: " + halfDollars);
        out.print(" Quarters: " + quarters);
        out.print(" Dimes: " + dimes);
        out.print(" Nickels: " + nickels);
        out.print(" Pennies: " + pennies);

        int totalCoins = dollars + halfDollars + quarters + dimes + nickels + pennies;

        out.print(" Total coins: " + totalCoins);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
