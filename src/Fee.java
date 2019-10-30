

public class Fee {
	public static int FEE_COUNT = 0;
    private double max, fine, total;
    private Media media;
    private int id;

    public Fee(Media aMedia) {
        FEE_COUNT++;
    	total = 0.00;
        fine = 0.10;
        max = 15.00;
        media = aMedia;
        this.id = FEE_COUNT;
        LibrarySystem.getInstance().fees.add(this);
    }

    public void dailyFineIncrease() {
        if(total+fine > max) {
            // System.out.println("Max fine reached");
        } else {  // total + fine < max
            total += fine;
        }
    }

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

    public Media returnMedia() {
        return returnMedia();
    }
    
    public String toString() {
    	return "Fine for " + this.media.title + ": $" + this.total;
    }
    
    public int returnId() {
    	return this.id;
    }
}
