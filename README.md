# SW API: prueba ténica

A continuación, se expone de manera detalla la estucutura, pasos y tecnologías utilizadas para realizar esta prueba técnica. 
Se ha utlizado Java 22.0.2, Apache Maven 3.9.9, Docker version 27.2.0 y Kubernetes 1.30.0.

## Tabla de Contenido
- [Estructura de carpeta](#estructura-de-carpetas)
- [Desarrollo del servicio](#desarrollo-del-servicio)
    - [App](#app)
    - [MakeRequest](#makerequest)
    - [MyHttpHandler](#myhttphandler)
    - [Compilación del servicio](#compilación-del-servicio)
- [Dockerización del servicio: Docker y Docker Compose](#dockerización-del-servicio-docker-y-docker-compose)
    - [Dockerfile](#dockerfile)

## Estructura de carpetas

- k8s: contiene los manifiestos de Kubernetes para desplegar los microservicios.
- sw-api: código en Java del servicio que hace las llamadas a la API externa.
- docker-compose.yml: manifiesto con los servicios para el despliegue del contendor mediante Docker Compose.
- Dockerfile: fichero de compilación del contenedor de Docker.

## Desarrollo del servicio
El servicio se ha desarrollado en JAVA haciendo uso de Maven para la gestión de las dependencias.
Contamos con tres clases:

### App
Es la clase principal, en ella se inicializan los objetos necesarios para levantar el servicio.
Declaramos un objeto de tipo HTTPServer pasandole como parámetros tanto el host como el puerto donde correrá el servidor web.

### MakeRequest
Encagado de realizar las peticiones a la API externa. Se hace uso de la liberia *com.github.kevinsawicki.http.HttpRequest* para realizar las peticiones GET al endpoint /people.

### MyHttpHandler

### Compilación del servicio
Una vez visto las clases que conforman el servicio, tenemos que construir un binario para poder usarlo.
De esta manera, mediante Maven compilamos el servicio y generamos un binario en el path *target/sw-api-1.0-SNAPSHOT.jar* que utilizaremos más tarde.</br>

`mvn clean package`


## Dockerización del servicio: Docker y Docker Compose
### Dockerfile
En el se describen las instrucciones para construir nuestra imagen de Docker con el servicio creado anteriormente:

1. A partir de la imagen base openjdk:22 vamos a construir nuestro contenedero, de esta forma contamos con una instalación base de todo lo que necesitamos para correr una aplicación Java. </br>
`FROM openjdk:22`
2. Especificamos que queremos trabajar a partir de ahora en el directorio raiz del contenedor. </br>
`WORKDIR /`
3. Copiamos el binario .jar local al contenedor con el nombre api.jar. </br>
`COPY ./sw-api/target/*.jar api.jar`
4. Exponemos el puerto 9090 indicando que hace uso del protocolo TCP. </br>
`EXPOSE 9090/tcp`
5. Le damos un punto de entrada al contenedor con la instrucción que tiene que ejecutar para arrancar el servidor web.</br>
`ENTRYPOINT ["java", "-jar", "api.jar"]`

Una vez visto el contenido del fichero podemos construir nuestra imagen. Para ello ejecutamos el siguiente comando: </br>
`docker build . -t sw-api:v2.2`

Con al imagen construida creamos el contenedor de docker exponiendo el puerto 9090 con el mismo en la máquina afitriona:</br>
`docker run -d --name sw-api -p 9090:9090 sw-api:v2.2`