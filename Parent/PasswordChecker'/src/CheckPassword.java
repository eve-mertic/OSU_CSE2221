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
public final class CheckPassword {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CheckPassword() {
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given String contains an lower case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLowerCase(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given String contains a digit.
     *
     * @param str
     *            the String to check
     * @return true if str contains an lower case letter, false otherwise
     */
    private static boolean containsDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given String contains a digit.
     *
     * @param str
     *            the String to check
     * @return true if str contains an lower case letter, false otherwise
     */
    private static boolean containsSpecialCharacter(String str) {
        String specialChars = "!@#$%^&*()_-+={}[]:;,.?";
        for (int i = 0; i < str.length(); i++) {
            if (specialChars.indexOf(str.charAt(i)) >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param passwordCandidate
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String passwordCandidate, SimpleWriter out) {
        boolean isValid = true;
        final int eight = 8;
        final int three = 3;

        if (passwordCandidate.length() < eight) {
            out.println("Error: Password must be at least 8 characters long.");
            isValid = false;
        }

        boolean hasUpper = containsUpperCaseLetter(passwordCandidate);
        boolean hasLower = containsLowerCaseLetter(passwordCandidate);
        boolean hasDigit = containsDigit(passwordCandidate);
        boolean hasSpecialC = containsSpecialCharacter(passwordCandidate);

        //count characters
        int typeCount = 0;
        if (hasUpper) {
            typeCount++;
        }
        if (hasLower) {
            typeCount++;
        }
        if (hasDigit) {
            typeCount++;
        }
        if (hasSpecialC) {
            typeCount++;
        }

        //check that minimum types are present
        if (typeCount < three) {
            out.println("Error: Password must contain at least 3 of the following:");
            out.println("  - Upper case letters");
            out.println("  - Lower case letters");
            out.println("  - Digits");
            out.println("  - Special characters (!@#$%^&*()_-+={}[]:;,.?)");
            out.println();
            out.println("Your password contains:");
            if (hasUpper) {
                out.println("  - Upper case letters");
            }
            if (hasLower) {
                out.println("  - Lower case letters");
            }
            if (hasDigit) {
                out.println("  - Digits");
            }
            if (hasSpecialC) {
                out.println("  - Special characters");
            }
            isValid = false;
        }

        // print result
        if (isValid) {
            out.println("Valid password!");
        } else {
            out.println("Invalid password.");
        }
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

        out.println("Password Checker");
        out.println("----------------");
        out.println("Enter a password to check (or press Enter to quit):");
        out.println();

        String password = in.nextLine();

        while (password.length() > 0) {
            checkPassword(password, out);
            out.println();
            out.println("Enter another password to check (or press Enter to quit):");
            password = in.nextLine();
        }

        out.println();

        in.close();
        out.close();

    }

}
