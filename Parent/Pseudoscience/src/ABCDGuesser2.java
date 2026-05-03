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
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
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
     * Ask the user repeatedly for a positive number that is not 1.0 until they
     * do and returns the users number.
     *
     * @param baseNumbers
     *            array with w, x, y, z
     * @param exponentIndex
     *            array with all of the indices
     * @return the result of the de Jager formula
     *
     */

    private static double calculateDeJagerFormula(double[] baseNumbers,
            int[] exponentIndex) {
        final int three = 3;
        final int four = 4;

        return Math.pow(baseNumbers[0], EXPONENTS[exponentIndex[1]])
                * Math.pow(baseNumbers[2], EXPONENTS[exponentIndex[2]])
                * Math.pow(baseNumbers[three], EXPONENTS[exponentIndex[three]])
                * Math.pow(baseNumbers[four], EXPONENTS[exponentIndex[four]]);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     *
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        final int three = 3;
        final int four = 4;

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

//create arrays for the method
        double[] baseNumbers = { w, x, y, z };
        int[] exponentIndex = new int[four];

        // try all possible combinations
        for (int a = 0; a < EXPONENTS.length; a++) {
            for (int b = 0; b < EXPONENTS.length; b++) {
                for (int c = 0; c < EXPONENTS.length; c++) {
                    for (int d = 0; d < EXPONENTS.length; d++) {
                        //set the index
                        exponentIndex[0] = a;
                        exponentIndex[1] = b;
                        exponentIndex[2] = c;
                        exponentIndex[three] = d;

                        //Calculate deJager
                        double approximation = calculateDeJagerFormula(baseNumbers,
                                exponentIndex);

                        //Calculate relative error
                        double relativeError = Math.abs((approximation - mu) / mu);

                        //Loop to check if it is the best possible approximation
                        if (relativeError < bestError) {
                            bestError = relativeError;
                            bestApproximation = approximation;
                            bestA = a;
                            bestB = b;
                            bestC = c;
                            bestD = d;
                        }
                    }
                }
            }
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
