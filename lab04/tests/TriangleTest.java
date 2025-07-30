import org.junit.Rule;
import org.junit.Test;
import static com.google.common.truth.Truth.assertWithMessage;
public abstract class TriangleTest {

    /** For autograding purposes; do not change this line. */
    abstract Triangle getNewTriangle();

    /* ***** TESTS ***** */

    @Test
    public void sidesFormTriangle() {
        Triangle t = getNewTriangle();
        // remember that you'll have to call on Triangle methods like
        // t.functionName(arguments), where t is a Triangle object
        assertWithMessage("Any side must be positive.").that(t.sidesFormTriangle(0, 0, 0)).isEqualTo(false);
        assertWithMessage("Any the sum of any two sides must be > the third side.").that(t.sidesFormTriangle(2, 2, 1)).isEqualTo(true);
        assertWithMessage("Any the sum of any two sides must be > the third side.").that(t.sidesFormTriangle(3, 2, 1)).isEqualTo(false);
    }

    @Test
    public void pointsFormTriangle() {
        Triangle t = getNewTriangle();
        assertWithMessage("").that(t.pointsFormTriangle(1, 1, 2, 2, 3, 3)).isEqualTo(false);
        assertWithMessage("").that(t.pointsFormTriangle(1, 1, 2, 2, 5, 4)).isEqualTo(true);
    }

    @Test
    public void triangleType() {
        Triangle t = getNewTriangle();
        assertWithMessage("").that(t.triangleType(1, 2, 3)).isEqualTo("Scalene");
        assertWithMessage("").that(t.triangleType(1, 1, 1)).isEqualTo("Equilateral");
        assertWithMessage("").that(t.triangleType(1, 1, 2)).isEqualTo("Isosceles");
    }

    @Test
    public void squaredHypotenuse() {
        Triangle t = getNewTriangle();
        assertWithMessage("").that(t.squaredHypotenuse(1, 2)).isEqualTo(5);
        assertWithMessage("").that(t.squaredHypotenuse(3, 4)).isEqualTo(25);
    }
}
