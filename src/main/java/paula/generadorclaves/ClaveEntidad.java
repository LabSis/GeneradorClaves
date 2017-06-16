/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paula.generadorclaves;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author paula
 */
@Entity
@Table(name= "keys_size_1", schema = "keys_MD5@cassandra_pu")
public class ClaveEntidad {
    public ClaveEntidad(){}
    @Id
    private String idClave;
    
    @Column(name="keys_1")
    private String keys_1;
    
    @Column(name="hash")
    private String hash;
    
    public void setID(String idClave){
        this.idClave = idClave;
    }
    public void setClave(String keys_1){
        this.keys_1 = keys_1;
    }
    public void setHash(String hash){
        this.hash = hash;
    }
    public String getID(){
        return idClave;
    }
    public String getClave(){
        return keys_1;
    }
    public String getHash(){
        return hash;
    }
}
