/**
 * Command to "rate" a specific type of media
 */

//TODO: Rate method for user needs to written
public class RateMedia implements Command {

    /**
     * Variable
     */
    private User user;
    private Media media;
    private int rating;

    /**
     *
     * @param aUser tied to user
     * @param aMedia tied to media
     * @param aRating tied to rating
     */
    public RateMedia(User aUser, Media aMedia, int aRating) {
        this.user = aUser;
        this.media = aMedia;
        this.rating = aRating;
    }

    /**
     * Method to "rate" media
     */
    public void execute() {
        //TODO: when implemented needs to be added to InputHandler
        //user.rate(media, rating);
    }

    /**
     *
     * @return true if the user account is not closed, false if account is closed
     */
    public boolean requiresUser() {
        if(!user.isClosed()) {
            return true;
        } else {
            return false;
        }
    }
}
