package be.ecotravel.back.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BitFieldUtilsTests {

    @Test
    void testToArray() {
        String[] sourceArray = {"A", "B", "C", "D"};
        int value = 5;

        String[] result = BitFieldUtils.toArray(sourceArray, value);
        assertArrayEquals(new String[]{"A", "C"}, result);
    }

    @Test
    void testToNumber() {
        String[] sourceArray = {"A", "B", "C", "D"};
        String[] selectedValues = {"A", "C"};

        int result = BitFieldUtils.toNumber(sourceArray, selectedValues);
        assertEquals(5, result);
    }
}
