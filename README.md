# ProyectoFraudesIBM

## Ejercicio de programación

Para coordinar acciones de respuesta ante fraudes, es útil tener disponible información contextual del lugar de origen detectado en el momento de comprar, buscar y pagar. Para ello se decide crear una herramienta que dada una IP obtenga información asociada.

El ejercicio consiste en construir una API Rest que permita:

1. Dada una dirección IP, encontrar el país al que pertenece y mostrar:
   - El nombre y código ISO del país
   - Moneda local y su cotización actual en dólares o euros.
2. Ban/Blacklist de una IP:
   - Marcar la IP en una lista negra no permitiéndole consultar la información del punto 1.

### Observaciones

Tener en cuenta que el punto 1 puede recibir fluctuaciones agresivas de tráfico.

## Consideraciones

- Se solicita una solución con un diseño OOP.
- La solución debe ser en Java, Kotlin o Groovy.
- Es deseable que la aplicación pueda correr, ser construida y ejecutada dentro de un contenedor Docker (incluir un Dockerfile e instrucciones para ejecutarlo).
- La aplicación deberá hacer un uso racional de las APIs, evitando hacer llamadas innecesarias.
- La aplicación no deberá perder su estado ante un shutdown.
- Además de funcionamiento, prestar atención al estilo y calidad del código fuente.

# Debe incluir

1. Spring Boot Web
2. Eureka
3. Servidor de enrutamiento dinámico(Zuul o Api Gateway Spring Cloud)
4. Resilience4J(Tolerancia a fallos, latencia y timeout)
5. Logs(JUnit, Mockito y/o Spring Boot Test)
6. Test Unitarios
7. Base de Datos en memoria(H2)
8. Buenas practicas de programación
9. Patrones de Diseño y/o arquitectónicos (DTO, Chain Of Responsability)
10. Programación Orientada a Objetos
11. Métodos documentados
12. Métodos encapsulados(Response Entity)
13. Para el micro servicio principal, escalamiento dinámico (puertos random)
14. Principios SOLID
15. Consumo de Apis(RestTemplate o Feign)

#

### Reference Documentation

For further reference, please consider the following sections:

- [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/)
- [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/#build-image)
- [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#using-boot-devtools)
- [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides

The following guides illustrate how to use some features concretely:

- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
