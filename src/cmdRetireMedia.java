import java.util.ArrayList;

/**
 * cmdRetireMedia.java - Command for librarians to retire media
 * @author Team Proxi
 */
public class cmdRetireMedia implements Command {

	public void execute(String argument, User u) {
		if(u.getAccountType().equals("Librarian")) {
			ArrayList<Media> mediaRemoval = new ArrayList<Media>();
			for(Media m:LibrarySystem.getInstance().inventory) {
				if(m.title.equalsIgnoreCase(argument))
					mediaRemoval.add(m);
			}
			for(Media m:mediaRemoval) {
				if(m.isCheckedOut()) {
					System.out.println("You cannot remove this item!  A user has checked out a copy.");
					return;
				} 
			}
			LibrarySystem.getInstance().inventory.removeAll(mediaRemoval);
			System.out.println("Item removed successfully.");
		}else
			System.out.println("You must be a librarian to perform this action!");
	} 

	public boolean requiresUser() {
		return true;
	}

}
