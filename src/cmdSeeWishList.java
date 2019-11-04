

/**
 * Command to "see wish list"
 */
public class cmdSeeWishList implements Command {
    /**
     * Method to "see the whish list"
     */
    public void execute(String arg,User u) {
        u.checkWishlist();
    }

    /**
     *
     * @return true if the user account is not closed, false if account is closed
     */
    public boolean requiresUser() {
    	return true;
    }
}
