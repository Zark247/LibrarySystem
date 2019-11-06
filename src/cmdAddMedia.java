
public class cmdAddMedia implements Command {

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
