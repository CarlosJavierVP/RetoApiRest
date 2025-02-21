# API de Turismo y Meteorología

Esta API proporciona información sobre hoteles, restaurantes, eventos, puntos de interés
y el tiempo en una ciudad específica. Está diseñada para ser utilizada por aplicaciones de
turismo y planificación de viajes.


## Base URL
Todavía no está desplegada, se trabaja en local para pulir detalles:

http://localhost:8080/api

---

## Recursos y Endpoints

### 1. Hoteles

#### Obtener todos los hoteles
- **Endpoint**: 'GET /hoteles'
- **Descripción**: Devuelve una lista de todos los hoteles.
- **Respuesta**:
```json
[
    {
        "id":"1",
        "nombre":"Hotel NH Malaga",
        "ciudad":"Malaga",
        "direccion":"Calle San Jacinto, 2, 29007 Malaga",
        "categoria":4,
        "servicios":[
            "wifi",
            "piscina",
            "spa",
            "gimnasio"
        ],
        "precioNoche":120.0
    },
    {
        "id":"2",
        "nombre":"Hotel Casa del Poeta",
        "direccion":"Calle Don Carlos Alonso Chaparro, 3, 41004 Sevilla",
        "categoria":4,
        "servicios":[
            "wifi",
            "restaurante",
            "bar"
        ],
        "precioNoche":123.0
        
    }
]
````
#### Obtener un hotel por ID
- **Endpoint**: 'GET /hoteles/{id}'
- **Descripción**: Devuelve un hotel específico por su ID.
- **Respuesta**:
```json
{
        "id":"1",
        "nombre":"Hotel NH Malaga",
        "ciudad":"Malaga",
        "direccion":"Calle San Jacinto, 2, 29007 Malaga",
        "categoria":4,
        "servicios":[
            "wifi",
            "piscina",
            "spa",
            "gimnasio"
        ],
        "precioNoche":120.0
    }
```

#### Obtener una lista de hoteles por categoría
- **Endpoint**: 'GET /hoteles/categoria/{categoria}'
- **Descripción**: Devuelve una lista de hoteles filtrados por categoría (estrellas).
- **Respuesta**:
```json
[
    {
        "id":"2",
        "nombre":"Hotel Casa del Poeta",
        "direccion":"Calle Don Carlos Alonso Chaparro, 3, 41004 Sevilla",
        "categoria":4,
        "servicios":[
            "wifi",
            "restaurante",
            "bar"
        ],
        "precioNoche":123.0
        
    }
]
```
#### Obtener una lista de hoteles por precio
- **Endpoint**: 'GET /hoteles/precio/{precio}'
- **Descripción**: Devuelve una lista de hoteles filtrados por precio la noche
- **Respuesta**:
```json
[
    {
        "id":"2",
        "nombre":"Hotel Casa del Poeta",
        "direccion":"Calle Don Carlos Alonso Chaparro, 3, 41004 Sevilla",
        "categoria":4,
        "servicios":[
            "wifi",
            "restaurante",
            "bar"
        ],
        "precioNoche":123.0
        
    }
]
```
#### Crear un nuevo hotel
- **Endpoint**: 'POST /hoteles/'
- **Descripción**: Crear un nuevo hotel
- **Respuesta**: 201 Created

#### Eliminar un hotel
- **Endpoint**: 'DELETE /hoteles/delete/{id}'
- **Descripción**: Elimina un hotel por su ID
- **Respuesta**: 204 No Content

### 2. Restaurantes
#### Obtener todos los restaurantes
- **Endpoint**: 'GET /restaurantes'
- **Descripción**: Devuelve una lista de todos los restaurantes.
- **Respuesta**:
```json
[
  {
    "id":"1",
    "nombre":"El Pimpi",
    "direccion":"Calle Granada, 62, 29015 Malaga",
    "ciudad":"Malaga",
    "tipoCocina":"Mediterranea"
  }
]
```
#### Obtener un restaurante por ID
- **Endpoint**: 'GET /restaurantes/{id}'
- **Descripción**: Devuelve un restaurante específico por su ID.
- **Respuesta**:
```json
{
    "id":"1",
    "nombre":"El Pimpi",
    "direccion":"Calle Granada, 62, 29015 Malaga",
    "ciudad":"Malaga",
    "tipoCocina":"Mediterranea"
}
```
#### Obtener una lista de restaurantes por ciudad
- **Endpoint**: 'GET /restaurantes/ciudad/{ciudad}'
- **Descripción**: Devuelve una lista de restaurantes filtrados por ciudad.
- **Respuesta**:
```json
{
    "id":"1",
    "nombre":"El Pimpi",
    "direccion":"Calle Granada, 62, 29015 Malaga",
    "ciudad":"Malaga",
    "tipoCocina":"Mediterranea"
}
```
#### Obtener una lista de restaurantes por tipo de cocna
- **Endpoint**: 'GET /restaurantes/cocina/{cocina}'
- **Descripción**: Devuelve una lista de restaurantes filtrados por cocina.
- **Respuesta**:
```json
{
    "id":"1",
    "nombre":"El Pimpi",
    "direccion":"Calle Granada, 62, 29015 Malaga",
    "ciudad":"Malaga",
    "tipoCocina":"Mediterranea"
}
```
#### Crear un nuevo restaurante
- **Endpoint**: 'POST /restaurantes/'
- **Descripción**: Crear un nuevo restaurante
- **Respuesta**: 201 Created
- 
#### Eliminar un restaurante
- **Endpoint**: 'DELETE /restaurantes/delete/{id}'
- **Descripción**: Elimina un restaurante por su ID
- **Respuesta**: 204 No Content

### 3. Eventos
#### Obtener todos los eventos
- **Endpoint**: 'GET /eventos'
- **Descripción**: Devuelve una lista de todos los eventos.
- **Respuesta**:
```json
[
  {
    "id":"1",
    "nombre": "Festival de Cine de Malaga",
    "descripcion": "Festival internacional de cine con proyecciones y actividades relacionadas",
    "ubicacion": "Teatro Cervantes",
    "ciudad": "Malaga",
    "tipo": "festival",
    "fechaInicio": "2023-11-01",
    "fechaFin": "2023-11-10",
    "precio": 15
  }
]
```
#### Obtener un evento por ID
- **Endpoint**: 'GET /eventos/{id}'
- **Descripción**: Devuelve un evento específico por id.
- **Respuesta**:
```json
  {
    "id":"1",
    "nombre": "Festival de Cine de Malaga",
    "descripcion": "Festival internacional de cine con proyecciones y actividades relacionadas",
    "ubicacion": "Teatro Cervantes",
    "ciudad": "Malaga",
    "tipo": "festival",
    "fechaInicio": "2023-11-01",
    "fechaFin": "2023-11-10",
    "precio": 15
  }
