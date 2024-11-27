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
        return "     .-.            .-.\n" +
                "    /   \\          /   \\\n" +
                "   |   _ \\        / _   |\n" +
                "   ;  | \\ \\      / / |  ;\n" +
                "    \\  \\ \\ \\_.._/ / /  /\n" +
                "     '. '.;'    ';,' .'\n" +
                "       './ _    _ \\.'\n" +
                "       .'  a __ a  '.\n" +
                "  '--./ _,   \\/   ,_ \\.--'\n" +
                " ----|   \\   /\\   /   |----\n" +
                "  .--'\\   '-'  '-'    /'--.\n" +
                "      _>.__  -- _.-  `;\n" +
                "    .' _     __/     _/\n" +
                "   /    '.,:\".-\\    /:,\n" +
                "   |      \\.'   `\"\"`'.\\\\\n" +
                "    '-,.__/  _   .-.  ;|_\n" +
                "    /` `|| _/ `\\/_  \\_|| `\\\n" +
                "   |    ||/ \\-./` \\ / ||   |\n" +
                "    \\   ||__/__|___|__||  /\n" +
                "     \\_ |_Happy Easter_| /\n" +
                "    .'  \\ =  _= _ = _= /`\\\n" +
                "   /     `-;----=--;--'   \\\n" +
                "   \\    _.-'        '.    /\n" +
                "    `\"\"`              `\"\"`\n";
    }

}
