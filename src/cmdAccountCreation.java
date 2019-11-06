import java.util.Scanner;
/**
 * cmdAccountCreation.java - Command to create an account
 */
public class cmdAccountCreation implements Command {

    /**
     * Method for account creation
     */
    public void execute(String argument, User u) {
        Scanner s = LibraryDriver.s;
        System.out.println("You are about to create a Library account. If you are over the age of 16, enter: \"Yes\", otherwise enter: \"No\"");
        String input = s.nextLine();
        if(input.equalsIgnoreCase("Yes")) {
            System.out.println("Enter your first name: ");
            String firstName = s.nextLine();
            System.out.println("Enter your last name: ");
            String lastName = s.nextLine();
            System.out.println("When is your birthday? Enter in a format that matches: 00-00-00");
            String birthday = s.nextLine();
            System.out.println("What is your address? Enter in a format that matches: 000 Blank St. Ferguson, CA 22222");
            String address = s.nextLine();
            System.out.println("Enter your email address: ");
            String eAddress = s.nextLine();
            System.out.println("Enter a telephone number that matches this format: 000-000-0000");
            String phoneNumber = s.nextLine();
            System.out.println("Enter the username you would like to use: ");
            String userName = s.nextLine();
            System.out.println("Enter the password you would like to use: ");
            String password = s.nextLine();
            System.out.println("Are you a teacher? (Requires Verification) (yes/no)");
            String teacher = s.nextLine();
            int passwordLength = password.length();
            StringBuilder x = new StringBuilder();
            for(int i = 0; i <passwordLength; ++i) {
                x.append("X");
            }
            String name = firstName + " " + lastName;
            int id = (int)(Math.random()*((10000-1)+1))+1;
            System.out.println("Name: " + name
            +"\nBirthday: " + birthday
            +"\nAddress: " + address
            +"\nEmail address: " + eAddress
            +"\nPhone number: " + phoneNumber
            +"\nUser name: " + userName
            +"\nPassword: " + x
            +"\nUser ID: " + id);
            if(teacher.equalsIgnoreCase("yes"))
            	LibrarySystem.getInstance().createAccount(3, name, birthday, address, eAddress, phoneNumber, userName, password, id);
            else
            	LibrarySystem.getInstance().createAccount(2, name, birthday, address, eAddress, phoneNumber, userName, password, id);

        } else if(input.equalsIgnoreCase("No")) {
            System.out.println("You are under the age of 16, unfortunately you need to have a guardian/parent help you set up an account.");
        } else {
            System.out.println("Incorrect input, try again.");
        }
    }

    /**
     *
     * @return true if the user account is not closed, false if account is closed
     */
    public boolean requiresUser() {
        return false;
    }
}
