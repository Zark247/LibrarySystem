
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
		InputHandler InHandle = new InputHandler();
		json.loadDatabase();
		String input = "";
		System.out.println("Welcome to the library!  You are not currently logged in.");
		while(!(input.toLowerCase().equals("quit"))) {
			if(InputHandler.currentUser == null)
				System.out.print("guest> ");
			else
				System.out.print(InputHandler.currentUser.getEmail() + "> ");
			input = s.nextLine();
			String[] inputs = input.split(" ");
			InHandle.inputCommand(inputs);
		}
		
		if(false) {
		
		AverageUser avgUser = new AverageUser("Average Name", "10/10/1980", "99 Sumter Dr.",
				"average@email.com", "000-000-0000", "averageusername",
				"averagepassword", 0);
		ChildUser child = new ChildUser("Child Name", "05/20/2010", "99 Sumter Dr.",
				"child@email.com", "111-111-1111", "childusername",
				"childpassword", 0);
		TeacherUser teacher = new TeacherUser("Teacher Name", "10/10/2000", "499 Sumter Dr.",
				"teacher@email.com", "222-222-2222", "teacherusername",
				"teacherpassword", 0);
		LibrarianUser librarian = new LibrarianUser("Librarian Name", "11/11/1967", "10 Sumter Dr.",
				"librarian@email.com", "333-333-3333", "librarianusername",
				"librarianpassword", 0);

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
		for (Media media : lib.inventory) {
			System.out.println(media.title);
		}
		
		System.out.println("Removing AudioBook 1, Magazine 1, and DVD 1");
		librarian.retireMedia(audioBook);
		librarian.retireMedia(magazine);
		librarian.retireMedia(dvd);
		
		System.out.println("Currently in the library inventory");
		for (Media media : lib.inventory) {
			System.out.println(media.title);
		}
		
		
		avgUser.checkoutMedia(book);
		
		System.out.println("Currently in the library inventory");
		for (Media media : lib.inventory) {
			System.out.println(media.title);
		}
		
		System.out.println("Currently checked out:");
		for (Media m:avgUser.checkedOutMedia) {
			System.out.println(m.getTitle());
		}

		avgUser.returnMedia(book);
	}
	}
}
