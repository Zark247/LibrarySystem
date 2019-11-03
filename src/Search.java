/**
 * Command to search the library system
 */
public class Search implements Command {

    /**
     * Variables
     */
    private User user;
    private String searchString;

    /**
     *
     * @param aUser is tied to user
     * @param aString is tied to searchString
     */
    public Search(User aUser, String aString) {
        this.user = aUser;
        this.searchString = aString;
    }

    /**
     * Method to "search" using the searchString parameter
     */
    public void execute() {
        user.search(searchString);
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
