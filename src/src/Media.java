package src;

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
	protected int checkoutLength; //Length of standard checkout in DAYS.
	
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
		LibrarySystem.getInstance().inventory.add(this);
	}
	
	public void checkout() {
		//TODO: Add hold functionality.
		if(!this.checkedOut) {
			this.checkedOut = true;
			System.out.println("You have successfully checked out " + this.title + " on " + LibrarySystem.getInstance().returnSystime().getTime());
			setDueDates();
			System.out.println("This item is due on: " + this.lastDueDate.toString());
		} else {
			System.out.println("This item is already checked out!  Checkout failed.");
		}
			
	}
	
	public void renew() {
		if(this.checkedOut && this.renewCount < 3) {
			renewCount++;
			setDueDates();
			System.out.println("Success! New due date is: " + this.lastDueDate.toString());
			System.out.println("This has been renewed " + this.renewCount + " times.");
		} else {
			if(renewCount >= 3)
				System.out.println("You cannot renew this item: it has already been renewed 3 times.");
			else
				System.out.println("You can't renew an item you haven't checked out!");
		}
	}
	
	/**
	 * Resets renew counts and sets checked out to false.  Notifies the next user on the waitlist that the item is now avaliable.
	 * TODO: May be modified later to call a user method that sets a timer to claim the hold.
	 */
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
	
	/**
	 * Compares the current date to the last due date.  If it's after, the item is overdue.
	 * @return true is the due date has passed.
	 */
	public boolean isOverdue() {
		return LibrarySystem.getInstance().returnSystime().getTime().after(this.lastDueDate);
	}
	
	/**
	 * This method sets the due dates of the media item.  It does so by storing the current date in the lastBorrowDate member, then it adds an 
	 * amount of days equal to the checkoutLength to the calendar.  It stores that value into the lastDueDate member, then restores the calendar.
	 */
	private void setDueDates() {
		Calendar temp = (Calendar) LibrarySystem.getInstance().returnSystime().clone();
		this.lastBorrowDate = temp.getTime();
		LibrarySystem.getInstance().returnSystime().add(Calendar.DAY_OF_YEAR, checkoutLength);
		this.lastDueDate = LibrarySystem.getInstance().returnSystime().getTime();
		LibrarySystem.getInstance().updateSystime(temp);
	}
	
	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}

	public void setLastBorrowDate(Date lastBorrowDate) {
		this.lastBorrowDate = lastBorrowDate;
	}

	public void setLastDueDate(Date lastDueDate) {
		this.lastDueDate = lastDueDate;
	}

	public void setWaitlist(ArrayList<User> waitlist) {
		this.waitlist = waitlist;
	}

	public void setRenewCount(int renewCount) {
		this.renewCount = renewCount;
	}
	
	public int getId() {
		return id;
	}
}
