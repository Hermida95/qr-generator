package com.Miguel.qr_generator.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service // Le decimos a Spring: "Esta clase es un Servicio"
public class QrService {

    // Definimos el tamaño del QR
    private static final int QR_WIDTH = 250;
    private static final int QR_HEIGHT = 250;

    public byte[] generateQrCode(String url) throws WriterException, IOException {

        // Si la URL está vacía, lanzamos un error
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("La URL no puede estar vacía");
        }

        // Usamos el "escritor" de QR de la librería zxing
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        // "encode" es el método que genera la matriz de puntos (bits)
        // Le decimos el texto (la url), el formato (QR), y el tamaño
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT);

        // Necesitamos convertir esa matriz de bits en una imagen (PNG)
        // Usamos un "flujo de bytes en memoria" para no tener que crear un archivo físico
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();

        // Escribimos la matriz de bits como una imagen PNG en nuestro flujo de bytes
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

        // Devolvemos la imagen como un array de bytes
        return pngOutputStream.toByteArray();
    }
}