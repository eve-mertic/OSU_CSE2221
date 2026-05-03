import java.util.Random;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Monte Carlo Estimate: compute percentage of pseudo-random points in [0.0,1.0)
 * interval that fall in the left half subinterval [0.0,0.5).
 *
 * @author Eve Mertic
 *
 */
public final class MonteCarlo {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MonteCarlo() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {

        //Define a constant for the area of the square
        final double squareAREA = 4.0;

        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        /*
         * Ask user for number of points to generate
         */
        output.print("Number of points: ");
        int n = input.nextInteger();
        /*
         * Declare counters and initialize them
         */
        int ptsInSquare = 0, ptsInCircle = 0;
        /*
         * Create pseudo-random number generator
         */
        Random rnd = new Random();
        /*
         * Generate points and count how many fall in [0.0,2.0) x [0.0,2.0
         * interval
         */
        while (ptsInSquare < n) {
            /*
             * Generate pseudo-random number in [0.0,2.0) x [0.0,2.0) interval
             */
            double x = rnd.nextDouble() * 2.0;
            double y = rnd.nextDouble() * 2.0;
            /*
             * Increment total number of generated points
             */
            ptsInSquare++;
            /*
             * Check if point is in [0.0,2.0) x [0.0,2.0 interval and increment
             * counter if it is
             */
            double distanceSquared = (x - 1.0) + (x - 1.0) + (y - 1.0) * (y - 1.0);
            if (distanceSquared < 1.0) {
                ptsInCircle++;
            }
        }
        /*
         * Estimate percentage of points generated in [0.0,1.0) interval that
         * fall in the [0.0,0.5) subinterval
         */
        double squareArea = squareAREA;
        double estimateArea = squareArea * ((double) ptsInCircle / ptsInSquare);
        double theoreticalArea = Math.PI;

        //Print  out all of the calculations:

        output.println("Points in square: " + ptsInSquare);
        output.println("Points in circle: " + ptsInCircle);
        output.println("Ratio: " + (double) ptsInCircle / ptsInSquare);
        output.println("Estimated circle area: " + estimateArea);
        output.println("Theoretical area: " + theoreticalArea);
        output.println("Error: " + Math.abs(estimateArea - theoreticalArea));
        /*
         * Close input and output streams
         */
        input.close();
        output.close();
    }

}