```
#### Obtener una lista de eventos por tipo
- **Endpoint**: 'GET /eventos/tipo/{tipo}'
- **Descripción**: Devuelve una lista de todos los eventos filtrados por tipo.
- **Respuesta**:
```json
[
  {
    "id":"1",
    "nombre": "Festival de Cine de Malaga",
    "descripcion": "Festival internacional de cine con proyecciones y actividades relacionadas",
    "ubicacion": "Teatro Cervantes",
    "ciudad": "Malaga",
    "tipo": "festival",
    "fechaInicio": "2023-11-01",
    "fechaFin": "2023-11-10",
    "precio": 15
  }
]
```
#### Obtener una lista de eventos por ciudad
- **Endpoint**: 'GET /eventos/ciudad/{ciudad}'
- **Descripción**: Devuelve una lista de todos los eventos filtrados por ciudad.
- **Respuesta**:
```json
[
  {
    "id":"1",
    "nombre": "Festival de Cine de Malaga",
    "descripcion": "Festival internacional de cine con proyecciones y actividades relacionadas",
    "ubicacion": "Teatro Cervantes",
    "ciudad": "Malaga",
    "tipo": "festival",
    "fechaInicio": "2023-11-01",
    "fechaFin": "2023-11-10",
    "precio": 15
  }
]
```
#### Obtener una lista de eventos por ciudad y tipo
- **Endpoint**: 'GET /eventos/ciudad/{ciudad}/tipo/{tipo}'
- **Descripción**: Devuelve una lista de todos los eventos filtrados por ciudad y por tipo.
- **Respuesta**:
```json
[
  {
    "id":"1",
    "nombre": "Festival de Cine de Malaga",
    "descripcion": "Festival internacional de cine con proyecciones y actividades relacionadas",
    "ubicacion": "Teatro Cervantes",
    "ciudad": "Malaga",
    "tipo": "festival",
    "fechaInicio": "2023-11-01",
    "fechaFin": "2023-11-10",
    "precio": 15
  }
]
```
### 4. Puntos de interés
#### Obtener todos los puntos de interés
- **Endpoint**: 'GET /punto_interes'
- **Descripción**: Devuelve una lista de todos los puntos de interés turístico
- **Respuesta**:
```json
[
  {
    "id":"1",
    "nombre": "Alcazaba de Malaga",
    "tipo": "monumento",
    "direccion": "Calle Alcazabilla, 2, 29015 Malaga",
    "ciudad": "Malaga",
    "descripcion": "Fortaleza palaciega de la epoca musulmana con vistas panoramicas de la ciudad",
    "categorias": [
      "historico",
      "cultural"
    ],
    "localizacion": {
      "type": "Point",
      "coordinates": [
        -4.4155,
        36.7213
      ]
    }
  }
]
```
#### Obtener un punto de interés por ID
- **Endpoint**: 'GET /punto_interes/{id}'
- **Descripción**: Devuelve un punto de interés turístico específico por id.
- **Respuesta**:
```json
{
    "id":"1",
    "nombre": "Alcazaba de Malaga",
    "tipo": "monumento",
    "direccion": "Calle Alcazabilla, 2, 29015 Malaga",
    "ciudad": "Malaga",
    "descripcion": "Fortaleza palaciega de la epoca musulmana con vistas panoramicas de la ciudad",
    "categorias": [
      "historico",
      "cultural"
    ],
    "localizacion": {
      "type": "Point",
      "coordinates": [
        -4.4155,
        36.7213
      ]
    }
  }
