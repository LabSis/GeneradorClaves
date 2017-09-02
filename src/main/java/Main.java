/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Lógica.ControladorPrincipal;
import java.util.regex.Pattern;
/**
 *
 * @author paula
 */
public class Main {
    /*
        Carga de datos o búsqueda de hash, según los argumentos recibidos. 
        @param args[0]: <run> para realizar una búsqueda, <load> para cargar datos
               args[1]: tipo de hash
               args[2]: hash a crackear o longitud de las claves a generar.
    */
    public static void main(String args[]) {

       Pattern patronHash = Pattern.compile("^[a-zA-Z0-9]+$");
       Pattern patronTipoHash = Pattern.compile("^[a-zA-Z]{2,3}[0-9]$");
       try{
           //compruebo que la cantidad de argumentos ingresados sea válida
           if (args.length==3) {
               if (args[0].compareTo("run") == 0){

                   //verifico que el hash contenga caracteres válidos
                   if (patronTipoHash.matcher(args[1]).find() && patronHash.matcher(args[2]).find()) {
                    //verifico que el largo del hash ingresado se encuentre en un rango válido
                    if (args[2].length()>=32 && args[2].length()<=128) {
                         hashInverso(args);
                    }
                   }

            }
               else{
                   if(args[0].compareTo("load") == 0){
                       if (Integer.parseInt(args[2]) < 12) {
                           guardarClaves(args);
                       }
                   }
            }}
          else{
               //System.out.println("Ingresar la cantidad correcta de argumentos.");
               System.out.println("__ERROR__");
           }
           }
            
        catch(NumberFormatException e){
            //System.out.println("Error de argumentos. ");
            System.out.println("__ERROR__");
        }
        
    }

    public static void guardarClaves(String args[]) {
        ControladorPrincipal controlador;
        //longitud clave, tipo de hash a generar
        controlador = new ControladorPrincipal(Integer.parseInt(args[2]), args[1]);
        //genera todas las claves con sus respectivos hash, según una longitud deseada y un tipo de hash determinado.
        controlador.guardarClavesArchivo();
    }

    public static void hashInverso(String args[]) {
        ControladorPrincipal controlador = new ControladorPrincipal();
        //tipo de hash, hash a crackear
        //controlador.reverseHash(args[1], args[2]);
        controlador.recorrerBD(args[1], args[2]);
    }
}
