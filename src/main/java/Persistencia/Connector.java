/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

/**
 *
 * @author paula
 */
public class Connector {
    private Cluster cluster;
    private Session session;
    
    public void connect(final String node, final int port){
         this.cluster = Cluster.builder().addContactPoint(node).withPort(port).build();
         session = cluster.connect();

    }
    public Session getSesion(){
        return this.session;
    }
    public void close(){
        cluster.close();
    }
}
