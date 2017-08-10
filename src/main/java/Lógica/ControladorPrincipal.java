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
import java.util.ArrayList;
import java.util.HashMap;
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

  public void guardarClavesArchivo(){
      File archivoClaves = controladorClaves.generarArchivo();
      String algoritmo = controladorClaves.getAlgoritmoUsado();
      BufferedReader br;
      FileReader fr;
      String clave;
      String hash;
      StringBuilder keyspace = new StringBuilder();
      StringBuilder table = new StringBuilder();
      
      keyspace.append("keys_");
      keyspace.append(controladorClaves.getAlgoritmoUsado());
      if (controladorClaves.getLongitudClave()<=4) {
         table.append("minor_keys");
      }
      else{
          table.append("keys_size_");
          table.append(controladorClaves.getLongitudClave());
      }
      
      try{
        persistencia.initInsert(keyspace.toString(), table.toString());
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
  /*
    Método de prueba nada más
  */
  public void guardarRT(){
      File archivoClaves = controladorClaves.generarArchivo();
      String algoritmo = controladorClaves.getAlgoritmoUsado();
      BufferedReader br;
      FileReader fr;
      String clave;
            
      String vector[];
      
      String keyspace = "pruebas";
      String tabla = "pruebas";
     
      try{
        persistencia.initInsert(keyspace, tabla);
        fr = new FileReader(archivoClaves);
        br = new BufferedReader(fr);
        clave = br.readLine();
        
        while(clave!=null){
            
            vector = controladorClaves.generarRT(algoritmo, clave);
            persistencia.guardarClaves(vector[0], vector[1]);
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
  /*
    Devuelve una contraseña dado un determinado hash
    Sirve para claves de longitud menor o igual a cuatro
  */
  public void reverseHash(String keyspace, String hash){
      try{
          persistencia.initSelect(keyspace);
          persistencia.reverseHash(hash);
      }
      catch(Exception e){
          System.out.println("No se encontró coincidencia con la actual base de datos");
      }
      finally{
          persistencia.cerrarConexion();
      }
  }
  public void procesoInverso(String algoritmo, String hashACrackear){
      String palabraEncontrada;
      HashMap<String, String> dupla ;
      try{
          
          ArrayList<String> palabras = controladorClaves.procesoInverso(hashACrackear, algoritmo);
          persistencia.initSelectBis();
          for (int i = 0; i < palabras.size(); i++) {
             if (persistencia.existeCoincidencia(palabras.get(i))) {
             
             //la palabra encontrada vendría a ser el hash reducido que coincidió primero? i guess so
             palabraEncontrada = persistencia.yanose(palabras.get(i));

//             palabras.clear();
             dupla = controladorClaves.encontrarHash(algoritmo, palabraEncontrada);

             dupla.forEach((String hash, String palabra)->{
              if (hash.equals(hashACrackear)) {
                  System.out.println("Hash encontrado es: ");
                  System.out.println(palabra + " " + hash);

              }
                
             });

              }
          }
      }
      catch(Exception e){
          System.out.println("ERROR: " + e.getMessage() );
      }
      finally{
          persistencia.cerrarConexion();
      }
   
  }

}
