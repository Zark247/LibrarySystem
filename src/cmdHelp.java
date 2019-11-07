/**
 * cmdHelp.java - Displays a list of all known commands.
 * @author Team Proxi
 */
public class cmdHelp implements Command {

	public void execute(String argument, User u) {
		System.out.println("Below is a list of all executable commands, and a brief description.");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		System.out.println("createaccount - allows for account creation.");
		System.out.println("login - prompts for username and password of account.");
		System.out.println("logout - logs the current user out.");
		System.out.println("checkout <media> - checks out the specified media, by title.  Must be logged in.");
		System.out.println("renew <media> - renews the specified media, by title.  Must be logged in.");
		System.out.println("return <media> - returns the specified media, by title.  Must be logged in.");
		System.out.println("closeaccount - closes your account.  Must be logged in.");
		System.out.println("payfine <media> - pays the fine tied with the media, by title.  Must be logged in.  Will prompt for an amount.  Use 'all' to pay all fines.");
		System.out.println("seefines - shows your current fines.  Must be logged in.");
		System.out.println("addwishlist - adds media to wishlist.");
		System.out.println("wishlist - shows your current wishlist.  Must be logged in.");
		System.out.println("viewcheckedout - shows your current checked out media");
		System.out.println("search <term> - Searches the current inventory using specified term.");
		System.out.println("viewmedia <media> - Displays information on the specified media, by title.");
		System.out.println("rate <media> - Rates the media specified, by title.  Will prompt for specific rating.");
		System.out.println("viewratings <media> - Shows the ratings the media specified, by title.");
		System.out.println("viewaccount - Returns the account type. Must be logged in.");
		System.out.println("quit - closes the terminal.");
		System.out.println("help <type> - displays a help page of all commands. Use <type> to show specific commands, leave blank for general: ex. Librarian");
		if(argument.equalsIgnoreCase("librarian")) {
			System.out.println("addmedia <media> - Adds a new media item, by title.  Must be a librarian.");
			System.out.println("approveteacher <user> - approves a teacher account, from username");
			System.out.println("retiremedia <media> - Removes the media from the system, as long as nobody has it checked out, by title.  Fees tied to the media will remain.");
		}
	}

	public boolean requiresUser() {
		return false;
	}

}
