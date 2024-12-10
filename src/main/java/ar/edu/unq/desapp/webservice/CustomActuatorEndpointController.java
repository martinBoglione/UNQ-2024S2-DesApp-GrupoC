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
        return """
                     .-.            .-.
                    /   \\          /   \\
                   |   _ \\        / _   |
                   ;  | \\ \\      / / |  ;
                    \\  \\ \\ \\_.._/ / /  /
                     '. '.;'    ';,' .'
                       './ _    _ \\.'
                       .'  a __ a  '.
                  '--./ _,   \\/   ,_ \\.--'
                 ----|   \\   /\\   /   |----
                  .--'\\   '-'  '-'    /'--.
                      _>.__  -- _.-  `;
                    .' _     __/     _/
                   /    '.,:\".-\\    /:,
                   |      \\.'   `\"\"`'.\\\\
                    '-,.__/  _   .-.  ;|_
                    /` `|| _/ `\\/_  \\_|| `\\
                   |    ||/ \\-./` \\ / ||   |
                    \\   ||__/__|___|__||  /
                     \\_ |_Happy Easter_| /
                    .'  \\ =  _= _ = _= /`\\
                   /     `-;----=--;--'   \\
                   \\    _.-'        '.    /
                    `\"\"`              `\"\"` """;
    }

}
