/**
 * Command to check media out
 */
public class CheckoutMedia implements Command {

    /**
     * Variables
     */
    private User user;
    private Media media;

    /**
     *
     * @param aUser set to user
     * @param aMedia set to media
     */
    public CheckoutMedia(User aUser, Media aMedia) {
        this.user = aUser;
        this.media = aMedia;
    }

    /**
     * Method to "check out" media
     */
    public void execute() {
        user.checkoutMedia(media);
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
