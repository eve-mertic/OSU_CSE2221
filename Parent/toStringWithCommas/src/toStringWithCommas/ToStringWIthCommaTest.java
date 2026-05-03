package toStringWithCommas;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

public class ToStringWIthCommaTest {

    /**
     * Tests n = 0 (smallest NN, no commas).
     */
    @Test
    public void testZero() {
        NaturalNumber n = NaturalNumber2(0);
        String result = ToStringWithCommas.toStringWithCommas(n);
        assertEquals("0", result);
    }

    /**
     * Tests a single-digit number (n = 1). No commas.
     */
    @Test
    public void testOneDigit_1() {
        NaturalNumber n = NaturalNumber2(1);
        String result = ToStringWithCommas.toStringWithCommas(n);
        assertEquals("1", result);
    }

    /**
     * Tests n = 9 (largest single-digit number). No commas.
     */
    @Test
    public void testOneDigit_9() {
        NaturalNumber n = NaturalNumber2(9);
        String result = ToStringWithCommas.toStringWithCommas(n);
        assertEquals("9", result);
    }

    /**
     * Tests n = 1,234 — four digit number with one comma.
     */
    @Test
    public void testFourDigits_1234() {
        NaturalNumber n = NaturalNumber2(1234);
        String result = ToStringWithCommas.toStringWithCommas(n);
        assertEquals("1,234", result);
    }

}
