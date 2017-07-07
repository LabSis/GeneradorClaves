/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;
import Persistencia.Persistencia;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
  public void guardarClavesArchivo(){
      File archivoClaves = controladorClaves.generarArchivo();
      String algoritmo = controladorClaves.getAlgoritmoUsado();
      BufferedReader br;
      FileReader fr;
      String clave;
      String hash;

      StringBuilder keyspace = new StringBuilder();
      keyspace.append("keys_");
      keyspace.append(controladorClaves.getAlgoritmoUsado());

      String tabla = "minor_keys";
     
      try{
        persistencia.initInsert(keyspace.toString(), tabla);
        fr = new FileReader(archivoClaves);
        br = new BufferedReader(fr);
        clave = br.readLine();
        
        while(clave!=null){
            hash = controladorClaves.generarHash(algoritmo, clave);
            persistencia.guardarClaves(clave, hash);
            clave = br.readLine();
        }

          System.out.println("EL PROCESO SE HA COMPLETADO DE MANERA CORRECTA");
          
      }
      catch(IOException e){
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
