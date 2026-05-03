import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    @Test
    public void testOverlapNoOverlap() {
        assertEquals(0, StringReassembly.overlap("abc", "xyz"));
    }

    @Test
    public void testOverlapPartialOverlap() {
        assertEquals(2, StringReassembly.overlap("abcdef", "efghij"));
    }

    @Test
    public void testOverlapSingleChar() {
        assertEquals(1, StringReassembly.overlap("abc", "cde"));
    }

    @Test
    public void testOverlapFullOverlap() {
        assertEquals(3, StringReassembly.overlap("abcd", "bcd"));
    }

    @Test
    public void testOverlapEmptyStrings() {
        assertEquals(0, StringReassembly.overlap("", "xyz"));
        assertEquals(0, StringReassembly.overlap("abc", ""));
    }

    @Test
    public void testCombinationWithOverlap() {
        assertEquals("abcdefghij", StringReassembly.combination("abcdefg", "fghij", 2));
    }

    @Test
    public void testAddToSetAvoidingSubstringsEmpty() {
        Set<String> set = new Set1L<>();
        StringReassembly.addToSetAvoidingSubstrings(set, "hello");
        assertTrue(set.contains("hello"));
        assertEquals(1, set.size());
    }

    @Test
    public void testAddToSetAvoidingSubstringsNoSubstring() {
        Set<String> set = new Set1L<>();
        set.add("hello");
        StringReassembly.addToSetAvoidingSubstrings(set, "world");
        assertTrue(set.contains("hello"));
        assertTrue(set.contains("world"));
        assertEquals(2, set.size());
    }

    @Test
    public void testAddToSetAvoidingSubstringsIsSubstring() {
        Set<String> set = new Set1L<>();
        set.add("hello");
        StringReassembly.addToSetAvoidingSubstrings(set, "ell");
        assertTrue(set.contains("hello"));
        assertFalse(set.contains("ell"));
        assertEquals(2, set.size());
    }

    @Test
    public void testAddToSetAvoidingSubstringsContainsExisting() {
        Set<String> set = new Set1L<>();
        set.add("hello");
        StringReassembly.addToSetAvoidingSubstrings(set, "helloworld");
        assertFalse(set.contains("hello"));
        assertFalse(set.contains("world"));
        assertFalse(set.contains("helloworld"));
        assertEquals(1, set.size());
    }

    @Test
    public void testAddToSetAvoidingSubstringsEmptyString() {
        Set<String> set = new Set1L<>();
        set.add("hello");
        StringReassembly.addToSetAvoidingSubstrings(set, "");
        assertTrue(set.contains("hello"));
        assertEquals(1, set.size());

    }

    @Test
    public void testLinesFromInput() {
        SimpleWriter fileOut = new SimpleWriter1L("test-output2.txt");
        fileOut.println("abc");
        fileOut.println("abcdef");
        fileOut.println("def");
        fileOut.close();

        SimpleReader in = new SimpleReader1L("testsubstrings.txt");
        Set<String> lines = StringReassembly.linesFromInput(in);
        in.close();

        assertEquals(1, lines.size());
        assertTrue(lines.contains("abcdef"));
    }

    @Test
    public void testLinesFromInputWithSeperators() {
        SimpleWriter out = new SimpleWriter1L("test-output.txt");
        StringReassembly.printWithLineSeparators("Hello~World", out);
        out.close();

        SimpleReader in = new SimpleReader1L("test-output.txt");
        String line1 = in.nextLine();
        String line2 = in.nextLine();
        in.close();

        assertEquals("Hello", line1);
        assertEquals("World", line2);

    }

    @Test
    public void testPrintWithLineSeperatorsExtra() {
        SimpleWriter out = new SimpleWriter1L("test-output2.txt");
        StringReassembly.printWithLineSeparators("One~Two~Three", out);
        out.close();

        SimpleReader in = new SimpleReader1L("test-output2.txt");
        String line1 = in.nextLine();
        String line2 = in.nextLine();
        String line3 = in.nextLine();
        in.close();

        assertEquals("One", line1);
        assertEquals("Two", line2);
        assertEquals("Three", line3);
    }

    @Test
    public void testAssembleSingle() {
        Set<String> parts = new Set1L<>();
        parts.add("hello");

        StringReassembly.assemble(parts);
        assertEquals(1, parts.size());
        assertTrue(parts.contains("hello"));

    }

    @Test
    public void testAssembleSimple() {
        Set<String> parts = new Set1L<>();
        parts.add("he");
        parts.add("ello");

        StringReassembly.assemble(parts);
        assertEquals(1, parts.size());
        assertTrue(parts.contains("hello"));
    }

    @Test
    public void testAssembleMoreParts() {
        Set<String> parts = new Set1L<>();
        parts.add("abc");
        parts.add("bcd");
        parts.add("cde");

        StringReassembly.assemble(parts);
        assertEquals(1, parts.size());
        assertTrue(parts.contains("abcde"));
    }

    @Test
    public void testAssembleNoOverlap() {
        Set<String> parts = new Set1L<>();
        parts.add("abc");
        parts.add("def");

        StringReassembly.assemble(parts);
        assertEquals(2, parts.size());
        assertTrue(parts.contains("abc"));
        assertTrue(parts.contains("def"));
    }
}
