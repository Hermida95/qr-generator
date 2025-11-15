# 🚀 Generador de QR - API en Spring Boot

Un microservicio simple creado con Java y Spring Boot que genera códigos QR a partir de una URL. 

Este proyecto fue creado como parte de mi aprendizaje en **Desarrollo de Aplicaciones Web (DAW)**, enfocado en la creación de APIs RESTful.

## 🛠️ Stack Tecnológico

* **Backend:** Java 17
* **Framework:** Spring Boot 3
* **Librería QR:** `com.google.zxing` ("Zebra Crossing")
* **Build Tool:** Maven

---

## 🏁 Cómo ejecutar el proyecto

1.  Clona este repositorio:
    ```bash
    git clone [https://github.com/TuUsuario/qr-generator-api.git](https://github.com/TuUsuario/qr-generator-api.git)
    ```
2.  Navega a la carpeta del proyecto:
    ```bash
    cd qr-generator-api
    ```
3.  Ejecútalo usando el wrapper de Maven (requiere Java 17):
    ```bash
    ./mvnw spring-boot:run
    ```
4.  El servidor arrancará en `http://localhost:8080`.

---

## 📡 Endpoints de la API

### 1. Generar QR (GET)

Ideal para pruebas rápidas en el navegador.

* **Método:** `GET`
* **Endpoint:** `/api/v1/generate-qr`
* **Query Param:** `url`
* **Respuesta:** `image/png`

**Ejemplo (Navegador):**
```
http://localhost:8080/api/v1/generate-qr?url=[https://github.com](https://github.com)
```

### 2. Generar QR (POST)

El endpoint principal, diseñado para ser consumido por un frontend (JSON).

* **Método:** `POST`
* **Endpoint:** `/api/v1/generate-qr`
* **Respuesta:** `image/png`

**Request Body (JSON):**
```json
{
  "url": "[https://www.linkedin.com/in/tu-perfil](https://www.linkedin.com/in/tu-perfil)"
}
```

**Ejemplo (curl):**
```bash
curl -X POST http://localhost:8080/api/v1/generate-qr \
     -H "Content-Type: application/json" \
     -d '{"url":"[https://google.com](https://google.com)"}' \
     --output mi_qr.png
```
