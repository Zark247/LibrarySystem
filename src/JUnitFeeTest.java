import org.junit.*;
import java.io.*;

/**
 * Tests for Fee class
 * @author Jacob A. Hiers
 */
public class JUnitFeeTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Fee fee;
    private Media media;
    private Book aBook = new Book("title", "fiction", "description", "2077",
    false, 1, "author", "00000001", "publisher");

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        fee = new Fee(media);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * Test for increasing daily fee
     */
    @Test
    public void dailyIncreaseTest() {
        fee.dailyFineIncrease();
        double fees = fee.getTotal();
        Assert.assertEquals(.1, fees, .0001);
    }

    /**
     * Test for overpayment
     */
    @Test
    public void payTest() {
        double amount = 16.25;
        fee.setTotal(10.08);
        String expected = "You cannot pay more than your current total.\n" +
                "Total fees: 10.08\r\n";
        fee.pay(amount);
        Assert.assertEquals(expected, outContent.toString());
    }

    /**
     * Test for correct retrieval of Id
     */
    @Test
    public void returnIdTest() {
        int expected = fee.getFEE_COUNT();
        Assert.assertEquals(expected, fee.returnId());
    }

    /**
     * Test for correct retrieval of Max fine for a specific media
     */
    @Test
    public void getMaxTest() {
        double expected = 15.00;
        Assert.assertEquals(expected, fee.getMax(), .0001);
    }

    /**
     * Test for correct retrieval of fine per day per media
     */
    @Test
    public void getFineTest() {
        double expected = 0.10;
        Assert.assertEquals(expected, fee.getFine(), .0001);
    }

    /**
     * Test for correct retrieval of total fees for specific user
     */
    @Test
    public void getTotalTest() {
        fee.setTotal(98.52);
        double expected = 98.52;
        Assert.assertEquals(expected, fee.getTotal(), .0001);
    }

    /**
     * Test for correct retrieval of specific media
     */
    @Test
    public void getMediaTest() {
        Assert.assertNotSame(aBook, fee.getMedia());
    }
}
