/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;
import Persistencia.Persistencia;
import java.util.Map;
/**
 *
 * @author paula
 */
public class ControladorPrincipal {
  private final Persistencia persistencia;  
  private ControladorClaves controladorClaves;
  
  public ControladorPrincipal(){
      persistencia = new Persistencia("localhost", 9042);
  }
  
  public ControladorPrincipal(int longitudClave, String algoritmo){
      persistencia = new Persistencia("localhost", 9042);
      controladorClaves = new ControladorClaves(longitudClave, algoritmo); 
  }
  public void guardarClaves(){
      Map<String, String> hashParaCadaClave = controladorClaves.generarClaves();
      
      StringBuilder keyspace = new StringBuilder();
      keyspace.append("keys_");
      keyspace.append(controladorClaves.getAlgoritmoUsado());
      
      String tabla = "minor_keys";
     
      try{
        persistencia.initInsert(keyspace.toString(), tabla);
        hashParaCadaClave.entrySet().forEach((entry) -> {
            String clave = entry.getKey();
            String hash = entry.getValue();
            persistencia.guardarClaves(clave, hash);
        
        });
          System.out.println("EL PROCESO SE HA COMPLETADO DE MANERA CORRECTA");
          
      }
      catch(Exception e){
          System.out.println("ERROR: " + e.getLocalizedMessage());
      }
      finally{
          persistencia.cerrarConexion();
      }
  }
  public void reverseHash(String keyspace, String hash){
      try{
          persistencia.initSelect(keyspace);
          persistencia.reverseHash(hash);
      }
      catch(Exception e){
          System.out.println("Error: " + e.getLocalizedMessage());
      }
      finally{
          persistencia.cerrarConexion();
      }
  }
}
