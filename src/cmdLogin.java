import java.util.Scanner;

public class cmdLogin implements Command {

	public void execute(String argument, User u) {
		Scanner s = TempDriver.s;
		System.out.print("Username: ");
		String email = s.next();
		s.nextLine();
		System.out.print("Password: ");
		String password = s.nextLine();
		LibrarySystem.getInstance().login(email, password);
		}

	public boolean requiresUser() {
		// TODO Auto-generated method stub
		return false;
	}

}
