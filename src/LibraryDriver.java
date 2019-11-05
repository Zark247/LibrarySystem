import java.util.Scanner;

/**
 * LibraryDriver.java - A driver class which takes in input and runs commands.
 * @author Kevin Prince
 */
public class LibraryDriver {
	public static Scanner s = new Scanner(System.in);
	public static void main(String[] args) {
		JSONReader json = new JSONReader();
		InputHandler InHandle = new InputHandler();
		json.loadDatabase();
		String input = "";
		System.out.println("Welcome to the library!");
		while(!(input.toLowerCase().equals("quit"))) {
			if(InputHandler.currentUser == null)
				System.out.print("guest> ");
			else
				System.out.print(InputHandler.currentUser.getUsername() + "> ");
			input = s.nextLine();
			String[] inputs = input.split(" ");
			InHandle.inputCommand(inputs);
			json.writeDatabase();
		}
		s.close();
	}
}
