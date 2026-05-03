import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Eve Mertic :)
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires * [exp is a subtree of a well-formed XML arithmetic expression]
     *           and [the label of the root of exp is not "expression"]
     *
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        NaturalNumber result = null;

        //Base Case:
        if (exp.label().equals("number")) {
            String value = exp.attributeValue("value");
            result = new NaturalNumber2(value);

        } else {

            //Recursive case:
            //Evaluate left and right
            NaturalNumber left = evaluate(exp.child(0));
            NaturalNumber right = evaluate(exp.child(1));

            String operator = exp.label();

            //Modify left for all expression types:
            if (operator.equals("plus")) {
                left.add(right);
                result = left;

            } else if (operator.equals("minus")) {
                if (left.compareTo(right) < 0) {
                    Reporter.fatalErrorToConsole("Error: Cannot subtract larger "
                            + "number from smaller number");
                }

                left.subtract(right);
                result = left;
            } else if (operator.equals("times")) {
                left.multiply(right);
                result = left;

            } else if (operator.equals("divide")) {
                if (right.isZero()) {
                    Reporter.fatalErrorToConsole("Error: " + "Division by zero");

                }
                left.divide(right);
                result = left;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
