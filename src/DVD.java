public class DVD extends Media{
    private String director;
    private String[] actors;
    public DVD(String title,String genre,String description,String year,boolean newRelease,int copies,String aDirector) {
    	super(title,genre,description,year,newRelease,copies);
    	this.director = aDirector;
        //TODO: need to initialize actors
    	this.checkoutLength = 7;
    }
}
