/**
 * cmdLogOut.java - Command to log out of an account
 * @author Team Proxi
 */
public class cmdLogOut implements Command {

    /**
     * Method to log out
     */
    public void execute(String argument, User u) {
        System.out.println("Account logged out.");

    }

    public boolean requiresUser() {
        return true;
    }
}
