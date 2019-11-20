import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * Book JUnit Test
 * @author henryvy
 *
 */
public class JUnitBookTest {

	Book validBook = new Book("Title", "Genre", "Description", "Year", false, 1,
            "Author", "ISBN", "Publisher");
	
	@Test
	public final void testCopy() {
		Book clone = new Book(validBook.getTitle(),validBook.getGenre(),validBook.getDescription(),validBook.getYearOfRelease()
				,validBook.isNewRelease(),validBook.getCopies(),validBook.getAuthor(),validBook.getISBN(), validBook.getPublisher());
		
		assertTrue(clone.title == "Title" && clone.genre == "Genre" && clone.description == "Description" 
				&& clone.yearOfRelease == "Year" && clone.checkedOut == false && clone.copies == 1
				&& clone.getAuthor() == "Author" && clone.getISBN() == "ISBN" && clone.getPublisher() == "Publisher");
	}

	@Test
	public final void testBook() {
		assertTrue(validBook.title == "Title" && validBook.genre == "Genre" && validBook.description == "Description" 
				&& validBook.yearOfRelease == "Year" && validBook.checkedOut == false && validBook.copies == 1
				&& validBook.getAuthor() == "Author" && validBook.getISBN() == "ISBN" && validBook.getPublisher() == "Publisher");
	}

	@Test
	public final void testGetAuthor() {
		String author = validBook.getAuthor();
		assertEquals(author, "Author");
	}

	@Test
	public final void testGetPublisher() {
		String publisher = validBook.getPublisher();
		assertEquals(publisher, "Publisher");
	}

	@Test
	public final void testGetISBN() {
		String ISBN = validBook.getISBN();
		assertEquals(ISBN, "ISBN");
	}

}
