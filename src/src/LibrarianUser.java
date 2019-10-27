package src;

/**
 * A type of user that is the average user (what most accounts are)
 * @author Cameron Brandenburg
 */
public class LibrarianUser extends User{
	
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
	}
	
	/**
	 * Allows a Librarian to approve Teacher accounts
	 * @param teacher The TeacherUser account to be approved
	 */
	public void approveTeacherAccount(TeacherUser teacher) {
		teacher.setIsApproved(true);
	}
	
	//TODO add functionality for removing media
	void retireMedia() {
		
	}
	
	//TODO add functionality for adding media
	public void addMedia() {
		
	}
	
	//TODO add functionality for flagging specific users
	public void flagUser() {
		
	}
	
	public void viewUser() {
		super.viewUser();
	}
}
