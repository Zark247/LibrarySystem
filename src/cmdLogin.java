import java.util.Scanner;
/**
 * cmdLogin.java - Prompts the user for a username and password, then attempts to login.
 * @author Team Proxi
 */
public class cmdLogin implements Command {

	public void execute(String argument, User u) {
		Scanner s = LibraryDriver.s;
		System.out.print("Username: ");
		String userName = s.nextLine();
		System.out.print("Password: ");
		String password = s.nextLine();
		LibrarySystem.getInstance().login(userName, password);
		}

	public boolean requiresUser() {
		return false;
	}

}
