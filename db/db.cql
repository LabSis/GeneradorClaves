/*INSTRUCCIONES
    Una vez logueado en la consola de cqlsh, utilizar el comando SOURCE para ejecutar el script:
    > SOURCE 'directorio/db.cql'
*/

//KEYSPACES

create keyspace keys_MD5 with replication = {'class':'SimpleStrategy', 'replication_factor':2};
create keyspace keys_SHA1 with replication = {'class':'SimpleStrategy', 'replication_factor':2};
create keyspace keys_SHA224 with replication = {'class':'SimpleStrategy', 'replication_factor':2};
create keyspace keys_SHA256 with replication = {'class':'SimpleStrategy', 'replication_factor':2};
create keyspace keys_SHA384 with replication = {'class':'SimpleStrategy', 'replication_factor':2};
create keyspace keys_SHA512 with replication = {'class':'SimpleStrategy', 'replication_factor':2};


//PARA CLAVES MENORES A 5 CARACTERES UTILIZO UNA SÓLA TABLA, YA QUE SUS TAMAÑOS POR SEPARADO NO JUSTIFICAN EL EMPLEO DE TABLAS SEPARADAS

create table keys_MD5.minor_keys(key varchar primary key, hash varchar);
create table keys_MD5.keys_size_5(key varchar primary key, hash varchar);
create table keys_MD5.keys_size_6(key varchar primary key, hash varchar);
create table keys_MD5.keys_size_7(key varchar primary key, hash varchar);
create table keys_MD5.keys_size_8(key varchar primary key, hash varchar);
create table keys_MD5.keys_size_9(key varchar primary key, hash varchar);
create table keys_MD5.keys_size_10(key varchar primary key, hash varchar);
create table keys_MD5.keys_size_11(key varchar primary key, hash varchar);
create table keys_MD5.keys_size_12(key varchar primary key, hash varchar);

create table keys_SHA1.minor_keys(key varchar primary key, hash varchar);
create table keys_SHA1.keys_size_5(key varchar primary key, hash varchar);
create table keys_SHA1.keys_size_6(key varchar primary key, hash varchar);
create table keys_SHA1.keys_size_7(key varchar primary key, hash varchar);
create table keys_SHA1.keys_size_8(key varchar primary key, hash varchar);
create table keys_SHA1.keys_size_9(key varchar primary key, hash varchar);
create table keys_SHA1.keys_size_10(key varchar primary key, hash varchar);
create table keys_SHA1.keys_size_11(key varchar primary key, hash varchar);
create table keys_SHA1.keys_size_12(key varchar primary key, hash varchar);

create table keys_SHA224.minor_keys(key varchar primary key, hash varchar);
create table keys_SHA224.keys_size_5(key varchar primary key, hash varchar);
create table keys_SHA224.keys_size_6(key varchar primary key, hash varchar);
create table keys_SHA224.keys_size_7(key varchar primary key, hash varchar);
create table keys_SHA224.keys_size_8(key varchar primary key, hash varchar);
create table keys_SHA224.keys_size_9(key varchar primary key, hash varchar);
create table keys_SHA224.keys_size_10(key varchar primary key, hash varchar);
create table keys_SHA224.keys_size_11(key varchar primary key, hash varchar);
create table keys_SHA224.keys_size_12(key varchar primary key, hash varchar);

create table keys_SHA256.minor_keys(key varchar primary key, hash varchar);
create table keys_SHA256.keys_size_5(key varchar primary key, hash varchar);
create table keys_SHA256.keys_size_6(key varchar primary key, hash varchar);
create table keys_SHA256.keys_size_7(key varchar primary key, hash varchar);
create table keys_SHA256.keys_size_8(key varchar primary key, hash varchar);
create table keys_SHA256.keys_size_9(key varchar primary key, hash varchar);
create table keys_SHA256.keys_size_10(key varchar primary key, hash varchar);
create table keys_SHA256.keys_size_11(key varchar primary key, hash varchar);
create table keys_SHA256.keys_size_12(key varchar primary key, hash varchar);

create table keys_SHA384.minor_keys(key varchar primary key, hash varchar);
create table keys_SHA384.keys_size_5(key varchar primary key, hash varchar);
create table keys_SHA384.keys_size_6(key varchar primary key, hash varchar);
create table keys_SHA384.keys_size_7(key varchar primary key, hash varchar);
create table keys_SHA384.keys_size_8(key varchar primary key, hash varchar);
create table keys_SHA384.keys_size_9(key varchar primary key, hash varchar);
create table keys_SHA384.keys_size_10(key varchar primary key, hash varchar);
create table keys_SHA384.keys_size_11(key varchar primary key, hash varchar);
create table keys_SHA384.keys_size_12(key varchar primary key, hash varchar);

create table keys_SHA512.minor_keys(key varchar primary key, hash varchar);
create table keys_SHA512.keys_size_5(key varchar primary key, hash varchar);
create table keys_SHA512.keys_size_6(key varchar primary key, hash varchar);
create table keys_SHA512.keys_size_7(key varchar primary key, hash varchar);
create table keys_SHA512.keys_size_8(key varchar primary key, hash varchar);
create table keys_SHA512.keys_size_9(key varchar primary key, hash varchar);
create table keys_SHA512.keys_size_10(key varchar primary key, hash varchar);
create table keys_SHA512.keys_size_11(key varchar primary key, hash varchar);
create table keys_SHA512.keys_size_12(key varchar primary key, hash varchar);



