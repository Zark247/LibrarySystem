
public class cmdHelp implements Command {

	public void execute(String argument, User u) {
		System.out.println("Below is a list of all executable commands, and a brief description.");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		System.out.println("checkout <media> - checks out the specified media, by title.  Must be logged in.");
		System.out.println("closeaccount - closes your account.  Must be logged in.");
		System.out.println("payfine <media> - pays the fine tied with the media, by title.  Must be logged in.  Will prompt for an amount.");
		System.out.println("seefines - shows your current fines.  Must be logged in.");
		System.out.println("wishlist - shows your current wishlist.  Must be logged in.");
		System.out.println("search <term> - Searches the current inventory using specified term.");
		System.out.println("rate <media> - Rates the media specified, by title.  Will prompt for specific rating.");
		System.out.println("quit - closes the terminal.");
		System.out.println("help - displays a help page of all commands.");
	}

	public boolean requiresUser() {
		return false;
	}

}
