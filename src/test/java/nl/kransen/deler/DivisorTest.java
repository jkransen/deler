package nl.kransen.deler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivisorTest {

    @Test
    void test10() {
        Divisor divisor = Divisor.create(10);
        assertEquals(2520, divisor.firstMatch());
    }

    @Test
    void test15() {
        Divisor divisor = Divisor.create(15);
        assertEquals(360360, divisor.firstMatch());
    }

    @Test
    void test25() {
        long before = System.currentTimeMillis();
        long firstMatch = Divisor.create(25).firstMatch();
        long duration = System.currentTimeMillis() - before;
        assertEquals(26771144400L, firstMatch);

        Assertions.assertTrue(duration < 8000);
    }
}
