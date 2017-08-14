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
        StringBuilder insert = new StringBuilder();
        insert.append("INSERT INTO ");
        insert.append(keyspace);
        insert.append(".");
        insert.append(tableName);
        insert.append("(key, hash) VALUES (?,?)");
           
        statement = insert.toString();
    }
    public void guardarClaves(String clave, String hash){
       SESSION.execute(statement, clave, hash);
    }
    /*
        @param keyspace: nombre correspondiente al keyspace que se está trabajando... tener en cuenta que tengo
                         uno por cada hash trabajado. En este caso es necesario para saber en cuál me posiciono 
                         para hacer la búsqueda del hash
    */
    public String initSelect(String keyspace){
        StringBuilder select = new StringBuilder();
        select.append("SELECT * FROM keys_");
        select.append(keyspace);
        select.append(".minor_keys");
        select.append(" WHERE hash = ?");
        select.append(" ALLOW FILTERING");
                
        statement = select.toString();
        return statement;
    }
    /*
        trial
    */
    public String initSelectBis(){
        StringBuilder select = new StringBuilder();
        select.append("SELECT * FROM pruebas.pruebas WHERE key = ?");
        select.append("ALLOW FILTERING");
        
        statement = select.toString();
        return statement;
    }
    /*
        @param hash: valor de hash al cual le quiero buscar una coincidencia
    */
    public String reverseHash(String hash){
        String key = "";
        try{
            ResultSet rs = SESSION.execute(statement, hash);
            Row row = rs.one();
            if (row.getString("key") != null) {
                 key = row.getString("key");
                 System.out.println("La clave es: " + key);
            }
        }
        catch(Exception e){
            //System.out.println("Error en la búsqueda: " + e.getLocalizedMessage());
            System.out.println("Error en la búsqueda.");
        }
        return key;
    }
    public String yanose(String primeraCoincidencia){
        try{
            ResultSet rs = SESSION.execute(statement, primeraCoincidencia);
            Row row = rs.one();
            if (row.getString("hash") != null) {
               return row.getString("hash");
            }
        }
        catch(Exception e){
            System.out.println("ya no se que error poneeeer");
        }
        return null;
    }
    
    public boolean existeCoincidencia(String palabra){
        boolean flag = false;
       
        try{
             ResultSet rs = SESSION.execute(statement, palabra);
             Row row = rs.one();
             if (row.getString("hash")!=null) {
                flag = true;
                
             }
        }
        catch(Exception e){
//            System.out.println("no hay coincidencias");
        }
        return flag;
    }
    
    public void cerrarConexion(){
        CONNECTOR.close();
    }

    
}
