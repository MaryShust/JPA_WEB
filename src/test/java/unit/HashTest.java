package unit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.Hash;

public class HashTest {

    @Test
    public void testSHA() {
    	String password = "password";
    	String actualHash = Hash.SHA(password);
    	assertEquals(31, actualHash.getBytes().length);
    }

    @Test
    public void testSHA2() {
        String password = "password";
        String actualHash = Hash.SHA(password);
        assertNotNull(actualHash);
    }

    @Test
    public void testSHA_null() {
        String password = null;
        String actualHash = Hash.SHA(password);
        assertNull(actualHash);
    }

    @Test
    public void testSHA_empty() {
        String password = "";
        String actualHash = Hash.SHA(password);
        assertNotNull(actualHash);
    }
}