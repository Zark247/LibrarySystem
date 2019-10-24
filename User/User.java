package User;

import java.util.ArrayList;

public class User 
{
	protected String name;
	protected String dateOfBirth;
	protected String Address;
	protected String email;
	protected String phoneNumber;
	protected String username;
	protected String password;
	protected int id;
	protected int checkoutLimit;
	protected ArrayList<Fee> fines = new ArrayList<Fee>();
	protected ArrayList<Double> fine = new ArrayList<Double>();
	protected ArrayList<Media> wishlist = new ArrayList<Media>();
	protected String accountType;
	protected ArrayList<String> notifications = new ArrayList<String>();
	protected boolean isClosed;
	protected ArrayList<User> children = new ArrayList<User>();
	
	public User()
	{
		this.name = this.dateOfBirth = this.Address =
				this.email = this.phoneNumber = this.username =
				this.password = "";
		this.id = 0;
	}
	
	public User(String name, String dateOfBirth, String address,
			String email, String phoneNumber, String username,
			String password, int id)
	{
		this.setName(name);
		this.setDateOfBirth(dateOfBirth);
		this.setAddress(address);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
		this.setUsername(username);
		this.setPassword(password);
		this.setId(id);
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
	
	
	public void closeAccount()
	{
		
	}
	
	
	public void viewCurrentlyCheckedOutMedia()
	{
		
	}
	
	public void viewMediaOnHold()
	{
		
	}
	
	public double checkFines()
	{

	}
	
	public void checkAccountNumber()
	{
		
	}
	
	public void checkWishlist()
	{
		
	}
	
	public int returnAccountType()
	{
		
	}
	
	public void checkoutMedia(Media m)
	{
		
	}
	
	public void requestMedia(Media m)
	{
		
	}
	
	public void returnMedia(Media m)
	{
		
	}
	
	public void renewMedia(Media m)
	{
		
	}
	
	public void search(String s)
	{
		
	}
	
	public void putOnHold()
	{
		
	}
	
	public void linkChildAccount()
	{
		
	}
	
	public void viewUser(User u)
	{
		
	}
	
	public void updateFees()
	{
		
	}
	
	public String payFine(Fee f)
	{
		
	}
}
