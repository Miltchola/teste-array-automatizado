import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class NumberUtilsTest {

    @Test
    public void testAddBasicCase() {
        List<Integer> left = Arrays.asList(2, 3);   // 23
        List<Integer> right = Arrays.asList(4, 2);  // 42
        List<Integer> expected = Arrays.asList(6, 5); // 65
        assertEquals(expected, NumberUtils.add(left, right));
    }

    @Test
    public void testAddWithCarry() {
        List<Integer> left = Arrays.asList(5, 6);   // 56
        List<Integer> right = Arrays.asList(7, 8);  // 78
        List<Integer> expected = Arrays.asList(1, 3, 4); // 134
        assertEquals(expected, NumberUtils.add(left, right));
    }

    @Test
    public void testAddWithDifferentLengths() {
        List<Integer> left = Arrays.asList(1, 0, 0);   // 100
        List<Integer> right = Arrays.asList(1);        // 1
        List<Integer> expected = Arrays.asList(1, 0, 1); // 101
        assertEquals(expected, NumberUtils.add(left, right));
    }

    @Test
    public void testAddWithEmptyLeft() {
        List<Integer> left = Arrays.asList();   // 0
        List<Integer> right = Arrays.asList(4, 2);  // 42
        List<Integer> expected = Arrays.asList(4, 2); // 42
        assertEquals(expected, NumberUtils.add(left, right));
    }

    @Test
    public void testAddWithEmptyRight() {
        List<Integer> left = Arrays.asList(2, 3);   // 23
        List<Integer> right = Arrays.asList();  // 0
        List<Integer> expected = Arrays.asList(2, 3); // 23
        assertEquals(expected, NumberUtils.add(left, right));
    }

    @Test
    public void testAddWithBothEmpty() {
        List<Integer> left = Arrays.asList();   // 0
        List<Integer> right = Arrays.asList();  // 0
        List<Integer> expected = Arrays.asList(); // 0
        assertEquals(expected, NumberUtils.add(left, right));
    }

    @Test
    public void testAddWithNullLeft() {
        List<Integer> right = Arrays.asList(4, 2);  // 42
        assertNull(NumberUtils.add(null, right));
    }

    @Test
    public void testAddWithNullRight() {
        List<Integer> left = Arrays.asList(2, 3);   // 23
        assertNull(NumberUtils.add(left, null));
    }

    @Test
    public void testAddWithBothNull() {
        assertNull(NumberUtils.add(null, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWithInvalidDigitInLeft() {
        List<Integer> left = Arrays.asList(10, 3);   // Invalid digit 10
        List<Integer> right = Arrays.asList(4, 2);   // 42
        NumberUtils.add(left, right);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWithInvalidDigitInRight() {
        List<Integer> left = Arrays.asList(2, 3);   // 23
        List<Integer> right = Arrays.asList(-1, 2);  // Invalid digit -1
        NumberUtils.add(left, right);
    }

    @Test
    public void testAddWithLargeNumbers() {
        List<Integer> left = Arrays.asList(9, 9, 9, 9);   // 9999
        List<Integer> right = Arrays.asList(1);          // 1
        List<Integer> expected = Arrays.asList(1, 0, 0, 0, 0); // 10000
        assertEquals(expected, NumberUtils.add(left, right));
    }
}