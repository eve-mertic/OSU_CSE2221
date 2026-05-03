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
public final class Hailstone3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone3() {
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

        while (n != 1) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
            out.print(", " + n);
            length++;

            if (n > maxValue) {
                maxValue = n;
            }
        }
        out.println();
        out.println("The Length of the series is " + length);
        out.println("The maximum value in the series is " + maxValue);
    }

    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter a positive integer: ");
        int startValue = in.nextInteger();

        out.print("Hailstones series: ");
        generateSeries(startValue, out);

        in.close();
        out.close();
    }

}
