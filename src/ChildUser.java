
/**
 * ChildUser.java - A type of user that is a child (should be linked to a parent)
 * @author Team Proxi
 */
public class ChildUser extends User{
	User parent;
	boolean isLinked = false;
	
	/**
	 * Default constructor, calls superclass User's default constructor
	 */
	public ChildUser() {
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
	 * @param parent The child's parent that is linked to the the account
	 * @param isLinked A boolean value if the child account is linked to a parent account
	 */
	public ChildUser(String name, String dateOfBirth, String address,
			String email, String phoneNumber, String username,
			String password, int id)
	{
		super(name, dateOfBirth, address, email, phoneNumber, username, password, id);
		this.checkoutLimit = 3;
    this.accountType = "Child";
		//this.parent = parent;
		//this.isLinked = isLinked;
	}
	
	/**
	 * Links a parent account to the child's account if it has not been done already
	 * @param parent The parent to be linked to the child's account
	 */
	public void linkParentAccount(User parent) {
		this.parent = parent;
		this.isLinked = true;
	}
	
	/**
	 * Changes the ChildUser to an AverageUser by creating a new AverageUser
	 */
	public void changeToAverageUser() {
		User user = new AverageUser(this.name, this.dateOfBirth, this.address, this.email,
				this.phoneNumber, this.username, this.password, this.id);
		user_count--; // Decrement user count because creating a new user increments it,and we are just switching over user types
	}
	
	public void viewUser() {
		super.viewUser();
		if (parent != null)
			System.out.print("Parent: " +parent.getName());
		else
			System.out.print("Parent: No parent");
		System.out.print("\nisLinked: " +this.isLinked +"\n");
				
	}
}
