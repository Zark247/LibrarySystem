

/**
 * Command to "pay fines"
 */
public class cmdPayFines implements Command {
    /**
     * Method to "pay fine"
     */
    public void execute(String arg,User u) {
        //u.payFine(fee, amount);
    }

    /**
     *
     * @return true if the user account is not closed, false if account is closed
     */
    public boolean requiresUser() {
    	return true;
    }
}
