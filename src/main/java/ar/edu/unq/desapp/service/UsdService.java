package ar.edu.unq.desapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class UsdService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String DOLAR_URL = "https://dolarapi.com/v1/dolares/blue";

    // Clase interna para mapear la respuesta JSON
    private static class DolarResponse {
        private String moneda;
        private String casa;
        private String nombre;
        private Double compra;
        private Double venta;
        private String fechaActualizacion;

        // Getters y setters
        public Double getCompra() {
            return compra;
        }
    }

    public Double getDolarValue() {
        // Realiza la llamada a la API y mapea la respuesta a la clase DolarResponse
        DolarResponse response = restTemplate.getForObject(DOLAR_URL, DolarResponse.class);
        if (response != null) {
            return response.getCompra();
        }
        throw new RuntimeException("No se pudo obtener el valor del dólar");
    }
}
