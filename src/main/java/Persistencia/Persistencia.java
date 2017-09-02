/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 *
 * @author paula
 */
public class Persistencia {
    private final Connector CONNECTOR;
    private final Session SESSION;
    private String statement;
    
    public Persistencia(String host, int port){
        CONNECTOR = new Connector();
        CONNECTOR.connect(host, port);
        SESSION = CONNECTOR.getSesion();
    }
    /*
        @param keyspace: nombre correspondiente al keyspace que se está trabajando... tener en cuenta que tengo
                         uno por cada hash trabajado
        @param tableName: nombre correspondiente a la tabla que se esté trabajando
    */
    public void initInsert(String keyspace, String tableName){
        try{
            StringBuilder insert = new StringBuilder();
            insert.append("INSERT INTO ");
            insert.append(keyspace);
            insert.append(".");
            insert.append(tableName);
            insert.append("(key, hash) VALUES (?,?)");
            statement = insert.toString();
        }
        catch(Exception e){
             System.out.println("Error de la base de datos: " + e.getLocalizedMessage());
            //System.out.println("__ERROR__");
            
        }
        
    }
    public void guardarClaves(String clave, String hash){
       try{
           SESSION.execute(statement, clave, hash);
       }
       catch(Exception e){
           System.out.println("Error al guardar los datos: " + e.getLocalizedMessage());
            //System.out.println("__ERROR__");
       }
    }
    /*
        @param keyspace: nombre correspondiente al keyspace que se está trabajando... tener en cuenta que tengo
                         uno por cada hash trabajado. En este caso es necesario para saber en cuál me posiciono 
                         para hacer la búsqueda del hash
                         (claves menores o iguales a 4)
    */
    public String initSelect(String keyspace){
        try{
            StringBuilder select = new StringBuilder();
            select.append("SELECT * FROM keys_");
            select.append(keyspace);
            select.append(".minor_keys");
            select.append(" WHERE hash = ?");
            select.append(" ALLOW FILTERING");

            statement = select.toString();
            //statement = "SELECT * FROM pruebas.pruebas WHERE hash = ? ALLOW FILTERING";
            return statement;
        }
        catch(Exception e){
            System.out.println("Error de la base de datos: " + e.getLocalizedMessage());
            //System.out.println("__ERROR__");
            return null;
        }
        
    }
    /*
    keyspace: según el algoritmo que esté utilizando
    */
    public String initSelectRT(String keyspace, int longitud){
        
        try{
            StringBuilder select = new StringBuilder();
            select.append("SELECT * FROM keys_");
            select.append(keyspace);
            select.append(".keys_size_");
            select.append(longitud);
            select.append(" WHERE hash = ?");
            select.append(" ALLOW FILTERING");

            statement = select.toString();  
            
            return statement;
        }
        catch(Exception e){
            System.out.println("Error de la base de datos: " + e.getLocalizedMessage());
            //System.out.println("__ERROR__");
            return null;
        }
    }
    
    /*
        @param hash: valor de hash al cual le quiero buscar una coincidencia
    */
    public String reverseHash(String hash){
        String key = null;
        try{
            ResultSet rs = SESSION.execute(statement, hash);
            Row row = rs.one();
            if (!row.isNull("key")) {
                 key = row.getString("key");
                  //System.out.println("La clave es: " + key);
            }
        }
        catch(Exception e){
            //System.out.println("Error en la búsqueda: " + e.getLocalizedMessage());
            //System.out.println("__ERROR__");
        }
       
        return key;
    }
    /*
        Dada una determinada palabra (hash reducido) busco si tiene una clave asociada
    */
    public boolean coincidenciaRT(String palabra){
        try{
            ResultSet rs = SESSION.execute(statement, palabra);
            Row row = rs.one();
            if (!row.isNull("key")) {return true;}
        }
        catch(Exception e){return false;}
        return false;
    }
    public void cerrarConexion(){
        CONNECTOR.close();
    }   
}
