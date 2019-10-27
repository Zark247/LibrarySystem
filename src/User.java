package src;

import java.util.ArrayList;

public abstract class User
{
	public static int USER_COUNT = 0;
	protected String name;
	protected String dateOfBirth;
	protected String Address;
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
	protected boolean isClosed;
	protected ArrayList<User> children = new ArrayList<User>();
	protected ArrayList<Media> checkedOutMedia = new ArrayList<Media>();
	protected ArrayList<Media> heldMedia = new ArrayList<Media>();
	
	public User()
	{
		this.name = this.dateOfBirth = this.Address =
				this.email = this.phoneNumber = this.username =
				this.password = "";
		this.accountId = 0;
	}
	
	public User(String name, String dateOfBirth, String address,
			String email, String phoneNumber, String username,
			String password, int id)
	{
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
		
	}
	
	
	public void closeAccount()
	{
		this.isClosed = true;
	}
	
	
	public void viewCurrentlyCheckedOutMedia()
	{
		System.out.println("Currently checked out media:");
		for(Media m: this.checkedOutMedia)
			System.out.println(m);
	}
	
	public void viewMediaOnHold()
	{
		System.out.println("Currently held media:");
		for(Media m: this.heldMedia)
			System.out.println(m);
	}
	
	/**
	 * Prints out the fines in the array
	 * @return
	 */
	public double checkFines()
	{
		System.out.println("Your list of fines");
		for (Fee f : fines)
			System.out.println("$" + f);

		//TODO: add return statement
	}
	
	public void checkAccountNumber()
	{
		System.out.println(this.accountId);
	}
	
	/**
	 * Prints out the wishlist in the array
	 */
	public void checkWishlist()
	{
		System.out.println("Your wishlist");
		for (Media m : wishlist)
			System.out.println(m);	
	}
	
	/**
	 * Returns account type
	 * @return
	 */
	public String returnAccountType()
	{
		return accountType;
	}
	
	public void checkoutMedia(Media m)
	{
		m.checkout();
	}
	
	public void requestMedia(Media m)
	{
		this.wishlist.add(m);
	}
	
	public void returnMedia(Media m)
	{
		m.returnMedia();
	}
	
	public void renewMedia(Media m)
	{
		m.renew();
	}
	
	public void search(String s)
	{
		
	}
	
	public void putOnHold(Media m)
	{
		m.placeHold(this);
	}
	
	public void linkChildAccount()
	{
		//TODO: Add child and parent functionality
	}
	
	/**
	 * Views user info
	 * @param u
	 */
	public void viewUser(User u)
	{
		System.out.println("Name: " + this.getName() +
				"\nEmail: " + this.getEmail() +
				"\nUsername: " + this.getUsername() +
				"\nPassword: " + this.getPassword() +
				"\nID: " + this.getId() + 
				"\nPhone Number: " + this.getPhoneNumber() +
				"\nAddress: " + this.getAddress() +
				"\nDate of Birth: " + this.getDateOfBirth()
				);
	}
	
	public void updateFees()
	{
		//TODO: Update fees by calling the increment method for each fee.
	}
	
	public String payFine(Fee f, double amt)
	{
		//TODO: Add fine payment functionality.

		//TODO: add return statement
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
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
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

	
	public void setCheckoutLimit(int age) {
		
	}

	public void notify(String note) {
		this.notifications.add(note);
	}
	
	public void update() {
		//TODO: Add update functionality
	}
}
