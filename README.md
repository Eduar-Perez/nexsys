Cordial saludo. En el archivo PDF donde se describía la prueba, se especifican el nombre de la BD y el nombre de la tabla producto_nexsys con sus atributos. 
El campo description no se encuentra especificado, pero en el servicio de crear un producto hace referencia a este campo. 
Por tal motivo, lo cree también en la tabla Por otro lado. 
Para no tener que modificar y crear una tabla para guardar usuario con sus credenciales, maneje la seguridad directamente del back 
Con un usuario quemado Y con sus credenciales en el archivo application.properties. con dos roles: admin y user

useradmin = admin passwordadmin = password 
user = user passworduser = password

Para la autenticación y login se creó el siguiente cURL: curl --location 'http://localhost:8080/nexsys/v1/login'
--header 'Content-Type: application/json'
--data '{ "username": "admin", "password": "password" }'

Para la BD se uso MySQL 
username = root 
password = root
