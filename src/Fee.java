/**
 * Fines User per media daily
 */
public class Fee {

    /**
     * Variables
     */
    private double max, fine, total;
    private Media media;

    /**
     *
     * @param aMedia Declares media to aMedia
     * Constructor for variables
     */
    public Fee(Media aMedia) {
        total = 0.00;
        fine = 0.10;
        max = 15.00;
        media = aMedia;
    }

    /**
     * Method for increasing the total daily
     */
    public void dailyFineIncrease() {
        if(total+fine > max) {
            // System.out.println("Max fine reached");
        } else {  // total + fine < max
            total += fine;
        }
    }

    /**
     *
     * @param amount The user is asked what amount they would like to pay towards their total
     */
    public void pay(double amount) {
        double tempTotal = total;
        if(amount > total) {
            System.out.println("You cannot pay more than your current total."
            +"\nTotal fees: " + tempTotal);
        } else {  // amount <= total
            total -= amount;
            System.out.println("Total fees: " + tempTotal + " , Payment: " + amount
            +"\nNew total after payment: "+ total);
        }
    }

    /**
     *
     * @return total fine for specifc Media
     */
    public double getTotal(){
    	return this.total;
    }

    /**
     *
     * @return the Media that is associated with the total fine
     */
    public Media getMedia(){
    	return this.media;
    }

    /**
     *
     * @return Stops charging fines to this specific Media when it is returned
     */
    public Media returnMedia() {
        return returnMedia();
    }
}
