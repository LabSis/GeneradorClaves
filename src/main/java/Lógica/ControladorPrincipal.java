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

/**
 *
 * @author paula
 */
public class ControladorPrincipal {
  private final Persistencia persistencia;  
  private final ControladorClaves controladorClaves;
  
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
        persistencia.initInsert(keyspace.toString(), table.toString());
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
  public String reverseHash(String algoritmo, String hash){
      try{
          persistencia.initSelect(algoritmo);
          return persistencia.reverseHash(hash);
      }
      catch(Exception e){
          //System.out.println("No se encontró coincidencia con la actual base de datos");
          System.out.println("__ERROR__");
          return "";
      }
 }
      
    /*
        @param hash
        método que tiene por objetivo recuperar la clave coincidente con el hash dado
    */
    public boolean buscarCoincidencia(String algoritmo, String hash, int longitud){
        boolean flag = false; //porque todavía no se si hay una coincidencia
        persistencia.initSelectRT(algoritmo, longitud);
        
        String palabra = controladorClaves.getReduccion(hash);
        String hash_aux;
        
        String clave; //vendría a ser la clave
        String hashEncontrado;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <=100; i++) {
           //si la palabra reducida está en la base de datos, compruebo si el hash es coincidente
            if (persistencia.coincidenciaRT(palabra)) {
              clave = persistencia.reverseHash(palabra);
              hashEncontrado = controladorClaves.generarHash(algoritmo, clave);
             
              if (hashEncontrado.compareTo(hash)==0) {
                sb.append("Se ha encontrado coincidencia: ");
                sb.append(clave);
                sb.append(" - ");
                sb.append(hashEncontrado);
                System.out.println(sb.toString());
                flag = true;
                break;

                }
            }
               hash_aux = controladorClaves.generarHash(algoritmo, palabra);
               palabra = controladorClaves.getReduccion(hash_aux);
         }

        
        return flag;    
    }
    /*recorrer la base de datos para hacer la búsqueda*/
    public String recorrerBD(String algoritmo, String hash){
        StringBuilder sb = new StringBuilder();
        for (int i = 4; i < 13; i++) {
            
            if (i<=4) {
                if (reverseHash(algoritmo, hash) !=null) {
                  
                    sb.append("Se ha encontrado coincidencia: ");
                    sb.append(reverseHash(algoritmo, hash));
                    break;
                }
            }
            
            if (buscarCoincidencia(algoritmo, hash, i)) {
                break;
            }
        }
        persistencia.cerrarConexion();
        return sb.toString();
    }
}
