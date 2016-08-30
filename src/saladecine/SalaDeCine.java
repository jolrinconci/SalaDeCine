/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saladecine;

/**
 * Este programa nos permite administrar una taquilla de sala de cine, donde los 
 * clientes pueden relizar reservas, cancelar reservas, descuento de pagos con
 * tarjeta y venta normal de boletería; además de mostrar los asientos 
 * disponibles, vendidos y reservados.
 * 
 * @author José Luis Rincón
 * @version 1.0
 * since 28/08/2016
 */
import java.util.Scanner;//importamos Scanner para usarlo en la captura del teclado.
public class SalaDeCine {
    //variables globales.
    static int[][] asientos = new int[11][20];//En esta matriz se guardan la disponibilidad de los asientos. 1 diponible, 2 en reserva, y 3 vendido.
    static int ventaTotal=0;//total de las ventas.
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Scanner teclado = new Scanner(System.in);//captura el teclado.
        //declaración de variables.
        int[] columnas = new int[20];//variable que guarda el valor de columnas que se muestra en pantalla.
        char[] filas = new char[11];//variable que guarda el valor de filas que se muestra en pantalla.
        boolean salida = false;
        //damos valores iniciales a nuestras variables.
        columnas=valorInicialColumnas();//Valor de columnas que se muestra en pantalla.
        filas=valorInicialFilas();//Valor de filas que se muestra en pantalla.
        valorInicialAsientos();//Todos los asientos disponibles.
        
