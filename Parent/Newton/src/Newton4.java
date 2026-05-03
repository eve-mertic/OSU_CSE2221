import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This program is made to calculate the square root of a number the user
 * provides.
 *
 * @author Eve Mertic
 *
 */
public final class Newton4 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01% and
     * output 0.0 if the user enters 0.0 for x.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     *
     * @param epsilon
     *            value of relative error
     */
    private static double sqrt(double x, double epsilon) {
        if (x == 0.0) {
            return 0.0;
        }

        double r = x;

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

        out.print("Enter the value of epsilon as a decimal: ");
        double epsilon = in.nextDouble();

        while (true) {
            out.print("Enter a positive number: ");
            double x = in.nextDouble();

            if (x < 0) {
                break;
            }

            // Print the result of the square root.
            double result = sqrt(x, epsilon);
            out.println("The square root of " + x + " is approximately " + result);
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
