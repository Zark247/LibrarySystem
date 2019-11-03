/**
 * Command to "pay fines"
 */
public class PayFines implements Command {

    /**
     * Variables
     */
    private User user;
    private Fee fee;
    private double amount;

    /**
     *
     * @param aUser tied to user
     * @param aFee tied to fee
     * @param anAmount tied to amount
     */
    public PayFines(User aUser, Fee aFee, double anAmount) {
        this.user = aUser;
        this.fee = aFee;
        this.amount = anAmount;
    }

    /**
     * Method to "pay fine"
     */
    public void execute() {
        user.payFine(fee, amount);
    }

    /**
     *
     * @return true if the user account is not closed, false if account is closed
     */
    public boolean requiresUser() {
        if(!user.isClosed()) {
            return true;
        } else {
            return false;
        }
    }
}
