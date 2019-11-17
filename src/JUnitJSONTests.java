/**
 * JUnitJSONTests.java - JUnit Testing for all (or most) JSON functions.
 * @author Kevin Prince
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitJSONTests {
	private static JSONReader json = new JSONReader();
	private static ArrayList<Media> invHolder;
	private static ArrayList<User>  userHolder;
	private static ArrayList<Fee>   feeHolder;
	
	//Before the tests, save a backup of the current Json's, then clear the system.
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		json.loadDatabase();
		invHolder = (ArrayList<Media>) LibrarySystem.getInstance().inventory.clone();
		userHolder = (ArrayList<User>) LibrarySystem.getInstance().users.clone();
		feeHolder = (ArrayList<Fee>) LibrarySystem.getInstance().fees.clone();
		clearSystem();
		json.writeDatabase();
	}
	
	//Before each tests, clear system & jsons, so we start with an empty database.
	@Before
	public void clearBefore() {
		clearSystem();
		json.writeDatabase();
	}
	
	/**
	 * A test for reading and writing a basic book.
	 */
	@Test
	public void BookBasicWriteReadTest() {
		try {
			Book temp = new Book("Test Title", "Test Genre", "Test Description", "Test Year", true, 1, "Test Author", "Test ISBN", "Test Publisher");
			JSONRefresh();
			Book tempLoaded = (Book) LibrarySystem.getInstance().inventory.get(0);
			assertTrue("Loaded book does not equal saved book.",tempLoaded.getTitle().equals("Test Title") && 
					tempLoaded.getGenre().equals("Test Genre") &&
					tempLoaded.getDescription().equals("Test Description") &&
					tempLoaded.getYearOfRelease().equals("Test Year") &&
					tempLoaded.isNewRelease() &&
					tempLoaded.getAuthor().equals("Test Author") &&
					tempLoaded.getISBN().equals("Test ISBN") &&
					tempLoaded.getPublisher().equals("Test Publisher") &&
					tempLoaded.getId() == 1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("Exception while writing books.");
		}
	}
	
	@Test
	public void AudioBookBasicWriteReadtest() {
		try {
			AudioBook temp = new AudioBook("Test Title", "Test Genre", "Test Description", "Test Year", true, 1, "Test Author", "Test Narrator");
			JSONRefresh();
			AudioBook tempLoaded = (AudioBook) LibrarySystem.getInstance().inventory.get(0);
			assertTrue("Loaded audiobook does not equal saved audiobook.",tempLoaded.getTitle().equals("Test Title") && 
					tempLoaded.getGenre().equals("Test Genre") &&
					tempLoaded.getDescription().equals("Test Description") &&
					tempLoaded.getYearOfRelease().equals("Test Year") &&
					tempLoaded.isNewRelease() &&
					tempLoaded.getAuthor().equals("Test Author") &&
					tempLoaded.getNarrator().equals("Test Narrator") &&
					tempLoaded.getId() == 1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("Exception while writing audiobooks.");
		}
	}
	
	@Test
	public void EBookBasicWriteReadtest() {
		try {
			EBook temp = new EBook("Test Title", "Test Genre", "Test Description", "Test Year", true, 1, "Test Author");
			JSONRefresh();
			EBook tempLoaded = (EBook) LibrarySystem.getInstance().inventory.get(0);
			assertTrue("Loaded Ebook does not equal saved Ebook.",tempLoaded.getTitle().equals("Test Title") && 
					tempLoaded.getGenre().equals("Test Genre") &&
					tempLoaded.getDescription().equals("Test Description") &&
					tempLoaded.getYearOfRelease().equals("Test Year") &&
					tempLoaded.isNewRelease() &&
					tempLoaded.getAuthor().equals("Test Author") &&
					tempLoaded.getId() == 1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("Exception while writing Ebooks.");
		}
	}
	
	@Test
	public void MagazineBasicWriteReadtest() {
		try {
			Magazine temp = new Magazine("Test Title", "Test Genre", "Test Description", "Test Year", true, 1, "Test Author", 1, 2);
			JSONRefresh();
			Magazine tempLoaded = (Magazine) LibrarySystem.getInstance().inventory.get(0);
			assertTrue("Loaded magazine does not equal saved magazine.",tempLoaded.getTitle().equals("Test Title") && 
					tempLoaded.getGenre().equals("Test Genre") &&
					tempLoaded.getDescription().equals("Test Description") &&
					tempLoaded.getYearOfRelease().equals("Test Year") &&
					tempLoaded.isNewRelease() &&
					tempLoaded.getAuthor().equals("Test Author") &&
					tempLoaded.getVolume() == 1 &&
					tempLoaded.getIssue() == 2 &&
					tempLoaded.getId() == 1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("Exception while writing Magazines.");
		}
	}
	
	@Test
	public void DVDBasicWriteReadtest() {
		try {
			DVD temp = new DVD("Test Title", "Test Genre", "Test Description", "Test Year", true, 1, "Test Director");
			JSONRefresh();
			DVD tempLoaded = (DVD) LibrarySystem.getInstance().inventory.get(0);
			assertTrue("Loaded DVD does not equal saved DVD.",tempLoaded.getTitle().equals("Test Title") && 
					tempLoaded.getGenre().equals("Test Genre") &&
					tempLoaded.getDescription().equals("Test Description") &&
					tempLoaded.getYearOfRelease().equals("Test Year") &&
					tempLoaded.isNewRelease() &&
					tempLoaded.getDirector().equals("Test Director") &&
					tempLoaded.getId() == 1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("Exception while writing DVDs.");
		}
	}
	
	@Test
	public void UserBasicWriteReadTest() {
		try {
			User temp = new AverageUser("Test Name", "Test DOB", "Test Address", "Test Email", "Test P#", "Test Username", "Test Pass", 100);
			JSONRefresh();
			AverageUser tempLoaded = (AverageUser) LibrarySystem.getInstance().users.get(0);
			assertTrue("Loaded User does not match saved User.", tempLoaded.getName().equals("Test Name") &&
					tempLoaded.getDateOfBirth().equals("Test DOB") &&
					tempLoaded.getAddress().equals("Test Address") &&
					tempLoaded.getEmail().equals("Test Email") &&
					tempLoaded.getPhoneNumber().equals("Test P#") &&
					tempLoaded.getUsername().equals("Test Username") &&
					tempLoaded.getPassword().equals("Test Pass") &&
					tempLoaded.getId() == 1);
		} catch(Exception e) {
			e.printStackTrace();
			fail("Exception while writing users.");
		}
	}
	
	/**
	 * A test for giving a fee to a user, and ensuring it is loaded correctly.
	 */
	@Test
	public void BasicFeeWriteReadTest() {
		AverageUser tempU = new AverageUser("Test Name", "Test DOB", "Test Address", "Test Email", "Test P#", "Test Username", "Test Pass", 100);
		Book tempM = new Book("Test Title", "Test Genre", "Test Description", "Test Year", true, 1, "Test Author", "Test ISBN", "Test Publisher");
		Fee tempF = new Fee(tempM);
		tempF.setTotal(12.3);
		ArrayList<Fee> feeList = new ArrayList<Fee>();
		feeList.add(tempF);
		tempU.setFines(feeList);
		JSONRefresh();
		AverageUser tempULoaded = (AverageUser) LibrarySystem.getInstance().users.get(0);
		Fee tempFLoaded = LibrarySystem.getInstance().fees.get(0);
		Book tempMLoaded = (Book) LibrarySystem.getInstance().inventory.get(0);
		assertTrue("User does not correctly have Fee",!tempULoaded.getFines().isEmpty() && tempULoaded.getFines().get(0).equals(tempFLoaded));
		assertTrue("Fine does not have the correct amount", tempFLoaded.getTotal() == 12.3);
		assertTrue("Fine is not tied to the correct media", tempFLoaded.getMedia().equals(tempMLoaded));
	}
	
	/**
	 * Tests for data referring to checkout information.
	 */
	@Test
	public void CheckoutRenewInformationTest() {
		AverageUser tempU = new AverageUser("Test Name", "Test DOB", "Test Address", "Test Email", "Test P#", "Test Username", "Test Pass", 100);
		Book tempM = new Book("Test Title", "Test Genre", "Test Description", "Test Year", true, 1, "Test Author", "Test ISBN", "Test Publisher");
		tempU.checkoutMedia(tempM);
		tempU.renewMedia(tempM);
		Date dueDate = tempM.getLastDueDate();
		Date borrowDate = tempM.getLastBorrowDate();
		dueDate.setSeconds(0);
		borrowDate.setSeconds(0);
		JSONRefresh();
		AverageUser tempULoaded = (AverageUser) LibrarySystem.getInstance().users.get(0);
		Book tempMLoaded = (Book) LibrarySystem.getInstance().inventory.get(0);
		assertTrue("Item is not checked out.", tempMLoaded.isCheckedOut());
		//Set the time 30 days in the future, and compare it to the due date.  Should be equal.
		assertTrue("Due dates do not match", tempMLoaded.getLastDueDate().toString().equals(dueDate.toString()));
		assertTrue("Borrow dates do not match", tempMLoaded.getLastBorrowDate().toString().equals(borrowDate.toString()));
		assertTrue("Loaded user does not have media checked out", tempULoaded.getCheckedOutMedia().contains(tempMLoaded));
		assertTrue("Loaded book does have correct renew count", tempMLoaded.getRenewCount() == 1);
	}
	
	/**
	 * Test method to add a rating to a book, and ensure it remains after refresh.
	 */
	@Test
	public void BookRatingTest() {
		Book tempM = new Book("Test Title", "Test Genre", "Test Description", "Test Year", true, 1, "Test Author", "Test ISBN", "Test Publisher");
		tempM.addRating(3, "Test Review 1");
		tempM.addRating(4, "Test Review 2");
		JSONRefresh();
		assertTrue("First rating does not match.",tempM.getRatingList().get(0).equals("Rating: 3.0\nComment: Test Review 1"));
		assertTrue("Second rating does not match.",tempM.getRatingList().get(1).equals("Rating: 4.0\nComment: Test Review 2"));
	}
	
	/**
	 * Test method for book's waiting lists.
	 */
	@Test
	public void BookWaitlistTest() {
		AverageUser tempU = new AverageUser("Test Name", "Test DOB", "Test Address", "Test Email", "Test P#", "Test Username", "Test Pass", 100);
		AverageUser tempU2 = new AverageUser("Test Name2", "Test DOB2", "Test Address2", "Test Email2", "Test P#2", "Test Username2", "Test Pass2", 101);
		Book tempM = new Book("Test Title", "Test Genre", "Test Description", "Test Year", true, 1, "Test Author", "Test ISBN", "Test Publisher");
		ArrayList<User> waitList = new ArrayList<User>();
		waitList.add(tempU);
		waitList.add(tempU2);
		tempM.setWaitlist(waitList);
		JSONRefresh();
		Book tempMLoaded = (Book) LibrarySystem.getInstance().inventory.get(0);
		AverageUser tempULoaded = (AverageUser) LibrarySystem.getInstance().users.get(0);
		AverageUser tempU2Loaded = (AverageUser) LibrarySystem.getInstance().users.get(1);
		assertFalse("Book waitlist is empty", tempMLoaded.getWaitlist().isEmpty());
		assertTrue("Book waitlist does not contain users.", tempMLoaded.getWaitlist().contains(tempULoaded) && tempMLoaded.getWaitlist().contains(tempU2Loaded));
		assertTrue("Book waitlist does not order first user correctly.", tempMLoaded.getWaitlist().get(0).equals(tempULoaded));
		assertTrue("Book waitlist does not order second user correctly.", tempMLoaded.getWaitlist().get(1).equals(tempU2Loaded));
	}
	
	/**
	 * Test to ensure that the JSON correctly creates a number of copies.
	 */
	@Test
	public void CopyTest() {
		Book tempM = new Book("Test Title", "Test Genre", "Test Description", "Test Year", true, 4, "Test Author", "Test ISBN", "Test Publisher");
		JSONRefresh();
		int copiesCount = 0;
		for(Media m:LibrarySystem.getInstance().inventory) {
			if(m.getTitle().equals("Test Title"))
				copiesCount++;
		}
		assertTrue("Item has 0 copies.", copiesCount != 0);
		assertTrue("Item only has " + copiesCount + " copies.", copiesCount == 4);
	}
	
	/**
	 * Test to ensure that JSON correctly writes and reads wishlists for users.
	 */
	@Test
	public void WishlistTest() {
		AverageUser tempU = new AverageUser("Test Name", "Test DOB", "Test Address", "Test Email", "Test P#", "Test Username", "Test Pass", 100);
		ArrayList<String> wishList = new ArrayList<String>();
		wishList.add("Test Book 1");
		wishList.add("Test Book 2");
		tempU.setWishlist(wishList);
		JSONRefresh();
		AverageUser tempULoaded = (AverageUser) LibrarySystem.getInstance().users.get(0);
		assertFalse("User wishlist is empty", tempULoaded.getWishlist().isEmpty());
		assertTrue("User wishlist does not contain string 1", tempULoaded.getWishlist().contains("Test Book 1"));
		assertTrue("User wishlist does not contain string 2", tempULoaded.getWishlist().contains("Test Book 2"));
	}
	
	/**
	 * Test to ensure that JSON correctly writes and reads notifications for users.
	 */
	@Test
	public void NotificationTest() {
		AverageUser tempU = new AverageUser("Test Name", "Test DOB", "Test Address", "Test Email", "Test P#", "Test Username", "Test Pass", 100);
		ArrayList<String> notification = new ArrayList<String>();
		notification.add("Test Note 1");
		notification.add("Test Note 2");
		tempU.setNotifications(notification);
		JSONRefresh();
		AverageUser tempULoaded = (AverageUser) LibrarySystem.getInstance().users.get(0);
		assertFalse("User notification is empty", tempULoaded.getNotifications().isEmpty());
		assertTrue("User notification does not contain string 1", tempULoaded.getNotifications().contains("Test Note 1"));
		assertTrue("User notification does not contain string 2", tempULoaded.getNotifications().contains("Test Note 2"));
	}
	
	/**
	 * Test to ensure that JSON correctly writes and reads held media for users.
	 */
	@Test
	public void HeldMediaTest() {
		AverageUser tempU = new AverageUser("Test Name", "Test DOB", "Test Address", "Test Email", "Test P#", "Test Username", "Test Pass", 100);
		Book tempM = new Book("Test Title", "Test Genre", "Test Description", "Test Year", true, 1, "Test Author", "Test ISBN", "Test Publisher");
		Book tempM2 = new Book("Test Title2", "Test Genre2", "Test Description2", "Test Year2", true, 1, "Test Author2", "Test ISBN2", "Test Publisher2");
		ArrayList<Media> heldList = new ArrayList<Media>();
		heldList.add(tempM);
		heldList.add(tempM2);
		tempU.setHeldMedia(heldList);
		JSONRefresh();
		AverageUser tempULoaded = (AverageUser) LibrarySystem.getInstance().users.get(0);
		Book tempMLoaded = (Book) LibrarySystem.getInstance().inventory.get(0);
		Book tempM2Loaded = (Book) LibrarySystem.getInstance().inventory.get(1);
		assertFalse("User has no held books", tempULoaded.getHeldMedia().isEmpty());
		assertTrue("User array lacks media 1", tempULoaded.getHeldMedia().contains(tempMLoaded));
		assertTrue("User array lacks media 2", tempULoaded.getHeldMedia().contains(tempM2Loaded));
	}
	
	/**
	 * Test for a user with two children.
	 */
	@Test
	public void ChildrenTest() {
		AverageUser tempU = new AverageUser("Test Name", "Test DOB", "Test Address", "Test Email", "Test P#", "Test Username", "Test Pass", 100);
		ChildUser tempU2 = new ChildUser("Test Name2", "Test DOB2", "Test Address2", "Test Email2", "Test P#2", "Test Username2", "Test Pass2", 101);
		ChildUser tempU3 = new ChildUser("Test Name3", "Test DOB3", "Test Address3", "Test Email3", "Test P#3", "Test Username3", "Test Pass3", 102);
		ArrayList<User> childList = new ArrayList<User>();
		childList.add(tempU2);
		childList.add(tempU3);
		tempU.setChildren(childList);
		JSONRefresh();
		AverageUser tempULoaded = (AverageUser) LibrarySystem.getInstance().users.get(0);
		ChildUser tempU2Loaded = (ChildUser) LibrarySystem.getInstance().users.get(1);
		ChildUser tempU3Loaded = (ChildUser) LibrarySystem.getInstance().users.get(2);
		assertFalse("User has no childen", tempULoaded.getChildren().isEmpty());
		assertTrue("User child list does not contain child 1", tempULoaded.getChildren().contains(tempU2Loaded));
		assertTrue("User child list does not contain child 2", tempULoaded.getChildren().contains(tempU3Loaded));
	}
	
	@AfterClass
	public static void closeDownAfterClass() throws Exception {
		LibrarySystem.getInstance().inventory = invHolder;
		LibrarySystem.getInstance().users = userHolder;
		LibrarySystem.getInstance().fees = feeHolder;
		json.writeDatabase();
	}
	
	//Empties the system's store of inventory, users, and fees.
	private static void clearSystem() {
		LibrarySystem.getInstance().inventory = new ArrayList<Media>();
		LibrarySystem.getInstance().users = new ArrayList<User>();
		LibrarySystem.getInstance().fees = new ArrayList<Fee>();
		json = new JSONReader();
		Media.media_count = 0;
		User.user_count = 0;
		Fee.FEE_COUNT = 0;
	}
	
	//Method for writing database, then starting fresh and reading.
	private static void JSONRefresh() {
		json.writeDatabase();
		clearSystem();
		json.loadDatabase();
	}
}
