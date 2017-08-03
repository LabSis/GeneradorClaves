/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import java.io.File;
/**
 *
 * @author paula
 */
public class ControladorClaves {
    private final String alfabeto;
    private final int longitudClave;
    
    private final String algoritmo;
    
    private final ClavesArchivo clavesArchivo;
     
    //este constructor DEBIERA utilizarse una sola vez, cuando se genere la bd de claves
    public ControladorClaves(int longitudClave, String algoritmo){
        alfabeto = "1234567890|’¿qwertyuiop'+asdfghjklñ{}<zxcvbnm,.-°!”#$%&/()=?¡\"*[]>;:_¬\\~^`@QWERTYUIOPASDFGHJKLÑZXCVBNM ";
        this.longitudClave = longitudClave;
        this.algoritmo = algoritmo;
        
        clavesArchivo = new ClavesArchivo();
    }
    /*
        @return el archivo que contiene todas las claves posibles
    */
    public File generarArchivo(){
        clavesArchivo.generarCombinaciones(alfabeto.toCharArray(), longitudClave);
        return clavesArchivo.getFile();
    }
    public String generarHash(String algoritmo, String clave){
        return clavesArchivo.generarHash(algoritmo, clave);
    }
    public String getAlgoritmoUsado(){
        return algoritmo;
    } 
}
