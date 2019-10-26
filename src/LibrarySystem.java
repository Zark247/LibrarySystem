import java.util.ArrayList;
import java.util.Calendar;

/**
 * LibrarySystem.java - Class for the library system information.
 * @author Kevin Prince
 *
 */
public class LibrarySystem {
	public ArrayList<Media> inventory;
	public ArrayList<Media> comingSoon;
	public ArrayList<User> users;
	public static LibrarySystem librarySystem;
	private Calendar systime;
	private boolean midnightUpdated;
	
	private LibrarySystem() {
		inventory = new ArrayList<Media>();
		comingSoon = new ArrayList<Media>();
		users = new ArrayList<User>();
		systime = Calendar.getInstance();
		midnightUpdated = false;
	}
	
	public static LibrarySystem getInstance() {
		if(librarySystem == null) {
			librarySystem = new LibrarySystem();
		}
		return librarySystem;
	}
	
	public void createAccount(String name, String dateOfBirth, String address,
			String email, String phoneNumber, String username,
			String password) {
		if(!checkDuplicateAccount(email,phoneNumber)) {
			//TODO: Uncomment below lines once user subtypes have been created.
			//users.add(new GeneralUser(name, dateOfBirth, address,
			//email, phoneNumber, username,
			//password));
		} else
			System.out.println("That email or phone number is already in use.  Try another.");
	}
	
	/**
	 * Checks for duplicate accounts within the system.
	 * @param email - an email for account creation
	 * @param phoneNumber - a phone number for account creation.
	 * @return true if the account is a duplicate
	 */
	private boolean checkDuplicateAccount(String email, String phoneNumber) {
		for(User user : users) {
			if(user.getEmail().equals(email) || user.getPhoneNumber().equals(phoneNumber))
				return true;
		}
		return false;
	}
	
	public void login(String username,String password) {
		//TODO: Create a login function once InputHandler is complete.
	}
	
	public Calendar returnSystime() {
		return this.systime;
	}
	
	public void updateSystime(Calendar SysTime) {
		this.systime = SysTime;
		if(systime.get(Calendar.HOUR_OF_DAY) == 0 && systime.get(Calendar.MINUTE) == 0)
			midnightUpdate();
		else
			midnightUpdated = false;
	}
	
	private void midnightUpdate() {
		if(!midnightUpdated) {
			//TODO: Call fee/media update methods once implimented.
		} else 
			midnightUpdated = true;
	}
	
	public void search(String searchterm) {
		//TODO: Impliment call to the search class.
	}
}
