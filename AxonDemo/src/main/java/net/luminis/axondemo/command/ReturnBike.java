package net.luminis.axondemo.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ReturnBike {

    @TargetAggregateIdentifier
    private final String id;

    public ReturnBike(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
