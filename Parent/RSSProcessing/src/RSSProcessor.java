import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * This program inputs an XML RSS (version 2.0) feed from a given URL and
 * outputs various elements of the feed to the console.
 *
 * @author Eve Mertic :)
 *
 */
public final class RSSProcessor {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSProcessor() {
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of the {@code XMLTree} matching the
     *         given tag or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first tag child of the {@code XMLTree} matching the
     *   given tag or -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        int index = -1;
        int i = 0;

        // Search through all children for the matching tag
        while (i < xml.numberOfChildren() && index == -1) {
            if (xml.child(i).label().equals(tag)) {
                index = i;
            }
            i++;
        }

        return index;
    }

    /**
     * Processes one news item and outputs the title, or the description if the
     * title is not present, and the link (if available) with appropriate
     * labels.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures <pre>
     * out.content = #out.content * [the title (or description) and link]
     * </pre>
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item")
                : "" + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        int titleIndex = getChildElement(item, "title");

        if (titleIndex != -1) {
            XMLTree title = item.child(titleIndex);
            out.print("Title: ");
            if (title.numberOfChildren() > 0) {
                out.println(title.child(0).label());
            } else {
                out.println();
            }
        } else {
            int descIndex = getChildElement(item, "description");
            if (descIndex != -1) {
                XMLTree description = item.child(descIndex);
                out.print("Description: ");
                if (description.numberOfChildren() > 0) {
                    out.println(description.child(0).label());
                } else {
                    out.println();
                }
            }
        }

        // Look for link element (optional)
        int linkIndex = getChildElement(item, "link");
        if (linkIndex != -1) {
            XMLTree link = item.child(linkIndex);
            out.print("Link: ");
            if (link.numberOfChildren() > 0) {
                out.println(link.child(0).label());
            } else {
                out.println();
            }
        } else {
            out.println("Link: not present");
        }

        int pubDateIndex = getChildElement(item, "pubDate");
        if (pubDateIndex != -1) {
            XMLTree pubDate = item.child(pubDateIndex);
            out.print("Publication Date: ");
            if (pubDate.numberOfChildren() > 0) {
                out.println(pubDate.child(0).label());
            } else {
                out.println();
            }
        } else {
            out.println("Publication Date: not present");
        }

        int sourceIndex = getChildElement(item, "source");
        if (sourceIndex != -1) {
            XMLTree source = item.child(sourceIndex);
            out.print("Source: ");

            if (source.hasAttribute("url")) {
                String sourceUrl = source.attributeValue("url");
                out.print(sourceUrl);

                if (source.numberOfChildren() > 0) {
                    out.print(" (" + source.child(0).label() + ")");
                }
                out.println();
            } else if (source.numberOfChildren() > 0) {
                out.println(source.child(0).label());
            } else {
                out.println();
            }
        } else {
            out.println("Source: not present");
        }

        out.println();
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Input the source URL.
         */
        out.print("Enter the URL of an RSS 2.0 news feed: ");
        String url = in.nextLine();

        XMLTree xml = new XMLTree1(url);

        XMLTree channel = xml.child(0);

        // Output channel title
        int titleIndex = getChildElement(channel, "title");
        if (titleIndex != -1) {
            XMLTree title = channel.child(titleIndex);
            out.print("Channel Title: ");
            if (title.numberOfChildren() > 0) {
                out.println(title.child(0).label());
            } else {
                out.println();
            }
        }

        // Output channel link
        int linkIndex = getChildElement(channel, "link");
        if (linkIndex != -1) {
            XMLTree link = channel.child(linkIndex);
            out.print("Channel Link: ");
            if (link.numberOfChildren() > 0) {
                out.println(link.child(0).label());
            } else {
                out.println();
            }
        }

        // Output channel description
        int descIndex = getChildElement(channel, "description");
        if (descIndex != -1) {
            XMLTree description = channel.child(descIndex);
            out.print("Channel Description: ");
            if (description.numberOfChildren() > 0) {
                out.println(description.child(0).label());
            } else {
                out.println();
            }
        }

        out.println();

        for (int i = 0; i < channel.numberOfChildren(); i++) {
            XMLTree child = channel.child(i);
            if (child.label().equals("item")) {
                processItem(child, out);
            }
        }

        /*
         * Close I/O streams.
         */
        in.close();
        out.close();
    }

}
