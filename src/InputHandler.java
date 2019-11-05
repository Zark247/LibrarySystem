import java.util.HashMap;

/**
 * InputHandler.java - Handles input depending on what command the User enters
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
        commands.put("seefines", new cmdSeeFines());
        commands.put("wishlist", new cmdSeeWishList());
        commands.put("closeaccount", new cmdCloseAccount());
        commands.put("rate", new cmdRateMedia());
        commands.put("quit", new cmdQuit());
        commands.put("help", new cmdHelp());
        commands.put("login", new cmdLogin());
        commands.put("viewmedia", new cmdViewMedia());
        commands.put("renew", new cmdRenewMedia());
        commands.put("return", new cmdReturnMedia());
        commands.put("viewratings", new cmdMediaRatings());
    }

    /**
     *
     * @param cmdArgs Strings the user enters, seperated by spaces.
     */
    public void inputCommand(String[] cmdArgs) {
        if(commands.containsKey(cmdArgs[0])) { //The first argument is the command, the rest are pass-ins.
        	Command cmd = commands.get(cmdArgs[0]);
        	String argument = "";
        	for(int i = 0;i < cmdArgs.length;i++) {
        		if(i != 0) {
        			if(i != cmdArgs.length-1)
        				argument += cmdArgs[i] + " ";
        			else
        				argument += cmdArgs[i];
        		}
        	}
        	if(cmd.requiresUser()) {
        		if(InputHandler.currentUser == null)
        			System.out.println("You must be logged in to use that command!");
        		else
        			cmd.execute(argument, InputHandler.currentUser);
        	}else {
        		cmd.execute(argument, null);
        	}
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
