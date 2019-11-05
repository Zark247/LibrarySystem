/**
 * cmdSearch.java - Command to search the library system
 */
public class cmdSearch implements Command {
	/**
     * Method to "search" using the searchString parameter
     */
	public void execute(String arg,User u) {
		LibrarySystem.getInstance().search(arg);
	}
    /**
     *
     * @return true if the user account is not closed, false if account is closed
     */
    public boolean requiresUser() {
    	return false;
    }
}
