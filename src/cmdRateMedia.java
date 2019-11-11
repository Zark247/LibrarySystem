import java.util.Scanner;

/**
 * cmdRateMedia.java - Command to "rate" a specific type of media
 * @author Team Proxi
 */

public class cmdRateMedia implements Command {
    /**
     * Method to "rate" media
     */
    public void execute(String arg,User u) {
        Scanner s = LibraryDriver.s;
        Media mediaRate = null;
        for(Media m:LibrarySystem.getInstance().inventoryNoCopies())
        	if(m.title.toUpperCase().equals(arg.toUpperCase()))
        		mediaRate = m; //Find the media to rate by title.
        if(mediaRate == null) {
        	System.out.println("No media with that title found!");
        } else {
        	System.out.println("You are now rating: " + mediaRate.title);
        	mediaRate.displayRating();
        	System.out.print("What rating would you give this item (1-5)? ");
        	int rate = s.nextInt();
        	s.nextLine();
        	System.out.println("Please leave a brief comment about the item.");
        	String comment = s.nextLine();
        	mediaRate.addRating(rate, comment);
        }
    }

    /**
     *
     * @return true if the user account is not closed, false if account is closed
     */
    public boolean requiresUser() {
    	return false;
    }
}
