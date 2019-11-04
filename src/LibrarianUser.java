import java.util.Scanner;

/**
 * A type of user that is the average user (what most accounts are)
 * @author Cameron Brandenburg
 */
public class LibrarianUser extends User{
	
	/**
	 * An instance of LibrarySystem used to access its attributes
	 */
	LibrarySystem lib = LibrarySystem.getInstance();
	
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
		this.checkoutLimit = 50;
    this.accountType = "Librarian";
	}
	
	/**
	 * Allows a Librarian to approve Teacher accounts
	 * @param teacher The TeacherUser account to be approved
	 */
	public void approveTeacherAccount(TeacherUser teacher) {
		teacher.setIsApproved(true);
	}
	
	/**
	 * Adds a piece of media to the LibrarySystem's inventory.  If it already exists, prompts to add a copy.
	 * If it doesn't exist, it prompts the user for information regarding the media, and creates it.
	 * @param title the title of the media to be added.
	 */
	public void addMedia(String title) {
		Scanner s = TempDriver.s;
		for(Media m:LibrarySystem.getInstance().inventory) {
			if(m.title.equals(title)) {
				System.out.println("This item is already present within the system.  Would you like to add another copy? (Y/N)");
				String result = s.nextLine();
				if(result.toUpperCase().equals("Y")) {
					m.copy();
					System.out.println("Success!  New copy added.");
					m.returnMedia();
				}
				return;
			} 
		}
		System.out.println("Please input the following information for this piece of media:");
		System.out.println("Media type:");
		String mediaType = s.nextLine();
		System.out.print("Genre: ");
		String newGenre = s.next();
		s.nextLine();
		System.out.print("Description: ");
		String newDesc = s.next();
		s.nextLine();
		System.out.print("Year of Release: ");
		String newYear = s.next();
		s.nextLine();
		System.out.print("New Release (true/false): ");
		boolean newRelease = s.nextBoolean();
		if(mediaType.toUpperCase().equals("AUDIOBOOK")) {
			System.out.print("Author: ");
			String newAuth = s.next();
			s.nextLine();
			System.out.print("Narrator: ");
			String newNarr = s.next();
			AudioBook temp = new AudioBook(title,newGenre,newDesc,newYear,newRelease,1,newAuth,newNarr);
			System.out.println("Success!  New audiobook added.");
			return;
		}
		if(mediaType.toUpperCase().equals("BOOK")) {
			System.out.print("Author: ");
			String newAuth = s.next();
			s.nextLine();
			System.out.print("ISBN: ");
			String newISBN = s.next();
			s.nextLine();
			System.out.print("Publisher: ");
			String newPubl = s.next();
			s.nextLine();
			Book temp = new Book(title,newGenre,newDesc,newYear,newRelease,1,newAuth,newISBN,newPubl);
			System.out.println("Success!  New book added.");
			return;
		}
		if(mediaType.toUpperCase().equals("DVD")) {
			System.out.print("Director: ");
			String newActors = s.next();
			DVD temp = new DVD(title,newGenre,newDesc,newYear,newRelease,1,newActors);
			System.out.println("Success!  New DVD added.");
			return;
		}
		if(mediaType.toUpperCase().equals("EBOOK")) {
			System.out.print("Author: ");
			String newAuth = s.next();
			s.nextLine();
			EBook temp = new EBook(title,newGenre,newDesc,newYear,newRelease,1,newAuth);
			System.out.println("Success!  New Ebook added.");
			return;
		}
		if(mediaType.toUpperCase().equals("MAGAZINE")) {
			System.out.print("Author: ");
			String newAuth = s.next();
			s.nextLine();
			System.out.print("Volume: ");
			int newVol = s.nextInt();
			s.nextLine();
			System.out.print("Publisher: ");
			int newIssue = s.nextInt();
			s.nextLine();
			Magazine temp = new Magazine(title,newGenre,newDesc,newYear,newRelease,1,newAuth, newVol, newIssue);
			System.out.println("Success!  New magazine added.");
			return;
		}
	}
	
	/**
	 * Removes a piece of media from the LibrarySystem's inventory
	 * @param media The media to be removed
	 */
	void retireMedia(Media media) {
		lib.inventory.remove(media);
	}
	
	//TODO add functionality for flagging specific users
	public void flagUser() {
		super.flagUser();
	}
	
	//TODO add functionality for viewing users
	public void viewUser() {
		super.viewUser();
	}
}
