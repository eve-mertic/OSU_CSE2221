import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program to test arrays, references, and arrays of references.
 *
 * @author Eve Mertic :)
 *
 */
public final class ArraysAndReferences {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ArraysAndReferences() {
    }

    /**
     * Computes the product of the {@code NaturalNumber}s in the given array.
     *
     * @param nnArray
     *            the array
     * @return the product of the numbers in the given array
     * @requires nnArray.length > 0
     * @ensures <pre>
     * productOfArrayElements =
     *    [nnArray[0] * nnArray[1] * ... * nnArray[nnArray.length-1]]
     * </pre>
     */
    private static NaturalNumber productOfArrayElements(NaturalNumber[] nnArray) {
        assert nnArray != null : "Violation of: nnArray is not null";
        assert nnArray.length > 0 : "Violation of: nnArray.length > 0";

        NaturalNumber product = new NaturalNumber2(1);
        for (int i = 0; i < nnArray.length; i++) {
            product.multiply(nnArray[i]);
        }
        return product;
    }

    /**
     * Replaces each element of {@code nnArray} with the partial product of all
     * the elements in the incoming array, up to and including the current
     * element.
     *
     * @param nnArray
     *            the array
     * @updates nnArray
     * @requires nnArray.length > 0
     * @ensures <pre>
     * for all i: integer where (0 <= i < nnArray.length)
     *   (nnArray[i] = [#nnArray[0] * #nnArray[1] * ... * #nnArray[i]])
     * </pre>
     */
    private static void computePartialProducts(NaturalNumber[] nnArray) {
        assert nnArray != null : "Violation of: nnArray is not null";
        assert nnArray.length > 0 : "Violation of: nnArray.length > 0";

        NaturalNumber runningProduct = new NaturalNumber2(1);

        for (int i = 0; i < nnArray.length; i++) {
            runningProduct.multiply(nnArray[i]);
            nnArray[i] = new NaturalNumber2(runningProduct);
        }
    }

    /**
     * Creates and returns a new array of {@code NaturalNumber}s, of the same
     * size of the given array, containing the partial products of the elements
     * of the given array.
     *
     * @param nnArray
     *            the array
     * @return the array of partial products of the elements of the given array
     * @requires nnArray.length > 0
     * @ensures <pre>
     * partialProducts.length = nnArray.length  and
     *  for all i: integer where (0 <= i < partialProducts.length)
     *    (partialProducts[i] = [nnArray[0] * nnArray[1] * ... * nnArray[i]])
     * </pre>
     */
    private static NaturalNumber[] partialProducts(NaturalNumber[] nnArray) {
        assert nnArray != null : "Violation of: nnArray is not null";
        assert nnArray.length > 0 : "Violation of: nnArray.length > 0";

        NaturalNumber[] result = new NaturalNumber[nnArray.length];
        NaturalNumber runningProduct = new NaturalNumber2(1);

        for (int i = 0; i < nnArray.length; i++) {
            runningProduct.multiply(nnArray[i]);
            result[i] = new NaturalNumber2(runningProduct);
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
        SimpleWriter out = new SimpleWriter1L();

        NaturalNumber[] array = new NaturalNumber[5];
        for (int i = 0; i < array.length; i++) {
            array[i] = new NaturalNumber2(i + 1);
        }

        NaturalNumber product = productOfArrayElements(array);
        out.println("Product of {1,2,3,4,5} = " + product);
        out.println("Expected: 120");
        out.println();

        out.print("Before computePartialProducts: [");
        for (int i = 0; i < array.length; i++) {
            out.print(array[i] + (i < array.length - 1 ? ", " : ""));
        }
        out.println("]");

        computePartialProducts(array);

        out.print("After  computePartialProducts: [");
        for (int i = 0; i < array.length; i++) {
            out.print(array[i] + (i < array.length - 1 ? ", " : ""));
        }
        out.println("]");
        out.println("Expected: [1, 2, 6, 24, 120]");
        out.println();

        NaturalNumber[] array2 = new NaturalNumber[5];
        for (int i = 0; i < array2.length; i++) {
            array2[i] = new NaturalNumber2(i + 1);
        }

        NaturalNumber[] pp = partialProducts(array2);

        out.print("Original after partialProducts: [");
        for (int i = 0; i < array2.length; i++) {
            out.print(array2[i] + (i < array2.length - 1 ? ", " : ""));
        }
        out.println("]");
        out.println("Expected (unchanged): [1, 2, 3, 4, 5]");

        out.print("Returned array:        [");
        for (int i = 0; i < pp.length; i++) {
            out.print(pp[i] + (i < pp.length - 1 ? ", " : ""));
        }
        out.println("]");
        out.println("Expected:             [1, 2, 6, 24, 120]");

        out.close();
    }

}
