package nl.kransen.deler;

import org.junit.jupiter.api.Test;
import org.scalatest.Ignore;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DelerTest {

    @Test
    public void testTien() {
        Deler tien = Deler.create(10);
        assertEquals(2520, tien.eersteMatch());
    }

    @Test
    public void testVijftien() {
        Deler vijftien = Deler.create(15);
        assertEquals(360360, vijftien.eersteMatch());
    }

    @Test
    @Ignore
    public void testVijfentwintig() {
        Deler vijftien = Deler.create(25);
        assertEquals(26771144400L, vijftien.eersteMatch());
    }
}
