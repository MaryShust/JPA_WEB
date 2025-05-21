package unit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ejb.CheckerPoint;

public class CheckerPointTest {

    @Test
    public void testValidation_InRect() {
        CheckerPoint checker = new CheckerPoint();
        double x = 1.0;
        double y = -1.0;
        int r = 2;
        assertTrue(checker.validation(x, y, r));
    }

    @Test
    public void testValidation_InTriangle() {
        CheckerPoint checker = new CheckerPoint();
        double x = -1.0;
        double y = 1.0;
        int r = 2;
        assertFalse(checker.validation(x, y, r));
    }

    @Test
    public void testValidation_InCircle() {
        CheckerPoint checker = new CheckerPoint();
        double x = 0.5;
        double y = 0.5;
        int r = 2;
        assertTrue(checker.validation(x, y, r));
    }

    @Test
    public void testValidation_OutOfRect() {
        CheckerPoint checker = new CheckerPoint();
        double x = 3.0;
        double y = -1.0;
        int r = 2;
        assertFalse(checker.validation(x, y, r));
    }

    @Test
    public void testValidation_OutOfTriangle() {
        CheckerPoint checker = new CheckerPoint();
        double x = 1.0;
        double y = 2.0;
        int r = 2;
        assertFalse(checker.validation(x, y, r));
    }

    @Test
    public void testValidation_OutOfCircle() {
        CheckerPoint checker = new CheckerPoint();
        double x = 1.5;
        double y = 1.5;
        int r = 2;
        assertFalse(checker.validation(x, y, r));
    }

    @Test
    public void testInRect_Corner() {
        CheckerPoint checker = new CheckerPoint();
        double x = 0.0;
        double y = 0.0;
        int r = 2;
        assertTrue(checker.validation(x, y, r));
    }

    @Test
    public void testInTriangle_Corner() {
        CheckerPoint checker = new CheckerPoint();
        double x = 0.0;
        double y = 0.0;
        int r = 2;
        assertTrue(checker.validation(x, y, r));
    }

    @Test
    public void testInCircle_Center() {
        CheckerPoint checker = new CheckerPoint();
        double x = 0.0;
        double y = 0.0;
        int r = 2;
        assertTrue(checker.validation(x, y, r));
    }

    @Test
    public void testValidation_NegativeR() {
        CheckerPoint checker = new CheckerPoint();
        double x = 1.0;
        double y = 1.0;
        int r = -2;
        assertFalse(checker.validation(x, y, r));
    }
}