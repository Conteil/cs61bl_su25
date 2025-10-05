import org.junit.Test;

import static com.google.common.truth.Truth.*;

public class MinHeapTest {
    @Test
    public void test1() {
        MinHeap<Integer> test = new MinHeap<>();
        assertThat(test.findMin()).isEqualTo(null);
        assertThat(test.removeMin()).isNull();
        test.insert(6);
        test.insert(5);
        test.insert(2);
        test.insert(3);
        test.insert(7);
        test.insert(1);
        assertThat(test.findMin()).isEqualTo(1);
        assertThat(test.size()).isEqualTo(6);
        assertThat(test.removeMin()).isEqualTo(1);
        assertThat(test.size()).isEqualTo(5);
        assertThat(test.findMin()).isEqualTo(2);
        assertThat(test.removeMin()).isEqualTo(2);
        assertThat(test.removeMin()).isEqualTo(3);
        test.insert(0);
        assertThat(test.removeMin()).isEqualTo(0);
        assertThat(test.size()).isEqualTo(3);
        test.update(7);
    }
}
