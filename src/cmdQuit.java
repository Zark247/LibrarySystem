
public class cmdQuit implements Command {

	public void execute(String argument, User u) {
		System.out.println("Come back soon!");
	}

	public boolean requiresUser() {
		return false;
	}

}
