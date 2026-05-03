import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * This program was made to find the best approximation of a user given number
 * utilizing the de Jager formula.
 *
 * @author Eve Mertic :)
 *
 */
public final class ABCDGuesser1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser1() {
    }

    /**
     * This is an array of the exponents used in the de Jager formula.
     */
    private static final double[] EXPONENTS = { -5, -4, -3, -2, -1, -1.0 / 2, -1.0 / 3,
            -1.0 / 4, 0, 1.0 / 4, 1.0 / 3, 1.0 / 2, 1, 2, 3, 4, 5 };

    /**
     * Ask the user for a positive real number until they enter one and then
     * returns the users number.
     *
     * @param in
     *            input stream
     * @param out
     *            output stream
     * @return a positive real number from the user
     *
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        double result = 0.0;
        boolean isValidInput = false;

        while (!isValidInput) {
            String userInput = in.nextLine();

            if (FormatChecker.canParseDouble(userInput)) {
                result = Double.parseDouble(userInput);
                if (result > 0) {
                    isValidInput = true;
                } else {
                    out.println("Incorrect. Please enter a positive number.");
                }
            } else {
                out.print("Incorrect. Please enter a valid number");
            }
        }
        return result;
    }

    /**
     * Ask the user repeatedly for a positive number that is not 1.0 until they
     * do and returns the users number.
     *
     * @param in
     *            input stream
     * @param out
     *            output stream
     * @return a positive real number from the user thats not 1.0
     *
     */
    private static double getPositiveDoubleNotOne(SimpleReader in, SimpleWriter out) {

        double result = 0.0;
        boolean isValidInput = false;
        while (!isValidInput) {
            String userInput = in.nextLine();

            if (FormatChecker.canParseDouble(userInput)) {
                result = Double.parseDouble(userInput);
                if (result > 0 && result != 1.0) {
                    isValidInput = true;
                } else if (result == 1.0) {
                    out.println("Incorrect. Please enter a number that is not 1.");
                } else {
                    out.println("Incorrect. Please enter a positive number.");
                }
            } else {
                out.println("Incorrect. Please enter a valid number.");
            }
        }
        return result;
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

//Prompt user for all necessary numbers
        out.println("What is the constant you would like to approximate?");
        double mu = getPositiveDouble(in, out);

        out.println("Enter your first number: ");
        double w = getPositiveDoubleNotOne(in, out);

        out.println("Enter your second number: ");
        double x = getPositiveDoubleNotOne(in, out);

        out.println("Enter your third number: ");
        double y = getPositiveDoubleNotOne(in, out);

        out.println("Enter your fourth number: ");
        double z = getPositiveDoubleNotOne(in, out);

// find the approximation
        double bestApproximation = 0.0;
        double bestError = Double.MAX_VALUE;
        int bestA = 0;
        int bestB = 0;
        int bestC = 0;
        int bestD = 0;

        // try all possible combinations
        int a = 0;

        while (a < EXPONENTS.length) {
            int b = 0;
            while (b < EXPONENTS.length) {
                int c = 0;
                while (c < EXPONENTS.length) {
                    int d = 0;
                    while (d < EXPONENTS.length) {

                        //Calculate approximation
                        double approximation = Math.pow(w, EXPONENTS[a])
                                * Math.pow(x, EXPONENTS[b]) * Math.pow(y, EXPONENTS[c])
                                * Math.pow(z, EXPONENTS[d]);

                        //Calculate relative error
                        double relativeError = Math.abs((approximation - mu) / mu);

                        //Check if it is the best possible approximation
                        if (relativeError < bestError) {
                            bestError = relativeError;
                            bestApproximation = approximation;
                            bestA = a;
                            bestB = b;
                            bestC = c;
                            bestD = d;
                        }

                        d++;

                    }

                    c++;

                }

                b++;

            }

            a++;

        }
//Print all of the results.

        final int percentConversion = 100;

        out.println();
        out.println("Your results: ");
        out.println("Constant: " + mu);
        out.println("Your Numbers: w=" + w + ", x=" + x + ", y=" + y + ", z=" + z);
        out.println("Best esponents: a=" + EXPONENTS[bestA] + ", b=" + EXPONENTS[bestB]
                + ", c=" + EXPONENTS[bestC] + ", d=" + EXPONENTS[bestD]);
        out.println("Best approximation: " + bestApproximation);
        out.print("Relative error: ");
        out.print(bestError * percentConversion, 2, false);
        out.println("%");

        in.close();
        out.close();
    }

}
