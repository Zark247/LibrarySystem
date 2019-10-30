
/**
 * Sub-media
 */
public class AudioBook extends Media{

    /**
     * Variables
     */
    private String author, narrator;

    /**
     *
     * @param title The name of the AudioBook
     * @param genre The genre of the AudioBook
     * @param description A brief description of the AudioBook
     * @param year The year the AudioBook was released
     * @param newRelease If it is a new release will be marked true, otherwise marked false
     * @param copies The amount of copies available
     * @param anAuthor Declares author to anAuthor
     * @param aNarrator Declares narrator to aNarrator
     */
    public AudioBook(String title, String genre, String description, String year, boolean newRelease, int copies,
                     String anAuthor, String aNarrator) {
    	super(title, genre, description, year, newRelease, copies);
    	this.author = anAuthor;
        this.narrator = aNarrator;
        this.checkoutLength = 30;
    }

	public String getAuthor() {
		return author;
	}
	public String getNarrator() {
		return narrator;
	}
}
