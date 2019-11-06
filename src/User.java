import java.util.ArrayList;

/**
 * User.java - Abstract superclass for all users.
 *
 */
public abstract class User {
	public static int USER_COUNT = 0;
	protected String name;
	protected String dateOfBirth;
	protected String address;
	protected String email;
	protected String phoneNumber;
	protected String username;
	protected String password;
	protected int id;
	protected int accountId;
	protected int checkoutLimit;
	protected ArrayList<Fee> fines = new ArrayList<Fee>();
	protected ArrayList<Media> wishlist = new ArrayList<Media>();
	protected String accountType;
	protected ArrayList<String> notifications = new ArrayList<String>();
	protected boolean isClosed = false;
	protected boolean isFlagged = false;
	protected ArrayList<User> children = new ArrayList<User>();
	protected ArrayList<Media> checkedOutMedia = new ArrayList<Media>();
	protected ArrayList<Media> heldMedia = new ArrayList<Media>();
	
	/**
	 * User default
	 */
	public User() {
		this.name = this.dateOfBirth = this.address =
				this.email = this.phoneNumber = this.username =
				this.password = "";
		this.accountId = 0;
	}
	
	/**
	 * User constructor
	 * @param name
	 * @param dateOfBirth
	 * @param address
	 * @param email
	 * @param phoneNumber
	 * @param username
	 * @param password
	 * @param id
	 */
	public User(String name, String dateOfBirth, String address, String email, String phoneNumber, String username,
			String password, int id) {
		USER_COUNT++;
		this.setName(name);
		this.setDateOfBirth(dateOfBirth);
		this.setAddress(address);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
		this.setUsername(username);
		this.setPassword(password);
		this.setId(USER_COUNT);
		this.setAccountId(id);
		LibrarySystem.getInstance().users.add(this);
	}
	
	
	/**
	 * Closes a User's account
	 */
	public void closeAccount(){
		this.isClosed = true;
		this.fines = new ArrayList<Fee>();
	}
	
	/**
	 * allows user to currently view their checked out media
	 */
	public void viewCurrentlyCheckedOutMedia() {
		if(checkedOutMedia.isEmpty())
			System.out.println("Checked Out list is empty");
		else {
			System.out.println("Currently checked out media:");
			for(Media m: this.checkedOutMedia)
				System.out.println(m.title + " | Overdue: " + m.isOverdue());
		}
	}
	
	/**
	 * Allows user to view their hold list
	 */
	public void viewMediaOnHold() {
		if(heldMedia.isEmpty())
			System.out.println("Hold list is empty");
		else {
			System.out.println("Currently held media:");
			for(Media m: this.heldMedia)
				System.out.println(m);	
		}
	}
	
	/**
	 * Prints out the current user account id
	 */
	public void checkAccountNumber(){
		System.out.println(this.accountId);
	}
	
	/**
	 * Prints out the wishlist in the array
	 */
	public void checkWishlist() {
		if(wishlist.isEmpty()) {
			System.out.println("Wishlist is empty");
		}
		else {
			System.out.println("Your wishlist");
			for (Media m : wishlist)
				System.out.println(m);	
		}
	}
	
	/**
	 * Returns account type
	 * @return
	 */
	public String returnAccountType(){
		return accountType;
	}
	
	//Checks out the media returned by the Checkout method.
	public void checkoutMedia(Media m)
	{
		if(this.fineTotal() < 100.00) {
			if(this.checkedOutMedia.size() <= this.checkoutLimit) {
				Media checkoutAttempt = m.checkout();
				if(checkoutAttempt != null) { //The checkout method returns a media if it's successful.  If it is, add it to this list.
					this.checkedOutMedia.add(checkoutAttempt);
					if(checkoutAttempt.getWaitlist().contains(this)) //If this user was on the waitlist, remove it.
						checkoutAttempt.getWaitlist().remove(this);
				}
			} else {
				System.out.println("You have hit your maximum checkout limit!  Please return something first.");
			}
		} else {	
				System.out.println("Your fine total is above the maximum ($100)!  Please pay off your balance first.");
		}
	}
	
	/**
	 * Adds a requested media to the list
	 * @param m
	 */
	public void requestMedia(Media m){
		this.wishlist.add(m);
	}
	
