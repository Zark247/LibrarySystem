/**
 * Command.java - Interface for Commands
 * @author Team Proxi
 */
public interface Command {

    /**
     * Methods to be modified by calling classes
     */
    public void execute(String argument,User u);
    public boolean requiresUser();
}
