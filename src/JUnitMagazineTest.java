import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Magazine JUnit test
 * @author henryvy
 *
 */
class JUnitMagazineTest {
	
	Magazine validMagazine = new Magazine("Title", "Genre", "Description", "Year", false, 1,
            "Author", 1, 1);

	@Test
	final void testCopy() {
		Magazine clone = new Magazine(validMagazine.getTitle(),validMagazine.getGenre(),validMagazine.getDescription(),validMagazine.getYearOfRelease()
				,validMagazine.isNewRelease(),validMagazine.getCopies(),validMagazine.getAuthor(),validMagazine.getVolume(),validMagazine.getIssue());
		
		assertTrue(clone.title == "Title" && clone.genre == "Genre" && clone.description == "Description" 
				&& clone.yearOfRelease == "Year" && clone.checkedOut == false && clone.copies == 1
				&& clone.getAuthor() == "Author" && clone.getVolume() == 1 && clone.getIssue() == 1);
	}

	@Test
	final void testMagazine() {
		assertTrue(validMagazine.title == "Title" && validMagazine.genre == "Genre" && validMagazine.description == "Description" 
				&& validMagazine.yearOfRelease == "Year" && validMagazine.checkedOut == false && validMagazine.copies == 1
				&& validMagazine.getAuthor() == "Author" && validMagazine.getVolume() == 1 && validMagazine.getIssue() == 1);
	}

	@Test
	final void testGetAuthor() {
		String author = validMagazine.getAuthor();
		assertEquals(author, "Author");
	}

	@Test
	final void testGetVolume() {
		int volume = validMagazine.getVolume();
		assertEquals(volume, 1);
	}

	@Test
	final void testGetIssue() {
		int issue = validMagazine.getIssue();
		assertEquals(issue, 1);
	}

}
