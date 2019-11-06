/**
 * cmdViewAccount.java - Command to "view account"
 */
public class cmdViewAccount implements Command {

    /**
     * method to "view account"
     */
    public void execute(String argument, User u) {
        System.out.println(u);
    }

    /**
     *
     * @return true if the user account is not closed, false if account is closed
     */
    public boolean requiresUser() {
        return true;
    }
}