	/**
	 * Returns a media
	 * @param m
	 */
	public void returnMedia(Media m){
		if(this.checkedOutMedia.contains(m)) {
			m.returnMedia();
			this.checkedOutMedia.remove(m);
		} else {
			System.out.println("You cannot return an item you do not have checked out!");
		}
	}
	
	/**
	 * Renews a media
	 * @param m
	 */
	public void renewMedia(Media m){
		if(this.checkedOutMedia.contains(m))
			m.renew();
		else
			System.out.println("You cannot renew a media you do not have checked out!");
	}

	// Search for username???
	public void search(String s){
		if (this.username.equals(s))
			this.viewUser();
		else
			System.out.println("No user found");
	}
	
	/**
	 * Prints out User information
	 */
	public void viewUser() {
		System.out.println("Name: " + this.name +
		"\nEmail: " + this.email +
		"\nUsername: " + this.username +
		"\nPassword: " + this.password +
		"\nID: " + this.id +
		"\nPhone Number: " + this.phoneNumber +
		"\nAddress: " + this.address +
		"\nDate of Birth: " + this.dateOfBirth);
	}
	
	/**
	 * Prints out the fines according to the media
	 * @return
	 */
	public void checkFines() {
		if(fines.isEmpty()) {
			System.out.println("You have no fines");
		}
		else {
			System.out.println("You currently owe $" + this.fineTotal() + ".  List of fines:");
			for (Fee f : fines) {
				System.out.println(f); // toString method already in Fee class
			}
		}
	}
	
	private double fineTotal() {
		double total = 0;
		for(Fee f:this.fines)
			total += f.getTotal();
		return total;
	}	
	
	/**
	 * Gets the updated total to display to the user
	 */
	public void update() {
		for(Media m:this.checkedOutMedia) {
			if(m.isOverdue()) {
				boolean fineExists = false;
				for(Fee f:this.fines) {
					if(f.getMedia().getId() == m.id)
						fineExists = true;
				}
				if(!fineExists)
					this.fines.add(new Fee(m));
			}
		}
		updateFees();
	}
	
	/**
	 * System method for fees
	 */
	private void updateFees() {
		if (fines.isEmpty())
			System.out.println("There are currently no fines");
		else {
			System.out.println("Increasing each fine in the list");
			for (Fee f : fines)
				f.dailyFineIncrease();
		}
	}
	
	/**
	 * pays individual fines on the fines list
	 * @param f
	 * @param amt
	 */
	public void payFine(Fee f, double amt) {
		f.pay(amt);
		if(f.getTotal() == 0)
			this.fines.remove(f);
	}
	
	/**
	 * Adds a String of notifications to the list
	 * @param note
	 */
	public void notify(String note) {
		this.notifications.add(note);
	}
	
	/**
	 * Flagging Users
	 */
	public void flagUser(){
		isFlagged = true;
	}
	
	/**
	 * Getters and Setters
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id > 0)
			this.id = id;
	}
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		if (id > 0)
			this.accountId = accountId;
	}

	public void setFines(ArrayList<Fee> fines) {
		this.fines = fines;
	}

	public void setWishlist(ArrayList<Media> wishlist) {
		this.wishlist = wishlist;
	}

	public void setNotifications(ArrayList<String> notifications) {
		this.notifications = notifications;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

	public void setChildren(ArrayList<User> children) {
		this.children = children;
	}

	public void setCheckedOutMedia(ArrayList<Media> checkedOutMedia) {
		this.checkedOutMedia = checkedOutMedia;
	}

	public void setHeldMedia(ArrayList<Media> heldMedia) {
		this.heldMedia = heldMedia;
	}

	public ArrayList<Fee> getFines() {
		return this.fines;
	}

	public ArrayList<Media> getWishlist() {
		return wishlist;
	}

	public ArrayList<String> getNotifications() {
		return notifications;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public ArrayList<User> getChildren() {
		return children;
	}

	public ArrayList<Media> getCheckedOutMedia() {
		return checkedOutMedia;
	}

	public ArrayList<Media> getHeldMedia() {
		return heldMedia;
	}
	public String getAccountType() {
		return accountType;
	}
	
	public String toString() {
		return "Account Name: " + this.name + "\n" +
				"Account ID: " + this.accountId + "\n" +
				"Account Type: " + this.accountType + "\n" +
				"Date of Birth: " + this.dateOfBirth + "\n" + 
				"Address: " + this.address + "\n" +
				"Email: " + this.email + "\n" +
				"Phone Number: " + this.phoneNumber;
	}
}
