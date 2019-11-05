/**
 * cmdMediaRatings.java - Command to return ratings for an item.
 * @author Kevin
 *
 */
public class cmdMediaRatings implements Command {

	public void execute(String argument, User u) {
		Media mediaRate = null;
        for(Media m:LibrarySystem.getInstance().inventoryNoCopies())
        	if(m.title.toUpperCase().equals(argument.toUpperCase())) //Search for media matching the input title.
        		mediaRate = m;
        if(mediaRate == null) {
        	System.out.println("No media with that title found!");
        } else {
        	mediaRate.displayRating();
        }
	}

	public boolean requiresUser() {
		return false;
	}

}
