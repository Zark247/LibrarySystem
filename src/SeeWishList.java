/**
 * Command to "see wish list"
 */
public class SeeWishList implements Command {

    /**
     * Variable
     */
    private User user;

    /**
     *
     * @param aUser tied to user
     */
    public SeeWishList(User aUser) {
        this.user = aUser;
    }

    /**
     * Method to "see the whish list"
     */
    public void execute() {
        user.checkWishlist();
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
