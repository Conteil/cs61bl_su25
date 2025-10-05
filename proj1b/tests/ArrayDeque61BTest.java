import deque.ArrayDeque61B;
import deque.Deque61B;

import deque.LinkedListDeque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

    @Test
    @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
             .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
             .toList();

        assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    }

    @Test
    public void getTest() {
        ArrayDeque61B<String> array = new ArrayDeque61B<>();
        assertThat(array.get(-1)).isEqualTo(null);
        assertThat(array.get(9)).isEqualTo(null);
        assertThat(array.get(3)).isEqualTo(null);
        array.addLast("a");
        assertThat(array.get(0)).isEqualTo("a");
        array.addLast("b");
        array.addFirst("c");
        assertThat(array.get(1)).isEqualTo("a");
        array.addLast("d");
        array.addLast("e");
        array.addFirst("f");
        assertThat(array.get(0)).isEqualTo("f");
        assertThat(array.get(4)).isEqualTo("d");
        array.addLast("g");
        array.addLast("h");
        array.addLast("Z");
        array.addFirst("m");
        assertThat(array.get(1)).isEqualTo("f");
        assertThat(array.get(0)).isEqualTo("m");
        assertThat(array.get(9)).isEqualTo("Z");
    }

    @Test
    public void toListTest() {
        ArrayDeque61B<String> array = new ArrayDeque61B<>();
        assertThat(array.toList()).containsExactly();
        array.addLast("a");
        assertThat(array.toList()).containsExactly("a").inOrder();
        array.addLast("b");
        array.addFirst("c");
        assertThat(array.toList()).containsExactly("c", "a", "b").inOrder();
        array.addLast("d");
        array.addLast("e");
        array.addFirst("f");
        assertThat(array.toList()).containsExactly("f", "c", "a", "b", "d", "e").inOrder();
        array.addLast("g");
        array.addLast("h");
        array.addLast("Z");
        assertThat(array.toList()).containsExactly("f", "c", "a", "b", "d", "e", "g", "h", "Z").inOrder();
        }

    @Test
    public void removeFirst() {
        ArrayDeque61B<String> array = new ArrayDeque61B<>();
        assertThat(array.size()).isEqualTo(0);
        array.removeFirst();
        assertThat(array.size()).isEqualTo(0);
        
        array.addLast("a");
        array.addLast("b");
        array.addFirst("c");
        assertThat(array.removeFirst()).isEqualTo("c");
        assertThat(array.toList()).containsExactly("a", "b").inOrder();
        assertThat(array.removeFirst()).isEqualTo("a");
        assertThat(array.toList()).containsExactly("b").inOrder();
        assertThat(array.removeFirst()).isEqualTo("b");
        array.addLast("a");
        array.addLast("b");
        array.addFirst("c");
        assertThat(array.removeLast()).isEqualTo("b");
        assertThat(array.toList()).containsExactly("c", "a").inOrder();
        assertThat(array.removeLast()).isEqualTo("a");
        assertThat(array.toList()).containsExactly("c").inOrder();
        assertThat(array.removeLast()).isEqualTo("c");
        array.addLast("b");
        assertThat(array.size()).isEqualTo(1);
        for (int i = 0; i < 30; i++) {
            array.addLast("n");
        }
        for (int i = 0; i < 29; i++) {
            array.removeLast();
        }
        assertThat(array.removeLast()).isEqualTo("n");
        assertThat(array.removeLast()).isEqualTo("b");

        ArrayDeque61B<String> array2 = new ArrayDeque61B<>();
        array2.addFirst("b");
        assertThat(array2.size()).isEqualTo(1);
        for (int i = 0; i < 30; i++) {
            array2.addFirst("n");
        }
        for (int i = 0; i < 29; i++) {
            array2.removeFirst();
        }
        assertThat(array2.removeFirst()).isEqualTo("n");
        assertThat(array2.removeFirst()).isEqualTo("b");
        assertThat(array2.toList()).containsExactly();
        assertThat(array2.size()).isEqualTo(0);
        array2.addFirst("n");
        assertThat(array2.size()).isEqualTo(1);
        assertThat(array2.toList()).containsExactly("n");
    }

    @Test
    public void isEmptyTest() {
        Deque61B<String> array = new ArrayDeque61B<>();
        assertThat(array.isEmpty()).isEqualTo(true);
        array.addLast("n");
        assertThat(array.isEmpty()).isEqualTo(false);
    }

    @Test
    public void addLastTestBasicWithoutToList() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        assertThat(lld1).containsExactly();
        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    public void testEqualDeque61B() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        Deque61B<String> lld2 = new ArrayDeque61B<>();
        Deque61B<String> lld3 = new ArrayDeque61B<>();
        Deque61B<String> lld4 = new LinkedListDeque61B<>();

        String str = "front";

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        lld3.addLast("front");
        lld3.addLast("middle");

        lld4.addLast("front");
        lld4.addLast("middle");
        lld4.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
        assertThat(lld1).isNotEqualTo(str);
        assertThat(lld1).isNotEqualTo(lld3);
        assertThat(lld4).isEqualTo(lld1);
        assertThat(lld1).isEqualTo(lld4);

        lld3.addLast("none");
        assertThat(lld1).isNotEqualTo(lld3);
    }

    @Test
    public void toStringTest() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toString()).isEqualTo("[front, middle, back]");
    }
}
