import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program.
 *
 * @author Eve Mertic
 */

public final class HelloWorld {
    /**
     * No-argument constuctor--private to prevent instantation
     */
    private HelloWorld() {
        //no code needed here
    }

    /**
     * Main Method.
     *
     * @param args
     *            the command line arguments; unused here
     */

    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        out.println("Hello World!");
        out.close();

    }

}
