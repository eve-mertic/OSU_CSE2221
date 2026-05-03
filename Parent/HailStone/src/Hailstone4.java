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
public final class Hailstone4 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone4() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int length = 1;
        int maxValue = n;
        out.print(n);
        int nTemp = n;

        while (nTemp != 1) {
            if (nTemp % 2 == 0) {
                nTemp = nTemp / 2;
            } else {
                nTemp = 3 * nTemp + 1;
            }
            out.print(", " + nTemp);
            length++;

            if (nTemp > maxValue) {
                maxValue = nTemp;
            }
        }
        out.println();
        out.println("The Length of the series is " + length);
        out.println("The maximum value in the series is " + maxValue);
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param out
     *            the output stream
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        String response = "y";

        while (response.equals("y")) {
            out.print("Enter a positive integer: ");
            int startValue = in.nextInteger();

            out.print("Hailstones series: ");
            generateSeries(startValue, out);

            out.print("Do you wish to calculate another series? (y/n): ");
            response = in.nextLine();
        }

        out.println("Bye!");

        in.close();
        out.close();
    }

}
