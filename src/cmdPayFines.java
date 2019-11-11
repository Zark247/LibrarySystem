import java.util.ArrayList;
import java.util.Scanner;

/**
 * cmdPayFines.java - Command to "pay fines"
 * @author Team Proxi
 */
public class cmdPayFines implements Command {
    /**
     * Method to "pay fine"
     */
    public void execute(String arg,User u) {
    	if(arg.toUpperCase().equals("ALL")) { //If payfine all, pay all fines.
    		System.out.println("Paying all fines...");
    		System.out.println(u.fineTotal() + " have been paid. Thank you");
    		u.setFines(new ArrayList<Fee>());
    	} else {
    		String fineTitle = null;
    		for(Media m:LibrarySystem.getInstance().inventoryNoCopies()) {
        		if(m.getTitle().toUpperCase().equals(arg.toUpperCase())) { //Otherwise, check for fine by title of media.
        			fineTitle = m.title;
        		}
        	}
        	if(fineTitle != null) {
        		for(Fee f:u.getFines()) {
        			if(f.getMedia().getTitle().equals(fineTitle)) {
        				Scanner s = LibraryDriver.s;
        				System.out.println("Your fine for " + fineTitle + " totals $" + f.getTotal());
        				System.out.println("How much would you like to pay towards this fine?"); //Prompt user to input amount to pay.
        				double payment = s.nextDouble();
        				u.payFine(f, payment);
        				s.nextLine();
        				return;
        			}
        		}
        	} else {
        		System.out.println("There is no fine for that media assosciated with your account.");
        	}
    	}
    }

    /**
     *
     * @return true if the user account is not closed, false if account is closed
     */
    public boolean requiresUser() {
    	return true;
    }
}
