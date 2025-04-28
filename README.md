# API de Cálculo y Registro de Historial

## Descripción

Servicio REST que permite realizar cálculos aplicando un porcentaje dinámico obtenido de un servicio externo (mockeado), almacenar en caché este porcentaje, y registrar el historial de llamadas realizadas a la API en una base de datos PostgreSQL.

## Tecnologías utilizadas
* Java 21
* Spring Boot 
* PostgreSQL (en Docker) 
* Docker / Docker Compose 
* Postman (para pruebas de endpoints) 
* Swagger (para documentación de la API)
* JUnit 5 y Mockito (para tests unitarios)

---

## Requisitos previos
* Docker y Docker Compose instalados
  * En Windows/Mac: se recomienda Docker Desktop
   * En Linux: asegurarse de tener ambos instalados.
* Java 21 (opcional, solo si se desea correr la aplicación fuera de Docker)

---

## Instalación y ejecución

### 1. Clonar el repositorio
```bash
git clone https://github.com/renzoBascougnet/change-backend.git
cd change-backend 
```

### 2. Levantar los servicios con Docker Compose

   Desde la raíz del proyecto, ejecutar:

```bash
docker-compose up
```
Esto hará que Docker:
* Construya la imagen de la API.
* Descargue y levante una instancia de PostgreSQL.
* Ejecute ambos servicios en contenedores separados.

---

### Acceso a los servicios

* API disponible en: http://localhost:8080
* Swagger UI en: http://localhost:8080/swagger-ui/index.html
* Base de datos PostgreSQL:
  * Host: localhost
  * Puerto: 5432
  * Base de datos: challenge
  * Usuario: postgres
  * Contraseña: postgres

---

## Endpoints disponibles

### 1. /api/calculation

* Método: GET
* Descripción: Realiza un cálculo de suma entre dos números (num1, num2) y le aplica un porcentaje.
* Funcionamiento:
  * Suma num1 + num2.
  * Obtiene el porcentaje de un servicio externo (mock fijo).
  * Si el servicio falla, utiliza el valor cacheado (durante 30 minutos).
  * Si no hay valor cacheado, retorna error 404.
  * El cálculo final es:<br>
    **resultado = (num1 + num2) × (1 + (porcentaje / 100))**
* Parámetros:
  * `num1` (Double) - Primer número<br>
  * `num2` (Double) - Segundo número<br>
* Ejemplo de request:
```
GET /api/calculation?num1=10&num2=20
```
* Ejemplo de respuesta (porcentaje 20%):
```json
{
    "result": 36.0
}
```
(Ya que (10 + 20) × 1.2 = 36.0)
* Ejemplo de respuesta en caso de error:
```json
{
  "status": 404,
  "message": "No se pudo obtener el porcentaje ni hay valor cacheado.",
  "timestamp": 1745762068966
}
```

### 2. /api/request-history
* Método: GET
* Descripción: Obtiene el historial de llamadas realizadas a la API.
* Parámetros:
  * `page` (Integer) - Número de página
  * `size` (Integer) - Cantidad de elementos por página
