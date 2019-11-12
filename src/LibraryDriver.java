import java.util.Calendar;
import java.util.Scanner;

/**
 * LibraryDriver.java - A driver class which takes in input and runs commands.
 * @author Team Proxi
 */
public class LibraryDriver {
	public static Scanner s = new Scanner(System.in);
	public static void main(String[] args) {
		JSONReader json = new JSONReader();
		InputHandler InHandle = new InputHandler();
		json.loadDatabase();
		String input = "";
		System.out.println("Welcome to the library!"
		+"\nType \"help\" for a list of commands.");
		while(!(input.toLowerCase().equals("quit"))) {
			LibrarySystem.getInstance().updateSystime(Calendar.getInstance());
			if(input.equalsIgnoreCase("logout")) {
				InputHandler.changeUser(null);
			}
			if(InputHandler.currentUser == null)
				System.out.print("guest> ");
			else {
				System.out.print(InputHandler.currentUser.getUsername() + "> ");
			}
			input = s.nextLine();
			String[] inputs = input.split(" ");
			InHandle.inputCommand(inputs);
			System.out.println();
		}
		//json.writeDatabase();
		s.close();
	}
}
