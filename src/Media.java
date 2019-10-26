import java.util.ArrayList;
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
	protected String lastBorrowDate;
	protected String lastDueDate;
	protected ArrayList<User> waitlist;
	protected int renewCount;
	protected int copies;
	
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
	
	/*public boolean checkout() {
	*	if(!this.checkedOut) {
	*		this.checkedOut = true;
	*		
	*		
	*	}
	}*/
}
