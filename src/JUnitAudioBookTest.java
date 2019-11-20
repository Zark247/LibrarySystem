import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * AudioBook JUnit Test
 * @author henryvy
 *
 */
public class JUnitAudioBookTest {

	AudioBook validAudioBook = new AudioBook("Title", "Genre", "Description", "Year", false, 1,
	            "Author", "Narrator");

	
	@Test
	public final void testCopy() {
		AudioBook clone = new AudioBook(validAudioBook.getTitle(),validAudioBook.getGenre(),validAudioBook.getDescription(),validAudioBook.getYearOfRelease()
				,validAudioBook.isNewRelease(),validAudioBook.getCopies(),validAudioBook.getAuthor(),validAudioBook.getNarrator());
		
		assertTrue(clone.title == "Title" && clone.genre == "Genre" && clone.description == "Description" 
				&& clone.yearOfRelease == "Year" && clone.checkedOut == false && clone.copies == 1
				&& clone.getAuthor() == "Author" && clone.getNarrator() == "Narrator");
	}
	
	@Test
	public final void testAudioBook() {
		assertTrue(validAudioBook.title == "Title" && validAudioBook.genre == "Genre" && validAudioBook.description == "Description" 
				&& validAudioBook.yearOfRelease == "Year" && validAudioBook.checkedOut == false && validAudioBook.copies == 1					
				&& validAudioBook.getAuthor() == "Author" && validAudioBook.getNarrator() == "Narrator");
	}

	@Test
	public final void testGetAuthor() {
		String author = validAudioBook.getAuthor();
		assertEquals(author, "Author");
	}

	@Test
	public final void testGetNarrator() {
		String narrator = validAudioBook.getNarrator();
		assertEquals(narrator, "Narrator");
	}

}
