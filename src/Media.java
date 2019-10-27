import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/**
 * Media.java - Abstract superclass for all media items.
 * @author Kevin Prince
 *
 */
public abstract class Media {
	public static int MEDIA_COUNT = 0;
	
	protected String title;
	protected String genre;
	protected String description;
	protected String yearOfRelease;
	protected int rating;
	protected boolean newRelease;
	protected boolean checkedOut;
	protected int id;
	protected Date lastBorrowDate;
	protected Date lastDueDate;
	protected ArrayList<User> waitlist;
	protected int renewCount;
	protected int copies;
	protected int checkoutLength;
	
	public Media(String title,String genre,String description,String year,boolean newRelease,int copies) {
		MEDIA_COUNT++;
		this.title = title;
		this.genre = genre;
		this.description = description;
		this.yearOfRelease = year;
		this.newRelease = newRelease;
		this.copies = copies;
		this.renewCount = 0;
		this.checkedOut = false;
		this.id = MEDIA_COUNT;
		this.waitlist = new ArrayList<User>();
	}
	
	public void checkout() {
		//TODO: Add hold functionality.
		if(!this.checkedOut) {
			this.checkedOut = true;
			System.out.println("You have successfully checked out " + this.title + " on " + LibrarySystem.getInstance().returnSystime());
			setDueDates();
		} else {
			System.out.println("This item is already checked out!  Checkout failed.");
		}
			
	}
	
	public void renew() {
		if(this.checkedOut && this.renewCount < 3) {
			renewCount++;
			setDueDates();
		} else {
			if(renewCount >= 3)
				System.out.println("You cannot renew this item: it has already been renewed 3 times.");
			else
				System.out.println("You can't renew an item you haven't checked out!");
		}
	}
	
	public void returnMedia() {
		checkedOut = false;
		renewCount = 0;
		if(!waitlist.isEmpty()) {
			waitlist.get(0).notify("An item on your wishlist is available: " + this.title);
		}
	}
	
	public void placeHold(User user) {
		waitlist.add(user);
	}
	
	public boolean isCheckedOut() {
		return this.checkedOut;
	}
	
	public String returnCheckoutTimestamp() {
		return this.lastBorrowDate.toString();
	}
	
	public int returnId() {
		return this.id;
	}
	
	public boolean hasCopies() {
		return this.copies > 1;
	}
	
	public int returnTimesRenewed() {
		return this.renewCount;
	}
	
	public String toString() {
		return "Placeholder";
	}
	
	public boolean isOverdue() {
		return LibrarySystem.getInstance().returnSystime().getTime().after(this.lastDueDate);
	}
	
	/**
	 * This method sets the due dates of the media item.  It does so by storing the current date in the lastBorrowDate member, then it adds an 
	 * amount of days equal to the checkoutLength to the calendar.  It stores that value into the lastDueDate member, then restores the calendar.
	 */
	private void setDueDates() {
		Calendar temp = LibrarySystem.getInstance().returnSystime();
		this.lastBorrowDate = temp.getTime();
		LibrarySystem.getInstance().returnSystime().add(Calendar.DAY_OF_YEAR, checkoutLength);
		this.lastDueDate = LibrarySystem.getInstance().returnSystime().getTime();
		LibrarySystem.getInstance().updateSystime(temp);
	}
	
	
}