* Ejemplo de request:
```
GET /api/request-history?page=0&size=10
```
* Ejemplo de respuesta:
```json
{
  "content": [
    {
      "timestamp": "2025-04-27T14:26:21.087071",
      "endpoint": "/api/request-history",
      "parameters": "page=0&size=5",
      "response": "{\"content\":[{\"id\":125,\"timestamp\":\"2025-04-27T14:26:14.545999\",\"endpoint\":\"/api/calculation\",\"parameters\":\"num1=23&num2=12\",\"response\":\"{\\\"status\\\":404,\\\"message\\\":\\\"No se pudo obtener el porcentaje ni hay valor cacheado\\\",\\\"timestamp\\\":1745763974539}\",\"error\":true},{\"id\":124,\"timestamp\":\"2025-04-27T14:23:50.089944\",\"endpoint\":\"/api/calculation\",\"parameters\":\"num1=23&num2=12\",\"response\":\"42.0\",\"error\":false},{\"id\":123,\"timestamp\":\"2025-04-27T14:23:42.440863\",\"endpoint\":\"/api/calculation\",\"parameters\":\"num1=23&num2=12\",\"response\":\"42.0\",\"error\":false},{\"id\":122,\"timestamp\":\"2025-04-27T14:23:16.08002\",\"endpoint\":\"/api/calculation\",\"parameters\":\"num1=23&num2=12\",\"response\":\"42.0\",\"error\":false},{\"id\":121,\"timestamp\":\"2025-04-27T14:23:12.512431\",\"endpoint\":\"/api/calculation\",\"parameters\":\"num1=23&num2=12\",\"response\":\"42.0\",\"error\":false}],\"pageable\":{\"pageNumber\":0,\"pageSize\":5,\"sort\":[{\"direction\":\"DESC\",\"property\":\"timestamp\",\"ignoreCase\":false,\"nullHandling\":\"NATIVE\",\"descending\":true,\"ascending\":false}],\"offset\":0,\"paged\":true,\"unpaged\":false},\"totalPages\":25,\"totalElements\":125,\"last\":false,\"numberOfElements\":5,\"first\":true,\"size\":5,\"number\":0,\"sort\":[{\"direction\":\"DESC\",\"property\":\"timestamp\",\"ignoreCase\":false,\"nullHandling\":\"NATIVE\",\"descending\":true,\"ascending\":false}],\"empty\":false}",
      "error": false
    },
    {
      "timestamp": "2025-04-27T14:26:14.545999",
      "endpoint": "/api/calculation",
      "parameters": "num1=23&num2=12",
      "response": "{\"status\":404,\"message\":\"No se pudo obtener el porcentaje ni hay valor cacheado\",\"timestamp\":1745763974539}",
      "error": true
    },
    {
      "timestamp": "2025-04-27T14:23:50.089944",
      "endpoint": "/api/calculation",
      "parameters": "num1=23&num2=12",
      "response": "42.0",
      "error": false
    },
    {
      "timestamp": "2025-04-27T14:23:42.440863",
      "endpoint": "/api/calculation",
      "parameters": "num1=23&num2=12",
      "response": "42.0",
      "error": false
    },
    {
      "timestamp": "2025-04-27T14:23:16.08002",
      "endpoint": "/api/calculation",
      "parameters": "num1=23&num2=12",
      "response": "42.0",
      "error": false
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 5,
    "sort": [
      {
        "direction": "DESC",
        "property": "timestamp",
        "ignoreCase": false,
        "nullHandling": "NATIVE",
        "descending": true,
        "ascending": false
      }
    ],
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 26,
  "totalElements": 126,
  "last": false,
  "numberOfElements": 5,
  "first": true,
  "size": 5,
  "number": 0,
  "sort": [
    {
      "direction": "DESC",
      "property": "timestamp",
      "ignoreCase": false,
      "nullHandling": "NATIVE",
      "descending": true,
      "ascending": false
    }
  ],
  "empty": false
}
```
* Ejemplo de respuesta en caso de error al consultar historial:
```json
{
  "status": 500,
  "message": "Se produjo un error inesperado.",
  "timestamp": 1745762068966
}
```

---

## Documentación de la API
* Swagger UI: http://localhost:8080/swagger-ui/index.html
* Colección Postman: Incluida en el proyecto (postman_collection.json) para pruebas manuales.

---

## Tests
El proyecto cuenta con una cobertura de tests unitarios desarrollados con JUnit 5 y Mockito, abarcando los principales componentes y flujos de la aplicación.

### Áreas testeadas:
#### 1. Servicio de Cálculo (CalculationService)
   * Cálculo correcto de suma + porcentaje.
   * Manejo del porcentaje proveniente de caché.
   * Manejo de errores cuando falla el servicio externo y no hay cache disponible.

#### 2. Módulo de Caché (PercentageCache)
   * Almacenamiento exitoso del porcentaje.
   * Expiración automática del valor en 30 minutos.
   * Recuperación del porcentaje cacheado ante caídas del servicio externo.

### Herramientas adicionales:
* Mockito para simular dependencias externas y escenarios de error. 

---

## Docker
* Dockerfile: Define cómo construir la imagen de la API.
* docker-compose.yml: Orquesta el levantamiento conjunto de los servicios:
  * API disponible en `localhost:8080`
  * Base de datos PostgreSQL disponible en `localhost:5432`

---

## Consideraciones adicionales
* El servicio de porcentaje es mockeado, simulando posibles fallas para probar cacheo y resiliencia.
* Se manejan los errores de manera adecuada usando @ExceptionHandler y retornando códigos HTTP correctos.
* Arquitectura del proyecto respetando capas (Controller, Service, Repository).
* Código siguiendo buenas prácticas: nombres descriptivos, separación clara de responsabilidades, principios SOLID.