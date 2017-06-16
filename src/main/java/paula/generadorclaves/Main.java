/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paula.generadorclaves;

import com.impetus.client.cassandra.common.CassandraConstants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author paula
 */
public class Main {
    public static void main(String args[]){
        System.out.println("Main class.");
        
        try{
        ClaveEntidad claves = new ClaveEntidad();
      
        claves.setClave("prueba");
        claves.setHash("c893bad68927b457dbed39460e6afd62");
        Map<String, String> props = new HashMap<>();
        props.put(CassandraConstants.CQL_VERSION, CassandraConstants.CQL_VERSION_3_0);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cassandra_pu", props);
        EntityManager em = emf.createEntityManager();
        em.persist(claves);
        em.close();
        emf.close();
            
        
//        String query = "SELECT * FROM keys_md5.keys_size_1";
//        Query q = em.createNamedQuery(query);
//        q.getFirstResult();
//        
        System.out.println("Inserción exitosa");
        
        }
        catch(Exception e){
            System.out.println("Un error ha ocurrido: " + e.getLocalizedMessage());
        }
       
        
        
        
    }

}