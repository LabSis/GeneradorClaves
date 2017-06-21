/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

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
        @param keyspace
        @param tableName: nombre correspondiente a la tabla que se esté trabajando
    */
    public void initConsulta(String keyspace, String tableName){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ");
        stringBuilder.append(keyspace);
        stringBuilder.append(".");
        stringBuilder.append(tableName);
        stringBuilder.append("(key, hash) VALUES (?,?)");
           
        statement = stringBuilder.toString();
      
        
    }
    public void guardarClaves(String clave, String hash){
        SESSION.execute(statement, clave, hash);
     
    }
    public void cerrarConexion(){
        CONNECTOR.close();
    }

    
}
