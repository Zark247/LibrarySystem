/**
 * Command to "close a user account"
 */
public class CloseAccount implements Command {

    /**
     * Variable
     */
    private User user;

    /**
     *
     * @param aUser tied to user
     */
    public CloseAccount(User aUser) {
        this.user = aUser;
    }

    /**
     * Method to "close account"
     */
    public void execute() {
        user.closeAccount();
    }

    /**
     *
     * @return true if the user account is not closed, false if account is closed
     */
    public boolean requiresUser() {
        if(!user.isClosed()) {
            return true;
        } else {
            return false;
        }
    }
}
