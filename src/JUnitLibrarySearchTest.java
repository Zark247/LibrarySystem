import org.junit.*;
import java.io.*;

/**
 * Tests for LibrarySearch class
 * @author Jacob A. Hiers
 */
public class JUnitLibrarySearchTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private LibrarySearch libSearch;
    private boolean show;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        libSearch = new LibrarySearch();
        show = false;
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * Test for false mature rating
     */
    @Test
    public void showMatureContentTest() {
        libSearch.showMatureContent(show);
        Assert.assertNotNull(outContent.toString());
    }

    /**
     * Test for search function in LibrarySearch
     */
    @Test
    public void searchTest() {
        libSearch.search("Mark's guid to cats");
        String expected = "No media within the library matches the search parameter: \"Mark's guid to cats\"\r\n";
        Assert.assertEquals(expected, outContent.toString());
    }
}
