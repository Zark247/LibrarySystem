
/**
 * AudioBook.java - Submedia item representing audiobooks.
 */
public class AudioBook extends Media{

    private String author, narrator;

    /**
     * Parameterized constructor for AudioBook
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
	
	@Override
	/**
	 * Creates a copy of the audiobook
	 * Overrides abstract copy() method from Media
	 */
	public void copy() {
		Object[] data = super.copyMediaData();
		AudioBook clone = new AudioBook((String)data[0],(String)data[1],(String)data[2],(String)data[3],((Boolean)data[4]).booleanValue(),((Integer)data[5]).intValue(),
				this.author,this.narrator);
	}
}