        while(!salida){//ciclo principal del programa.
            int opcion = menuPrincipal();
            if(opcion==1){//1.Vender boletas
                boolean siguiente = false;
                boolean compra = false;
                int columna=0;
                char fila=0;
                while(!compra){
                    while(!siguiente){
                        fila=selFila();
                        if(filaCorrecta(fila)==true){
                            siguiente=true;
                        }else{
                            System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                            System.out.println(":No has seleccionado una opción correcta!:");
                            System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                        }
                    }
                    while(siguiente){
                        columna=selColumna();
                        if(columnaCorrecta(columna)==true){
                            siguiente=false;
                        }else{
                            System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                            System.out.println(":No has seleccionado una opción correcta!:");
                            System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                        }
                    }
                    if(asientoVendido(columna,fila)==true){
                        compra=true;
                    }
                }
                System.out.println("Gracias por comprar!");
            }else if(opcion==2){//2.Reservar boletas
                System.out.println("Reserva con éxito!");
            }else if(opcion==3){//3.Crear TARCINE
                System.out.println("Se ha creado su tarjeta!");
            }else if(opcion==4){//4.Recargar TARCINE
                System.out.println("Recarga con éxito");
            }else if(opcion==5){//5.Pagar reservas
                System.out.println("Reserva pagada!");
            }else if(opcion==6){//6.Cancelar reserva
                System.out.println("Reserva cancelada!");
            }else if(opcion==7){//7.Mostrar disponibles
                System.out.println("Lista de asientos!      V:vendido  R:reservado");
                dibujarAsientos(columnas,filas);//imprime en pantalla los asientos disponibles.
            }else if(opcion==8){//8.Mostrar total de ventas
                System.out.println("El total de ventas es de: $"+ventaTotal);
            }else if(opcion==9){//9.Reiniciar programa
                valorInicialAsientos();//Todos los asientos disponibles.
                ventaTotal=0;
                System.out.println("Datos restablecidos!");
            }else if(opcion==0){//0.Salir
                System.out.println("Hasta luego!");
                salida=true;
            }else{
                System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                System.out.println(":No has seleccionado una opción correcta!:");
                System.out.println("::::::::::::::::::::::::::::::::::::::::::");
            }
        }
    }
    static int[] valorInicialColumnas(){
        int[] columnas = new int[20];
        for(int i=0; i<20; i++){
            columnas[i]=i+1;
        }
        return columnas;
    }//valores iniciales para imprimir en pantalla.
    static char[] valorInicialFilas(){
        char[] filas = new char[11];
        for(int i=0; i<11; i++){
            filas[i]= (char) (65+i) ;
        }
        return filas;
    }//valores iniciales para imprimir en pantalla.
    static void valorInicialAsientos(){
        for(int i=0; i<11;i++){
            for(int j=0; j<20; j++){
                asientos[i][j]=1;//1 diponible, 2 en reserva, y 3 vendido.
            }
        }
    }//estado de los asientos, inicialmente todos disponibles(1)
    static int menuPrincipal(){
        Scanner teclado = new Scanner(System.in);//captura el teclado.
        int opcion;
            System.out.println("          ¿Qué desea hacer?");
            System.out.println("1.Vender boletas    6.Cancelar reserva");
            System.out.println("2.Reservar boletas  7.Mostrar disponibles");
            System.out.println("3.Crear TARCINE     8.Mostrar total de ventas");
            System.out.println("4.Recargar TRACINE  9.Reiniciar programa");
            System.out.println("5.Pagar reservas    0.Salir");
            opcion=teclado.nextInt();
        return opcion;
    }//imprime en pantalla las opciones iniciales y retorna una opción válida.
    static void dibujarAsientos(int[]columnas, char[]filas){
        for(int i=0; i<11;i++){
            for(int j=0; j<20; j++){
                if(asientos[i][j]==1){//1 diponible, 2 en reserva, y 3 vendido.
                    System.out.print(filas[i]);
                    System.out.print(columnas[j] + "  ");
                }else if(asientos[i][j]==2){//1 diponible, 2 en reserva, y 3 vendido.
                    if(j<9){
                        System.out.print("-R  ");
                    }else{
                        System.out.print("-R-  ");
                    }
                }else{
                    if(j<9){
                        System.out.print("-V  ");
                    }else{
                        System.out.print("-V-  ");
                    }
                }
            }
            System.out.println(" ");
        }
    }//imprime en pantalla los asientos disponibles.
    static boolean asientoVendido(int columna, char fila){
        int aux=(int)fila;//convertimos fila en tipo int.
        System.out.println(aux);
        aux=aux-65;
        System.out.println(aux);
        if(asientos[aux][(columna-1)]==1){
            asientos[aux][(columna-1)]=3;
            boolean siguiente=false;
            Scanner teclado = new Scanner(System.in);
            while(!siguiente){
                int opcion;
                System.out.println("¿Desea pagar en efectivo o con TARCINE?");
                System.out.println("1. Efectivo     2. TARCINE");
                opcion=teclado.nextInt();
                if(opcion==1){
                    if(aux<8){
                        ventaTotal=ventaTotal+8000;
                    }else{
                        ventaTotal=ventaTotal+11000;
                    }
                    siguiente=true;
                }else if(opcion==2){
                    if(aux<8){
                        ventaTotal=ventaTotal+7200;
                    }else{
                        ventaTotal=ventaTotal+9900;
                    }
                    System.out.println("Obtuviste un descuento del 10%!!!");
                    siguiente=true;
                }else{
                    System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                    System.out.println(":No has seleccionado una opción correcta!:");
                    System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                }
            }
            
            return true;
        }else if(asientos[aux][(columna-1)]==2){
            System.out.println(":::::::::::::::::::::::::::::::::::::");
            System.out.println(":El asiento se encuentra en reserva!:");
            System.out.println("::::  Por favor seleccione otro  ::::");
            System.out.println(":::::::::::::::::::::::::::::::::::::");
            return false;
        }else if(asientos[aux][(columna-1)]==3){
            System.out.println("::::::::::::::::::::::::::::::::::");
            System.out.println(":El asiento se encuentra vendido!:");
            System.out.println("::  Por favor seleccione otro.  ::");
            System.out.println("::::::::::::::::::::::::::::::::::");
            return false;
        }        
        return false;
    }//cambia el estado de un asiento a vendido y registra la venta;
    static boolean filaCorrecta(char fila){
        if((int)fila>64 && (int)fila<76){
            return true;
        }else{
            return false;
        }
    }//compare si la fila ingresada es un valor correcto y retorne true o false
    static boolean columnaCorrecta(int columna){
        if(columna>0 && columna<21){
            return true;
        }else{
            return false;
        }
    }//compare si la columna ingresada es un valor correcto y retorne true o false
    static char selFila(){
        char opcion;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Seleccione una fila (A-K en Mayúscula):");
        opcion=teclado.next().charAt(0); 
        return opcion;
    }//ingresamos un valor fila en el teclado y lo retornamos;
    static int selColumna(){
        int opcion;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Seleccione una columna (1-20)");
        opcion=teclado.nextInt();
        return opcion;
    }//ingresamos un valor columna en el teclado y lo retornamos;
}
