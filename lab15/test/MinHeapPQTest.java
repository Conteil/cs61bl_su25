import org.junit.Test;

import static com.google.common.truth.Truth.*;


public class MinHeapPQTest {

    @Test
    public void testAddOneThing() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("l", 2);
        String item = pq.poll();
        assertThat("l").isEqualTo(item);
    }

    @Test
    public void testAddThenRemove() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("h", 100);
        pq.insert("i", 0);
        String item = pq.poll();
        assertThat("i").isEqualTo(item);
        assertThat("h").isEqualTo(pq.poll());
    }

    /**
     * Tests that a MinHeapPQ can add and remove a single element.
     */
    @Test
    public void testOneThing() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        assertThat(pq.poll()).isNull();
        pq.insert("l", 2);
        assertThat(1).isEqualTo(pq.size());
        String item = pq.poll();
        assertThat("l").isEqualTo(item);
        assertThat(0).isEqualTo(pq.size());
    }

    @Test
    public void test1() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("l", 2);
        pq.insert("h", 100);
        pq.insert("i", 0);
        assertThat(pq.contains("l")).isEqualTo(true);
        assertThat(pq.contains("a")).isEqualTo(false);
        pq.changePriority("h", -1);
        pq.changePriority("i", 3);
        assertThat(pq.peek()).isEqualTo("h");
        assertThat(pq.contains("h")).isEqualTo(true);
        assertThat(pq.poll()).isEqualTo("h");
        assertThat(pq.contains("h")).isEqualTo(false);
        assertThat(pq.size()).isEqualTo(2);
        assertThat(pq.poll()).isEqualTo("l");
        assertThat(pq.poll()).isEqualTo("i");
    }
}