package ar.edu.unq.desapp.webservice;

import ar.edu.unq.desapp.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Transactions", description = "Orders (intentions) & Operations APIs")
public class TransactionsController {

    @Operation(summary = "Get active orders")
    @GetMapping("/orders")
    public List<Order> getActiveOrders() {
        /* TODO
        *  5. Construir un listado donde se muestran las intenciones activas de compra/venta, es decir, las intenciones que expresan la intención de comprar o vender de un usuario.
            Día y hora del momento en que se creó la intención de compra/venta
            Criptoactivo
            Cantidad nominal del Cripto Activo
            Cotización del Cripto Activo expresa en la intención de compra/venta
            Monto de la operación en pesos ARG
            Usuario (Nombre/Apellido)
            Cantidad de operaciones (realizadas por el usuario)
            Reputación, se calcula como cantidad de puntos otorgados / cantidad de operaciones. Si la persona no ha operado se muestra “Sin operaciones”.
            Direccion de envio:
                Si la operación es venta, debe mostrar un CVU para que el usuario 2 haga la transferencia
                Si la operación es compra,  debe mostrar la dirección de la billetera de CriptoActivos
         */
        return List.of();
    }

    @Operation(summary = "Register order")
    @PostMapping("/orders/create")
    public Order createOrder(@RequestBody Order order) {
        /* TODO
        *   4. Permitir que un usuario exprese su intención de compra/venta
            Criptoactivo
            Cantidad nominal del Cripto Activo
            Cotización del Cripto Activo
            Monto de la operación en pesos ARG
            Usuario email
            Operación: [COMPRA|VENTA]
        */
        return new Order();
    }

    @Operation(summary = "Cancel order (forced by user)")
    @PatchMapping("/orders/cancel/{id}")
    public void cancelOrder(@PathVariable Long id) {
        /* TODO
          Revisar que sea el método HTTP correcto.
          Cancela una orden activa
        * */
        return;
    }

    @Operation(summary = "User (counterparty) wants to fill an order (transact)")
    @PostMapping("/orders/fill/{id}")
    public ResponseEntity<String> fillOrder(@PathVariable Long id) {
        /* TODO
            Se busca la orden {id}, si pasa los controles se crea una Transaction, y se cambia el estado de la orden.

        	6. Procesar la transacción informada por un usuario
            Definir bien qué campos deberían estar en el body (lo que dice el punto 6 está mal).
        * */

        return ResponseEntity.ok("ok");
    }

    @Operation(summary = "User (issuer) confirms transaction completed")
    @PatchMapping("/orders/confirm/{id}")
    public ResponseEntity<String> transactionCompleted(@PathVariable Long id) {
        /* TODO
            Se busca la Transaction con {id} y se confirma junto con la orden asociada.

        	6. Procesar la transacción informada por un usuario
            Definir bien qué campos deberían estar en el body (lo que dice el punto 6 está mal).
        * */

        return ResponseEntity.ok("ok");
    }

}