```
#### Obtener una lista de puntos de interés por ciudad
- **Endpoint**: 'GET /punto_interes/ciudad/{ciudad}'
- **Descripción**: Devuelve una lista de puntos de interés turístico filtrado por ciudad.
- **Respuesta**:
```json
[
  {
    "id":"1",
    "nombre": "Alcazaba de Malaga",
    "tipo": "monumento",
    "direccion": "Calle Alcazabilla, 2, 29015 Malaga",
    "ciudad": "Malaga",
    "descripcion": "Fortaleza palaciega de la epoca musulmana con vistas panoramicas de la ciudad",
    "categorias": [
      "historico",
      "cultural"
    ],
    "localizacion": {
      "type": "Point",
      "coordinates": [
        -4.4155,
        36.7213
      ]
    }
  }
]
```
#### Obtener una lista de puntos de interés por tipo
- **Endpoint**: 'GET /punto_interes/tipo/{tipo}'
- **Descripción**: Devuelve una lista de puntos de interés turístico filtrado por tipo.
- **Respuesta**:
```json
[
  {
    "id":"1",
    "nombre": "Alcazaba de Malaga",
    "tipo": "monumento",
    "direccion": "Calle Alcazabilla, 2, 29015 Malaga",
    "ciudad": "Malaga",
    "descripcion": "Fortaleza palaciega de la epoca musulmana con vistas panoramicas de la ciudad",
    "categorias": [
      "historico",
      "cultural"
    ],
    "localizacion": {
      "type": "Point",
      "coordinates": [
        -4.4155,
        36.7213
      ]
    }
  }
]
```
#### Obtener una lista de puntos de interés por categoria
- **Endpoint**: 'GET /punto_interes/categoria/{categoria}'
- **Descripción**: Devuelve una lista de puntos de interés turístico filtrado por categoria.
- **Respuesta**:
```json
[
  {
    "id":"1",
    "nombre": "Alcazaba de Malaga",
    "tipo": "monumento",
    "direccion": "Calle Alcazabilla, 2, 29015 Malaga",
    "ciudad": "Malaga",
    "descripcion": "Fortaleza palaciega de la epoca musulmana con vistas panoramicas de la ciudad",
    "categorias": [
      "historico",
      "cultural"
    ],
    "localizacion": {
      "type": "Point",
      "coordinates": [
        -4.4155,
        36.7213
      ]
    }
  }
]
```
#### Obtener una lista de puntos de interés por ciudad y tipo
- **Endpoint**: 'GET /punto_interes/ciudad/{ciudad}/tipo/{tipo}'
- **Descripción**: Devuelve una lista de puntos de interés turístico filtrado por ciudad y tipo.
- **Respuesta**:
```json
[
  {
    "id":"1",
    "nombre": "Alcazaba de Malaga",
    "tipo": "monumento",
    "direccion": "Calle Alcazabilla, 2, 29015 Malaga",
    "ciudad": "Malaga",
    "descripcion": "Fortaleza palaciega de la epoca musulmana con vistas panoramicas de la ciudad",
    "categorias": [
      "historico",
      "cultural"
    ],
    "localizacion": {
      "type": "Point",
      "coordinates": [
        -4.4155,
        36.7213
      ]
    }
  }
]
```
#### Obtener una lista de puntos de interés por ciudad y categoria
- **Endpoint**: 'GET /punto_interes/ciudad/{ciudad}/categoria/{categoria}'
- **Descripción**: Devuelve una lista de puntos de interés turístico filtrado por ciudad y categoria.
- **Respuesta**:
```json
[
  {
    "id":"1",
    "nombre": "Alcazaba de Malaga",
    "tipo": "monumento",
    "direccion": "Calle Alcazabilla, 2, 29015 Malaga",
    "ciudad": "Malaga",
    "descripcion": "Fortaleza palaciega de la epoca musulmana con vistas panoramicas de la ciudad",
    "categorias": [
      "historico",
      "cultural"
    ],
    "localizacion": {
      "type": "Point",
      "coordinates": [
        -4.4155,
        36.7213
      ]
    }
  }
]
```
#### Obtener una lista de puntos de interés cercanos a una ubicación
- **Endpoint**: 'GET /punto_interes/localizacion?latitud={latitud}&longitud={longitud}&maxDist={maxDist}'
- **Descripción**: Devuelve una lista de puntos de interés turístico cercanos a las coordenadas especificadas.
- **Respuesta**:
```json
[
  {
    "id":"1",
    "nombre": "Alcazaba de Malaga",
    "tipo": "monumento",
    "direccion": "Calle Alcazabilla, 2, 29015 Malaga",
    "ciudad": "Malaga",
    "descripcion": "Fortaleza palaciega de la epoca musulmana con vistas panoramicas de la ciudad",
    "categorias": [
      "historico",
      "cultural"
    ],
    "localizacion": {
      "type": "Point",
      "coordinates": [
        -4.4155,
        36.7213
      ]
    }
  }
]
```