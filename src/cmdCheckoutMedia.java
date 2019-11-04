

/**
 * Command to check media out
 */
public class cmdCheckoutMedia implements Command {
    /**
     * Method to "check out" media
     */
    public void execute(String arg,User u) {
        //u.checkoutMedia(media);
    }

    /**
     *
     * @return true if the user account is not closed, false if account is closed
     */
    public boolean requiresUser() {
    	return true;
    }
}
