import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This program is designed to generate an HTML glossary based on a text file
 * that has the terms and definitions.
 *
 * @author Eve Mertic :)
 *
 */
public final class Glossary {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Glossary() {
    }

    /**
     * This method reads the input and maps the terms and definitions as keys
     * and values.
     *
     * @param input
     *            the input stream
     * @return map containing the terms as keys and definitions as values
     * @requires input.is_open
     * @ensures glossary contains all terms and definitons from input
     */
    private static Map<String, String> readGlossary(SimpleReader input) {
        Map<String, String> glossary = new Map1L<>();

        while (!input.atEOS()) {
            String term = input.nextLine();
            if (term.length() > 0) {
                StringBuilder definition = new StringBuilder();
                String line = input.nextLine();

                while (line.length() > 0 && !input.atEOS()) {
                    definition.append(line).append(" ");
                    line = input.nextLine();
                }

                if (line.length() > 0) {
                    definition.append(line).append(" ");
                }

                glossary.add(term, definition.toString().trim());
            }
        }

        return glossary;
    }

    /**
     * This method returns a queue of terms that are sorted alphabetically.
     *
     * @param glossary
     *            the map that contains terms and definitions
     * @return queue of sorted terms
     * @ensures sorted terms in alphabetical order
     */
    private static Queue<String> sortTerms(Map<String, String> glossary) {
        Queue<String> unsorted = new Queue1L<>();
        Queue<String> sorted = new Queue1L<>();

        //Add all terms to unsorted queue:
        for (Map.Pair<String, String> pair : glossary) {
            unsorted.enqueue(pair.key());
        }

        //Insertion sort:
        while (unsorted.length() > 0) {
            String current = unsorted.dequeue();
            Queue<String> temp = new Queue1L<>();

            //Instertion position:
            boolean inserted = false;
            while (sorted.length() > 0 && !inserted) {
                String sortedTerm = sorted.dequeue();
                if (current.compareToIgnoreCase(sortedTerm) < 0) {
                    temp.enqueue(current);
                    temp.enqueue(sortedTerm);
                    inserted = true;
                } else {
                    temp.enqueue(sortedTerm);
                }
            }

            //If not inserted yet, add it at the end:
            if (!inserted) {
                temp.enqueue(current);
            }

            //Move the rest from sorted to temp
            while (sorted.length() > 0) {
                temp.enqueue(sorted.dequeue());
            }

            //Move everthing back into sorted
            while (temp.length() > 0) {
                sorted.enqueue(temp.dequeue());
            }
        }
        return sorted;
    }

    /**
     * Generates a set of seperators for definitions.
     *
     * @return set of separator characters
     */
    private static Set<Character> generateSeparators() {
        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        separators.add('\t');
        separators.add('\n');
        separators.add('\r');
        separators.add(',');
        separators.add('.');
        separators.add('!');
        separators.add('?');
        separators.add(';');
        separators.add(':');
        separators.add('-');
        separators.add('(');
        separators.add(')');
        separators.add('[');
        separators.add(']');
        separators.add('{');
        separators.add('}');
        return separators;

    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * separators) or "separator string" (maximal length string of characters in
     * separators) in the given text starting at the given position.
     *
     * @param text
     *            the text to tokenize
     * @param position
     *            the starting position
     * @param separators
     *            the set of separator characters
     * @return the next token
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        int i = position;
        char ch = text.charAt(position);
        if (separators.contains(ch)) {
            while (i < text.length() && separators.contains(text.charAt(i))) {
                i++;
            }
        } else {
            while (i < text.length() && !separators.contains(text.charAt(i))) {
                i++;
            }
        }
        return text.substring(position, i);
    }

    /**
     * Processes definition text and converts terms that appear in glossary to
     * links.
     *
     * @param definition
     *            the definition text
     * @param glossary
     *            the map of all terms
     * @return HTML formatted definition with links
     */
    private static String processDefinition(String definition,
            Map<String, String> glossary) {
        StringBuilder result = new StringBuilder();
        Set<Character> separators = Glossary.generateSeparators();
        int position = 0;
        while (position < definition.length()) {
            String token = nextWordOrSeparator(definition, position, separators);
            if (glossary.hasKey(token)) {
                result.append("<a href=\"").append(token).append(".html\">").append(token)
                        .append("</a>");
            } else {
                result.append(token);
            }
            position += token.length();
        }
        return result.toString();
    }

    /**
     * Outputs the opening tags for an HTML file.
     *
     * @param out
     *            the output stream
     * @param title
     *            the title page
     * @updates out.content
     * @requires out.is_open
     * @ensure out.content = #out.content * [HTML opening tags]
     */
    private static void outputHeader(SimpleWriter out, String title) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body>");
    }

    /**
     * Outputs the closing tags for an HTML file.
     *
     * @param out
     *            the output stream
     * @updates out.content
     * @requires out.is_open
     * @ensure out.content = #out.content * [HTML closing tags]
     */
    private static void outputFooter(SimpleWriter out) {
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Generates the index.html file with all terms listed.
     *
     * @param outputFolder
     *            the folder where the file will be saved
     * @param sortedTerms
     *            queue of terms in alphabetical order
     * @requires outputFolder exists
     * @ensure index.html is created with links to all terms
     */
    private static void generateIndexPage(String outputFolder,
            Queue<String> sortedTerms) {
        SimpleWriter out = new SimpleWriter1L(outputFolder + "/index.html");
        outputHeader(out, "Glossary");
        out.println("<h2>Glossary</h2>");
        out.println("<hr />");
        out.println("<h3>Index</h3>");
        out.println("<ul>");

        for (String term : sortedTerms) {
            out.println("<li><a href=\"" + term + ".html\">" + term + "</a></li>");
        }
        out.println("</ul>");
        outputFooter(out);
        out.close();
    }

    /**
     * Generates the individual HTML page for a term.
     *
     * @param outputFolder
     *            the folder where the file will be saved
     * @param term
     *            the term
     * @param definition
     *            the definition
     * @param glossary
     *            the complete glossary map.
     * @requires outputFolder exists
     * @ensure term.html is created with definition
     */
    private static void generateTermPage(String outputFolder, String term,
            String definition, Map<String, String> glossary) {
        SimpleWriter out = new SimpleWriter1L(outputFolder + "/" + term + ".html");
        outputHeader(out, term);
        out.println(
                "<h2><b><i><font color=\"red\">" + term + "</font></i></b>" + "</h2>");
        out.println("<blockquote>" + processDefinition(definition, glossary)
                + "</blockquote>");
        out.println("<hr />");
        out.println("<p>Return to <a href=\"index.html\"> index</a>.</p>");
        outputFooter(out);
        out.close();
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

        out.print("Enter the name of the input file: ");
        String inputFile = in.nextLine();

        out.print("Enter the name of the output folder: ");
        String outputFolder = in.nextLine();

        SimpleReader fileIn = new SimpleReader1L(inputFile);
        Map<String, String> glossary = readGlossary(fileIn);
        fileIn.close();

        Queue<String> sortedTerms = sortTerms(glossary);
        generateIndexPage(outputFolder, sortedTerms);

        for (Map.Pair<String, String> entry : glossary) {
            generateTermPage(outputFolder, entry.key(), entry.value(), glossary);
        }
        out.println("Glossary has been generated sucessfully.");

        in.close();
        out.close();
    }

}
