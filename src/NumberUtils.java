import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NumberUtils {

    /**
     * This method receives two numbers, `left` and `right`, both represented as a list of digits.
     * It adds these numbers and returns the result also as a list of digits.
     *
     * For example, if you want to add the numbers 23 and 42, you would need to create
     * a (left) list with two elements [2,3] and a (right) list with two elements [4,2].
     * 23+42 = 65, so the program would produce another list with two elements [6,5]
     *
     * [2,3] + [4,2] = [6,5]
     *
     * Each element in the left and right lists should be a number from [0-9].
     * An IllegalArgumentException is thrown in case this pre-condition does not hold.
     *
     * @param left  a list containing the left number. Null returns null, empty means 0.
     * @param right a list containing the right number. Null returns null, empty means 0.
     * @return the sum of left and right, as a list
     */
    public static List<Integer> add(List<Integer> left, List<Integer> right) {
        // if any is null, return null
        if (left == null || right == null) {
            return null;
        }

        // Handle empty lists
        if (left.isEmpty()) {
            return new ArrayList<>(right);
        }
        if (right.isEmpty()) {
            return new ArrayList<>(left);
        }

        // create copies to avoid modifying the original lists
        List<Integer> leftCopy = new ArrayList<>(left);
        List<Integer> rightCopy = new ArrayList<>(right);

        // reverse the copies so that the least significant digit goes to the left.
        Collections.reverse(leftCopy);
        Collections.reverse(rightCopy);

        LinkedList<Integer> result = new LinkedList<>();

        // while there's a digit, keep summing them
        // if there's carry, take the carry into consideration
        int carry = 0;
        for (int i = 0; i < Math.max(leftCopy.size(), rightCopy.size()); i++) {
            int leftDigit = leftCopy.size() > i ? leftCopy.get(i) : 0;
            int rightDigit = rightCopy.size() > i ? rightCopy.get(i) : 0;

            if (leftDigit < 0 || leftDigit > 9 || rightDigit < 0 || rightDigit > 9) {
                throw new IllegalArgumentException("All digits must be between 0 and 9");
            }

            int sum = leftDigit + rightDigit + carry;
            result.addFirst(sum % 10);
            carry = sum / 10;
        }

        // if there's remaining carry, add it
        if (carry > 0) {
            result.addFirst(carry);
        }

        return result;
    }
}