/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paula.generadorclaves;

import com.impetus.client.cassandra.common.CassandraConstants;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author paula
 */
public class Main {
    public static void main(String args[]){
        System.out.println("Main class.");
        
        try{
        ClaveEntidad claves = new ClaveEntidad();
        claves.setID("1");
        claves.setClave("prueba");
        claves.setHash("c893bad68927b457dbed39460e6afd62");
            Map<String, String> props = new HashMap<>();
        props.put(CassandraConstants.CQL_VERSION, CassandraConstants.CQL_VERSION_3_0);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cassandra_pu", props);
        EntityManager em = emf.createEntityManager();
    
        em.persist(claves);
        em.close();
        emf.close();
            System.out.println("Inserción exitosa");
        }
        catch(Exception e){
            System.out.println("Un error ha ocurrido: " + e.getLocalizedMessage());
        }
        
        
        
    }

}