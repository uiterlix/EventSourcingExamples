package net.luminis.axondemo;

import net.luminis.axondemo.projection.RentalOverviewProjection;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Replayer {

    private final EventProcessingConfiguration configuration;
    private final RentalOverviewProjection projection;

    @Autowired
    public Replayer(EventProcessingConfiguration configuration, RentalOverviewProjection projection) {
        this.configuration = configuration;
        this.projection = projection;
    }

    public void replay() {
        System.out.println("replaying");
        projection.reset();
        configuration.eventProcessor("net.luminis.axondemo.projection", TrackingEventProcessor.class).ifPresent(processor -> {
            processor.shutDown();
            processor.resetTokens();
            processor.start();
        });
        System.out.println("replayed");
    }
}
