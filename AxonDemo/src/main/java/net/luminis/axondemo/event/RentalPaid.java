package net.luminis.axondemo.event;

public class RentalPaid {
    private final String id;
    private final Integer amount;

    public RentalPaid(String id, Integer amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }
}
