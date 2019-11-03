/**
 * Command to "see fines"
 */
public class SeeFines implements Command {

    /**
     * Variable
     */
    private User user;

    /**
     *
     * @param aUser tied to user
     */
    public SeeFines(User aUser) {
        this.user = aUser;
    }

    /**
     * Method to "see fines" associated with this account
     */
    public void execute() {
        user.update();
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
