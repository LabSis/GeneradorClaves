/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author paula
 */
public class ClavesArchivo {
    private static File archivoClaves;
    private static File archivoHashes;
    private String hash;
    
    
    public ClavesArchivo(){
        archivoClaves = new File("claves.txt");
        archivoHashes = new File("hashes.txt");
        hash = null;
        
    }
    public File getFile(){
        return archivoClaves;
    }
    public  void generarCombinaciones(char[] alfabeto, int longitud){
        List<String> combinaciones = new LinkedList<>();
        int indice = 0;
        int contador = 0;
        
        StringBuilder buffer = new StringBuilder();
        
        int indicesAlfabeto[] = new int[longitud];
        while(indice < longitud){
            for(int i = indicesAlfabeto[indice]; i < alfabeto.length; i++, indicesAlfabeto[indice]++){
                char caracter = alfabeto[i];
                buffer.append(caracter);
                if(indice == longitud - 1){
                    combinaciones.add(buffer.toString());
                    buffer.setLength(buffer.length() - 1);
                    contador++;

                    if(contador % 100000 == 0){
                        // Cada 100000 generadas podríamos ir escribiendo en algún lado.
                        escribir(combinaciones);
                        combinaciones.clear();
                    }
                } else {
                    indicesAlfabeto[indice]++;
                    break;
                }
            }
            // A la salida de este for tenemos 3 posibilidades.
            if(indice < longitud - 1 && indicesAlfabeto[indice] < alfabeto.length){
                indice++;
            }
            
            if(indice < longitud - 1 && indicesAlfabeto[indice] == alfabeto.length){
                indice++;
            }
            if(indice == longitud - 1 && indicesAlfabeto[indice] == alfabeto.length){
                while(indicesAlfabeto[indice] == alfabeto.length){
                    if(buffer.length() == 0){
                        indice = longitud;
                        break;
                    }
                    buffer.setLength(buffer.length() - 1);
                    indicesAlfabeto[indice] = 0;
                    indice--;
                }
            }
        }
        escribir(combinaciones);
    }
    
    public void escribir(List<String> lista){
        try(FileWriter fw = new FileWriter(archivoClaves, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            lista.forEach((l) -> {
                out.println(l);
            });
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /*
        Método para generar un archivo que contenga todos los hashes de mis claves...
    */
    public void generarArchivosHashes(String algoritmo){
       
       String clave;
       try(FileWriter fw = new FileWriter(archivoHashes, true); 
           BufferedWriter bw = new BufferedWriter(fw);
           PrintWriter out = new PrintWriter(bw))    
       {
           FileReader fr = new FileReader(archivoClaves);
           BufferedReader br = new BufferedReader(fr);
           clave = br.readLine();
           
           while(clave!=null){
            
                hash = generarHash(algoritmo, clave);
                out.println(hash);
                
                clave = br.readLine();
            }
       }
       catch(Exception e){
           System.out.println("Error en la generación del archivo de Hashes: " + e.getLocalizedMessage());
       }
    }
    /*
        Método que genera un hash de una determinada clave
    
        @param algoritmo: MD5, SHA-1, SHA-224, SHA-256, SHA-384, SHA-512
        @param clave: clave de la cual quiero obtener el valor de hash
    
    */
    public String generarHash(String algoritmo, String clave){
        try {

            byte bytesContraseña[] = clave.getBytes();

            MessageDigest messageDigest = MessageDigest.getInstance(algoritmo);
            byte[] array = messageDigest.digest(bytesContraseña);

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < array.length; ++i) {
              String out = Integer.toHexString((array[i] & 0xFF) | 0x100);
              stringBuilder.append(out.substring(1,3));
            }
            return stringBuilder.toString(); //devuelve el valor de hash que le corresponde a la clave
        }

        catch (NoSuchAlgorithmException e) {
            StringBuilder exception = new StringBuilder();
            exception.append("El algoritmo seleccionado es ");
            exception.append(algoritmo);
            exception.append(", pero es inválido. ");

            return exception.toString();
        }
    }
 
}
