package net.luminis.axondemo;

import net.luminis.axondemo.command.PayRental;
import net.luminis.axondemo.command.RequestRental;
import net.luminis.axondemo.command.ReturnBike;
import net.luminis.axondemo.event.BikeReturned;
import net.luminis.axondemo.event.RentalPaid;
import net.luminis.axondemo.event.RentalRequested;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class BikeRental {
    @AggregateIdentifier
    private String id;
    private int rentalFee;
    private int paidAmount;

    public BikeRental() {
    }

    @CommandHandler
    public BikeRental(RequestRental cmd) {
        if(cmd.getRentalFee() <= 0) throw new IllegalArgumentException("amount <= 0");
        AggregateLifecycle.apply(new RentalRequested(cmd.getId(), cmd.getRentalFee()));
    }

    @EventSourcingHandler
    public void on(RentalRequested evt) {
        id = evt.getId();
        rentalFee = evt.getRentalFee();
    }

    @CommandHandler
    public void handle(PayRental cmd) {
        if (cmd.getAmount() < rentalFee) {
            throw new IllegalStateException("Amount paid is insufficient.");
        }
        AggregateLifecycle.apply(new RentalPaid(id, cmd.getAmount()));
    }

    @EventSourcingHandler
    public void on(RentalPaid evt) {
        this.paidAmount = evt.getAmount();
    }

    @CommandHandler
    public void handle(ReturnBike cmd) {
        if (paidAmount < rentalFee) {
            throw new IllegalStateException("Rental has not been paid.");
        }
        AggregateLifecycle.apply(new BikeReturned(id));
    }

    @EventSourcingHandler
    public void on(BikeReturned evt) {

    }
}
