package net.luminis.axondemo;

import net.luminis.axondemo.command.PayRental;
import net.luminis.axondemo.command.RequestRental;
import net.luminis.axondemo.command.ReturnBike;
import net.luminis.axondemo.projection.RentalOverview;
import net.luminis.axondemo.projection.RentalOverviewQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@SpringBootApplication
public class BikeRentalResource {

    private final Replayer replayer;
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @Autowired
    public BikeRentalResource(CommandGateway commandGateway,
                              QueryGateway queryGateway,
                              Replayer replayer) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
        this.replayer = replayer;
    }

    @PostMapping("/requestRental")
    void requestRental(String id, Integer fee) {
        commandGateway.sendAndWait(new RequestRental(id, fee));
    }

    @PostMapping("/payRental")
    void payRental(String id, Integer amount) {
        commandGateway.sendAndWait(new PayRental(id, amount));
    }

    @PostMapping("/returnBike")
    void payRental(String id) {
        commandGateway.sendAndWait(new ReturnBike(id));
    }

    @PostMapping("/replay")
    void replay() {
        replayer.replay();
    }

    @GetMapping("/listNotReturned")
    List<RentalOverview> query() throws ExecutionException, InterruptedException {
        return queryGateway.query(new RentalOverviewQuery(true), ResponseTypes.multipleInstancesOf(RentalOverview.class)).get();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BikeRentalResource.class, args);
    }
}
