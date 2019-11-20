import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Media JUnit Test
 * @author henryvy
 *
 */
public class JUnitMediaTest {
	
	/**
	 * Creating temporary tests
	 */
	Media validDVD = new DVD("Title", "Genre", "Description", "Year", false, 1,
            "Director");
	
	User validUser = new AverageUser("John Brown", "11/11/2000", "21 Address Way", "email@email.com", 
			"123-456-7890", "username", "password", 0);
	
	/**
	 * Testing Media
	 */
	@Test
	public final void testMedia() {
		assertTrue(validDVD.title == "Title" && validDVD.genre == "Genre" && validDVD.description == "Description" 
				&& validDVD.yearOfRelease == "Year" && validDVD.checkedOut == false && validDVD.copies == 1);
	}
	
	/**
	 * Testing Checkout
	 */
	@Test
	public final void testCheckout() {
		validDVD.checkedOut = true;
		assertTrue(validDVD.isCheckedOut());
	}

	/**
	 * Testing Renew
	 */
	@Test
	public final void testRenew() {
		assertEquals(validDVD.getRenewCount(), 0);
	}

	/**
	 * Testing returnMedia
	 */
	@Test
	public final void testReturnMedia() {
		validDVD.setCheckedOut(true);
		validDVD.returnMedia();
		assertEquals(validDVD.checkedOut, false);
	}

	/**
	 * Testing isCheckedOut
	 */
	@Test
	public final void testIsCheckedOut() {
		validDVD.setCheckedOut(true);
		assertEquals(validDVD.checkedOut, true);
	}

	/**
	 * Testing returnId
	 */
	@Test
	public final void testReturnId() {
		validDVD.id = 1;
		assertEquals(validDVD.returnId(), 1);
	}

	/**
	 * Testing hasCopies
	 */
	@Test
	public final void testHasCopies() {
		assertEquals(validDVD.hasCopies(), false);
	}

	/**
	 * Testing returnTimesRenewed
	 */
	@Test
	public final void testReturnTimesRenewed() {
		assertEquals(validDVD.returnTimesRenewed(), 0);
	}

	/**
	 * Testing toString
	 */
	@Test
	public final void testToString() {
		String expected = "Title: " + validDVD.title + "\n Genre: " + validDVD.genre + "\n Year of Release: " + validDVD.yearOfRelease + 
		"\n Description: " + validDVD.description + "\n Copies: " + validDVD.copies + "\n Rating: " + validDVD.rating + " stars";
		assertEquals(validDVD.toString(), expected);
	}

	/**
	 * Testing addRating
	 */
	@Test
	public final void testAddRating() {
		validDVD.addRating(3, "Okay DVD");
		assertEquals(validDVD.getRatingList().size(), 1);
	}

	/**
	 * Testing displayRating
	 */
	@Test
	public final void testDisplayRating() {
		String expected = validDVD.title + " has no ratings.";
		assertEquals("Title has no ratings.", expected);
	}

	/**
	 * Testing setCheckedOut
	 */
	@Test
	public final void testSetCheckedOut() {
		validDVD.setCheckedOut(true);
		assertEquals(validDVD.checkedOut, true);
	}

	/**
	 * Testing setRenewCount
	 */
	@Test
	public final void testSetRenewCount() {
		validDVD.setRenewCount(2);
		assertEquals(validDVD.getRenewCount(), 2);
	}

	/**
	 * Testing getId
	 */
	@Test
	public final void testGetId() {
		validDVD.id = 1;
		assertEquals(validDVD.getId(), 1);
	}

	/**
	 * Testing getTitle
	 */
	@Test
	public final void testGetTitle() {
		assertEquals(validDVD.getTitle(), "Title");
	}

	/**
	 * Testing getGenre
	 */
	@Test
	public final void testGetGenre() {
		assertEquals(validDVD.getGenre(), "Genre");
	}

	/**
	 * Testing getDescription
	 */
	@Test
	public final void testGetDescription() {
		assertEquals(validDVD.getDescription(), "Description");
	}

	/**
	 * Testing getYearOfRelease
	 */
	@Test
	public final void testGetYearOfRelease() {
		assertEquals(validDVD.getYearOfRelease(), "Year");
	}

	/**
	 * Testing getRating
	 */
	@Test
	public final void testGetRating() {
		validDVD.rating = 3;
		assertTrue(validDVD.getRating() == 3);
	}

	/**
	 * Testing getComment
	 */
	@Test
	public final void testGetComment() {
		validDVD.comment = "Nice book";
		assertEquals(validDVD.getComment(), "Nice book");
	}

	/**
	 * Testing isNewRelease
	 */
	@Test
	public final void testIsNewRelease() {
		validDVD.newRelease = true;
		assertEquals(validDVD.isNewRelease(), true);
	}

	/**
	 * Testing getWaitlist
	 */
	@Test
	public final void testGetWaitlist() {
		validDVD.getWaitlist().add(validUser);
		assertEquals(validDVD.getWaitlist().size(), 1);
	}

	/**
	 * Testing getRenewCount
	 */
	@Test
	public final void testGetRenewCount() {
		int renewCount = validDVD.getRenewCount();
		assertEquals(renewCount, 0);
	}

	/**
	 * Testing getCopies
	 */
	@Test
	public final void testGetCopies() {
		int copies = validDVD.getCopies();
		assertEquals(copies, 1);
	}

	/**
	 * Testing getCheckoutLength
	 */
	@Test
	public final void testGetCheckoutLength() {
		validDVD.checkoutLength = 7;
		assertEquals(validDVD.getCheckoutLength(), 7);
	}

	/**
	 * Testing copyCountUp
	 */
	@Test
	public final void testCopyCountUp() {
		validDVD.copyCountUp();
	}

	/**
	 * Testing getRatingList
	 */
	@Test
	public final void testGetRatingList() {
		assertTrue(validDVD.getRatingList() != null);
	}

	/**
	 * Testing returnCopies
	 */
	@Test
	public final void testReturnCopies() {
		Media tempDVD = new DVD("Title123", "Genre", "Description", "Year", false, 1,
	            "Director");
		assertTrue(!validDVD.equals(tempDVD));
	}

	/**
	 * Testing getReviewCount
	 */
	@Test
	public final void testGetReviewCount() {
		assertEquals(validDVD.getRenewCount(), 0);
	}

	/**
	 * Testing setRating
	 */
	@Test
	public final void testSetRating() {
		validDVD.setRating(5);
		assertTrue(validDVD.rating == 5);
	}

	/**
	 * Testing setReviewCount
	 */
	@Test
	public final void testSetReviewCount() {
		validDVD.setRenewCount(2);
		assertEquals(validDVD.getRenewCount(), 2);
	}
}
