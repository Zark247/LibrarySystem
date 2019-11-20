import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * DVD JUnit Test
 * @author henryvy
 *
 */
public class JUnitDVDTest {

	DVD validDVD = new DVD("Title", "Genre", "Description", "Year", false, 1,
            "Director");
	
	@Test
	public final void testCopy() {
		DVD clone = new DVD(validDVD.getTitle(),validDVD.getGenre(),validDVD.getDescription(),validDVD.getYearOfRelease()
				,validDVD.isNewRelease(),validDVD.getCopies(),validDVD.getDirector());
		
		assertTrue(clone.title == "Title" && clone.genre == "Genre" && clone.description == "Description" 
				&& clone.yearOfRelease == "Year" && clone.checkedOut == false && clone.copies == 1
				&& clone.getDirector() == "Director");
	}

	@Test
	public final void testDVD() {
		assertTrue(validDVD.title == "Title" && validDVD.genre == "Genre" && validDVD.description == "Description" 
				&& validDVD.yearOfRelease == "Year" && validDVD.checkedOut == false && validDVD.copies == 1					
				&& validDVD.getDirector() == "Director");
	}

	@Test
	public final void testGetDirector() {
		String director = validDVD.getDirector();
		assertEquals(director, "Director");
	}

}
