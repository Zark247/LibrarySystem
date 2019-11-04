
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
	public ArrayList<Fee> fees;

	public static LibrarySystem librarySystem;
	private Calendar systime;
	private boolean midnightUpdated;
	
	private LibrarySystem() {
		inventory = new ArrayList<Media>();
		comingSoon = new ArrayList<Media>();
		users = new ArrayList<User>();
		fees = new ArrayList<Fee>();
		systime = Calendar.getInstance();
		midnightUpdated = false;
		systime.setLenient(true);
	}
	/**
	 * Returns the instance of the library system - since there should only ever be one.
	 * @return the current LibrarySystem.
	 */
	public static LibrarySystem getInstance() {
		if(librarySystem == null) {
			librarySystem = new LibrarySystem();
		}
		return librarySystem;
	}
	
	/**

	 * Creates a new  using the passed in information.  Modified to create different types of users based on type int
	 * @param type - Indicates type of account. 1 = child, 2 = average, 3 = teacher, 4 = librarian.
	 */
	public User createAccount(int type,String name, String dateOfBirth, String address,
			String email, String phoneNumber, String username,
			String password, int id) {
		User temp = null;
		if(!checkDuplicateAccount(email,phoneNumber)) {
			if(type == 1)
				temp = new ChildUser(name, dateOfBirth, address,
						email, phoneNumber, username,
						password, id);
			if(type == 2)
				temp = new AverageUser(name, dateOfBirth, address,
						email, phoneNumber, username,
						password, id);
			if(type == 3)
				temp = new TeacherUser(name, dateOfBirth, address,
						email, phoneNumber, username,
						password, id);
			if(type == 4)
				temp = new LibrarianUser(name, dateOfBirth, address,
						email, phoneNumber, username,
						password, id);
		} else {
			System.out.println("That email or phone number is already in use.  Try another.");
		}
		return temp;
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
	
	/**
	 * Logins in to a user account if username and password is entered correctly,
	 * if successfully logged in, a welcome and list of notifications will display.
	 * @param username
	 * @param password
	 */
	public void login(String username,String password) {
		// Looks for the right username
		for (User user : users){
			if(user.getUsername().equals(username)) {
				if(user.getPassword().equals(password)) {
					InputHandler.changeUser(user);
					System.out.println("Welcome, " + user.getName() +
							"\nYou have " + user.getNotifications().size() + " notification(s):");
					for (String notification : user.getNotifications())
						System.out.println(notification);
				}
				else
					System.out.println("Incorrect password");
				
			}
		}
	}
	
	public Calendar returnSystime() {
		return this.systime;
	}
	
	/**
	 * Change the current systime to the passed in systime, and calls midnight update if it is currently midnight.
	 * Since the time 00:00 is likely to occur multiple times, the variable midnightUpdated is used to create a threshold.
	 * The method midnight update will only run if midnight update is false, after which it will be made true.
	 * Then, the midnightUpdated member will be made false once midnight has passed, preventing multiple updates.
	 * @param SysTime
	 */
	public void updateSystime(Calendar SysTime) {
		this.systime = SysTime;
		if(systime.get(Calendar.HOUR_OF_DAY) == 0 && systime.get(Calendar.MINUTE) == 0 && !midnightUpdated)
			midnightUpdate();
		else
			midnightUpdated = false;
	}
	
	/**
	 * Calls the update methods for various classes once systime hits midnight.
	 */
	private void midnightUpdate() {
		for(User u:LibrarySystem.getInstance().users)
			u.update();
		midnightUpdated = true;
	}
	
	/**
	 * Searches the library's inventory for media based on the title and prints out all matching media
	 * @param searchFor The title of the media to search for
	 */
	public void search(String searchFor) {
		LibrarySearch libSearch = new LibrarySearch();
		ArrayList<Media> foundMedia = new ArrayList<Media>();
		
		foundMedia = libSearch.search(searchFor);
		 
		for (Media media : foundMedia) {
			System.out.println(media.getTitle());
		}
	}
	
	public ArrayList<Media> inventoryNoCopies() {
		ArrayList<Media> nocopies = new ArrayList<Media>();
		for(Media m:this.inventory) {
			boolean copy = false;
			for(Media n:nocopies)
				if(m.title.contentEquals(n.title))
					copy = true;
			if(!copy)
				nocopies.add(m);
		}
		return nocopies;
	}
}
