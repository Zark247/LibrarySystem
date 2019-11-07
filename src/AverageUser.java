
/**
 * AverageUser.java - A type of user that is the average user (what most accounts are)
 * @author Team Proxi
 */
public class AverageUser extends User{
	
	/**
	 * Default constructor, calls superclass User's default constructor
	 */
	public AverageUser() {
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
	public AverageUser(String name, String dateOfBirth, String address,
			String email, String phoneNumber, String username,
			String password, int id)
	{
		super(name, dateOfBirth, address, email, phoneNumber, username, password, id);
		this.checkoutLimit = 10;
		this.accountType = "Average";
	}
	
	public void viewUser() {
		super.viewUser();
	}
}