/**
 * cmdAddMedia.java - Command to add Media
 * @author Team proxi
 */
public class cmdAddMedia implements Command {

	/**
	 * Method to add Media.  
	 * Prompts the user to fill in information about the media if it's new, if not, asks if they want to add a new copy.
	 */
	public void execute(String argument, User u) {
		if(u.getAccountType().equals("Librarian"))
			((LibrarianUser) u).addMedia(argument);
		else
			System.out.println("You must be a librarian to add media!");
	}

	public boolean requiresUser() {
		return true;
	}

}
