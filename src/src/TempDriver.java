package src;

import java.util.ArrayList;

/**
 * A temporary driver class used to test classes and methods
 * @author Cameron Brandenburg
 */
public class TempDriver {

	public static void main(String[] args) {
		Book Exaltiora = new Book("Exaltiora", "Tomes", "Codex Caelum", "1934", false, 1, "Lightener", 00000001, "None");
		Book Exaltiora2 = new Book("Exaltiora2", "Tomes2", "Codex Caelum2", "19342", false, 1, "Lightener2", 000000012, "None");
		JSONReader json = new JSONReader();
		
		User Lightener = LibrarySystem.getInstance().createAccount(4, "Jorgen", "1978", "REDACTED", "REDACTED", "REDACTED", "jlight9", "tomer", 1);
		Lightener.checkoutMedia(Exaltiora);
		Lightener.renewMedia(Exaltiora);
		User Lightener2 = LibrarySystem.getInstance().createAccount(4, "Jorgen2", "19782", "REDACTED2", "REDACTED2", "REDACTED2", "jlight92", "tomer2", 1);
		
		json.writeUsers();
		LibrarySystem.getInstance().users.clear();
		json.loadUsers();
		for(User u: LibrarySystem.getInstance().users) {
			u.viewUser();
			u.viewCurrentlyCheckedOutMedia();
			u.viewMediaOnHold();
		}
		
		
		if (false) {
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
		}
	}

}
