

/**
 * Command to "rate" a specific type of media
 */

//TODO: Rate method for user needs to written
public class cmdRateMedia implements Command {
    /**
     * Method to "rate" media
     */
    public void execute(String arg,User u) {
        //TODO: when implemented needs to be added to InputHandler
        //user.rate(media, rating);
    }

    /**
     *
     * @return true if the user account is not closed, false if account is closed
     */
    public boolean requiresUser() {
    	return false;
    }
}
