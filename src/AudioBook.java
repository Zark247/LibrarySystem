

public class AudioBook extends Media{
    private String author, narrator;
    public AudioBook(String title,String genre,String description,String year,boolean newRelease,int copies,String anAuthor, String aNarrator) {
    	super(title,genre,description,year,newRelease,copies);
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
