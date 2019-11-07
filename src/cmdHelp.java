/**
 * cmdHelp.java - Displays a list of all known commands.
 * @author Team Proxi
 */
public class cmdHelp implements Command {

	public void execute(String argument, User u) {
		System.out.println("Below is a list of all executable commands, and a brief description.");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		System.out.println("createaccount - Allows for account creation.");
		System.out.println("login - Prompts for username and password of account.");
		System.out.println("logout - Logs the current user out. Must be logged in.");
		System.out.println("checkout <media> - Checks out the specified media, by title.  Must be logged in.");
		System.out.println("renew <media> - Renews the specified media, by title.  Must be logged in.");
		System.out.println("return <media> - Returns the specified media, by title.  Must be logged in.");
		System.out.println("closeaccount - Closes your account.  Must be logged in.");
		System.out.println("payfine <media> - Pays the fine tied with the media, by title.  Must be logged in.  Will prompt for an amount.  Use 'all' to pay all fines.");
		System.out.println("seefines - Shows your current fines.  Must be logged in.");
		System.out.println("addwishlist - Adds media to wishlist. Must be logged in.");
		System.out.println("wishlist - Shows your current wishlist.  Must be logged in.");
		System.out.println("viewcheckedout - Shows your current checked out media. Must be logged in");
		System.out.println("search <term> - Searches the current inventory using specified term.");
		System.out.println("viewmedia <media> - Displays information on the specified media, by title.");
		System.out.println("rate <media> - Rates the media specified, by title.  Will prompt for specific rating.");
		System.out.println("viewratings <media> - Shows the ratings the media specified, by title.");
		System.out.println("viewaccount - Displays account information. Must be logged in.");
		System.out.println("quit - closes the terminal.");
		System.out.println("help <type> - Displays a help page of all commands. Use <type> to show specific commands, leave blank for general: ex. Librarian");
		if(argument.equalsIgnoreCase("librarian")) {
			System.out.println("addmedia <media> - Adds a new media item, by title.  Must be a librarian.");
			System.out.println("approveteacher <user> - Approves a teacher account, from username");
			System.out.println("retiremedia <media> - Removes the media from the system, as long as nobody has it checked out, by title.  Fees tied to the media will remain.");
		}
	}

	public boolean requiresUser() {
		return false;
	}

}
