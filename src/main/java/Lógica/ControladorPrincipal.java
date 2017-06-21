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
  private final ControladorClaves controladorClaves;
  
  public ControladorPrincipal(int longitudClave, String algoritmo){
      persistencia = new Persistencia("localhost", 9042);
      controladorClaves = new ControladorClaves(longitudClave, algoritmo); 
      
  }
  public void guardarClaves(){
      Map<String, String> hashParaCadaClave = controladorClaves.generarClaves();
      
      StringBuilder tabla = new StringBuilder();
      tabla.append("keys_size_");
      tabla.append(controladorClaves.getLongitudClave());
      StringBuilder keyspace = new StringBuilder();
      keyspace.append("keys_");
      keyspace.append(controladorClaves.getAlgoritmoUsado());
     
      try{
        persistencia.initConsulta(keyspace.toString(), tabla.toString());
      
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
}
