
public class cmdRenewMedia implements Command {

	public void execute(String argument, User u) {
		for(Media m:u.getCheckedOutMedia()) {
			if(m.getTitle().toUpperCase().equals(argument.toUpperCase())) {
				u.renewMedia(m);
				return;
			}
		}
		System.out.println("Item not recognized/You don't have it checked out.");
	}

	public boolean requiresUser() {
		return true;
	}

}
