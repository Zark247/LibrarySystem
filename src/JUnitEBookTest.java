import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * EBook JUnit Test
 * @author henryvy
 *
 */
class JUnitEBookTest {

	EBook validEBook = new EBook("Title", "Genre", "Description", "Year", false, 1,
            "Author");
	
	@Test
	final void testCopy() {
		EBook clone = new EBook(validEBook.getTitle(),validEBook.getGenre(),validEBook.getDescription(),validEBook.getYearOfRelease()
				,validEBook.isNewRelease(),validEBook.getCopies(),validEBook.getAuthor());
		
		assertTrue(clone.title == "Title" && clone.genre == "Genre" && clone.description == "Description" 
				&& clone.yearOfRelease == "Year" && clone.checkedOut == false && clone.copies == 1
				&& clone.getAuthor() == "Author");
	}

	@Test
	final void testEBook() {
		assertTrue(validEBook.title == "Title" && validEBook.genre == "Genre" && validEBook.description == "Description" 
				&& validEBook.yearOfRelease == "Year" && validEBook.checkedOut == false && validEBook.copies == 1					
				&& validEBook.getAuthor() == "Author");
	}

	@Test
	final void testGetAuthor() {
		String author = validEBook.getAuthor();
		assertEquals(author, "Author");
	}

}
