import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Eve Mertic
 *
 */
public final class Hailstone5 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone5() {
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
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
        boolean valid = false;
        int result = 0;

        while (!valid) {
            out.print("Enter a positive integer: ");
            String s = in.nextLine().trim();

            if (FormatChecker.canParseInt(s)) {
                int n = Integer.parseInt(s);
                if (n > 0) {
                    result = n;
                    valid = true;
                } else {
                    out.println("Error: number must be greater than 0.");
                }
            } else {
                out.println("Error: input must be an integer.");
            }
        }

        return result;
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
            int startValue = getPositiveInteger(in, out);

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
