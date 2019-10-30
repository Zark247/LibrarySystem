
/**
 * Sub-media
 */
public class EBook extends Media{

    /**
     * Variable
     */
    private String author;

    /**
     *
     * @param title The name of the EBook
     * @param genre The genre of the EBook
     * @param description A brief description of the EBook
     * @param year The year the EBook was released
     * @param newRelease If it is a new release will be marked true, otherwise marked false
     * @param copies The amount of copies available
     * @param anAuthor Declares author to anAuthor
     */
    public EBook(String title, String genre, String description, String year, boolean newRelease, int copies,
                 String anAuthor) {
    	super(title, genre, description, year, newRelease, copies);
    	this.author = anAuthor;
    	this.checkoutLength = 30;
    }
    	
    public String getAuthor() {
		  return author;
	  }
}