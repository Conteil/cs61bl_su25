import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import static com.google.common.truth.Truth.assertWithMessage;

public class CodingChallenges {

    /**
     * Return the missing number from an array of length N containing all the
     * values from 0 to N except for one missing number.
     */
    public static int missingNumber(int[] values) {
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < values.length; i++) {
            seen.add(values[i]);
        }
        int i = 0;
        while (seen.contains(i)) {
            i += 1;
        }
        return i;
    }

    /**
     * Returns true if and only if s1 is a permutation of s2. s1 is a
     * permutation of s2 if it has the same number of each character as s2.
     */
    public static boolean isPermutation(String s1, String s2) {
        char[] s1_arr = s1.toCharArray();
        char[] s2_arr = s2.toCharArray();
        if (s1_arr.length != s2_arr.length) {
            return false;
        }
        Map<Character, Integer> s1Counts = new HashMap<>();
        Map<Character, Integer> s2Counts = new HashMap<>();
        for (int i = 0; i < s1_arr.length; i++) {
            if (s1Counts.containsKey(s1_arr[i])) {
                s1Counts.put(s1_arr[i], s1Counts.get(s1_arr[i]) + 1);
            } else {
                s1Counts.put(s1_arr[i], 1);
            }
        }
        for (int i = 0; i < s2_arr.length; i++) {
            if (s2Counts.containsKey(s2_arr[i])) {
                s2Counts.put(s2_arr[i], s2Counts.get(s2_arr[i]) + 1);
            } else {
                s2Counts.put(s2_arr[i], 1);
            }
        }
        if (s1Counts.equals(s2Counts)) {
            return true;
        } else {
            return false;
        }
    }
}
