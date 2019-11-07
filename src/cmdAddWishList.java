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
        System.out.println("Enter the media you would like to add to your wishlist: ");
        String media = s.nextLine();
        for(Media m:LibrarySystem.getInstance().inventoryNoCopies()) {
            if(m.title.toUpperCase().equals(media.toUpperCase())) { //Find media matching the input title
                u.requestMedia(m);
                System.out.println(m.getTitle() + " added to wishlist succesfuly");
                return;
            }
        }
        System.out.println("There is no media with that title.");
    }

    public boolean requiresUser() {
        return true;
    }
}
