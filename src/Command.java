/**
 * Interface for Commands
 */
public interface Command {

    /**
     * Methods to be modified by calling classes
     */
    public void execute();
    public boolean requiresUser();

}
