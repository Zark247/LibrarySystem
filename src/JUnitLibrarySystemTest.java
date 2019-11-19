import org.junit.*;
import java.io.*;
import java.util.*;

/**
 * Tests for LibrarySystem class
 * @author Jacob A. Hiers
 */
public class JUnitLibrarySystemTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private LibrarySystem actualLibSystem;
    private Calendar time;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        actualLibSystem = new LibrarySystem();
        time = Calendar.getInstance();
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    /**
     * Test to make sure that a new instance of LibrarySystem is not null
     */
    @Test
    public void getInstanceTest() {
        Assert.assertNotNull(actualLibSystem.getInstance());
    }

    /**
     * Test for incorrect account creation
     */
    @Test
    public void createAccountTest() {
        int type = 9;
        String name = "Firstname Lastname";
        String dateOfBirth = "01-01-01";
        String address = "123 Real St. Columbia, SC 29169";
        String email = "help@help.com";
        String phoneNumber = "803-555-9999";
        String username = "user";
        String password = "pass";
        int id = 123456;
        actualLibSystem.createAccount(type, name , dateOfBirth, address, email, phoneNumber, username, password, id);
        String expected = "Name: " +name+ "\nDate of Birth: " +dateOfBirth+ "\n Address: " +address+
        "\nEmail: " +email+ "\nPhone number: " +phoneNumber+ "\nUsername: " +username+ "\nPassword: " +password;
        Assert.assertNotEquals(expected, outContent.toString());

    }

    /**
     * Test for invalid login
     */
    @Test
    public void loginTest() {
        String expected = "Incorrect username/password\r\n";
        actualLibSystem.login("average", "password");
        Assert.assertEquals(expected, outContent.toString());
    }

    /**
     * Test for time
     */
    @Test
    public void returnSysTimeTest() {
        actualLibSystem.returnSystime();
        String expected = actualLibSystem.returnSystime().toString();
        Assert.assertNotSame(expected, outContent.toString());
    }

    /**
     * Test for Non-null update to time
     */
    @Test
    public void updateSysTimeTest() {
        actualLibSystem.updateSystime(time);
        Assert.assertNotNull(outContent.toString());
    }

    /**
     * Test to search library for media it does not have
     */
    @Test
    public void searchTest() {
        actualLibSystem.search("Mary had a little lamb");
        String expected = "No media within the library matches the search parameter: \"Mary had a little lamb\"\r\n";
        Assert.assertEquals(expected, outContent.toString());
    }

    /**
     * Test for Non-null inventory
     */
    @Test
    public void inventoryNoCopiesTest() {
        actualLibSystem.inventoryNoCopies();
        Assert.assertNotNull(outContent.toString());
    }
}
