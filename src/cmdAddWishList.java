import java.util.Scanner;

/**
 * cmdAddWishList.java - Command to add media to wish list
 * @author Team Proxi
 */
public class cmdAddWishList implements Command {

    /**
     * Method to add Media to wish list
     */
    public void execute(String argument, User u) {
        Scanner s = LibraryDriver.s;
        System.out.println("Enter the title of the media you would like to add to your wishlist: ");
        String media = s.nextLine();
        u.requestMedia(media);
        System.out.println(media + " added to wishlist succesfuly");
        return;
        }
        

    public boolean requiresUser() {
        return true;
    }
}
