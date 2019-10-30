

public class Book extends Media{
    private String author, publisher;
    private String ISBN;
    public Book(String title,String genre,String description,String year,boolean newRelease,int copies,String anAuthor, String anISBN, String aPublisher) {
    	super(title,genre,description,year,newRelease,copies);
    	this.author = anAuthor;
        this.ISBN = anISBN;
        this.publisher = aPublisher;
        this.checkoutLength = 30;
    }
	public String getAuthor() {
		return author;
	}
	public String getPublisher() {
		return publisher;
	}
	public String getISBN() {
		return ISBN;
	}
    
}
