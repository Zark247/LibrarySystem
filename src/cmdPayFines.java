import java.util.Scanner;

/**
 * Command to "pay fines"
 */
public class cmdPayFines implements Command {
    /**
     * Method to "pay fine"
     */
    public void execute(String arg,User u) {
    	String fineTitle = null;
    	for(Media m:LibrarySystem.getInstance().inventoryNoCopies()) {
    		if(m.getTitle().toUpperCase().equals(arg.toUpperCase())) {
    			fineTitle = m.title;
    		}
    	}
    	if(fineTitle != null) {
    		for(Fee f:u.getFines()) {
    			if(f.getMedia().getTitle().equals(fineTitle)) {
    				Scanner s = TempDriver.s;
    				System.out.println("Your fine for " + fineTitle + " totals $" + f.getTotal());
    				System.out.println("How much would you like to pay towards this fine?");
    				double payment = s.nextDouble();
    				f.pay(payment);
    			}
    		}
    	} else {
    		System.out.println("There is no fine for that media assosciated with your account.");
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
