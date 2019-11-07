/**
 * cmdReturnMedia.java - Returns media by title.
 * @author Team Proxi
 */
public class cmdReturnMedia implements Command {

	public void execute(String argument, User u) {
		for(Media m:u.getCheckedOutMedia()) {
			if(m.getTitle().toUpperCase().equals(argument.toUpperCase())) {
				u.returnMedia(m);
				System.out.println(m.getTitle() + " has been successfully returned.");
				return;
			}
		}
		System.out.println("Item not recognized/You don't have it checked out.");
	}

	public boolean requiresUser() {
		return true;
	}

}
