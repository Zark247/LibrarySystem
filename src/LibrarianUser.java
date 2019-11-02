/**
 * A type of user that is the average user (what most accounts are)
 * @author Cameron Brandenburg
 */
public class LibrarianUser extends User{
	
	/**
	 * An instance of LibrarySystem used to access its attributes
	 */
	LibrarySystem lib = LibrarySystem.getInstance();
	
	/**
	 * Default constructor, calls superclass User's default constructor
	 */
	public LibrarianUser() {
		super();
	}
	
	/**
	 * Parameterized constructor, calls superclass User's parameterized constructor
	 * @param name The user's name
	 * @param dateOfBirth The user's DOB
	 * @param address The user's address
	 * @param email The user's email
	 * @param phoneNumber The user's phone number
	 * @param username The user's username
	 * @param password The user's password
	 * @param id The user's unique ID number
	 */
	public LibrarianUser(String name, String dateOfBirth, String address,
			String email, String phoneNumber, String username,
			String password, int id)
	{
		super(name, dateOfBirth, address, email, phoneNumber, username, password, id);
		this.checkoutLimit = 50;
    this.accountType = "Librarian";
	}
	
	/**
	 * Allows a Librarian to approve Teacher accounts
	 * @param teacher The TeacherUser account to be approved
	 */
	public void approveTeacherAccount(TeacherUser teacher) {
		teacher.setIsApproved(true);
	}
	
	/**
	 * Adds a piece of media to the LibrarySystem's inventory
	 * @param media The media to be added
	 */
	public void addMedia(Media media) {
		lib.inventory.add(media);
	}
	
	/**
	 * Removes a piece of media from the LibrarySystem's inventory
	 * @param media The media to be removed
	 */
	void retireMedia(Media media) {
		lib.inventory.remove(media);
	}
	
	//TODO add functionality for flagging specific users
	public void flagUser() {
		super.flagUser();
	}
	
	//TODO add functionality for viewing users
	public void viewUser() {
		super.viewUser();
	}
}
