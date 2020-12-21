package net.luminis.axondemo.event;

public class RentalRequested {
    private final String id;
    private final Integer rentalFee;

    public RentalRequested(String id, Integer rentalFee) {
        this.id = id;
        this.rentalFee = rentalFee;
    }

    public String getId() {
        return id;
    }

    public Integer getRentalFee() {
        return rentalFee;
    }
}
