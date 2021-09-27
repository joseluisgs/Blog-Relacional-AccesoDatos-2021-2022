# Blog-Relacional-AccesoDatos-2021-2022
Ejemplo de desarrollo de un blog (backend básico) para Acceso a Datos, usando una base de datos realacional e implementando distintas técnicas y patrones de Acceso a Datos vistos en clase.

[![Kotlin](https://img.shields.io/badge/Code-Java-blue)](https://www.java.com/es/)
[![LISENCE](https://img.shields.io/badge/Lisence-MIT-green)]()
![GitHub](https://img.shields.io/github/last-commit/joseluisgs/Blog-Relacional-AccesoDatos-2021-2022)

- [Blog-Relacional-AccesoDatos-2021-2022](#blog-relacional-accesodatos-2021-2022)
  - [Descripción](#descripción)
  - [Tecnologías](#tecnologías)
  - [Enunciado](#enunciado)
    - [Ejemplo de diagrama](#ejemplo-de-diagrama)
  - [Desarrollo](#desarrollo)
    - [GitFlow](#gitflow)
    - [Maven](#maven)
    - [Secretos](#secretos)
    - [Lombok](#lombok)
  - [Ejecución](#ejecución)
    - [Docker](#docker)
    - [Adminer o cliente de Bases de Datos](#adminer-o-cliente-de-bases-de-datos)
  - [Autor](#autor)
    - [Contacto](#contacto)
  - [Licencia](#licencia)

## Descripción
Se ha implementado el desarrollo del un blog a nivel de backend para el acceso a los datos que se necesiten con fines didácticos para el módulo de Acceso a Datos de 2DAM.
Debes entender que es un ejemplo didáctico para clase, por lo que parte de la solución simplemente es para mostrar distintas técnicas y patrones y por lo tanto 
puede que no sea la más óptima o adecuada a niveles de producción o empresarial. Tenlo en cuenta.

## Tecnologías
Se han usado las siguientes tecnologías:
- Java 11, como lenguaje de programación.
- MariaDB como motor de base de datos relacional.
- Docker para lanzar la base de datos, así como otras utilidades para manejarla.

## Enunciado
Se desea implementar la base de un blog teniendo en cuenta que: 
- Un usuario una vez registrado mediante email y password puede hacer login y logout en el sistema.
- El usuario puede escribir varios posts los cuales pertenecen solo a una categoría existente, como general, dudas o evaluación. Se pueden crear nuevas categorías.
- Los usuarios pueden hacer distintos comentarios sobre posts existentes.

### Ejemplo de diagrama
![diagrama](./diagrams/Diagrams.png)

## Desarrollo
### GitFlow
Se ha usado GitFlow como modelo de flujo de desarrollo y trabajo con el repositorio.

### Maven
Apache Maven es un software de gestión de proyectos. Maven aumenta la reutilización y también se encarga de la mayoría 
de las tareas relacionadas con la construcción. Funciona en muchos pasos, como agregar archivos jar a la biblioteca del proyecto, 
crear informes y ejecutar casos de prueba o crear archivos Jar para el proyecto e incluso muchas más cosas.
La configuración de Maven se guarda en el fichero [pom.xml](./pom.xml).

### Secretos
Para trabajar con secretos y o variables globales se han usado dos enfoques en el directorio recursos:
- Ficheros de Properties (Propiedades): con ellos podemos leer propiedades de la manea clave valor.
- Ficheros .env: Mediante ellos leemos las variables de entorno ya sea del sistema o definidas en un fichero .env 

### Lombok
Se ha usado [Lombok](https://projectlombok.org/features/all) como sistema de anotaciones para aumentar la productividad 
y reducir el código repetitivo.

## Ejecución
### Docker
Entrar en el directorio docker y ejecutar
```sh
$ docker-compose up -d
```
Para iniciar la BD con algunos datos modifica el fichero [docker/mariadb/sql/init.sql](docker/mariadb/sql/init-db.sql)


### Adminer o cliente de Bases de Datos
Debes conectarte a http://localhost:8080/
- server: mariadb
- user: blog
- password: blog1234 
- base de datos blog

## Autor

Codificado con :sparkling_heart: por [José Luis González Sánchez](https://twitter.com/joseluisgonsan)

[![Twitter](https://img.shields.io/twitter/follow/joseluisgonsan?style=social)](https://twitter.com/joseluisgonsan)
[![GitHub](https://img.shields.io/github/followers/joseluisgs?style=social)](https://github.com/joseluisgs)

### Contacto
<p>
  Cualquier cosa que necesites házmelo saber por si puedo ayudarte 💬.
</p>
<p>
    <a href="https://twitter.com/joseluisgonsan" target="_blank">
        <img src="https://i.imgur.com/U4Uiaef.png" 
    height="30">
    </a> &nbsp;&nbsp;
    <a href="https://github.com/joseluisgs" target="_blank">
        <img src="https://cdn.iconscout.com/icon/free/png-256/github-153-675523.png" 
    height="30">
    </a> &nbsp;&nbsp;
    <a href="https://www.linkedin.com/in/joseluisgonsan" target="_blank">
        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/LinkedIn_logo_initials.png/768px-LinkedIn_logo_initials.png" 
    height="30">
    </a>  &nbsp;&nbsp;
    <a href="https://joseluisgs.github.io/" target="_blank">
        <img src="https://joseluisgs.github.io/favicon.png" 
    height="30">
    </a>
</p>


## Licencia

Este proyecto está licenciado bajo licencia **MIT**, si desea saber más, visite el fichero [LICENSE](./LICENSE) para su uso docente y educativo.
