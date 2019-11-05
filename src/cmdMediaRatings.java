
public class cmdMediaRatings implements Command {

	public void execute(String argument, User u) {
		Media mediaRate = null;
        for(Media m:LibrarySystem.getInstance().inventoryNoCopies())
        	if(m.title.toUpperCase().equals(argument.toUpperCase()))
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
