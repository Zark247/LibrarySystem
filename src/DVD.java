
/**
 * Sub-media
 */
public class DVD extends Media{

    /**
     * Variables
     */
    private String director;
    private String[] actors;

    /**
     *
     * @param title The name of the DVD
     * @param genre The genre of the DVD
     * @param description A brief description of the DVD
     * @param year The year the DVD was released
     * @param newRelease If it is a new release will be marked true, otherwise marked false
     * @param copies The amount of copies available
     * @param aDirector Declares director to aDirector
     */
    public DVD(String title, String genre, String description, String year, boolean newRelease, int copies,
               String aDirector) {
    	super(title, genre, description, year, newRelease, copies);
    	this.director = aDirector;
    	this.checkoutLength = 7;
    	this.actors = new String[50];
    }
    
    public String getDirector() {
		  return director;
	  }
}
