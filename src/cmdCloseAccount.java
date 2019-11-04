

/**
 * Command to "close a user account"
 */
public class cmdCloseAccount implements Command {
    /**
     * Method to "close account"
     */
    public void execute(String arg,User u) {
        u.closeAccount();
    }

    /**
     *
     * @return true if the user account is not closed, false if account is closed
     */
    public boolean requiresUser() {
    	return true;
    }
}
