import java.util.HashMap;

/**
 * Handles input depending on what command the User enters
 */
public class InputHandler {

    /**
     * Variables
     */
    private HashMap<String, Command> commands;
    public static User currentUser;
    /**
     * Declares a unique key for a command
     */
    public InputHandler() {
    	commands = new HashMap<String, Command>();
        //TODO: need to list out commands
        commands.put("search", new cmdSearch());
        commands.put("checkout", new cmdCheckoutMedia());
        commands.put("payfine", new cmdPayFines());
        commands.put("seefine", new cmdSeeFines());
        commands.put("wishlist", new cmdSeeWishList());
        commands.put("closeaccount", new cmdCloseAccount());
    }

    /**
     *
     * @param cmdArgs Strings the user enters, seperated by spaces.
     */
    public void inputCommand(String[] cmdArgs) {
        if(commands.containsKey(cmdArgs[0])) {
        	Command cmd = commands.get(cmdArgs[0]);
        	String argument = "";
        	for(int i = 0;i < cmdArgs.length;i++) {
        		if(i != 0)
        			argument += cmdArgs[i] + " ";
        	}
        	if(cmd.requiresUser())
        		cmd.execute(argument, InputHandler.currentUser);
        	else
        		cmd.execute(argument, null);
        } else {
            System.out.println("Command not recognized.");
        }
    }

    /**
     *
     * @param newUser swaps the current user to a new user
     */
    public static void changeUser(User newUser) {
        currentUser = newUser;
    }
}
