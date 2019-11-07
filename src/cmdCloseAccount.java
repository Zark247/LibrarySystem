import java.util.Scanner;

/**
 * cmdCloseAccount.java - Command to "close a user account"
 * @author Team Proxi
 */
public class cmdCloseAccount implements Command {
    /**
     * Method to "close account"
     */
    public void execute(String arg,User u) {
    	System.out.println("WARNING: This will close your account permanently, making it impossible to log in to.  Are you sure? (Y/N)");
    	Scanner s = LibraryDriver.s;
    	String response = s.nextLine();
    	if(response.toUpperCase().equals("Y")) {
    		u.closeAccount();
    		InputHandler.currentUser = null;
    		System.out.println("Account closed.  You have been logged out.");
    	}
    }

    /**
     *
     * @return true if the user account is not closed, false if account is closed
     */
    public boolean requiresUser() {
    	return true;
    }
}
