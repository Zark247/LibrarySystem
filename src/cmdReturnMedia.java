
public class cmdReturnMedia implements Command {

	public void execute(String argument, User u) {
		for(Media m:u.getCheckedOutMedia()) {
			if(m.getTitle().toUpperCase().equals(argument.toUpperCase())) {
				u.returnMedia(m);
				return;
			}
		}
		System.out.println("Item not recognized/You don't have it checked out.");
	}

	public boolean requiresUser() {
		return true;
	}

}
