package com.Miguel.qr_generator.controller;

// Imports necesarios
import com.Miguel.qr_generator.dto.UrlRequest;
import com.Miguel.qr_generator.service.QrService;
import com.google.zxing.WriterException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class QrController {

    // 1. INYECTAMOS EL SERVICIO
    // "final" significa que debe ser inicializado y no puede cambiar
    private final QrService qrService;

    // 2. CONSTRUCTOR PARA LA INYECCIÓN DE DEPENDENCIAS
    // Spring verá que este constructor necesita un QrService,
    // buscará el @Service que creamos y lo "inyectará" (pasará) automáticamente.
    public QrController(QrService qrService) {
        this.qrService = qrService;
    }

    // 3. MODIFICAMOS NUESTRO ENDPOINT
    @PostMapping("/generate-qr")
    public ResponseEntity<byte[]> generateQr(@RequestBody UrlRequest request) {

        try {
            // 4. Obtenemos la URL de nuestro DTO
            String url = request.getUrl();

            // 5. Llamamos al servicio para que genere los bytes de la imagen
            byte[] qrImage = qrService.generateQrCode(url);

            // 6. Devolvemos la respuesta
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG) // Decimos que la respuesta es una imagen PNG
                    .body(qrImage); // Añadimos la imagen (bytes) al cuerpo de la respuesta

        } catch (WriterException | IOException e) {
            // Si algo sale mal (ej. la URL es inválida), devolvemos un error
            e.printStackTrace(); // Imprime el error en la consola del servidor
            return ResponseEntity.status(500).body(null); // Devuelve un error 500
        }
    }

    // ESTE ES EL NUEVO MÉTODO PARA PROBAR CON EL NAVEGADOR
@GetMapping("/generate-qr")
public ResponseEntity<byte[]> generateQrFromGet(
        @RequestParam("url") String url // ¡Aquí está la magia!
) {
    try {
        // La lógica es la misma, usamos el servicio
        byte[] qrImage = qrService.generateQrCode(url);

        // Devolvemos la imagen
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qrImage);

    } catch (WriterException | IOException e) {
        e.printStackTrace();
        return ResponseEntity.status(500).body(null);
    }
}
}