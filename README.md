# Universidad Nacional de Quilmes

## Desarrollo de Aplicaciones - 2c 2024

## Trabajo Práctico - Crypto Exchange

[![SonarCloud](https://github.com/martinBoglione/UNQ-2024S2-DesApp-GrupoC/actions/workflows/build.yml/badge.svg)](https://github.com/martinBoglione/UNQ-2024S2-DesApp-GrupoC/actions/workflows/build.yml)

### Grupo C

|Nombre|Legajo|GitHub|
|---|---|---|
|Bolgione Martín|34488|@martinBoglione|
|Malsam Leandro|43779|@leatex|

Enunciado: <https://docs.google.com/document/d/1Imga3QhLWMd0fb5n8EFBmQnS7HtxFJ2sXOLgzfSEX0k/edit#heading=h.bwfobs81j2q4>
___

Swagger: <http://localhost:8080/swagger-ui/index.html>  
H2: <http://localhost:8080/h2-console/>

:green_circle: :yellow_circle: :red_circle:

|Entrega Nro 1| |
|---|---|
| **-- Core**|:white_circle:|
|Creación de repositorios GitHub|:green_circle:|
|Configuración en GitHubActions|:green_circle:|
|Build corriendo y SUCCESS|:green_circle:|
|SonarCloud (Registrar el proyecto Backend)|:green_circle:|
|~~Deploy automático utlizando HEROKU o cualquiera similar para deploy Automático~~|:red_circle:|
|TAG en GitHub y Confeccionar Release Notes de entrega 1|:green_circle:|
|Clean Code según la materia (todo en Ingles)|:green_circle:|
|Configuracion de https://swagger.io/ en el back-API (v3)|:green_circle:|
|**-- Modelo**|:white_circle:|
|Implementar modelo completo|:green_circle:|
|Testing automático unitario según las pautas de la materia|:red_circle:|
|**-- Funcionalidad**|:white_circle:|
|Proveer servicio de registracion de usuario (punto 1)|:green_circle:|

|Entrega Nro 2| |
|---|-|
|**-- Core**|:white_circle:|
|Estado de build en "Verde"|:yellow_circle:|
|Utilizar HSQLDB para persistir datos (opcion H2)|:green_circle:|
|Crear datos de prueba cuando levanta la aplicación| |
|Documentation de Endpoints (APIs) con Swagger (v3)|:green_circle:|
|TAG en GitHub y Confeccionar Release Notes de entrega 2| |
|Implementar JOB de Coverage| |
|Testing de endpoints usando Postman|:yellow_circle:|
|**-- Funcionalidad**|:white_circle:|
|Listar cotizacion de criptoactivos| |
|Permitir que un usuario exprese su intención de compra/venta| |
|Construir un listado donde se muestran las intenciones activas de compra/venta| |
|Procesar la transacción informada por un usuario| |
|Informar al usuario el volumen operado de cripto activos entre dos fechas| |
|Testing integral de 2 controllers (end-to-end)| |

|Entrega Nro 3| |
|---|---|
|**-- Core**| :white_circle: |
|Crear un test de arquitectura|:green_circle:|
|Auditoria de Web-Services. Loguear <timestamp,user,operación/metodo, parámetros, tiempoDeEjecicion> de los servicios publicados con Spring utilizando Log4j/logback|:green_circle:|
|TAG en GitHub y Confeccionar Release Notes de entrega 3|                |
|Configurar <https://www.baeldung.com/spring-boot-prometheus> (Metricas)|                |
|Configurar <https://www.baeldung.com/spring-boot-actuators> (Endpoints de Monitoreo)|                |
|**-- Funcionalidad**| :white_circle: |
|Segurizar el acceso a la API (JWT)|                |
|Mostrar las cotizaciones de las últimas 24hs para un cripto activo dado|                |
|Listado de cotizaciones (alta performance - implementar cache)|                |
