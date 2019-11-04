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
        //TODO: need to list out commands
        // Example
        //commands.put("aCommand", new wCommand(currentUser));
    }

    /**
     *
     * @param button The command that the user enters
     */
    public void buttonPressed(String button) {
        /*    // Example
        if(button.equalsIgnoreCase("aCommand")) {
            commands.put("aCommand", new wCommand(currentUser)).execute();
        }
         */
    }

    /**
     *
     * @param newUser swaps the current user to a new user
     */
    public static void changeUser(User newUser) {
        currentUser = newUser;
    }
}
