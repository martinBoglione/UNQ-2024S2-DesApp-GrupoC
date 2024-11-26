# Universidad Nacional de Quilmes

## Desarrollo de Aplicaciones - 2c 2024

## Trabajo Práctico - Crypto Exchange

[![Java CI with Gradle](https://github.com/martinBoglione/UNQ-2024S2-DesApp-GrupoC/actions/workflows/build.yml/badge.svg)](https://github.com/martinBoglione/UNQ-2024S2-DesApp-GrupoC/actions/workflows/build.yml) 
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=martinBoglione_UNQ-202402-grupoG&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=martinBoglione_UNQ-202402-grupoG) 
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=martinBoglione_UNQ-202402-grupoG&metric=coverage)](https://sonarcloud.io/summary/new_code?id=martinBoglione_UNQ-202402-grupoG)

### Grupo C

|Nombre|Legajo|GitHub|
|---|---|---|
|Bolgione Martín|34488|@martinBoglione|
|Malsam Leandro|43779|@leatex|

Enunciado: <https://docs.google.com/document/d/1Imga3QhLWMd0fb5n8EFBmQnS7HtxFJ2sXOLgzfSEX0k/edit#heading=h.bwfobs81j2q4>
___

Swagger: <http://localhost:8080/swagger-ui/index.html>  
Actuator: <http://localhost:8080/actuator>  
H2: <http://localhost:8080/h2-console/>

:green_circle: :yellow_circle: :red_circle:

### :arrow_forward: :arrow_forward: **Entrega Nro** :one:

|Requerimiento| Estado |
|---|----------------|
|:radioactive: **Core**| |
|Creación de repositorios GitHub| :green_circle: |
|Configuración en GitHubActions| :green_circle: |
|Build corriendo y SUCCESS| :green_circle: |
|SonarCloud (Registrar el proyecto Backend)| :green_circle: |
|~~Deploy automático utlizando HEROKU o cualquiera similar para deploy Automático~~| :red_circle: |
|TAG en GitHub y Confeccionar Release Notes de entrega 1| :green_circle: |
|Clean Code según la materia (todo en Ingles)| :green_circle: |
|Configuracion de https://swagger.io/ en el back-API (v3)|:green_circle:|
|:shield: **Modelo**| |
|Implementar modelo completo|:green_circle:|
|Testing automático unitario según las pautas de la materia|:yellow_circle:|
|:toolbox: **Funcionalidad**| |
|Proveer servicio de registracion de usuario (punto 1)|:green_circle:|

### :arrow_forward: :arrow_forward: **Entrega Nro** :two:

|Requerimiento|Estado|
|---|---|
|:radioactive: **Core**| |
|Estado de build en "Verde"|:green_circle:|
|Utilizar HSQLDB para persistir datos (opcion H2)|:green_circle:|
|Crear datos de prueba cuando levanta la aplicación|:green_circle:|
|Documentation de Endpoints (APIs) con Swagger (v3)|:green_circle:|
|TAG en GitHub y Confeccionar Release Notes de entrega 2|:green_circle:|
|Implementar JOB de Coverage|:green_circle:|
|Testing de endpoints usando Postman|:yellow_circle:|
|:toolbox: **Funcionalidad**| |
|Listar cotizacion de criptoactivos|:yellow_circle:|
|Permitir que un usuario exprese su intención de compra/venta|:green_circle:|
|Construir un listado donde se muestran las intenciones activas de compra/venta|:green_circle:|
|Procesar la transacción informada por un usuario|:yellow_circle:|
|Informar al usuario el volumen operado de cripto activos entre dos fechas|:yellow_circle:|
|Testing integral de 2 controllers (end-to-end)|:red_circle:|

### :arrow_forward: :arrow_forward: **Entrega Nro** :three:

|Requerimiento| Estado         |
|---|----------------|
|:radioactive: **Core**|                |
|Crear un test de arquitectura| :green_circle: |
|Auditoria de Web-Services. Loguear <timestamp,user,operación/metodo, parámetros, tiempoDeEjecicion> de los servicios publicados con Spring utilizando Log4j/logback| :green_circle: |
|TAG en GitHub y Confeccionar Release Notes de entrega 3|:green_circle:|
|Configurar <https://www.baeldung.com/spring-boot-prometheus> (Metricas)| :green_circle: |
|Configurar <https://www.baeldung.com/spring-boot-actuators> (Endpoints de Monitoreo)| :green_circle: |
|:toolbox: **Funcionalidad**|                |
|Segurizar el acceso a la API (JWT)| :green_circle: |
|Mostrar las cotizaciones de las últimas 24hs para un cripto activo dado| :green_circle: |
|Listado de cotizaciones (alta performance - implementar cache)| :red_circle:   |
