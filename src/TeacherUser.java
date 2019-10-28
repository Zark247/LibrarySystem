package src;

/**
 * A type of user that is the average user (what most accounts are)
 * @author Cameron Brandenburg
 */
public class TeacherUser extends User{
	
	boolean isApproved = false;
	
	/**
	 * Default constructor, calls superclass User's default constructor
	 */
	public TeacherUser() {
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
	 * @param isApproved A boolean value for if a Librarian has approved the Teacher account
	 */
	public TeacherUser(String name, String dateOfBirth, String address,
			String email, String phoneNumber, String username,
			String password, int id)
	{
		super(name, dateOfBirth, address, email, phoneNumber, username, password, id);
		this.checkoutLimit = 10; //This is increased to 50 once the account is approved.
	}
	
	/**
	 * Mutator used to set the boolean isApproved
	 * Used for Librarians to approved Teacher accounts
	 * @param isApproved
	 */
	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
		if(isApproved == true)
			this.checkoutLimit = 50;
		else
			this.checkoutLimit = 10;
	}
	
	public void viewUser() {
		super.viewUser();
		System.out.print("isApproved: " +this.isApproved + "\n");
	}
}
