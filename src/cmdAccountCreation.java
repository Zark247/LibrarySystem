import java.util.Scanner;
//TODO: Need to save user information
/**
 * cmdAccountCreation.java - Command to create an account
 */
public class cmdAccountCreation implements Command {

    /**
     * //TODO: write explanation here
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
            System.out.println("What is your address? Enter in a format that matches: 000 Blank St. Ferguson, CA");
            String address = s.nextLine();
            System.out.println("Enter your email address: ");
            String eAddress = s.nextLine();
            System.out.println("Enter a telephone number that matches this format: 000-000-0000");
            String phoneNumber = s.nextLine();
            System.out.println("Enter the username you would like to use: ");
            String userName = s.nextLine();
            System.out.println("Enter the password you would like to use: ");
            String passWord = s.nextLine();
            int passwordLength = passWord.length();
            String x = "";
            for(int i = 0; i <passwordLength; ++i) {
                x+="X";
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
            //TODO: This is supposed to create a new user, but throws error saying can't be instantiated
            //User newUser = new User(name, birthday, address, eAddress, phoneNumber, userName, passWord, id);
            //LibrarySystem.getInstance().users.add(newUser);
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
