/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import java.io.File;
import java.util.Map;

/**
 *
 * @author paula
 */
public class ControladorClaves {
    private final String auxiliar;
    private final String[] alfabeto;
    private final int longitudAlfabeto;
    private final int longitudClave;
    
    private final String algoritmo;
    
    private final Claves claves;
    private final ClavesArchivo clavesArchivo;
    private Map<String, String> hashParaCadaClave;

    
    //este constructor DEBIERA utilizarse una sola vez, cuando se genere la bd de claves
    public ControladorClaves(int longitudClave, String algoritmo){
        auxiliar = "|1234567890’¿qwertyuiop'+asdfghjklñ{}<zxcvbnm,.-°!”#$%&/()=?¡\"*[]>;:_¬\\~^`@QWERTYUIOPASDFGHJKLÑZXCVBNM ";
        alfabeto = auxiliar.split("");
        longitudAlfabeto = alfabeto.length;
        this.longitudClave = longitudClave;
        this.algoritmo = algoritmo;
        
        claves = new Claves();
        clavesArchivo = new ClavesArchivo();
    }
    /*
        @deprecado
        @return Map que contiene duplas hash-clave
    */
    public Map<String, String> generarClaves(){
        claves.variacion(alfabeto, "" , longitudClave, longitudAlfabeto);
       
        hashParaCadaClave = claves.hashParaCadaClave(algoritmo); //este es el hashmap que tendría que guardar
        return hashParaCadaClave;
    }
    /*
        @return el archivo que contiene todas las claves posibles
    
    */
    public File generarArchivo(){
        clavesArchivo.generarCombinaciones(auxiliar.toCharArray(), longitudClave);
        return clavesArchivo.getFile();
    }
    public String generarHash(String algoritmo, String clave){
        return clavesArchivo.generarHash(algoritmo, clave);
    }
    public String getAlgoritmoUsado(){
        return algoritmo;
    } 
}
