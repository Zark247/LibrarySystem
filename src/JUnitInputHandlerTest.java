import org.junit.*;
import java.io.*;

/**
 * Tests for InputHandler class
 * @author Jacob A. Hiers
 */
public class JUnitInputHandlerTest {

    InputHandler IHandler = new InputHandler();
    User user;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private LibrarySystem actualLibSystem;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        actualLibSystem = new LibrarySystem();
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * Test for incorrect command
     */
    @Test
    public void inputTest() {
        String[] expectedArray;
        expectedArray = new String[3];
        expectedArray[0] = "see";
        String expected = "Command not recognized.\r\n";
        IHandler.inputCommand(expectedArray);
        Assert.assertEquals(expected, outContent.toString());
    }

    /**
     * Test to see if changeUser() in InputHandler functions properly
     */
    @Test
    public void changeUserTest() {
        IHandler.changeUser(user);
        String expected = "";
        Assert.assertEquals(expected, outContent.toString());
    }
}
