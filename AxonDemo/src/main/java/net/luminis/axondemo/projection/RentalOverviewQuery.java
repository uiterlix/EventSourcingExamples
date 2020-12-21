package net.luminis.axondemo.projection;

public class RentalOverviewQuery {

    private final Boolean notReturned;

    public RentalOverviewQuery(Boolean notReturned) {
        this.notReturned = notReturned;
    }

    public Boolean getNotReturned() {
        return notReturned;
    }
}
