import java.util.HashMap;

/**
 * Handles input depending on what command the User enters
 */
public class InputHandler {

    /**
     * Variables
     */
    private HashMap<String, Command> commands;
    private static User currentUser;

    //TODO: need a better way to handle commands that require more parameters than just User
    private String string = " ";
    private Media media;
    private Fee fee;
    private double aDouble;
    /**
     * Declares a unique key for a command
     */
    public InputHandler() {
        //TODO: need to list out commands
        commands.put("search", new Search(currentUser,string));
        commands.put("checkout", new CheckoutMedia(currentUser, media));
        commands.put("payfine", new PayFines(currentUser, fee, aDouble));
        commands.put("seefine", new SeeFines(currentUser));
        commands.put("wishlist", new SeeWishList(currentUser));
        commands.put("closeaccount", new CloseAccount(currentUser));
    }

    /**
     *
     * @param button The command that the user enters
     */
    public void buttonPressed(String button) {
        if(button.equalsIgnoreCase("search")) {
            commands.put("search", new Search(currentUser, string)).execute();
        } else if(button.equalsIgnoreCase("checkout")) {
            commands.put("checkout", new CheckoutMedia(currentUser, media)).execute();
        } else if(button.equalsIgnoreCase("payfine")) {
            commands.put("payfine", new PayFines(currentUser, fee, aDouble));
        } else if(button.equalsIgnoreCase("seefine")) {
            commands.put("seefine", new SeeFines(currentUser)).execute();
        } else if(button.equalsIgnoreCase("wishlist")) {
            commands.put("wishlist", new SeeWishList(currentUser)).execute();
        } else if (button.equalsIgnoreCase("closeaccount")) {
            commands.put("closeaccount", new CloseAccount(currentUser)).execute();
        } else {
            System.out.println("Enter a proper command.");
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
