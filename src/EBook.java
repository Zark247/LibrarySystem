
public class EBook extends Media{
    private String author;
    public EBook(String title,String genre,String description,String year,boolean newRelease,int copies, String anAuthor) {
    	super(title,genre,description,year,newRelease,copies);
    	this.author = anAuthor;
    }
}
