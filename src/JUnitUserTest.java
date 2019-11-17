import static org.junit.Assert.*;

import java.util.ArrayList;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * JUnit test cases for User
 * @author Cameron Brandenburg
 */
public class JUnitUserTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	Book book = new Book("Book 1", "Fiction", "Book 1 Description", "2018", false, 1,
            "Book Author", "0000", "Book Publisher");
	DVD dvd = new DVD("DVD 1", "Documentary", "DVD 1 Description", "2005", false, 1,
               "DVD Director");
	
	User validUser = new AverageUser("John Brown", "11/11/2000", "21 Address Way", "email@email.com", 
			"123-456-7890", "username", "password", 0);
	
	User otherUser = new AverageUser("John Brown", "11/11/2000", "21 Address Way", "email@email.com", 
			"123-456-7890", "username", "password", 0);
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}
	
	/**
	 * Tests User's constructor using valid input
	 */
	@Test
	public void testConstructorValidInput() {
		assertTrue(validUser.name == "John Brown" || validUser.dateOfBirth == "11/11/2000" ||
				validUser.address == "21 Address Way" || validUser.email == "email@email.com" ||
				validUser.phoneNumber == "123-456-7890" || validUser.username == "username" ||
				validUser.password == "password" || validUser.accountId == 0);
	}
	
	/**
	 * Tests closing a user account
	 */
	@Test
	public void testCloseAccount() {
		User validUser = new AverageUser("John Brown", "11/11/2000", "21 Address Way", "email@email.com", 
				"123-456-7890", "username", "password", 0);
		validUser.closeAccount();
		assertTrue(validUser.isClosed);
	}
	
	/**
	 * Tests viewing the currently checked out media array list when it is empty
	 */
	@Test
	public void testViewCurrentlyCheckedOutMediaEmpty() {
		validUser.viewCurrentlyCheckedOutMedia();
		String expectedOutput = "Checked Out list is empty\r\n";
		assertEquals(expectedOutput, outContent.toString());
	}
	
	/*@Test
	public void testViewCurrentlyCheckedOutMedia() {
		validUser.checkedOutMedia.add(book);
		String expectedOutput = "Book 1";
		validUser.viewCurrentlyCheckedOutMedia();
		assertEquals(expectedOutput, outContent.toString());
	}*/
	
	
	
	/**
	 * Tests printing out the list of the user's held media when it is empty
	 */
	@Test
	public void testViewMediaOnHoldEmpty() {
		validUser.viewMediaOnHold();
		String expectedOutput = "Hold list is empty\r\n";
		assertEquals(expectedOutput, outContent.toString());
	}
	
	/**
	 * Tests printing out the list of the user's held media when it is not empty
	 */
	@Test
	public void testViewMediaOnHold() {
		validUser.heldMedia.add(book);
		validUser.viewMediaOnHold();
		String expectedOutput = "Currently held media:\r\n" + book.toString()+"\r\n";
		assertEquals(expectedOutput, outContent.toString());
	}
	
	/**
	 * Tests checking a user's account number
	 */
	@Test
	public void testCheckAccountNumber() {
		assertEquals(0, validUser.accountId);
	}
	
	/**
	 * Tests checking the user's wishlist when it is empty
	 */
	@Test
	public void testCheckWishlistEmpty() {
		validUser.checkWishlist();
		String expectedOutput = "Wishlist is empty\r\n";
		assertEquals(expectedOutput, outContent.toString());
	}
	
	/**
	 * Tests checking the user's wishlist when it is not empty
	 */
	@Test
	public void testCheckWishlist() {
		validUser.wishlist.add(book.getTitle());
		validUser.checkWishlist();
		String expectedOutput = "Your wishlist:\r\n"+book.getTitle()+"\r\n";
		assertEquals(expectedOutput, outContent.toString());
	}
	
	/**
	 * Tests returning a user's account type
	 */
	@Test
	public void testReturnAccountType() {
		assertEquals(validUser.getAccountType(), "Average");
	}
	
	/**
	 * Tests a user checking out a book
	 */
	@Test
	public void testCheckoutMedia() {
		validUser.checkoutMedia(book);
		assertTrue(validUser.checkedOutMedia.contains(book));
	}
	
	/**
	 * Tests a user checking out media when they already are at the maximum limit for checking out
	 *
	 */
	@Test
	public void testCheckoutMediaMaxLimit() {
		// Adds 20 books, which is over the limit for an AverageUser
		for (int i=0; i<20; ++i)
		{
			validUser.checkedOutMedia.add(book);
		}
		String expectedOutput = "You have hit your maximum checkout limit!  Please return something first.\r\n";
		validUser.checkoutMedia(book);
		assertEquals(expectedOutput, outContent.toString());
	}
	
	/**
	 * Tests a user checking of media when they have the max number of fines and should not be able to check
	 * out addition media
	 */
	@Test
	public void testCheckoutMediaMaxFines() {
		Fee fee1 = new Fee(book);
		fee1.setTotal(101);
		validUser.fines.add(fee1);
		validUser.checkoutMedia(book);
		String expectedOutput = "Your fine total is above the maximum ($100)!  Please pay off your balance first.\r\n";
		assertEquals(expectedOutput, outContent.toString());
		fee1.setTotal(0);
	}
	
	/**
	 * Tests a user requesting media (which adds it to their wishlist)
	 */
	@Test
	public void testRequestMedia() {
		validUser.requestMedia(book.getTitle());
		String expectedOutput = "Your wishlist:\r\n"+book.getTitle()+"\r\n";
		validUser.checkWishlist();
		assertEquals(expectedOutput, outContent.toString());
	}
	
	/**
	 * Tests a user returning media
	 */
	@Test
	public void testReturnMedia() {
		validUser.returnMedia(book);
		assertTrue(validUser.checkedOutMedia.isEmpty());
	}
	
	/**
	 * Tests a user attempting to return media when they do not have that media checked out
	 */
	@Test
	public void testReturnMediaNotCheckedOut() {
		validUser.returnMedia(book);
		String expectedOutput = "You cannot return an item you do not have checked out!\r\n";
		assertEquals(expectedOutput, outContent.toString());
	}
	
	/**
	 * Tests a user renewing media
	 */
	@Test
	public void testRenewMedia() {
		validUser.checkoutMedia(book);
		validUser.renewMedia(book);
		assertTrue(validUser.checkedOutMedia.get(0).renewCount == 1);
		validUser.returnMedia(book);
	}
	
	/**
	 * Tests a user renewing media they do not have checked out
	 */
	@Test
	public void testRenewMediaNotCheckedOut() {
		validUser.renewMedia(book);
		String expectedOutput = "You cannot renew a media you do not have checked out!\r\n";
		assertEquals(expectedOutput, outContent.toString());
	}
	
	/**
	 * Tests the method to view a user (like a toString for user info)
	 */
	@Test
	public void testViewUser() {
		validUser.viewUser();
		String expectedOutput = "Name: John Brown\n" + 
				"Email: email@email.com\n" + 
				"Username: username\n" + 
				"Password: password\n" + 
				"ID: 50\n" + 
				"Phone Number: 123-456-7890\n" + 
				"Address: 21 Address Way\n" + 
				"Date of Birth: 11/11/2000\r\n" + 
				"";
		assertEquals(expectedOutput, outContent.toString());
	}
	
	/**
	 * Tests searching for a username
	 */
	@Test
	public void testSearch() {
		// Something going on here with the IDs of users - need to look into
		validUser.search("username");
		String expectedOutput = "Name: John Brown\n" + 
				"Email: email@email.com\n" + 
				"Username: username\n" + 
				"Password: password\n" + 
				"ID: 42\n" + 
				"Phone Number: 123-456-7890\n" + 
				"Address: 21 Address Way\n" + 
				"Date of Birth: 11/11/2000\r\n" + 
				"";
		assertEquals(expectedOutput, outContent.toString());
	}
	
	/**
	 * Tests searching for a username when there are no results
	 */
	@Test
	public void testSearchIncorrect() {
		validUser.search("nameuser");
		String expectedOutput = "No user found\r\n";
		assertEquals(expectedOutput, outContent.toString());
	}
	
	/**
	 * Tests checking a user's fines when they have none
	 */
	@Test 
	public void testCheckFinesNone() {
		validUser.checkFines();
		String expectedOutput = "You have no fines\r\n";
		assertEquals(expectedOutput, outContent.toString());
	}
	
	/**
	 * Tests checking a user's fines when they have multiple
	 */
	@Test
	public void testCheckFines() {
		Fee fee1 = new Fee(book);
		fee1.setTotal(10);
		validUser.fines.add(fee1);
		Fee fee2 = new Fee(dvd);
		fee2.setTotal(8);
		validUser.fines.add(fee2);
		validUser.checkFines();
		String expectedOutput = "You currently owe $18.0.  List of fines:\r\n" + 
				"Your fine for \"Book 1\" is $10.0\r\n" + 
				"Your fine for \"DVD 1\" is $8.0\r\n";
		assertEquals(expectedOutput, outContent.toString());
	}
	
	/**
	 * Tests the fineTotal method which returns the total of all a user's fines
	 */
	@Test
	public void testFineTotal() {
		Fee fee1 = new Fee(book);
		fee1.setTotal(10);
		validUser.fines.add(fee1);
		Fee fee2 = new Fee(dvd);
		fee2.setTotal(8);
		validUser.fines.add(fee2);
		assertEquals(validUser.fineTotal(), 18.00, 0.001);
	}
	
	/**
	 * Tests the update method which increments a user's fees when they are late
	 */
	@Test
	public void testUpdate() {
		Fee fee1 = new Fee(book);
		fee1.setTotal(10);
		validUser.fines.add(fee1);
		Fee fee2 = new Fee(dvd);
		fee2.setTotal(8);
		validUser.fines.add(fee2);
		validUser.update();
		assertEquals(validUser.fineTotal(), 18.20, 0.001);
	}
	
	/**
	 * Tests paying a user's fines
	 */
	@Test
	public void testPayFine() {
		Fee fee1 = new Fee(book);
		fee1.setTotal(10);
		validUser.fines.add(fee1);
		validUser.payFine(fee1, 10);
		assertEquals(validUser.fineTotal(), 0, 0.001);
	}
	
	/**
	 * Tests notifying a user
	 */
	@Test
	public void testNotify() {
		validUser.notify("Notification");
		assertTrue(validUser.notifications.contains("Notification"));
	}
	
	/**
	 * Tests flagging a user
	 */
	@Test
	public void testFlagUser() {
		validUser.flagUser();
		assertTrue(validUser.isFlagged);
	}
}
