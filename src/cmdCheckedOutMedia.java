
/**
 * 
 * @author TeamProxi
 *
 */
public class cmdCheckedOutMedia implements Command{

	/**
	 * Allows a user that is logged in to view their currently checked out media
	 */
	public void execute(String argument, User u) {
		u.viewCurrentlyCheckedOutMedia();
	}

	public boolean requiresUser() {
		return true;
	}

}
