/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Lógica.ControladorPrincipal;
import Lógica.*;
/**
 *
 * @author paula
 */
public class Main {
    public static void main(String args[]){
        
        //args[0] = acción a realizar
        //<run> para hacer una búsqueda
        //<load> para cargar datos
       
        //agregar validaciones para verificar que se ingresen los argumentos correctos.
        if (args[0].compareTo("run") == 0){
             //args[1] = tipo de hash
            //args[2] = hash a crackear
                
            hashInverso(args);
        }
        else{
           if(args[0].compareTo("load") == 0){
            //carga masiva
            //args[1] = longitud clave
            //args[2] = tipo de hash
            guardarClaves(args);
           }
           else{
               System.out.println("argumento inválido");
           }
        }
    }
    public static void guardarClaves(String args[]){
        ControladorPrincipal controlador;
        //longitud clave, tipo de hash a generar
        controlador = new ControladorPrincipal(Integer.parseInt(args[1]), args[2]);
        //genera todas las claves con sus respectivos hash, según una longitud deseada y un tipo de hash determinado.
        controlador.guardarClavesArchivo();
    }
    public static void hashInverso(String args[]){
        ControladorPrincipal controlador = new ControladorPrincipal();
        //tipo de hash, hash a crackear
        controlador.reverseHash(args[1], args[2]);
    }
        
    
}