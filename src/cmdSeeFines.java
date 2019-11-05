/**
 * cmdSeeFines.java - Command to "see fines"
 */
public class cmdSeeFines implements Command {
    /**
     * Method to "see fines" associated with this account
     */
    public void execute(String arg,User u) {
        u.checkFines();
    }

    /**
     *
     * @return true if the user account is not closed, false if account is closed
     */
    public boolean requiresUser() {
    	return true;
    }
}
