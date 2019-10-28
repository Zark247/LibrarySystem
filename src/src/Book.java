package src;

public class Book extends Media{
    private String author, publisher;
    private int ISBN;
    public Book(String title,String genre,String description,String year,boolean newRelease,int copies,String anAuthor, int anISBN, String aPublisher) {
    	super(title,genre,description,year,newRelease,copies);
    	this.author = anAuthor;
        this.ISBN = anISBN;
        this.publisher = aPublisher;
        this.checkoutLength = 30;
    }
}
