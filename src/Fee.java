package src;

public class Fee {
    private double max, fine, total;

    public Fee() {
        total = 0.00;
        fine = 0.10;
        max = 15.00;

    }
    public void dailyFineIncrease() {
        total+=fine;
    }

    public void pay(double amount) {
        total-=amount;
    }

    public Media returnMedia() {
        return returnMedia();
    }
}
