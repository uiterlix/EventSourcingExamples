package net.luminis.axondemo.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class PayRental {

    @TargetAggregateIdentifier
    private final String id;
    private final Integer amount;

    public PayRental(String id, Integer amount) {
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
