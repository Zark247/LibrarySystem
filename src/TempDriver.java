
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A temporary driver class used to test classes and methods
 * @author Cameron Brandenburg
 */
public class TempDriver {
	public static Scanner s = new Scanner(System.in);
	public static void main(String[] args) {
		JSONReader json = new JSONReader();
		json.loadDatabase();
		
		AverageUser avgUser = (AverageUser) LibrarySystem.getInstance().users.get(1);
		ChildUser child = (ChildUser) LibrarySystem.getInstance().users.get(2);
		TeacherUser teacher = (TeacherUser) LibrarySystem.getInstance().users.get(3);
		LibrarianUser librarian = (LibrarianUser) LibrarySystem.getInstance().users.get(4);

		ArrayList<User> userList = new ArrayList<User>();
		userList.add(avgUser);
		userList.add(child);
		userList.add(teacher);
		userList.add(librarian);
		
		System.out.println("List of users:");
		
		for (User user : userList) {
			user.viewUser();
			System.out.println();
		}
		
		System.out.println("----------------------------------------------------------------");
		System.out.println("Linking "+avgUser.getName()+" as a parent of "+child.getName());
		child.linkParentAccount(avgUser);
		child.viewUser();
		
		System.out.println("----------------------------------------------------------------");
		System.out.println(librarian.getName()+" approving "+teacher.getName());
		librarian.approveTeacherAccount(teacher);
		teacher.viewUser();
		
		System.out.println("----------------------------------------------------------------");
		System.out.println("Updated list of users:");
		
		for (User user : userList) {
			user.viewUser();
			System.out.println();
		}
		
		
		AudioBook audioBook = new AudioBook("AudioBook 1", "NonFiction", "AudioBook 1 Description", "2019", true, 2,
                "Audiobook Author", "Audiobook Narrator");
		Book book = new Book("Book 1", "Fiction", "Book 1 Description", "2018", false, 1,
                "Book Author", "0", "Book Publisher");
		DVD dvd = new DVD("DVD 1", "Documentary", "DVD 1 Description", "2005", false, 1,
	               "DVD Director");
		EBook eBook = new EBook("EBook 1", "Historical Fiction", "EBook 1 Description", "2010", false, 10,
                 "EBook Author");
		Magazine magazine = new Magazine("Magazine 1", "Lifestyle", "Magazine 1 Description", "2019", true, 3,
                "Magazine Author", 10, 2);
		
		LibrarySystem lib = LibrarySystem.getInstance();
		
		System.out.println("Currently in the library inventory");
		for (Media media : lib.inventoryNoCopies()) {
			System.out.println(media.title);
		}
		
		System.out.println("\nRemoving AudioBook 1, Magazine 1, and DVD 1");
		librarian.retireMedia(audioBook);
		librarian.retireMedia(magazine);
		librarian.retireMedia(dvd);
		
		System.out.println("\nCurrently in the library inventory");
		for (Media media : lib.inventoryNoCopies()) {
			System.out.println(media.title);
		}
		
		System.out.println("\nChecking out: "+book.getTitle());
		avgUser.checkoutMedia(book);
		
		System.out.println("\nCurrently in the library inventory");
		for (Media media : lib.inventoryNoCopies()) {
			System.out.println(media.title);
		}
		
		System.out.println("\nCurrently checked out:");
		for (Media m:avgUser.checkedOutMedia) {
			System.out.println(m.getTitle());
		}

		System.out.println("\nReturning: "+book.getTitle());
		avgUser.returnMedia(book);
		

		System.out.println("\nSearching for: Tiny Houses");
		lib.search("Tiny Houses");
		
		System.out.println("\nSearching for: Top Gun");
		lib.search("Top Gun");
		
		System.out.println("\nSearching for: The");
		lib.search("The");
		
		System.out.println("\nSearching for: h");
		lib.search("h");
		
		System.out.println("\nSearching for: asdfjkl");
		lib.search("asdfjkl");

		System.out.println();
		
		System.out.println("Logging in as average user.");
		LibrarySystem.getInstance().login("averageusername", "averagepassword");
		System.out.println();
		
		System.out.println("Attempting to check out The Cat in the Hat 3 times. -------------------------------");
		avgUser.checkoutMedia(lib.inventory.get(0));
		avgUser.checkoutMedia(lib.inventory.get(0));
		avgUser.checkoutMedia(lib.inventory.get(0));
		System.out.println();
		
		System.out.println("Attempting to add media, \"The Cat in the Hat\". -------------------------------");
		librarian.addMedia("The Cat in the Hat");
		System.out.println();
		
		System.out.println("Logging in as average user.");
		LibrarySystem.getInstance().login("averageusername", "averagepassword");
		System.out.println();
		
		System.out.println("Rating book 1, then displaying ratings. -------------------------------");
		book.addRating(4, "Sort of generic, but still a good read.");
		
		book.displayRating();
		
		s.close();
	}
}
