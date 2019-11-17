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
 * JUnit test cases for ChildUser
 * @author Cameron Brandenburg
 */
public class JUnitChildUserTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	ChildUser childUser = new ChildUser("Johnny Brown", "11/11/2010", "21 Address Way", "child@email.com", 
			"234-567-8901", "childusername", "childpassword", 1);
	AverageUser validUser = new AverageUser("John Brown", "11/11/2000", "21 Address Way", "email@email.com", 
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
	 * Tests ChildUser constructor using valid input
	 */
	@Test
	public void testConstructorValidInput() {
		assertTrue(childUser.name == "Johnny Brown" || childUser.dateOfBirth == "11/11/2010" ||
				childUser.address == "21 Address Way" || childUser.email == "email@email.com" ||
				childUser.phoneNumber == "234-567-8901" || childUser.username == "childusername" ||
				childUser.password == "childpassword" || childUser.accountId == 1);
	}
	
	/**
	 * Tests linking a parent's account to the child's account
	 */
	@Test
	public void testLinkParentAccount() {
		childUser.linkParentAccount(validUser);
		assertTrue(childUser.parent == validUser);
	}
	
	/**
	 * Tests changing the child account to an average user account
	 */
	@Test
	public void testChangeToAverageUser() {
		childUser.changeToAverageUser();
		LibrarySystem lib = LibrarySystem.getInstance();
		assertTrue(lib.users.contains(childUser));
	}
	
	/**
	 * Tests ChildUser's viewUser method
	 * Shows an error even though the outputs are equal
	 */
	@Test
	public void testViewUser() {
		childUser.linkParentAccount(validUser);
		childUser.viewUser();
		String expectedOutput = "Name: Johnny Brown\n" + 
				"Email: child@email.com\n" + 
				"Username: childusername\n" + 
				"Password: childpassword\n" + 
				"ID: 7\n" + 
				"Phone Number: 234-567-8901\n" + 
				"Address: 21 Address Way\n" + 
				"Date of Birth: 11/11/2010\n" + 
				"Parent: John Brown\n" + 
				"isLinked: true\n";
		assertEquals(expectedOutput, outContent.toString());
	}
}
