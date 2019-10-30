

public class Magazine extends Media{
    private String author;
    private int volume, issue;
    public Magazine(String title,String genre,String description,String year,boolean newRelease,int copies, String anAuthor, int aVolume, int anIssue) {
    	super(title,genre,description,year,newRelease,copies);
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
