
/**
 * Sub-media
 */
public class Magazine extends Media{

    /**
     * Variables
     */
    private String author;
    private int volume, issue;

    /**
     *
     * @param title The title of the Magazine
     * @param genre The genre of the Magazine
     * @param description A brief description of the Magazine
     * @param year The year the Magazine was released
     * @param newRelease If it is a new release will be marked true, otherwise marked false
     * @param copies The amount of copies available
     * @param anAuthor Declares author to anAuthor
     * @param aVolume Declares volume to aVolume
     * @param anIssue Declares issue to anIssue
     */
    public Magazine(String title, String genre, String description, String year, boolean newRelease, int copies,
                    String anAuthor, int aVolume, int anIssue) {
    	super(title, genre, description, year, newRelease, copies);
        this.author = anAuthor;
        this.volume = aVolume;
        this.issue = anIssue;
        this.checkoutLength = 7;
    }

	public String getAuthor() {
		return author;
	}
	public int getVolume() {
		return volume;
	}
	public int getIssue() {
		return issue;
	}
}
