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
      controladorClaves = new ControladorClaves();
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
      String dupla[];
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
        persistencia.initInsert("pruebas", "pruebas");
       // persistencia.initInsert(keyspace.toString(), table.toString());
        fr = new FileReader(archivoClaves);
        br = new BufferedReader(fr);
        clave = br.readLine();
        
        while(clave!=null){
            if (controladorClaves.getLongitudClave()<=4) {
                hash = controladorClaves.generarHash(algoritmo, clave);
                persistencia.guardarClaves(clave, hash);
                clave = br.readLine();
            }
            else{
                dupla = controladorClaves.getDuplas(algoritmo, clave);
                persistencia.guardarClaves(dupla[0], dupla[1]);
                clave = br.readLine();
            }
        }

          System.out.println("EL PROCESO SE HA COMPLETADO DE MANERA CORRECTA");
          
      }
      catch(IOException e){
          System.out.println("__ERROR__");
      }
      finally{
          persistencia.cerrarConexion();
      }
  }
/*
    Devuelve una contraseña dado un determinado hash
    Sirve para claves de longitud menor o igual a cuatro
    Como controlo la longitud???
  */
  public String reverseHash(String keyspace, String hash){
      try{
          persistencia.initSelect(keyspace);
          return persistencia.reverseHash(hash);
      }
      catch(Exception e){
          //System.out.println("No se encontró coincidencia con la actual base de datos");
          System.out.println("__ERROR__");
          return "";
      }
      finally{
          persistencia.cerrarConexion();
      }
 }
      
    /*
        @param hash
        método que tiene por objetivo recuperar la clave coincidente con el hash dado
    */
    public void buscarCoincidencia(String algoritmo, String hash){
            persistencia.initSelect("pruebas");
            String palabra = controladorClaves.getReduccion(hash);
            System.out.println("palabra inicial: " + palabra);
            String hash_aux;
            System.out.println("Hash inicial: " + hash);
            
            String aux;
            String aux2;
            
            for (int i = 0; i <=100; i++) {
               //si la palabra reducida no está en la base de datos, sigo recorriendo...
                if (persistencia.reverseHash(palabra)==null) {
                   
                }
                else{
                   
                  aux = persistencia.reverseHash(palabra);
                  aux2 = controladorClaves.generarHash(algoritmo, aux);
                  
                  if (aux2.compareTo(hash)==0) {
                  System.out.println("se ha encontrado coincidencia: " + palabra);
                  System.out.println(aux + " - " + aux2);
                  break;                                                                                                                                                
                    }
                }
                hash_aux = controladorClaves.generarHash(algoritmo, palabra);
                   palabra = controladorClaves.getReduccion(hash_aux);
               
             }
            System.out.println("proceso finalizado");
    
    }
}
