/**
 * Fee.java - Class representing fees.  Have an associated media member.
 *
 */
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

    public void setTotal(double total) {
		this.total = total;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

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
    
    public String toString() {
    	return "Fine for " + this.media.title + ": $" + this.total;
    }
    
    public int returnId() {
    	return this.id;
    }

	public static int getFEE_COUNT() {
		return FEE_COUNT;
	}

	public double getMax() {
		return max;
	}

	public double getFine() {
		return fine;
	}

	public double getTotal() {
		return total;
	}

	public Media getMedia() {
		return media;
	}
}
