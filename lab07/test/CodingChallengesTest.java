import org.junit.Test;

import static com.google.common.truth.Truth.assertWithMessage;

public class CodingChallengesTest {

    @Test
    public void testMissingNumber() {
        assertWithMessage("").that(CodingChallenges.missingNumber(new int[] {1, 0, 3})).isEqualTo(2);
        assertWithMessage("").that(CodingChallenges.missingNumber(new int[] {1, 2, 4, 3})).isEqualTo(0);
    }

    @Test
    public void testIsPermutation() {
	    String a = "hello";
        assertWithMessage("true").that(CodingChallenges.isPermutation(a, "ohlel")).isEqualTo(true);
        assertWithMessage("counts").that(CodingChallenges.isPermutation(a, "helo")).isEqualTo(false);
        assertWithMessage("letters").that(CodingChallenges.isPermutation(a, "heloc")).isEqualTo(false);
    }
}
