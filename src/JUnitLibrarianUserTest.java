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
 * JUnit test cases for LibrarianUser
 * @author Cameron Brandenburg
 */
public class JUnitLibrarianUserTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	Book book = new Book("Book 1", "Fiction", "Book 1 Description", "2018", false, 1,
            "Book Author", "0000", "Book Publisher");
	
	LibrarianUser validUser = new LibrarianUser("John Brown", "11/11/2000", "21 Address Way", "email@email.com", 
			"123-456-7890", "username", "password", 0);
	TeacherUser teacherUser = new TeacherUser("Garfunkel Nottingham", "11/11/1999", "42 Address Way", "teacher@email.com", 
			"111-111-1111", "teacherusername", "teacherpassword", 1);
	
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
	 * Tests LibrarianUser's constructor using valid input
	 */
	@Test
	public void testConstructorValidInput() {
		assertTrue(validUser.name == "John Brown" || validUser.dateOfBirth == "11/11/2000" ||
				validUser.address == "21 Address Way" || validUser.email == "email@email.com" ||
				validUser.phoneNumber == "123-456-7890" || validUser.username == "username" ||
				validUser.password == "password" || validUser.accountId == 0);
	}
	
	/**
	 * Tests a librarian approving a teacher account
	 */
	@Test
	public void testApproveTeacherAccount() {
		validUser.approveTeacherAccount(teacherUser);
		assertTrue(teacherUser.isApproved);
	}
	
	/**
	 * Tests a librarian user retiring media from the inventory
	 */
	@Test
	public void testRetireMedia() {
		LibrarySystem lib = LibrarySystem.getInstance();
		lib.inventory.add(book);
		validUser.retireMedia(book);
		assertFalse(lib.inventory.contains(book));
	}
}
