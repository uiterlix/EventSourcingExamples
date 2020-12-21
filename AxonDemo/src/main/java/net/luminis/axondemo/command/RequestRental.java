package net.luminis.axondemo.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RequestRental {

    @TargetAggregateIdentifier
    private final String id;
    private final Integer rentalFee;

    public RequestRental(String id, Integer rentalFee) {
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
