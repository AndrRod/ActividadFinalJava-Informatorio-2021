# ActividadFinalJava-Informatorio-2021 - Java - Spring Boot (API-REST) 🚀

### Objetivo - DESCRIPCIÓN DE LA APLICACIÓN (API)

El Informatorio planea lanzar en varias plataformas (Web, iOS y Android) una aplicación donde se publicaran varios proyectos startup (emprendimientos). Donde los emprendedores podrán publicar sus ideas y de esa forma conseguir votos e inclusive recaudar fondos.
Los emprendimientos podrán ser de diferentes rubros (apps, desarrollo de productos, etc).
Se podrán conseguir votos y aportes en cualquier momento. Pero también existirá la posibilidad de suscribirse a eventos en donde el ganador recibirá el premio del evento.

## DOCUMENTACION 📋

Fueron documentados los endpoints utilizando Postman, el archivo json correspondiente al mismo fue agregado al proyecto con el nombre: Coleccion spring 2021.postman_collection.json

## REQUERIMIENTOS 📖

- 👉 Utilizar Spring Boot
- 👉 Las rutas deberán seguir el patrón REST
- 👉 Utilización de DTOS
- 👉 Utilización de ENUMS
- 👉 Encriptación de contraseña


## 1. Modelado de Base de Datos
### De las siguientes entidades se necesita conocer y registrar
**USUARIO:**  deberá tener:
- Id (Autogenerado)
- Nombre.
- Apellido.
- Email (Unico).
- Password (se almacenará pero no se mostrara).
- Fecha de creación (o alta).
- Ciudad
- Provincia
- Pais
- Tipo (USUARIO | COLABORADOR | OWNER)

 **EMPRENDIMIENTO:**  deberá tener:
- Id (Autogenerado)
- Nombre.
- Descripción.
- Contenido (cuerpo de la publicación).
- Fecha de creación (o alta).
- Objetivo ($ recaudación).
- URL (capturas, puede tener una o varias)
- Tags (ejemplo: #salud, #diversión, etc. Obs: el "#" es un decorado)

**EVENTO:** deberá tener:
- id (autogenerado)
- Detalles del evento (Descripción, info de auspiciantes, premio)
- fecha de creación (o alta)
- fecha de cierre (o alta)
- Estado: ABIERTO | EN CURSO | FINALIZADO
- suscriptores (Emprendimientos que se registraron)
- premio: $

**VOTO:** deberá tener:
- id (autogenerado)
- generado por (mobile, web, servicio)
- Usuario (username)
- fecha de creación (o alta)
- Observación: Se asume que los votos son LIKES (no hay negativos)



## USUARIOS :grinning:

Aclaración para el Tipo de Usuario se creo un ENUM:

    public enum TipoUsuario {
            USUARIO,
            COLABORADOR,
            OWNER
    }

## 1. Listado de USUARIOS

Con el siguiente endpoint se muestra los personajes, pero solamente se filtra a traves de un DTO todos los datos de usuario menos contraseña.

### GET
	http://localhost:8080/usuario/


Ejemplo:

    {
        "id": 3,
        "nombre": "fernando",
        "apellido": "Schmdit",
        "email": "123shc23m@hotmail.com",
        "ciudad": "vilelas",
        "provincia": "chaco",
        "pais": "argentina",
        "tipo": "OWNER",
        "fechaCreacion": "04-12-2021 07:56:32"
    }

## 2. USUARIO (CRUD)

### POST
	http://localhost:8080/usuario/

Ejemplo:

    {   
        "nombre": "roberto",
        "apellido": "gonzalez",
        "email": "gonzal3ez@gmail.com",
        "ciudad": "Resistencia",
        "provincia": "chaco",    
        "password": "4ewr13124123",
        "pais": "argentina",
        "tipo": "COLABORADOR"
    }

### PUT by ID
	http://localhost:8080/usuario/{id}


### DELETE by ID

	http://localhost:8080/usuario/{id}

### GET By ID

	http://localhost:8080/usuario/buscarPorId/{id}

### 5. Búsqueda de USUARIOS

Busqueda por ciudad:

    http://localhost:8080/usuario/buscarPorCiudad?ciudad={ciudad}

Busqueda por fecha:

    http://localhost:8080/usuario/buscarPorFecha/{fecha}

ejemplo:

    http://localhost:8080/usuario/buscarPorFecha/13-12-2021


## EMPRENDIMIENTOS 🔧

## 1. Listado de Emprendimientos

    http://localhost:8080/emprendimiento/

## 2. Emprendimiento (CRUD)

### POST
	http://localhost:8080/emprendimiento/

Ejemplo:
    
    {        
        "nombre": "emprendimiento10",
        "descripcion": "descripcionEmp10",
        "contenido": "contenidoEmp10",

        "publicado": true,
        "tags": 
            [{ 
                "nombre": "#Chaco"                                
            },{
                "nombre": "#superEmprendimientos"
            }],
        "urls": [
            {                    
                "name": "http://direccionurldelEmprendimiento.com"
            }
           ]
    }
    

### PUT by ID
	http://localhost:8080/emprendimiento/{id}

### DELETE by ID
	http://localhost:8080/emprendimiento/{id}


## 3.Búsqueda de Emprendimientos

Busqueda por Tags:

    http://localhost:8080/emprendimiento/buscarPorTag/{tag}

Filtro por Emprendimientos sin publicar:
    
    http://localhost:8080/emprendimiento/buscarSinPublicar/



## EVENTO

Aclaración: para el estado del evento se creo un ENUM:

    public enum EstadoEvento {
        ABIERTO,
        EN_CURSO,
        FINALIZADO;
    }

## 1. Listado de Eventos

    http://localhost:8080/evento/

## 2. Evento (CRUD)

### POST
	http://localhost:8080/evento/

Ejemplo:
    
    {                
        "detallesEvento": "detalleEvento",  
        "estadoEvento": "0",
        "premio": 1000000
    }    

### PUT by ID
	http://localhost:8080/evento/{id}

### DELETE by ID
	http://localhost:8080/evento/{id}


## 3. Búsqueda de Evento:

Busqueda por Ranking (colocando el id del evento se desplega un ranking de los emprendimientos que participaron en el evento y la cantidad de votos de cada uno en el evento):

    http://localhost:8080/evento/rankingPorEventoId/{id}

Ejemplo salida:

    {
    "emprendimiento1": 4,
    "emprendimiento3": 3,
    "emprendimiento5": 1
    }


## VOTO

Aclaración: para el tipo de Voto generado se creo un ENUM: 
    
    public enum VotoGenerado {
        mobile,
        web,
        servicio
    }


### 1. Listado de votos

    http://localhost:8080/voto/

### 2. POST

Para crear un voto será necesario ingresar en el siguiente orden: id del Usuario, Id del Evento, Id del Emprendimiento, y Id del Tipo de Voto (por mobile, web, servicio)

	http://localhost:8080/voto/crearVotoIdUsuario/{idUsuario}/IdEvento/{idEvento}/IdEmprendimiento/{idEmprendimiento}/StringGeneradoPor/{idVotoGenerado}


### 3. Búsqueda de Voto por Usuario:

    http://localhost:8080/voto/buscarPorUsername/{email}

Ejemplo:

    http://localhost:8080/voto/buscarPorUsername/a@gmail.com


## PENDIENTES:

- Captar de Mejor manera los errores.
- Tests verificando posibles erroes usando herramientas como JUnit y Mockito






Hecho por :raising_hand_man: [Andres Rodriguez](https://github.com/AndrRod/ "Andres Rodriguez") 🎁
