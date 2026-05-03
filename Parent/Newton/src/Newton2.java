import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Computes estimate of square root of x to within relative error 0.01%.
 *
 * @author Eve Mertic
 *
 */
public final class Newton2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton2() {
    }

    /**
     * This methods purpose is to estimate the square root of a given number
     * using epsilon and output 0.0 if the user enters 0.0 for x.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        if (x == 0.0) {
            return 0.0;
        }

        double r = x;
        final double epsilon = 0.0001;

        while (Math.abs(r * r - x) / x >= epsilon * epsilon) {
            r = (r + x / r) / 2.0;
        }
        return r;
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

        /**
         * This loop breaks if the user answers that they do not want to
         * calculate a square root. If they do then it prompts the user to enter
         * a number and then calculates the square root
         */

        while (true) {
            out.print("Do you want to calculate a square root? (y/n): ");
            String userResponse = in.nextLine();

            if (!userResponse.equals("y")) {
                break;
            }

            out.print("Enter a positive number: ");
            double x = in.nextDouble();

            // Print the result of the square root.
            double result = sqrt(x);
            out.println("The square root of " + x + " is approximately " + result);
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
