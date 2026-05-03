import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to explore XMLTree methods and extract information from XML data.
 *
 * @author Eve Mertic
 *
 */
public final class XMLTreeExploration {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeExploration() {
    }

    /**
     * Output information about the middle child of the given {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose middle child is to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of xt is a tag] and [xt has at least one
     *           child] and out.is_open
     * @ensures out.content = #out.content * [the label of the middle child of
     *          xt, whether the root of the middle child is a tag or text, and
     *          if it is a tag, the number of children of the middle child]
     */
    private static void printMiddleNode(XMLTree xt, SimpleWriter out) {
        int numChildren = xt.numberOfChildren();
        int middleIndex = numChildren / 2;

        XMLTree middleChild = xt.child(middleIndex);

        out.println("Middle child label: " + middleChild.label());

        if (middleChild.isTag()) {
            out.println("Middle child is a tag");

            out.println("Number of children: " + middleChild.numberOfChildren());
        } else {
            out.println("Middle child is text");
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
        final int ten = 10;

        XMLTree xml = new XMLTree1("https://cse22x1.engineering.osu.edu/2221/web-sw1/"
                + "extras/instructions/xmltree-model/columbus-weather.xml");

        xml.display();

        out.println("\n--- Root Information ---");
        if (xml.isTag()) {
            out.println("The root is a tag");
        } else {
            out.println("The root is text");
        }
        out.println("Root label: " + xml.label());

        out.println("\n--- Children Information ---");

        XMLTree results = xml.child(0);

        XMLTree channel = results.child(0);

        out.println("Number of children of channel: " + channel.numberOfChildren());

        XMLTree title = channel.child(1);

        XMLTree titleText = title.child(0);

        out.println("Title text: " + titleText.label());

        out.println("Title text (one statement): "
                + xml.child(0).child(0).child(1).child(0).label());

        out.println("\n--- Attributes Information ---");

        XMLTree astronomy = channel.child(ten);

        out.println("Has attribute 'sunset': " + astronomy.hasAttribute("sunset"));
        out.println("Has attribute 'midday': " + astronomy.hasAttribute("midday"));

        out.println("Sunrise: " + astronomy.attributeValue("sunrise"));
        out.println("Sunset: " + astronomy.attributeValue("sunset"));

        out.println("\n--- Middle Child Information ---");
        printMiddleNode(channel, out);

        in.close();
        out.close();
    }

}
