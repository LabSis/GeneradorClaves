/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Lógica.ControladorPrincipal;
import java.util.Scanner;

/**
 *
 * @author paula
 */
public class Main {
    public static void main(String args[]){
        
    }
    public static void guardarClaves(){
        Scanner scanner = new Scanner(System.in);
        int opcion;       
       
        do{
            System.out.println("Opciones: ");
            System.out.println("1-Generar claves para MD5");
            System.out.println("2-Generar claves para SHA1");
            System.out.println("3-Generar claves para SHA224");
            System.out.println("4-Generar claves para SHA256");
            System.out.println("5-Generar claves para SHA384");
            System.out.println("6-Generar claves para SHA512");
            System.out.println("7-SALIR");
            
          
            opcion = scanner.nextInt();
            int longitud;
            ControladorPrincipal controlador;
           
            switch(opcion){
                case 1:
                     System.out.print("Seleccionar longitud claves a generar: ");
                    longitud = scanner.nextInt();
                    controlador = new ControladorPrincipal(longitud, "MD5");
                    controlador.guardarClaves();
                    break;
                case 2:
                    System.out.print("Seleccionar longitud claves a generar: ");
                    longitud = scanner.nextInt();
                    controlador = new ControladorPrincipal(longitud, "SHA1");
                    controlador.guardarClaves();
                    break;
                case 3:
                    System.out.print("Seleccionar longitud claves a generar: ");
                    longitud = scanner.nextInt();
                    controlador = new ControladorPrincipal(longitud, "SHA224");
                    controlador.guardarClaves();
                    break;
                case 4:
                    System.out.print("Seleccionar longitud claves a generar: ");
                    longitud = scanner.nextInt();
                    controlador = new ControladorPrincipal(longitud, "SHA256");
                    controlador.guardarClaves();
                    break;    
                case 5:
                    System.out.print("Seleccionar longitud claves a generar: ");
                    longitud = scanner.nextInt();
                    controlador = new ControladorPrincipal(longitud, "SHA384");
                    controlador.guardarClaves();
                    break;
                case 6:
                    System.out.print("Seleccionar longitud claves a generar: ");
                    longitud = scanner.nextInt();
                    controlador = new ControladorPrincipal(longitud, "SHA512");
                    controlador.guardarClaves();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
        }while(true);  
        

    }
    public static void hashInverso(){
        
        Scanner scanner = new Scanner(System.in);
        int opcion;
        ControladorPrincipal controlador = new ControladorPrincipal();
        String hash; 
        do{
            System.out.println("Opciones: ");
            System.out.println("1-Hash Inverso para MD5");
            System.out.println("2-Hash Inverso para SHA1");
            System.out.println("3-Hash Inverso para SHA224");
            System.out.println("4-Hash Inverso para SHA256");
            System.out.println("5-Hash Inverso para SHA384");
            System.out.println("6-Hash Inverso para SHA512");
            System.out.println("7-Salir");
            
            opcion = scanner.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("Ingrese hash para obtener su inverso: ");
                    hash = scanner.next();
                    controlador.reverseHash("MD5", hash);
                    break;
                case 2:
                    System.out.println("Ingrese hash para obtener su inverso: ");
                    hash = scanner.next();
                    controlador.reverseHash("SHA1", hash);
                    break;
                case 3:
                    System.out.println("Ingrese hash para obtener su inverso: ");
                    hash = scanner.next();
                    controlador.reverseHash("SHA224", hash);
                    break;
                case 4:
                    System.out.println("Ingrese hash para obtener su inverso: ");
                    hash = scanner.next();
                    controlador.reverseHash("SHA256", hash);
                    break;
                case 5:
                    System.out.println("Ingrese hash para obtener su inverso: ");
                    hash = scanner.next();
                    controlador.reverseHash("SHA384", hash);
                    break;
                case 6:
                    System.out.println("Ingrese hash para obtener su inverso: ");
                    hash = scanner.next();
                    controlador.reverseHash("SHA512", hash);
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                    break;
            }       
        }while(true);  
    }
        
    
}