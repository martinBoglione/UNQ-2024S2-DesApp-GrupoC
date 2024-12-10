package ar.edu.unq.desapp.webservice;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
@Endpoint(id = "easteregg")
public class CustomActuatorEndpointController {

    @ReadOperation
    public String customEndpoint() {
        return " ¯\\_(ツ)_/¯  ||  (●'◡'●)  ||  (❁´◡`❁)  ||  ༼ つ ◕_◕ ༽つ  ";
    }

}
