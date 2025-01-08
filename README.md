# API para Gestión de Clientes en Java

Esta API está desarrollada en Java utilizando Spring Boot y se conecta a una base de datos PostgreSQL (la base de datos es opcional y se puede utilizar con cualquier gestor agergando las dependencias correspondientes al pom). Su función principal es manejar la persistencia de datos de clientes, permitiendo el registro individual, la carga masiva y la obtención de información para presentarla en el front-end.

## Características

- **Conexión con PostgreSQL**: Se utiliza Hibernate como ORM, lo que permite una configuración de tipo code-first donde las tablas se generan automáticamente.
- **Endpoints principales**:
  - Registro único de clientes.
  - Carga masiva de clientes.
  - Obtención de la lista de clientes.
- **Control de CORS**: Es necesario configurar el `alloworigin` para permitir solicitudes desde el front-end (por defecto `http://localhost:4200`).

## Estructura del Proyecto

El proyecto sigue una estructura básica organizada por paquetes:

```
com.mx.prueba
├── config          # Configuraciones generales (CORS)
├── controller      # Controladores REST (ej. ClientesController)
├── entity          # Entidades de Hibernate (mapeo de tablas)
├── model           # Modelos adicionales para manejar datos
├── repository      # Repositorios para interacción con la base de datos
```

## Prerrequisitos

Antes de ejecutar la API, asegúrate de tener:

- **Java** (JDK 17 o superior)
- **Maven** (para gestionar dependencias)
- **PostgreSQL** (base de datos opcional configurada y corriendo)

## Configuración Inicial

1. Configura el archivo `application.properties` ubicado en `src/main/resources/` con los detalles de tu base de datos PostgreSQL:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/nombre_base_datos
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

2. Verifica que el CORS permita solicitudes desde el front-end configurando `alloworigin` en la clase correspondiente en el paquete `com.mx.prueba.config`:

   ```java
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") <---- Cambiar para el Front-End
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Content-Type", "Authorization")
                .allowCredentials(true)
                .maxAge(3600);
    }
   ```

## Endpoints Principales

Todos los endpoints se encuentran en el controlador `ClientesController`. A continuación, un resumen:

- **GET /clientes/obtenerClientes**: Obtiene la lista de clientes.
- **POST /clientes/agregarCliente**: Registra un cliente de manera individual.
- **POST /clientes/cargaMasiva**: Realiza la carga masiva de clientes desde un archivo con extensión .txt.

## Dependencias Principales

El archivo `pom.xml` incluye las siguientes dependencias esenciales:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```
