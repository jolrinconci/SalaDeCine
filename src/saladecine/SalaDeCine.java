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
 * @author José Luis Rincón Ciriano
 * @version 1.0
 * since 28/08/2016
 */
import java.util.Scanner;//importamos Scanner para usarlo en la captura del teclado.
public class SalaDeCine {
    //variables globales.
    static int[][] asientos = new int[11][20];//En esta matriz se guardan la disponibilidad de los asientos. 1 diponible, 2 en reserva, y 3 vendido.
    static int ventaTotal=0;//total de las ventas. Inclute la venta y recarga de tarjetas.
    static int[][] reservaFila = new int[10][50];//limitado a 10 reservas por tarcine.
    static int[][] reservaColumna = new int[10][50];//limitado a 10 reservas por tarcine.
    static int[][] tarcine = new int[3][50];//información de las tarjetas tarcine. Actualmente hasta 50 tarjetas.
    //en las filas del 0 al 2 tenemos:(inicialmente todos los valores son iguales a cero)
    //0:documento ó número de tarjeta.
    //1:saldo.
    //2:tiene reservas?.(1 no, 2 si)
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
        valorInicialTarcine();
        valorInicialReservas();
        
        while(!salida){//ciclo principal del programa.
            int opcion = menuPrincipal();
            if(opcion==1){//1.Vender boletas
                venderBoletas();
                System.out.println("Gracias por comprar!");
            }else if(opcion==2){//2.Reservar boletas
                System.out.println("Reserva con éxito!");
            }else if(opcion==3){//3.Crear TARCINE
                crearTarcine();
                System.out.println("Se ha creado su tarjeta!");
            }else if(opcion==4){//4.Recargar TARCINE
                recargarTarcine();
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
                valorInicialTarcine();//Se eliminan los datos existentes de tarcine.
                valorInicialReservas();//Se eliminan las reservas de tarcine.
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
    }//estado de los asientos, inicialmente todos disponibles(1).
    static void valorInicialTarcine(){
        for(int i=0; i<3;i++){
            for(int j=0; j<50; j++){
                tarcine[i][j]=0;//0 vacio.
            }
        }
    }//las tarjetas tarcine tiene una valor de cero;
    static void valorInicialReservas(){
        for(int i=0; i<10;i++){
            for(int j=0; j<50; j++){
                reservaFila[i][j]=0;//0 vacio.
                reservaColumna[i][j]=0;//0 vacio.
            }
        }
    }//no hay reservas en las tarjetas.
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
    static void venderBoletas(){
        boolean siguiente = false;
        boolean compra = false;
        int columna=0;
        char fila=0;
        while(!compra){//termina con una compra exitosa.
            while(!siguiente){//termina si se ingresan valores correctos.
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
            compra=asientoVendido(columna,fila);
        }
    }//vende una boleta ó asiento disponible.
    static void crearTarcine(){
        Scanner teclado = new Scanner(System.in);
        boolean siguiente = false;//sale o continua el ciclo
        int captura;//captura con el teclado.
            while(!siguiente){//ingresa un número correcto de tarjeta.
                System.out.println("Ingrese un número de cédula: ");
                captura=teclado.nextInt();
                boolean correcto=false;
                int posicion=50;
                for(int i=0; i<50; i++){
                    if(captura == tarcine[0][i]){
                        System.out.println("::::::::::::::::::::::");
                        System.out.println(":El número ya existe!:");
                        System.out.println("::::::::::::::::::::::");
                        correcto = false;
                        break;
                    }else{
                        correcto = true;
                    }
                }
                for(int j=0; j<50; j++){
                    if(tarcine[0][j]==0){
                        posicion=j;
                        break;
                    }
                }
                if(correcto==true){
                    tarcine[0][posicion]=captura;
                    tarcine[1][posicion]=70000;
                    ventaTotal=ventaTotal+70000;
                    siguiente=true;
                }
            }
    }//crea una tarjeta tarcine con saldo inicial y número de cédula.
    static void recargarTarcine(){
        Scanner teclado = new Scanner(System.in);
        boolean siguiente = false;
        int captura;//captura con el teclado.
            System.out.println("Ingrese un número de tarjeta TARCINE: ");
            captura=teclado.nextInt();
        for(int i=0; i<50; i++){
            if(captura == tarcine[0][i]){
                System.out.println("::::::::::::::::::::");
                System.out.println(":Número encontrado!:");
                System.out.println(": Se ha recargado: :");
                System.out.println("::::  $ 50.000  ::::");
                System.out.println("::::::::::::::::::::");
                tarcine[1][i]+=50000;
                ventaTotal+=50000;
                siguiente=true;
                break;
            }
        }
        if(siguiente==false){
            System.out.println("::::::::::::::::::::::");
            System.out.println(":El número no existe!:");
            System.out.println("::::::::::::::::::::::");
        }
    }//recarga 50000 a una tarjeta TARCINE.
}
