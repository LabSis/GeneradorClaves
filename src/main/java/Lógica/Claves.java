/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author paula
 */
public class Claves {
   
    private String hash;
    private final ArrayList<String> listadoClaves;
    private final Map<String, String> hashPorClave;
    private final Map<String, Integer> colisiones;
    
    public Claves(){ 
       
        hash = null;
        listadoClaves = new ArrayList<>();
        hashPorClave = new HashMap<>();
        colisiones = new HashMap<>();
    }
    public ArrayList<String> getListadoClaves(){
        return listadoClaves;
    }
    public Map<String, String> getHashPorClave(){
        return hashPorClave;
    }
   
    /*A partir de aquí los métodos necesarios para generar las claves, y sus respectivos hashes*/
    
    /*  
        Método para generar contraseñas, utilizando técnica de Variación sin repeticiones
    
        @param alfabeto: caracteres a partir de los cuales voy a generar mi contraseña
        @param longitud: longitud de la contraseña
        @param aux: 
        @param longitudAlfabeto: longitud del alfabeto con el cual estoy trabajando
    
    */
    
    public void variacion(String[] alfabeto, String act, int longitud, int longitudAlfabeto) {
        longitudAlfabeto = alfabeto.length;  
        
        if (longitud != 0){
           for (int i = 0; i < longitudAlfabeto; i++) {
               
               
                variacion(alfabeto, act + alfabeto[i], longitud - 1, longitudAlfabeto);
//               
            }
        } else {
              listadoClaves.add(act); //agrego la clave a mi listado de claves
        }
    }
     /*
        Método que genera un hash de una determinada clave
    
        @param algoritmo: MD5, SHA-1, SHA 224, SHA-256, SHA-384, SHA-512
        @param clave: clave de la cual quiero obtener el valor de hash
     
     */
    private String generarHash(String algoritmo, String clave){
    try {
        
        byte bytesContraseña[] = clave.getBytes();
        
        MessageDigest messageDigest = MessageDigest.getInstance(algoritmo);
        byte[] array = messageDigest.digest(bytesContraseña);
       
        StringBuilder stringBuilder = new StringBuilder();
        
        for (int i = 0; i < array.length; ++i) {
          String out = Integer.toHexString((array[i] & 0xFF) | 0x100);
          stringBuilder.append(out.substring(1,3));
        }
        return stringBuilder.toString();
    }
  
    catch (NoSuchAlgorithmException e) {
        StringBuilder exception = new StringBuilder();
        exception.append("El algoritmo seleccionado es ");
        exception.append(algoritmo);
        exception.append(", pero es inválido. ");
       
        return exception.toString();
    }
    }
    /*
        Método que retorna un Hash Map que contiene los valores de las contraseñas y sus respectivos hashes
    
        @return hashPorClave:  <clave, hash> (para que admita valores duplicados de hash, en caso de haberlos)
    */
    public Map<String, String> hashParaCadaClave(String algoritmo){
       
       String clave;
       int size = listadoClaves.size();
       int repeticiones = 1;
       
       for (int i = 0; i < size; i++) {
        clave = listadoClaves.get(i);
        hash = generarHash(algoritmo, clave);
        hashPorClave.put(clave, hash);
        
        /*
            al mismo tiempo que genero la dupla hash-clave verifico la existencia de colisiones,
            y en caso de que existan, la cantidad de veces que ese hash se repite.
        */
           if (hashPorClave.containsKey(hash)) {
              if(!colisiones.containsKey(hash)){
                  
                    colisiones.put(hash, repeticiones);
              }
              else{
                  repeticiones++;
                  colisiones.put(hash, repeticiones);
              }
           }
        }
       listadoClaves.clear();
       return hashPorClave;
    }
    
       
}
