
public class cmdViewMedia implements Command {

	public void execute(String argument, User u) {
		for(Media m:LibrarySystem.getInstance().inventoryNoCopies()) {
			if(m.title.toUpperCase().equals(argument.toUpperCase())) {
				System.out.println(m.toString());
				return;
			}
		}
		System.out.println("That item could not be found.");
	}

	public boolean requiresUser() {
		return false;
	}

}
