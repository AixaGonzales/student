# Student

Proyecto con backend en Spring Boot y frontend en Angular para gestionar estudiantes.

## Estructura

```text
student/
|-- student-service/   # Backend Spring Boot
|-- student-frond/     # Frontend Angular
```

## Requisitos

- Java 21
- Maven
- Node.js 22 o superior
- npm
- Docker Desktop, solo si quieres ejecutar el backend con Docker

## Ejecutar backend local

En una terminal:

```bash
cd student-service
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

El backend se ejecuta en:

```text
http://localhost:8090
```

Endpoint principal:

```text
http://localhost:8090/v1/api/student
```

Con el perfil `dev` se usa la base de datos H2 en memoria.

Consola H2:

```text
http://localhost:8090/h2-console
```

Datos para H2:

```text
JDBC URL: jdbc:h2:mem:studentdb
User: sa
Password: password
```

## Ejecutar backend con Docker

En una terminal:

```bash
cd student-service
docker compose up -d
```

Servicios:

```text
Backend: http://localhost:8091
Nginx:   http://localhost:8092
MySQL:   localhost:3306
```

Para apagar los contenedores:

```bash
docker compose down
```

## Ejecutar frontend Angular

En otra terminal:

```bash
cd student-frond
npm install
npm start
```

El frontend se ejecuta en:

```text
http://localhost:4200
```

El frontend esta configurado para conectarse al backend local en:

```text
http://localhost:8090/v1/api/student
```

Por eso, para usar frontend + backend local, primero levanta el backend con Maven en el puerto `8090` y luego levanta Angular.

## Probar compilacion del frontend

```bash
cd student-frond
npm run build
```

## Funciones disponibles

- Listar estudiantes
- Buscar estudiante por ID
- Registrar estudiante
- Actualizar estudiante
- Eliminar estudiante
