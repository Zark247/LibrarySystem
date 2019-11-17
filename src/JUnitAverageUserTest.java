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
 * JUnit test cases for AverageUser
 * @author Cameron Brandenburg
 */
public class JUnitAverageUserTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	User validUser = new AverageUser("John Brown", "11/11/2000", "21 Address Way", "email@email.com", 
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
		assertTrue(validUser.name == "John Brown" || validUser.dateOfBirth == "11/11/2000" ||
				validUser.address == "21 Address Way" || validUser.email == "email@email.com" ||
				validUser.phoneNumber == "123-456-7890" || validUser.username == "username" ||
				validUser.password == "password" || validUser.accountId == 0);
	}
}
