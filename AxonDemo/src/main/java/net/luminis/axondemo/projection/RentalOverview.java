package net.luminis.axondemo.projection;

public class RentalOverview {
    private final String id;
    private final Integer rentalFee;
    private final Boolean returned;

    // hey bram!
    // hey!
    // cool, slow chat via code


    public RentalOverview(String id, Integer rentalFee, Boolean returned) {
        this.id = id;
        this.rentalFee = rentalFee;
        this.returned = returned;
    }

    public String getId() {
        return id;
    }

    public Integer getRentalFee() {
        return rentalFee;
    }

    public Boolean getReturned() {
        return returned;
    }

    public RentalOverview handIn() {
        return new RentalOverview(id, rentalFee, true);
    }

    @Override
    public String toString() {
        return "RentalOverview{" +
                "id='" + id + '\'' +
                ", rentalFee=" + rentalFee +
                ", returned=" + returned +
                '}';
    }
}
