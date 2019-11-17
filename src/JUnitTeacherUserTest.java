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
 * JUnit test cases for TeacherUser
 * @author Cameron Brandenburg
 */
public class JUnitTeacherUserTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	Book book = new Book("Book 1", "Fiction", "Book 1 Description", "2018", false, 1,
            "Book Author", "0000", "Book Publisher");
	
	TeacherUser validUser = new TeacherUser("Garfunkel Nottingham", "11/11/1999", "42 Address Way", "teacher@email.com", 
			"111-111-1111", "teacherusername", "teacherpassword", 0);
	LibrarianUser librarianUser = new LibrarianUser("John Brown", "11/11/2000", "21 Address Way", "email@email.com", 
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
	 * Tests AverageUser's constructor using valid input
	 */
	@Test
	public void testConstructorValidInput() {
		assertTrue(validUser.name == "Garfunkel NOttingham" || validUser.dateOfBirth == "11/11/1999" ||
				validUser.address == "42 Address Way" || validUser.email == "teacher@email.com" ||
				validUser.phoneNumber == "111-111-1111" || validUser.username == "teacherusername" ||
				validUser.password == "teacherpassword" || validUser.accountId == 0);
	}
	
	/**
	 * Tests setting the approved attribute for a teacher
	 */
	@Test
	public void testSetIsApproved() {
		validUser.setIsApproved(true);;
		assertTrue(validUser.isApproved);
	}
	
	/**
	 * Tests TeacherUser's viewUser method
	 * Shows an error even though the outputs are equal
	 */
	@Test
	public void testViewUser() {
		validUser.viewUser();
		String expectedOutput = "Name: Garfunkel Nottingham\n" + 
				"Email: teacher@email.com\n" + 
				"Username: teacherusername\n" + 
				"Password: teacherpassword\n" + 
				"ID: 5\n" + 
				"Phone Number: 111-111-1111\n" + 
				"Address: 42 Address Way\n" + 
				"Date of Birth: 11/11/1999\n" + 
				"isApproved: false\n";
		assertEquals(expectedOutput, outContent.toString());
	}
}
