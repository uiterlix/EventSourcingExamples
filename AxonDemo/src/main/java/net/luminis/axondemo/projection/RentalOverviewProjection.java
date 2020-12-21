package net.luminis.axondemo.projection;

import net.luminis.axondemo.event.BikeReturned;
import net.luminis.axondemo.event.RentalRequested;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
public class RentalOverviewProjection {
    private final List<RentalOverview> rentals = new CopyOnWriteArrayList<>();

    @EventHandler
    public void on(RentalRequested evt) {
        System.out.println(">>> Rental requested");
        RentalOverview rentalOverview = new RentalOverview(evt.getId(), evt.getRentalFee(), false);
        rentals.add(rentalOverview);
    }

    @EventHandler
    public void on(BikeReturned evt) {
        System.out.println(">>> Bike returned");
        rentals.stream()
                .filter(cs -> evt.getId().equals(cs.getId()))
                .findFirst()
                .ifPresent(rentalOverview -> {
                    RentalOverview updatedRentalOverview = rentalOverview.handIn();
                    rentals.remove(rentalOverview);
                    rentals.add(updatedRentalOverview);
                });
    }

    @QueryHandler
    public List<RentalOverview> fetch(RentalOverviewQuery query) {
        return rentals.stream()
                .filter(rental -> query.getNotReturned() ? !rental.getReturned() : true)
                .collect(Collectors.toList());
    }

    @ResetHandler
    public void reset() {
        rentals.clear();
    }

    public int count() {
        return rentals.size();
    }
}
