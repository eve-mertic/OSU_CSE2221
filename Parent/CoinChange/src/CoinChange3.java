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
public final class CoinChange3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange3() {
    }

    /**
     * Return an array of coin counts needed to make change for the number of
     * cents.
     *
     * @param cents
     *            amount of cents change is needed for
     * @param denominations
     *            array of coin denominations in cents in descending order
     * @return array of coin counts
     */
    private static int[] coinCountsToMakeChange(int cents, int[] denominations) {
        int[] counts = new int[denominations.length];
        int remaining = cents;

        for (int i = 0; i < denominations.length; i++) {
            counts[i] = remaining / denominations[i];
            remaining = remaining % denominations[i];
        }

        return counts;

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
        final int one = 1;
        final int three = 3;
        final int four = 4;
        final int five = 5;
        final int ten = 10;
        final int twentyFive = 25;
        final int fifty = 50;
        final int hundred = 100;

        int cents = in.nextInteger();
        int originalAmount = cents;

        int[] denominations = { hundred, fifty, twentyFive, ten, five, one };
        int[] counts = coinCountsToMakeChange(originalAmount, denominations);

        int dollars = counts[0];

        int halfDollars = counts[1];

        int quarters = counts[2];

        int dimes = counts[three];

        int nickels = counts[four];

        int pennies = counts[five];

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
