package net.luminis.axondemo.event;

public class BikeReturned {
    private final String id;

    public BikeReturned(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
