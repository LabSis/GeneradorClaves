# Generador de Claves

Proyecto realizado en el Laboratorio de Sistemas, UTN FRC.

Esta aplicación permite la generación de una base de datos clave-hash, de hasta 12 caracteres, como así también provee la lógica necesaria para realizar lá búsqueda de una clave a partir de su hash.

Se une con el proyecto [Hash Cracker](https://github.com/LabSis/labsis_hash_cracker_web) el cual provee una interfaz web para facilitar la búsqueda.

<i>Hashes soportados por esta aplicación: MD5, SHA-1, SHA-224, SHA-256, SHA-384, SHA-512 </i>

## Instrucciones:
        Generación: 
        java -jar GeneradorClaves.jar load [ALGORITMO] [LONGITUD CLAVE]
        
        Búsqueda:
        java -jar GeneradorClaves.jar run [ALGORITMO] [HASH]
        
<i> Requiere previamente tener instalado Java v8, y Apache Cassandra v3.11 </i>    
    
    

    



