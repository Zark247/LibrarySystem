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
	protected int checkoutLimit;
	protected ArrayList<Fee> fines = new ArrayList<Fee>();
	protected ArrayList<Media> wishlist = new ArrayList<Media>();
	protected String accountType;
	protected int id;
	protected ArrayList<String> notifications = new ArrayList<String>();
	protected boolean isClosed;
	protected ArrayList<User> children = new ArrayList<User>();
	
	public User(String s)
	{
		
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
